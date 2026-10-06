package com.saathi.aiassistant.data

import org.junit.Assert.assertTrue
import org.junit.Test

class DemoAssistantTest {
    @Test
    fun timerPromptClearlySaysNoDeviceTimerIsSet() {
        val reply = DemoAssistant.replyTo("Mujhe 10 minute ka timer lagana hai")
        assertTrue(reply.contains("timer device par set nahi hota"))
    }

    @Test
    fun directionsPromptClearlySaysMapsAreNotConnected() {
        val reply = DemoAssistant.replyTo("Mujhe directions chahiye")
        assertTrue(reply.contains("Maps abhi connect nahi hai"))
    }

    @Test
    fun unknownPromptStatesThisIsLocalDemo() {
        val reply = DemoAssistant.replyTo("Namaste")
        assertTrue(reply.contains("local demo"))
        assertTrue(reply.contains("Live AI"))
    }
}
