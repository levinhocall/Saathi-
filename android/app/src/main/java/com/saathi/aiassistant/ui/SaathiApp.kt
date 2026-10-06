package com.saathi.aiassistant.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FlashOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.saathi.aiassistant.data.ChatMessage
import com.saathi.aiassistant.data.ChatRole
import com.saathi.aiassistant.data.DemoAssistant
import com.saathi.aiassistant.ui.screens.ChatScreen
import com.saathi.aiassistant.ui.screens.HomeScreen
import com.saathi.aiassistant.ui.screens.SettingsScreen
import com.saathi.aiassistant.ui.screens.ToolsScreen
import com.saathi.aiassistant.ui.theme.SaathiPalette

private enum class SaathiTab(
    val title: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Outlined.Home),
    CHAT("Chat", Icons.Outlined.ChatBubbleOutline),
    TOOLS("Tools", Icons.Outlined.FlashOn),
    SETTINGS("Settings", Icons.Outlined.Settings),
}

private val ChatHistorySaver = listSaver<List<ChatMessage>, String>(
    save = { history -> history.flatMap { listOf(it.role.name, it.text) } },
    restore = { saved ->
        saved.chunked(2).mapNotNull { entry ->
            if (entry.size != 2) return@mapNotNull null
            val role = runCatching { ChatRole.valueOf(entry[0]) }.getOrNull() ?: return@mapNotNull null
            ChatMessage(role = role, text = entry[1])
        }
    },
)

@Composable
fun SaathiApp() {
    var selectedTabName by rememberSaveable { mutableStateOf(SaathiTab.HOME.name) }
    var draft by rememberSaveable { mutableStateOf("") }
    var messages by rememberSaveable(stateSaver = ChatHistorySaver) {
        mutableStateOf(
            listOf(
                ChatMessage(
                    role = ChatRole.ASSISTANT,
                    text = "Namaste! Main Saathi ka preview hoon. Aap message bhej kar demo chat try kar sakte hain.",
                ),
            ),
        )
    }
    val selectedTab = SaathiTab.valueOf(selectedTabName)

    BackHandler(enabled = selectedTab != SaathiTab.HOME) {
        selectedTabName = SaathiTab.HOME.name
    }

    Scaffold(
        containerColor = SaathiPalette.Background,
        bottomBar = {
            NavigationBar(
                containerColor = SaathiPalette.Surface,
                contentColor = SaathiPalette.Muted,
                tonalElevation = 0.dp,
            ) {
                SaathiTab.entries.forEach { tab ->
                    val selected = selectedTab == tab
                    NavigationBarItem(
                        selected = selected,
                        onClick = { selectedTabName = tab.name },
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = SaathiPalette.Red,
                            selectedTextColor = SaathiPalette.Red,
                            indicatorColor = SaathiPalette.Red.copy(alpha = 0.14f),
                            unselectedIconColor = SaathiPalette.Faint,
                            unselectedTextColor = SaathiPalette.Faint,
                        ),
                    )
                }
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (selectedTab) {
                SaathiTab.HOME -> HomeScreen(
                    onOpenChat = { selectedTabName = SaathiTab.CHAT.name },
                    onPrompt = { prompt ->
                        draft = prompt
                        selectedTabName = SaathiTab.CHAT.name
                    },
                )

                SaathiTab.CHAT -> ChatScreen(
                    messages = messages,
                    draft = draft,
                    onDraftChange = { draft = it },
                    onSend = {
                        val text = draft.trim()
                        if (text.isNotEmpty()) {
                            messages = (
                                messages +
                                    ChatMessage(ChatRole.USER, text) +
                                    ChatMessage(ChatRole.ASSISTANT, DemoAssistant.replyTo(text))
                                ).takeLast(81)
                            draft = ""
                        }
                    },
                )

                SaathiTab.TOOLS -> ToolsScreen()
                SaathiTab.SETTINGS -> SettingsScreen()
            }
        }
    }
}
