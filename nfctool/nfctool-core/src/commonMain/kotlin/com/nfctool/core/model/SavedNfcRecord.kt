package com.nfctool.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SavedNfcRecord(
    val id: String,
    val name: String,
    val tag: NfcTag,
    val savedAt: Long,
    val notes: String = "",
    val category: String = "",
)