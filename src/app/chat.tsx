import { Ionicons } from '@expo/vector-icons';
import { useLocalSearchParams } from 'expo-router';
import { useRef, useState } from 'react';
import { Alert, KeyboardAvoidingView, Platform, Pressable, ScrollView, StyleSheet, Text, TextInput, View } from 'react-native';
import { ScreenFrame } from '../components/ScreenFrame';
import { colors, radii } from '../lib/theme';

type Message = { id: string; role: 'assistant' | 'user'; text: string };

function demoReply(text: string) {
  const query = text.toLowerCase();
  if (query.includes('timer') || query.includes('alarm')) return 'Demo mein timer device par set nahi hota. Real timer ke liye Android notification permission aur local scheduling add karenge.';
  if (query.includes('map') || query.includes('direction') || query.includes('jagah')) return 'Maps abhi connect nahi hai. Agle phase mein permission/intent ke saath directions open karenge.';
  if (query.includes('search') || query.includes('web')) return 'Web search abhi demo hai—koi browser ya external service open nahi hui. Live search ko baad mein connect karenge.';
  if (query.includes('summary') || query.includes('summar')) return 'Text bhej dijiye; is preview mein main sample response dunga. Live AI provider abhi connect nahi hai.';
  return 'Main abhi Saathi ka local demo hoon. Live AI aur phone actions abhi connect nahi hain—API key app mein nahi rakhi jayegi.';
}

export default function ChatScreen() {
  const params = useLocalSearchParams<{ prompt?: string | string[] }>();
  const initialPrompt = Array.isArray(params.prompt) ? params.prompt[0] : params.prompt;
  const [draft, setDraft] = useState(initialPrompt ?? '');
  const [messages, setMessages] = useState<Message[]>([
    { id: 'hello', role: 'assistant', text: 'Namaste! Main Saathi ka preview hoon. Aap message bhej kar demo chat try kar sakte hain.' },
  ]);
  const scrollRef = useRef<ScrollView>(null);

  const sendMessage = () => {
    const value = draft.trim();
    if (!value) return;
    const userMessage: Message = { id: `${Date.now()}-user`, role: 'user', text: value };
    const assistantMessage: Message = { id: `${Date.now()}-assistant`, role: 'assistant', text: demoReply(value) };
    setMessages((current) => [...current, userMessage, assistantMessage]);
    setDraft('');
    setTimeout(() => scrollRef.current?.scrollToEnd({ animated: true }), 80);
  };

  return (
    <ScreenFrame>
      <KeyboardAvoidingView style={styles.fill} behavior={Platform.OS === 'ios' ? 'padding' : undefined}>
        <View style={styles.header}>
          <View style={styles.headerIcon}><Ionicons name="sparkles" size={18} color={colors.text} /></View>
          <View style={styles.headerText}><Text style={styles.title}>Saathi Chat</Text><Text style={styles.subtitle}>Local demo · AI not connected</Text></View>
          <Pressable onPress={() => Alert.alert('Preview chat', 'Messages are kept in memory for this session only.')} accessibilityRole="button" accessibilityLabel="Chat information">
            <Ionicons name="information-circle-outline" size={23} color={colors.muted} />
          </Pressable>
        </View>

        <View style={styles.modeBanner}>
          <Ionicons name="flask-outline" size={15} color={colors.amber} />
          <Text style={styles.modeBannerText}>Demo replies only — no request goes to an AI provider.</Text>
        </View>

        <ScrollView ref={scrollRef} style={styles.messages} contentContainerStyle={styles.messageList} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">
          {messages.map((message) => (
            <View key={message.id} style={[styles.messageRow, message.role === 'user' && styles.userRow]}>
              {message.role === 'assistant' && <View style={styles.avatar}><Ionicons name="sparkles" size={13} color={colors.red} /></View>}
              <View style={[styles.bubble, message.role === 'user' ? styles.userBubble : styles.assistantBubble]}>
                <Text style={styles.messageText}>{message.text}</Text>
              </View>
            </View>
          ))}
        </ScrollView>

        <View style={styles.suggestions}>
          {['Timer', 'Directions', 'Search'].map((label) => (
            <Pressable key={label} style={styles.suggestion} onPress={() => setDraft(label === 'Timer' ? 'Mujhe timer set karna hai' : label === 'Directions' ? 'Mujhe directions chahiye' : 'Web par search karo')}>
              <Text style={styles.suggestionText}>{label}</Text>
            </Pressable>
          ))}
        </View>

        <View style={styles.composer}>
          <Pressable style={styles.micButton} onPress={() => Alert.alert('Voice not connected', 'Microphone access is disabled in this preview.')} accessibilityRole="button" accessibilityLabel="Voice input not connected">
            <Ionicons name="mic-outline" size={20} color={colors.muted} />
          </Pressable>
          <TextInput
            value={draft}
            onChangeText={setDraft}
            placeholder="Message Saathi…"
            placeholderTextColor={colors.faint}
            style={styles.input}
            returnKeyType="send"
            onSubmitEditing={sendMessage}
            accessibilityLabel="Message Saathi"
          />
          <Pressable style={[styles.sendButton, !draft.trim() && styles.sendButtonDisabled]} onPress={sendMessage} disabled={!draft.trim()} accessibilityRole="button" accessibilityLabel="Send message">
            <Ionicons name="arrow-up" size={19} color={colors.text} />
          </Pressable>
        </View>
      </KeyboardAvoidingView>
    </ScreenFrame>
  );
}

