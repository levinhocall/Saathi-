package com.saathi.aiassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Timer
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saathi.aiassistant.ui.theme.SaathiPalette

private data class PlannedTool(
    val title: String,
    val description: String,
    val state: String,
    val icon: ImageVector,
    val tint: Color,
)

@Composable
fun ToolsScreen() {
    var selectedTool by remember { mutableStateOf<PlannedTool?>(null) }
    val tools = listOf(
        PlannedTool("Alarm & Timer", "Local reminders and countdowns", "PLANNED", Icons.Outlined.Timer, SaathiPalette.Amber),
        PlannedTool("Search & Browser", "Web queries and page summaries", "PLANNED", Icons.Outlined.Search, SaathiPalette.Purple),
        PlannedTool("Maps & Places", "Directions after your permission", "PLANNED", Icons.Outlined.LocationOn, SaathiPalette.Green),
        PlannedTool("Contacts & Calls", "Open dialer; confirm before calling", "PLANNED", Icons.Outlined.Phone, SaathiPalette.Red),
        PlannedTool("Files & OCR", "Find or read files only when asked", "LATER", Icons.Outlined.FolderOpen, SaathiPalette.Purple),
        PlannedTool("Notifications", "Optional notification access", "LATER", Icons.Outlined.Notifications, SaathiPalette.Amber),
    )

    selectedTool?.let { tool ->
        AlertDialog(
            onDismissRequest = { selectedTool = null },
            title = { Text(tool.title) },
            text = { Text("${tool.description}. This feature is not active in the current preview.") },
            confirmButton = { TextButton(onClick = { selectedTool = null }) { Text("OK") } },
            containerColor = SaathiPalette.Surface,
            titleContentColor = SaathiPalette.Text,
            textContentColor = SaathiPalette.Muted,
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 19.dp, vertical = 20.dp),
    ) {
        Text("SAATHI TOOLBOX", color = SaathiPalette.Red, fontSize = 9.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.7.sp)
        Text("Your tools", color = SaathiPalette.Text, fontSize = 29.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(top = 6.dp))

        Surface(
            modifier = Modifier.fillMaxWidth().padding(top = 19.dp, bottom = 26.dp),
            shape = RoundedCornerShape(18.dp),
            color = Color(0xFF13211C),
        ) {
            Row(Modifier.padding(15.dp), horizontalArrangement = Arrangement.spacedBy(11.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(36.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFF1D3629)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.Shield, contentDescription = null, tint = SaathiPalette.Green, modifier = Modifier.size(19.dp))
                }
                Column {
                    Text("Nothing connected yet", color = Color(0xFFD9F2E3), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Text("This preview has not requested access to your phone.", color = Color(0xFFA8CBB8), fontSize = 11.sp, modifier = Modifier.padding(top = 3.dp))
                }
            }
        }

        Text("Planned capabilities", color = SaathiPalette.Text, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        Text("We’ll connect these one by one, with clear permission prompts.", color = SaathiPalette.Faint, fontSize = 11.sp, modifier = Modifier.padding(top = 4.dp, bottom = 13.dp))
        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
            tools.forEach { tool ->
                Surface(
                    modifier = Modifier.fillMaxWidth().clickable(role = Role.Button) { selectedTool = tool },
                    shape = RoundedCornerShape(16.dp),
                    color = SaathiPalette.Surface,
                    border = androidx.compose.foundation.BorderStroke(1.dp, SaathiPalette.Border),
                ) {
                    Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(11.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(39.dp).clip(RoundedCornerShape(12.dp)).background(tool.tint.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
                            Icon(tool.icon, contentDescription = null, tint = tool.tint, modifier = Modifier.size(19.dp))
                        }
                        Column(Modifier.weight(1f)) {
                            Text(tool.title, color = SaathiPalette.Text, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Text(tool.description, color = SaathiPalette.Faint, fontSize = 10.sp, modifier = Modifier.padding(top = 3.dp))
                        }
                        Surface(shape = RoundedCornerShape(50.dp), color = if (tool.state == "LATER") SaathiPalette.SurfaceRaised else Color(0xFF30251B)) {
                            Text(tool.state, color = SaathiPalette.Amber, fontSize = 8.sp, fontWeight = FontWeight.Black, letterSpacing = 0.5.sp, modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp))
                        }
                    }
                }
            }
        }
        Spacer(Modifier.size(16.dp))
        Text(
            "Actions that send messages, start calls, read private data, or control the screen will need explicit setup and user confirmation.",
            color = SaathiPalette.Faint,
            fontSize = 10.sp,
            lineHeight = 16.sp,
        )
    }
}
