package com.nfctool.app.ui.state

import androidx.compose.runtime.compositionLocalOf
import com.nfctool.core.NfcManager
import com.nfctool.core.model.NfcTag
import com.nfctool.core.model.SavedNfcRecord
import com.nfctool.core.platform.NfcAvailability

data class AppState(
    val nfcAvailability: NfcAvailability = NfcAvailability.Unavailable,
    val isScanning: Boolean = false,
    val currentTag: NfcTag? = null,
    val savedRecords: List<SavedNfcRecord> = emptyList(),
    val isWriting: Boolean = false,
    val writeError: String? = null,
    val snackbarMessage: String? = null,
)

val LocalNfcManager = compositionLocalOf { NfcManager() }