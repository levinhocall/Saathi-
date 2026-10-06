package com.saathi.aiassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saathi.aiassistant.ui.theme.SaathiPalette

private data class SettingEntry(
    val title: String,
    val detail: String,
    val icon: ImageVector,
    val tint: Color,
)

@Composable
fun SettingsScreen() {
    var selectedSetting by remember { mutableStateOf<SettingEntry?>(null) }
    val settings = listOf(
        SettingEntry("Language", "Hinglish · more options later", Icons.Outlined.Language, SaathiPalette.Purple),
        SettingEntry("Voice assistant", "Not connected · microphone off", Icons.Outlined.Mic, SaathiPalette.Red),
        SettingEntry("Location", "Not requested", Icons.Outlined.LocationOn, SaathiPalette.Green),
        SettingEntry("Notifications", "Not requested", Icons.Outlined.Notifications, SaathiPalette.Amber),
        SettingEntry("Privacy & history", "Demo chat stays in memory only", Icons.Outlined.Lock, SaathiPalette.Green),
    )

    selectedSetting?.let { item ->
        AlertDialog(
            onDismissRequest = { selectedSetting = null },
            title = { Text(item.title) },
            text = { Text("${item.detail}. Settings will become active when the related feature is implemented.") },
            confirmButton = { TextButton(onClick = { selectedSetting = null }) { Text("OK") } },
            containerColor = SaathiPalette.Surface,
            titleContentColor = SaathiPalette.Text,
            textContentColor = SaathiPalette.Muted,
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 19.dp, vertical = 22.dp),
    ) {
        Text("MAKE IT YOURS", color = SaathiPalette.Red, fontSize = 9.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.7.sp)
        Text("Settings", color = SaathiPalette.Text, fontSize = 29.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(top = 6.dp))
        Text("Your controls should stay clear and in your hands.", color = SaathiPalette.Muted, fontSize = 12.sp, modifier = Modifier.padding(top = 6.dp))

        Surface(
            modifier = Modifier.fillMaxWidth().padding(top = 21.dp, bottom = 25.dp),
            shape = RoundedCornerShape(18.dp),
            color = SaathiPalette.Surface,
            border = androidx.compose.foundation.BorderStroke(1.dp, SaathiPalette.Border),
        ) {
            Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Surface(shape = RoundedCornerShape(15.dp), color = SaathiPalette.Red) {
                    Icon(Icons.Outlined.AutoAwesome, contentDescription = null, tint = SaathiPalette.Text, modifier = Modifier.padding(12.dp).size(22.dp))
                }
                Column(Modifier.weight(1f)) {
                    Text("Saathi AI", color = SaathiPalette.Text, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                    Text("Personal companion · Preview build", color = SaathiPalette.Faint, fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
                }
                Surface(shape = RoundedCornerShape(50.dp), color = Color(0xFF30251B)) {
                    Text("DEMO", color = SaathiPalette.Amber, fontSize = 8.sp, fontWeight = FontWeight.Black, modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp))
                }
            }
        }

        Text("Preferences & access", color = SaathiPalette.Text, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            color = SaathiPalette.Surface,
            border = androidx.compose.foundation.BorderStroke(1.dp, SaathiPalette.Border),
        ) {
            Column {
                settings.forEachIndexed { index, item ->
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable(role = Role.Button) { selectedSetting = item }.padding(horizontal = 12.dp, vertical = 13.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp),
                    ) {
                        Surface(shape = RoundedCornerShape(12.dp), color = item.tint.copy(alpha = 0.12f)) {
                            Icon(item.icon, contentDescription = null, tint = item.tint, modifier = Modifier.padding(9.dp).size(18.dp))
                        }
                        Column(Modifier.weight(1f)) {
                            Text(item.title, color = SaathiPalette.Text, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Text(item.detail, color = SaathiPalette.Faint, fontSize = 10.sp, modifier = Modifier.padding(top = 3.dp))
                        }
                        Icon(Icons.Outlined.ChevronRight, contentDescription = "More about ${item.title}", tint = SaathiPalette.Faint)
                    }
                    if (index < settings.lastIndex) {
                        Spacer(Modifier.fillMaxWidth().padding(horizontal = 12.dp).size(1.dp).background(SaathiPalette.Border))
                    }
                }
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth().padding(top = 18.dp),
            shape = RoundedCornerShape(14.dp),
            color = Color(0xFF13211C),
        ) {
            Row(Modifier.padding(13.dp), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                Icon(Icons.Outlined.Shield, contentDescription = null, tint = SaathiPalette.Green, modifier = Modifier.size(18.dp))
                Text("No microphone, contacts, location, or notification access is enabled in this version.", color = Color(0xFFA8CBB8), fontSize = 10.sp, lineHeight = 15.sp)
            }
        }
        Text("SAATHI · EARLY PROTOTYPE", color = SaathiPalette.Faint, fontSize = 9.sp, letterSpacing = 1.4.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(top = 22.dp))
    }
}
