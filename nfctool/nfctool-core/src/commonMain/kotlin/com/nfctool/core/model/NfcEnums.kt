package com.nfctool.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class NfcTechnology {
    NDEF,
    MIFARE_CLASSIC,
    MIFARE_ULTRA_LIGHT,
    NFC_A,
    NFC_B,
    NFC_F,
    NFC_V,
    ISO_DEP,
    NDEF_FORMATABLE,
    UNKNOWN
}

@Serializable
enum class NdefTnf(val value: Short) {
    EMPTY(0x00),
    WELL_KNOWN(0x01),
    MIME_MEDIA(0x02),
    ABSOLUTE_URI(0x03),
    EXTERNAL_TYPE(0x04),
    UNKNOWN(0x05),
    UNCHANGED(0x06);

    companion object {
        fun fromValue(value: Short): NdefTnf =
            entries.firstOrNull { it.value == value } ?: UNKNOWN
    }
}

@Serializable
enum class NdefWellKnownType {
    TEXT,
    URI,
    SMART_POSTER,
    UNKNOWN
}