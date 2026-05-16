package com.nfctool.core.platform

import com.nfctool.core.model.NdefRecord
import com.nfctool.core.model.NfcTag

sealed class NfcScanResult {
    data class TagDiscovered(val tag: NfcTag) : NfcScanResult()
    data class Error(val message: String) : NfcScanResult()
    data object ScanStarted : NfcScanResult()
    data object ScanStopped : NfcScanResult()
}

sealed class NfcWriteResult {
    data object Success : NfcWriteResult()
    data class Error(val message: String) : NfcWriteResult()
    data object TagLost : NfcWriteResult()
}

sealed class NfcAvailability {
    data object Available : NfcAvailability()
    data object Unavailable : NfcAvailability()
    data object Disabled : NfcAvailability()
    data class Error(val message: String) : NfcAvailability()
}

interface NfcAdapter {
    fun checkAvailability(): NfcAvailability
    fun enableNfc(callback: (Boolean) -> Unit)
    fun startScanning(onResult: (NfcScanResult) -> Unit)
    fun stopScanning()
    fun writeRecords(records: List<NdefRecord>, onResult: (NfcWriteResult) -> Unit)
    fun eraseTag(onResult: (NfcWriteResult) -> Unit)
}

expect class PlatformNfcAdapter() : NfcAdapter