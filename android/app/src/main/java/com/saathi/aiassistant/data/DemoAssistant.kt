package com.saathi.aiassistant.data

import java.util.Locale

enum class ChatRole {
    USER,
    ASSISTANT,
}

data class ChatMessage(
    val role: ChatRole,
    val text: String,
)

object DemoAssistant {
    fun replyTo(input: String): String {
        val query = input.trim().lowercase(Locale.ROOT)
        return when {
            query.contains("timer") || query.contains("alarm") ->
                "Demo mein timer device par set nahi hota. Real timer ke liye Android notifications aur local scheduling baad mein add honge."

            query.contains("map") || query.contains("direction") || query.contains("jagah") ->
                "Maps abhi connect nahi hai. Is preview ne koi location access nahi liya aur na hi external app kholi."

            query.contains("search") || query.contains("web") ->
                "Web search abhi demo hai—koi browser ya external service open nahi hui."

            query.contains("summary") || query.contains("summar") ->
                "Text bhej dijiye; is preview mein main sample response dunga. Live AI provider abhi connect nahi hai."

            else ->
                "Main abhi Saathi ka local demo hoon. Live AI aur phone actions connect nahi hain—API key app mein nahi rakhi jayegi."
        }
    }
}
