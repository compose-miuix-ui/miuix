package com.nfctool.core

import com.nfctool.core.model.NdefRecord
import com.nfctool.core.model.NfcTag
import com.nfctool.core.platform.NfcAdapter
import com.nfctool.core.platform.NfcAvailability
import com.nfctool.core.platform.NfcScanResult
import com.nfctool.core.platform.NfcWriteResult
import com.nfctool.core.platform.PlatformNfcAdapter

class NfcManager {
    private val adapter: NfcAdapter = PlatformNfcAdapter()

    fun isAvailable(): Boolean =
        adapter.checkAvailability() == NfcAvailability.Available

    fun isEnabled(): Boolean {
        val availability = adapter.checkAvailability()
        return availability == NfcAvailability.Available
    }

    fun checkAvailability(): NfcAvailability = adapter.checkAvailability()

    fun enableNfc(callback: (Boolean) -> Unit) = adapter.enableNfc(callback)

    fun startScanning(onTagDiscovered: (NfcTag) -> Unit, onError: (String) -> Unit) {
        adapter.startScanning { result ->
            when (result) {
                is NfcScanResult.TagDiscovered -> onTagDiscovered(result.tag)
                is NfcScanResult.Error -> onError(result.message)
                is NfcScanResult.ScanStarted -> {}
                is NfcScanResult.ScanStopped -> {}
            }
        }
    }

    fun stopScanning() = adapter.stopScanning()

    fun writeRecords(
        records: List<NdefRecord>,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        adapter.writeRecords(records) { result ->
            when (result) {
                is NfcWriteResult.Success -> onSuccess()
                is NfcWriteResult.Error -> onError(result.message)
                is NfcWriteResult.TagLost -> onError("Tag lost during write")
            }
        }
    }

    fun eraseTag(
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        adapter.eraseTag { result ->
            when (result) {
                is NfcWriteResult.Success -> onSuccess()
                is NfcWriteResult.Error -> onError(result.message)
                is NfcWriteResult.TagLost -> onError("Tag lost during erase")
            }
        }
    }
}