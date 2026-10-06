package com.saathi.aiassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saathi.aiassistant.data.ChatMessage
import com.saathi.aiassistant.data.ChatRole
import com.saathi.aiassistant.ui.theme.SaathiPalette

@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    draft: String,
    onDraftChange: (String) -> Unit,
    onSend: () -> Unit,
) {
    var showInfo by remember { mutableStateOf(false) }
    var showVoiceNotice by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) listState.animateScrollToItem(messages.lastIndex)
    }

    if (showInfo) {
        AlertDialog(
            onDismissRequest = { showInfo = false },
            title = { Text("Preview chat") },
            text = { Text("Messages are kept in this app session only. Replies are local examples; nothing is sent to an AI provider.") },
            confirmButton = { TextButton(onClick = { showInfo = false }) { Text("Theek hai") } },
            containerColor = SaathiPalette.Surface,
            titleContentColor = SaathiPalette.Text,
            textContentColor = SaathiPalette.Muted,
        )
    }
    if (showVoiceNotice) {
        AlertDialog(
            onDismissRequest = { showVoiceNotice = false },
            title = { Text("Voice not connected") },
            text = { Text("Microphone access and recording are disabled in this preview.") },
            confirmButton = { TextButton(onClick = { showVoiceNotice = false }) { Text("OK") } },
            containerColor = SaathiPalette.Surface,
            titleContentColor = SaathiPalette.Text,
            textContentColor = SaathiPalette.Muted,
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().imePadding(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(11.dp),
        ) {
            Surface(shape = RoundedCornerShape(13.dp), color = SaathiPalette.Red) {
                Icon(Icons.Outlined.AutoAwesome, contentDescription = null, tint = SaathiPalette.Text, modifier = Modifier.padding(10.dp).size(18.dp))
            }
            Column(Modifier.weight(1f)) {
                Text("Saathi Chat", color = SaathiPalette.Text, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text("Local demo · AI not connected", color = SaathiPalette.Faint, fontSize = 11.sp, modifier = Modifier.padding(top = 2.dp))
            }
            IconButton(onClick = { showInfo = true }) {
                Icon(Icons.Outlined.Info, contentDescription = "Chat information", tint = SaathiPalette.Muted)
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 17.dp),
            shape = RoundedCornerShape(13.dp),
            color = Color(0xFF2B2518),
        ) {
            Row(Modifier.padding(11.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("ⓘ", color = SaathiPalette.Amber, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text("Demo replies only — no request goes to an AI provider.", color = Color(0xFFD9C28E), fontSize = 10.sp)
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f).fillMaxWidth().padding(top = 8.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 13.dp),
            verticalArrangement = Arrangement.spacedBy(13.dp),
        ) {
            itemsIndexed(messages, key = { index, message -> "$index-${message.role.name}" }) { _, message ->
                val isUser = message.role == ChatRole.USER
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    if (!isUser) {
                        Surface(shape = RoundedCornerShape(9.dp), color = SaathiPalette.SurfaceRaised, modifier = Modifier.padding(end = 7.dp)) {
                            Icon(Icons.Outlined.AutoAwesome, contentDescription = null, tint = SaathiPalette.Red, modifier = Modifier.padding(6.dp).size(13.dp))
                        }
                    }
                    Surface(
                        modifier = Modifier.fillMaxWidth(0.88f),
                        shape = RoundedCornerShape(
                            topStart = 18.dp,
                            topEnd = 18.dp,
                            bottomEnd = if (isUser) 5.dp else 18.dp,
                            bottomStart = if (isUser) 18.dp else 5.dp,
                        ),
                        color = if (isUser) SaathiPalette.Red else SaathiPalette.SurfaceRaised,
                    ) {
                        Text(message.text, color = SaathiPalette.Text, fontSize = 13.sp, lineHeight = 19.sp, modifier = Modifier.padding(horizontal = 14.dp, vertical = 11.dp))
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf("Timer" to "Mujhe timer set karna hai", "Directions" to "Mujhe directions chahiye", "Search" to "Web par search karo").forEach { (label, prompt) ->
                Surface(
                    modifier = Modifier.clickable(role = Role.Button) { onDraftChange(prompt) },
                    shape = CircleShape,
                    color = SaathiPalette.Surface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaathiPalette.Border),
                ) {
                    Text(label, color = SaathiPalette.Muted, fontSize = 11.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 13.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            IconButton(onClick = { showVoiceNotice = true }) {
                Icon(Icons.Outlined.Mic, contentDescription = "Voice input unavailable", tint = SaathiPalette.Faint)
            }
            OutlinedTextField(
                value = draft,
                onValueChange = onDraftChange,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Message Saathi…", color = SaathiPalette.Faint) },
                maxLines = 3,
                shape = RoundedCornerShape(17.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { onSend() }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = SaathiPalette.Text,
                    unfocusedTextColor = SaathiPalette.Text,
                    focusedContainerColor = SaathiPalette.Surface,
                    unfocusedContainerColor = SaathiPalette.Surface,
                    focusedBorderColor = SaathiPalette.Red,
                    unfocusedBorderColor = SaathiPalette.Border,
                    cursorColor = SaathiPalette.Red,
                ),
            )
            IconButton(onClick = onSend, enabled = draft.isNotBlank()) {
                Surface(shape = RoundedCornerShape(13.dp), color = if (draft.isNotBlank()) SaathiPalette.Red else SaathiPalette.SurfaceRaised) {
                    Icon(Icons.AutoMirrored.Outlined.Send, contentDescription = "Send message", tint = SaathiPalette.Text, modifier = Modifier.padding(10.dp).size(18.dp))
                }
            }
        }
    }
}
