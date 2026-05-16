package com.nfctool.app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nfctool.core.model.NdefRecord
import com.nfctool.core.model.NfcTag

enum class WriteRecordType {
    TEXT, URI
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteScreen(
    tag: NfcTag?,
    onBack: () -> Unit,
    onWriteComplete: (String) -> Unit,
) {
    var recordType by remember { mutableStateOf(WriteRecordType.TEXT) }
    var textContent by remember { mutableStateOf("") }
    var uriContent by remember { mutableStateOf("https://") }
    var languageCode by remember { mutableStateOf("en") }
    var isWriting by remember { mutableStateOf(false) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Write to Tag") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Text("< Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Write data to NFC tag",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }

            if (tag != null) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Target Tag",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "UID: ${tag.uid}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = "Type: ${tag.technology.name}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Record Type",
                    style = MaterialTheme.typography.labelMedium,
                )

                ExposedDropdownMenuBox(
                    expanded = dropdownExpanded,
                    onExpandedChange = { dropdownExpanded = it },
                ) {
                    OutlinedTextField(
                        value = recordType.name,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth(),
                    )
                    ExposedDropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false },
                    ) {
                        DropdownMenuItem(
                            text = { Text("Text Record") },
                            onClick = {
                                recordType = WriteRecordType.TEXT
                                dropdownExpanded = false
                            },
                        )
                        DropdownMenuItem(
                            text = { Text("URI Record") },
                            onClick = {
                                recordType = WriteRecordType.URI
                                dropdownExpanded = false
                            },
                        )
                    }
                }
            }

            when (recordType) {
                WriteRecordType.TEXT -> {
                    item {
                        OutlinedTextField(
                            value = languageCode,
                            onValueChange = { languageCode = it },
                            label = { Text("Language Code") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                        )
                    }
                    item {
                        OutlinedTextField(
                            value = textContent,
                            onValueChange = { textContent = it },
                            label = { Text("Text Content") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                        )
                    }
                }

                WriteRecordType.URI -> {
                    item {
                        OutlinedTextField(
                            value = uriContent,
                            onValueChange = { uriContent = it },
                            label = { Text("URI") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        isWriting = true
                        val records = when (recordType) {
                            WriteRecordType.TEXT -> listOf(
                                NdefRecord.createTextRecord(textContent, languageCode),
                            )
                            WriteRecordType.URI -> listOf(
                                NdefRecord.createUriRecord(uriContent),
                            )
                        }
                        isWriting = false
                        onWriteComplete("Ready to write ${records.size} record(s)")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = when (recordType) {
                        WriteRecordType.TEXT -> textContent.isNotBlank()
                        WriteRecordType.URI -> uriContent.isNotBlank()
                    } && !isWriting,
                ) {
                    Text(
                        if (isWriting) "Writing..." else "Write to Tag",
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Preview",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        when (recordType) {
                            WriteRecordType.TEXT -> {
                                Text(
                                    text = "Type: Text Record",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                Text(
                                    text = "Language: $languageCode",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                Text(
                                    text = "Content: $textContent",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                            WriteRecordType.URI -> {
                                Text(
                                    text = "Type: URI Record",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                Text(
                                    text = "URI: $uriContent",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}