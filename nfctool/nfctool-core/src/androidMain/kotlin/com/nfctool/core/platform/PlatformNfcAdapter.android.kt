package com.nfctool.core.platform

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter as AndroidNfcAdapter
import android.nfc.NdefMessage
import android.nfc.NdefRecord as AndroidNdefRecord
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NfcA
import android.nfc.tech.NfcB
import android.nfc.tech.NfcF
import android.nfc.tech.NfcV
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareClassic
import android.nfc.tech.MifareUltralight
import android.nfc.tech.NdefFormatable
import android.os.Bundle
import android.provider.Settings
import com.nfctool.core.model.NdefRecord
import com.nfctool.core.model.NdefTnf
import com.nfctool.core.model.NfcTag
import com.nfctool.core.model.NfcTechnology

actual class PlatformNfcAdapter actual constructor() : NfcAdapter {

    private var androidAdapter: AndroidNfcAdapter? = null
    private var onScanCallback: ((NfcScanResult) -> Unit)? = null
    private var pendingWriteRecords: List<NdefRecord>? = null
    private var pendingWriteCallback: ((NfcWriteResult) -> Unit)? = null
    private var pendingEraseCallback: ((NfcWriteResult) -> Unit)? = null

    fun attach(context: Context) {
        androidAdapter = AndroidNfcAdapter.getDefaultAdapter(context)
    }

    actual override fun checkAvailability(): NfcAvailability {
        val adapter = androidAdapter ?: return NfcAvailability.Unavailable
        return when {
            adapter.isEnabled -> NfcAvailability.Available
            else -> NfcAvailability.Disabled
        }
    }

    actual override fun enableNfc(callback: (Boolean) -> Unit) {
        callback(checkAvailability() == NfcAvailability.Available)
    }

    actual override fun startScanning(onResult: (NfcScanResult) -> Unit) {
        onScanCallback = onResult
        onResult(NfcScanResult.ScanStarted)
    }

    actual override fun stopScanning() {
        onScanCallback?.invoke(NfcScanResult.ScanStopped)
        onScanCallback = null
    }

    actual override fun writeRecords(
        records: List<NdefRecord>,
        onResult: (NfcWriteResult) -> Unit,
    ) {
        pendingWriteRecords = records
        pendingWriteCallback = onResult
    }

    actual override fun eraseTag(onResult: (NfcWriteResult) -> Unit) {
        pendingEraseCallback = onResult
        pendingWriteRecords = emptyList()
    }

    fun handleNewIntent(intent: Intent?) {
        val tag = intent?.getParcelableExtra<Tag>(AndroidNfcAdapter.EXTRA_TAG) ?: return
        val nfcTag = parseTag(tag)

        pendingWriteRecords?.let { records ->
            performWrite(tag, records)
            return
        }

        pendingEraseCallback?.let { callback ->
            performErase(tag)
            return
        }

        onScanCallback?.invoke(NfcScanResult.TagDiscovered(nfcTag))
    }

    private fun parseTag(androidTag: Tag): NfcTag {
        val techList = androidTag.techList?.toList() ?: emptyList()
        val supportedTechnologies = techList.mapNotNull { tech ->
            when {
                tech == Ndef::class.java.name -> NfcTechnology.NDEF
                tech == MifareClassic::class.java.name -> NfcTechnology.MIFARE_CLASSIC
                tech == MifareUltralight::class.java.name -> NfcTechnology.MIFARE_ULTRA_LIGHT
                tech == NfcA::class.java.name -> NfcTechnology.NFC_A
                tech == NfcB::class.java.name -> NfcTechnology.NFC_B
                tech == NfcF::class.java.name -> NfcTechnology.NFC_F
                tech == NfcV::class.java.name -> NfcTechnology.NFC_V
                tech == IsoDep::class.java.name -> NfcTechnology.ISO_DEP
                tech == NdefFormatable::class.java.name -> NfcTechnology.NDEF_FORMATABLE
                else -> null
            }
        }

        val primaryTechnology = when {
            supportedTechnologies.contains(NfcTechnology.NDEF) -> NfcTechnology.NDEF
            supportedTechnologies.contains(NfcTechnology.MIFARE_CLASSIC) -> NfcTechnology.MIFARE_CLASSIC
            supportedTechnologies.contains(NfcTechnology.MIFARE_ULTRA_LIGHT) -> NfcTechnology.MIFARE_ULTRA_LIGHT
            supportedTechnologies.contains(NfcTechnology.ISO_DEP) -> NfcTechnology.ISO_DEP
            else -> supportedTechnologies.firstOrNull() ?: NfcTechnology.UNKNOWN
        }

        val uid = bytesToHex(androidTag.id)
        val records = readNdefRecords(androidTag)

        return NfcTag(
            id = uid,
            uid = uid,
            technology = primaryTechnology,
            supportedTechnologies = supportedTechnologies,
            isWritable = supportedTechnologies.contains(NfcTechnology.NDEF) ||
                supportedTechnologies.contains(NfcTechnology.NDEF_FORMATABLE),
            maxSize = 0,
            usedSize = 0,
            records = records,
            scannedAt = currentTimeMillis(),
        )
    }

    private fun readNdefRecords(androidTag: Tag): List<NdefRecord> {
        return try {
            val ndef = Ndef.get(androidTag) ?: return emptyList()
            ndef.connect()
            try {
                val ndefMessage = ndef.ndefMessage ?: ndef.cachedNdefMessage ?: return emptyList()
                ndefMessage.records.map { convertRecord(it) }
            } finally {
                ndef.close()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun convertRecord(androidRecord: AndroidNdefRecord): NdefRecord {
        return NdefRecord(
            tnf = NdefTnf.fromValue(androidRecord.tnf),
            type = androidRecord.type ?: byteArrayOf(),
            id = androidRecord.id ?: byteArrayOf(),
            payload = androidRecord.payload ?: byteArrayOf(),
        )
    }

    private fun performWrite(androidTag: Tag, records: List<NdefRecord>) {
        try {
            val ndef = Ndef.get(androidTag)
            if (ndef != null) {
                ndef.connect()
                try {
                    val ndefRecords = records.map { convertToAndroidRecord(it) }.toTypedArray()
                    val message = NdefMessage(ndefRecords)
                    ndef.writeNdefMessage(message)
                    pendingWriteCallback?.invoke(NfcWriteResult.Success)
                } finally {
                    ndef.close()
                }
            } else {
                pendingWriteCallback?.invoke(
                    NfcWriteResult.Error("Tag does not support NDEF")
                )
            }
        } catch (e: Exception) {
            pendingWriteCallback?.invoke(NfcWriteResult.Error(e.message ?: "Write failed"))
        }
        pendingWriteRecords = null
        pendingWriteCallback = null
    }

    private fun performErase(androidTag: Tag) {
        try {
            val ndef = Ndef.get(androidTag)
            if (ndef != null) {
                ndef.connect()
                try {
                    val message = NdefMessage(arrayOf(AndroidNdefRecord(android.nfc.NdefRecord.TNF_EMPTY, null, null, null)))
                    ndef.writeNdefMessage(message)
                    pendingEraseCallback?.invoke(NfcWriteResult.Success)
                } finally {
                    ndef.close()
                }
            } else {
                pendingEraseCallback?.invoke(
                    NfcWriteResult.Error("Tag does not support NDEF")
                )
            }
        } catch (e: Exception) {
            pendingEraseCallback?.invoke(NfcWriteResult.Error(e.message ?: "Erase failed"))
        }
        pendingEraseCallback = null
    }

    private fun convertToAndroidRecord(record: NdefRecord): AndroidNdefRecord {
        return AndroidNdefRecord(
            record.tnf.value,
            record.type,
            record.id,
            record.payload,
        )
    }

    private fun bytesToHex(bytes: ByteArray): String {
        return bytes.joinToString("") { "%02X".format(it) }
    }
}