package com.nfctool.core.platform

import com.nfctool.core.model.NdefRecord

actual class PlatformNfcAdapter actual constructor() : NfcAdapter {

    actual override fun checkAvailability(): NfcAvailability = NfcAvailability.Unavailable

    actual override fun enableNfc(callback: (Boolean) -> Unit) {
        callback(false)
    }

    actual override fun startScanning(onResult: (NfcScanResult) -> Unit) {
        onResult(NfcScanResult.Error("NFC is not supported on this platform"))
    }

    actual override fun stopScanning() {}

    actual override fun writeRecords(
        records: List<NdefRecord>,
        onResult: (NfcWriteResult) -> Unit,
    ) {
        onResult(NfcWriteResult.Error("NFC is not supported on this platform"))
    }

    actual override fun eraseTag(onResult: (NfcWriteResult) -> Unit) {
        onResult(NfcWriteResult.Error("NFC is not supported on this platform"))
    }
}