package com.nfctool.core.model

import kotlinx.serialization.Serializable

@Serializable
data class NdefRecord(
    val tnf: NdefTnf,
    val type: ByteArray,
    val id: ByteArray = byteArrayOf(),
    val payload: ByteArray = byteArrayOf(),
) {
    val payloadAsString: String
        get() = payload.decodeToString()

    val isText: Boolean
        get() = tnf == NdefTnf.WELL_KNOWN && type.contentEquals(RTD_TEXT)

    val isUri: Boolean
        get() = tnf == NdefTnf.WELL_KNOWN && type.contentEquals(RTD_URI)

    val isSmartPoster: Boolean
        get() = tnf == NdefTnf.WELL_KNOWN && type.contentEquals(RTD_SMART_POSTER)

    val wellKnownType: NdefWellKnownType
        get() = when {
            isText -> NdefWellKnownType.TEXT
            isUri -> NdefWellKnownType.URI
            isSmartPoster -> NdefWellKnownType.SMART_POSTER
            else -> NdefWellKnownType.UNKNOWN
        }

    val textValue: String
        get() {
            if (!isText || payload.size < 2) return ""
            val status = payload[0].toInt() and 0xFF
            val languageCodeLength = status and 0x3F
            val start = 1 + languageCodeLength
            val encoding = if ((status and 0x80) != 0) "UTF-16" else "UTF-8"
            return if (start < payload.size) {
                payload.copyOfRange(start, payload.size).decodeToString()
            } else {
                ""
            }
        }

    val uriValue: String
        get() {
            if (!isUri || payload.isEmpty()) return ""
            val prefixIndex = payload[0].toInt() and 0xFF
            val prefix = URI_PREFIXES.getOrElse(prefixIndex) { "" }
            val suffix = if (payload.size > 1) {
                payload.copyOfRange(1, payload.size).decodeToString()
            } else {
                ""
            }
            return prefix + suffix
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as NdefRecord
        return tnf == other.tnf &&
            type.contentEquals(other.type) &&
            id.contentEquals(other.id) &&
            payload.contentEquals(other.payload)
    }

    override fun hashCode(): Int {
        var result = tnf.hashCode()
        result = 31 * result + type.contentHashCode()
        result = 31 * result + id.contentHashCode()
        result = 31 * result + payload.contentHashCode()
        return result
    }

    companion object {
        val RTD_TEXT = byteArrayOf('T'.code.toByte())
        val RTD_URI = byteArrayOf('U'.code.toByte())
        val RTD_SMART_POSTER = byteArrayOf('S'.code.toByte(), 'p'.code.toByte())

        val URI_PREFIXES = arrayOf(
            "",
            "http://www.",
            "https://www.",
            "http://",
            "https://",
            "tel:",
            "mailto:",
            "ftp://anonymous:anonymous@",
            "ftp://ftp.",
            "ftps://",
            "sftp://",
            "smb://",
            "nfs://",
            "ftp://",
            "dav://",
            "news:",
            "telnet://",
            "imap:",
            "rtsp://",
            "urn:",
            "pop:",
            "sip:",
            "sips:",
            "tftp:",
            "btspp://",
            "btl2cap://",
            "btgoep://",
            "tcpobex://",
            "irdaobex://",
            "file://",
            "urn:epc:id:",
            "urn:epc:tag:",
            "urn:epc:pat:",
            "urn:epc:raw:",
            "urn:epc:",
            "urn:nfc:",
        )

        fun createTextRecord(text: String, languageCode: String = "en"): NdefRecord {
            val languageBytes = languageCode.encodeToByteArray()
            val textBytes = text.encodeToByteArray()
            val status = languageBytes.size.toByte()
            val payload = ByteArray(1 + languageBytes.size + textBytes.size)
            payload[0] = status
            languageBytes.copyInto(payload, 1)
            textBytes.copyInto(payload, 1 + languageBytes.size)
            return NdefRecord(
                tnf = NdefTnf.WELL_KNOWN,
                type = RTD_TEXT,
                payload = payload,
            )
        }

        fun createUriRecord(uri: String): NdefRecord {
            var bestPrefix = 0
            for (i in URI_PREFIXES.indices) {
                if (URI_PREFIXES[i].isNotEmpty() && uri.startsWith(URI_PREFIXES[i]) &&
                    URI_PREFIXES[i].length > URI_PREFIXES[bestPrefix].length
                ) {
                    bestPrefix = i
                }
            }
            val suffix = uri.removePrefix(URI_PREFIXES[bestPrefix]).encodeToByteArray()
            val payload = ByteArray(1 + suffix.size)
            payload[0] = bestPrefix.toByte()
            suffix.copyInto(payload, 1)
            return NdefRecord(
                tnf = NdefTnf.WELL_KNOWN,
                type = RTD_URI,
                payload = payload,
            )
        }
    }
}