package com.nfctool.app.android

import android.content.Intent
import android.nfc.NfcAdapter as AndroidNfcAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nfctool.app.ui.NfcToolApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NfcToolApp()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        val adapter = AndroidNfcAdapter.getDefaultAdapter(this)
        if (adapter != null) {
            val intent = Intent(this, javaClass).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
            val pendingIntent = android.app.PendingIntent.getActivity(
                this, 0, intent,
                android.app.PendingIntent.FLAG_MUTABLE or android.app.PendingIntent.FLAG_UPDATE_CURRENT,
            )
            adapter.enableForegroundDispatch(
                this, pendingIntent, null, null,
            )
        }
    }

    override fun onPause() {
        super.onPause()
        val adapter = AndroidNfcAdapter.getDefaultAdapter(this)
        adapter?.disableForegroundDispatch(this)
    }
}