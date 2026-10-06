package com.saathi.aiassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saathi.aiassistant.ui.theme.SaathiPalette

private data class QuickPrompt(
    val title: String,
    val prompt: String,
    val icon: ImageVector,
    val tint: Color,
)

@Composable
fun HomeScreen(
    onOpenChat: () -> Unit,
    onPrompt: (String) -> Unit,
) {
    var showVoiceNotice by remember { mutableStateOf(false) }
    val prompts = listOf(
        QuickPrompt("Timer set karo", "Mujhe 10 minute ka timer lagana hai", Icons.Outlined.Timer, SaathiPalette.Amber),
        QuickPrompt("Web par search", "Web par koi topic search karo", Icons.Outlined.Search, SaathiPalette.Purple),
        QuickPrompt("Directions", "Mujhe ek jagah ke directions chahiye", Icons.Outlined.Navigation, SaathiPalette.Green),
        QuickPrompt("Summarize", "Is text ka short summary banao", Icons.Outlined.Description, SaathiPalette.Red),
    )

    if (showVoiceNotice) {
        AlertDialog(
            onDismissRequest = { showVoiceNotice = false },
            title = { Text("Voice abhi connect nahi hai") },
            text = { Text("Is Compose preview mein microphone permission ya recording active nahi hai.") },
            confirmButton = {
                TextButton(onClick = { showVoiceNotice = false }) { Text("Theek hai") }
            },
            containerColor = SaathiPalette.Surface,
            titleContentColor = SaathiPalette.Text,
            textContentColor = SaathiPalette.Muted,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 14.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(11.dp))
                        .background(SaathiPalette.Red),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Outlined.AutoAwesome, contentDescription = null, tint = SaathiPalette.Text, modifier = Modifier.size(18.dp))
                }
                Text("SAATHI", color = SaathiPalette.Text, fontSize = 18.sp, fontWeight = FontWeight.Black, letterSpacing = 2.sp)
                Text(".", color = SaathiPalette.Red, fontSize = 18.sp, fontWeight = FontWeight.Black)
            }
            Surface(color = SaathiPalette.SurfaceRaised, shape = CircleShape) {
                Row(
                    modifier = Modifier.padding(horizontal = 11.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Box(Modifier.size(6.dp).clip(CircleShape).background(SaathiPalette.Amber))
                    Text("PREVIEW", color = SaathiPalette.Muted, fontSize = 9.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
                }
            }
        }

        Spacer(Modifier.height(30.dp))
        Text("YOUR PERSONAL AI COMPANION", color = SaathiPalette.Red, fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.5.sp)
        Spacer(Modifier.height(9.dp))
        Text("Namaste,", color = SaathiPalette.Text, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = (-1).sp)
        Text("Aaj kis cheez mein madad karun?", color = SaathiPalette.Muted, fontSize = 15.sp, modifier = Modifier.padding(top = 5.dp))

        Box(
            modifier = Modifier.fillMaxWidth().height(238.dp),
            contentAlignment = Alignment.Center,
        ) {
            Box(Modifier.size(204.dp).clip(CircleShape).background(Color(0x2231171D)).border(1.dp, Color(0xFF54232B), CircleShape))
            Box(Modifier.size(154.dp).clip(CircleShape).background(Color(0x22431B22)).border(1.dp, Color(0xFF81313B), CircleShape))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(106.dp)
                        .shadow(22.dp, CircleShape, ambientColor = SaathiPalette.Red, spotColor = SaathiPalette.Red)
                        .clip(CircleShape)
                        .background(SaathiPalette.Red)
                        .border(7.dp, SaathiPalette.RedDeep, CircleShape)
                        .clickable(role = Role.Button) { showVoiceNotice = true },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Outlined.Mic, contentDescription = "Voice assistant preview", tint = SaathiPalette.Text, modifier = Modifier.size(32.dp))
                }
                Spacer(Modifier.height(14.dp))
                Text("TAP TO TALK", color = SaathiPalette.Text, fontSize = 10.sp, fontWeight = FontWeight.Black, letterSpacing = 2.sp)
                Text("Voice setup pending · mic off", color = SaathiPalette.Faint, fontSize = 11.sp, modifier = Modifier.padding(top = 5.dp))
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth().clickable(role = Role.Button, onClick = onOpenChat),
            shape = RoundedCornerShape(20.dp),
            color = SaathiPalette.Surface,
            border = androidx.compose.foundation.BorderStroke(1.dp, SaathiPalette.Border),
        ) {
            Column(Modifier.padding(17.dp)) {
                Text("Ask Saathi anything", color = SaathiPalette.Text, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text("Type a message to try the chat preview.", color = SaathiPalette.Muted, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp, bottom = 13.dp))
                Button(
                    onClick = onOpenChat,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = SaathiPalette.Red),
                    shape = RoundedCornerShape(14.dp),
                ) {
                    Text("Message Saathi", color = SaathiPalette.Text, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(25.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text("Quick actions", color = SaathiPalette.Text, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text("Choose a prompt to try in demo chat", color = SaathiPalette.Faint, fontSize = 11.sp, modifier = Modifier.padding(top = 3.dp))
            }
            Icon(Icons.Outlined.AutoAwesome, contentDescription = null, tint = SaathiPalette.Red)
        }
        Spacer(Modifier.height(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
            prompts.forEach { prompt ->
                Surface(
                    modifier = Modifier.fillMaxWidth().clickable(role = Role.Button) { onPrompt(prompt.prompt) },
                    shape = RoundedCornerShape(16.dp),
                    color = SaathiPalette.Surface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaathiPalette.Border),
                ) {
                    Row(
                        modifier = Modifier.padding(11.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Box(
                            Modifier.size(36.dp).clip(RoundedCornerShape(12.dp)).background(prompt.tint.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(prompt.icon, contentDescription = null, tint = prompt.tint, modifier = Modifier.size(19.dp))
                        }
                        Text(prompt.title, modifier = Modifier.weight(1f), color = SaathiPalette.Text, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text("›", color = SaathiPalette.Faint, fontSize = 22.sp)
                    }
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth().padding(top = 18.dp, bottom = 14.dp),
            shape = RoundedCornerShape(14.dp),
            color = Color(0xFF13211C),
        ) {
            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Outlined.Shield, contentDescription = null, tint = SaathiPalette.Green, modifier = Modifier.size(18.dp))
                Text("Preview mode: no device permissions or external actions are enabled.", color = Color(0xFFA8CBB8), fontSize = 10.sp, lineHeight = 15.sp)
            }
        }
    }
}