const styles = StyleSheet.create({
  fill: { flex: 1 },
  header: { flexDirection: 'row', alignItems: 'center', gap: 11, paddingHorizontal: 19, paddingTop: 12, paddingBottom: 14 },
  headerIcon: { width: 38, height: 38, borderRadius: 13, backgroundColor: colors.red, alignItems: 'center', justifyContent: 'center' },
  headerText: { flex: 1 },
  title: { color: colors.text, fontSize: 17, fontWeight: '800' },
  subtitle: { color: colors.faint, fontSize: 11, marginTop: 2 },
  modeBanner: { flexDirection: 'row', alignItems: 'center', gap: 8, marginHorizontal: 18, padding: 11, borderRadius: 12, backgroundColor: '#2B2518' },
  modeBannerText: { color: '#D9C28E', fontSize: 10, flex: 1 },
  messages: { flex: 1, marginTop: 11 },
  messageList: { paddingHorizontal: 17, paddingVertical: 13, gap: 13 },
  messageRow: { flexDirection: 'row', alignItems: 'flex-end', gap: 7, maxWidth: '92%' },
  userRow: { alignSelf: 'flex-end', justifyContent: 'flex-end' },
  avatar: { width: 25, height: 25, borderRadius: 9, backgroundColor: colors.surfaceRaised, alignItems: 'center', justifyContent: 'center', marginBottom: 2 },
  bubble: { paddingHorizontal: 14, paddingVertical: 11, borderRadius: 18 },
  assistantBubble: { backgroundColor: colors.surfaceRaised, borderBottomLeftRadius: 5 },
  userBubble: { backgroundColor: colors.red, borderBottomRightRadius: 5 },
  messageText: { color: colors.text, fontSize: 13, lineHeight: 19 },
  suggestions: { flexDirection: 'row', gap: 8, paddingHorizontal: 17, paddingBottom: 10 },
  suggestion: { borderColor: colors.border, borderWidth: 1, borderRadius: radii.pill, paddingHorizontal: 12, paddingVertical: 7 },
  suggestionText: { color: colors.muted, fontSize: 11 },
  composer: { flexDirection: 'row', alignItems: 'center', gap: 8, paddingHorizontal: 12, paddingVertical: 9, marginHorizontal: 15, marginBottom: 11, backgroundColor: colors.surface, borderWidth: 1, borderColor: colors.border, borderRadius: 19 },
  micButton: { width: 34, height: 36, alignItems: 'center', justifyContent: 'center' },
  input: { flex: 1, minHeight: 38, color: colors.text, fontSize: 14, paddingVertical: 6 },
  sendButton: { width: 35, height: 35, borderRadius: 13, backgroundColor: colors.red, alignItems: 'center', justifyContent: 'center' },
  sendButtonDisabled: { backgroundColor: colors.surfaceSoft },
});
