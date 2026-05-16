package com.nfctool.core.model

import kotlinx.serialization.Serializable

@Serializable
data class NfcTag(
    val id: String,
    val uid: String = "",
    val technology: NfcTechnology = NfcTechnology.UNKNOWN,
    val supportedTechnologies: List<NfcTechnology> = emptyList(),
    val isWritable: Boolean = false,
    val maxSize: Int = 0,
    val usedSize: Int = 0,
    val records: List<NdefRecord> = emptyList(),
    val rawData: ByteArray = byteArrayOf(),
    val scannedAt: Long = 0L,
) {
    val isEmpty: Boolean
        get() = records.isEmpty()

    val textRecords: List<NdefRecord>
        get() = records.filter { it.isText }

    val uriRecords: List<NdefRecord>
        get() = records.filter { it.isUri }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as NfcTag
        return id == other.id &&
            uid == other.uid &&
            technology == other.technology &&
            supportedTechnologies == other.supportedTechnologies &&
            isWritable == other.isWritable &&
            maxSize == other.maxSize &&
            usedSize == other.usedSize &&
            records == other.records &&
            rawData.contentEquals(other.rawData)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + uid.hashCode()
        result = 31 * result + technology.hashCode()
        result = 31 * result + supportedTechnologies.hashCode()
        result = 31 * result + isWritable.hashCode()
        result = 31 * result + maxSize
        result = 31 * result + usedSize
        result = 31 * result + records.hashCode()
        result = 31 * result + rawData.contentHashCode()
        return result
    }
}