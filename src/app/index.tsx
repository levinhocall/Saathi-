import { Ionicons } from '@expo/vector-icons';
import { router } from 'expo-router';
import { Alert, Pressable, ScrollView, StyleSheet, Text, View } from 'react-native';
import { ScreenFrame } from '../components/ScreenFrame';
import { colors, radii } from '../lib/theme';

const prompts = [
  { icon: 'timer-outline', label: 'Timer set karo', color: colors.amber, prompt: 'Mujhe 10 minute ka timer lagana hai' },
  { icon: 'search-outline', label: 'Web par search', color: colors.purple, prompt: 'Web par koi topic search karo' },
  { icon: 'navigate-outline', label: 'Directions', color: colors.green, prompt: 'Mujhe ek jagah ke directions chahiye' },
  { icon: 'document-text-outline', label: 'Summarize', color: colors.red, prompt: 'Is text ka short summary banao' },
] as const;

function startPrompt(prompt: string) {
  router.push({ pathname: '/chat', params: { prompt } });
}

function PromptCard({ item }: { item: (typeof prompts)[number] }) {
  return (
    <Pressable style={styles.promptCard} onPress={() => startPrompt(item.prompt)} accessibilityRole="button">
      <View style={[styles.promptIcon, { backgroundColor: `${item.color}1A` }]}>
        <Ionicons name={item.icon} size={19} color={item.color} />
      </View>
      <Text style={styles.promptLabel}>{item.label}</Text>
      <Ionicons name="arrow-forward" size={14} color={colors.faint} />
    </Pressable>
  );
}

export default function HomeScreen() {
  return (
    <ScreenFrame>
      <ScrollView contentContainerStyle={styles.content} showsVerticalScrollIndicator={false}>
        <View style={styles.topline}>
          <View style={styles.brandLockup}>
            <View style={styles.brandMark}><Ionicons name="sparkles" size={17} color={colors.text} /></View>
            <Text style={styles.brand}>SAATHI<Text style={styles.brandDot}>.</Text></Text>
          </View>
          <View style={styles.previewBadge}><View style={styles.previewDot} /><Text style={styles.previewText}>PREVIEW</Text></View>
        </View>

        <View style={styles.greetingBlock}>
          <Text style={styles.eyebrow}>YOUR PERSONAL AI COMPANION</Text>
          <Text style={styles.greeting}>Namaste,</Text>
          <Text style={styles.greetingSub}>Aaj kis cheez mein madad karun?</Text>
        </View>

        <View style={styles.orbSection}>
          <View pointerEvents="none" style={styles.orbOuter} />
          <View pointerEvents="none" style={styles.orbMiddle} />
          <Pressable
            style={styles.orbButton}
            onPress={() => Alert.alert('Voice abhi connect nahi hai', 'Is preview mein microphone permission ya recording active nahi hai. Voice feature next build mein add hoga.')}
            accessibilityRole="button"
            accessibilityLabel="Voice assistant preview"
          >
            <Ionicons name="mic" size={31} color={colors.text} />
          </Pressable>
          <Text style={styles.orbCaption}>TAP TO TALK</Text>
          <Text style={styles.orbStatus}>Voice setup pending · mic off</Text>
        </View>

        <View style={styles.inputCard}>
          <Text style={styles.inputTitle}>Ask Saathi anything</Text>
          <Text style={styles.inputDescription}>Type a message to try the chat preview.</Text>
          <Pressable style={styles.chatCta} onPress={() => router.push('/chat')} accessibilityRole="button">
            <Text style={styles.chatCtaText}>Message Saathi</Text>
            <View style={styles.sendCircle}><Ionicons name="arrow-up" size={18} color={colors.text} /></View>
          </Pressable>
        </View>

        <View style={styles.sectionHeader}>
          <View><Text style={styles.sectionTitle}>Quick actions</Text><Text style={styles.sectionSubtitle}>Choose a prompt to try in demo chat</Text></View>
          <Ionicons name="flash" size={17} color={colors.red} />
        </View>
        <View style={styles.promptList}>
          {prompts.map((item) => <PromptCard item={item} key={item.label} />)}
        </View>

        <View style={styles.notice}>
          <Ionicons name="shield-checkmark-outline" size={17} color={colors.green} />
          <Text style={styles.noticeText}>Preview mode: no device permissions or external actions are enabled.</Text>
        </View>
      </ScrollView>
    </ScreenFrame>
  );
}

const styles = StyleSheet.create({
  content: { paddingHorizontal: 20, paddingTop: 12, paddingBottom: 22 },
  topline: { flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', marginBottom: 28 },
  brandLockup: { flexDirection: 'row', alignItems: 'center', gap: 9 },
  brandMark: { width: 32, height: 32, borderRadius: 11, alignItems: 'center', justifyContent: 'center', backgroundColor: colors.red },
  brand: { color: colors.text, fontSize: 17, letterSpacing: 2.4, fontWeight: '900' },
  brandDot: { color: colors.red },
  previewBadge: { flexDirection: 'row', alignItems: 'center', gap: 6, borderWidth: 1, borderColor: colors.border, backgroundColor: colors.surface, paddingHorizontal: 10, paddingVertical: 7, borderRadius: radii.pill },
  previewDot: { width: 6, height: 6, borderRadius: 3, backgroundColor: colors.amber },
  previewText: { color: colors.muted, fontSize: 9, letterSpacing: 1, fontWeight: '800' },
  greetingBlock: { marginBottom: 2 },
  eyebrow: { color: colors.red, fontSize: 10, fontWeight: '800', letterSpacing: 1.6, marginBottom: 10 },
  greeting: { color: colors.text, fontSize: 34, fontWeight: '800', letterSpacing: -1.1 },
  greetingSub: { color: colors.muted, fontSize: 15, marginTop: 5 },
  orbSection: { height: 238, alignItems: 'center', justifyContent: 'center', marginTop: 6, marginBottom: 13 },
  orbOuter: { position: 'absolute', width: 205, height: 205, borderRadius: 103, borderWidth: 1, borderColor: '#54232B', backgroundColor: '#31171D55' },
  orbMiddle: { position: 'absolute', width: 155, height: 155, borderRadius: 78, borderWidth: 1, borderColor: '#81313B', backgroundColor: '#431B2255' },
  orbButton: { width: 104, height: 104, borderRadius: 52, alignItems: 'center', justifyContent: 'center', backgroundColor: colors.red, borderWidth: 7, borderColor: '#69232D', shadowColor: colors.red, shadowOpacity: 0.5, shadowRadius: 24, shadowOffset: { width: 0, height: 0 }, elevation: 9 },
  orbCaption: { color: colors.text, fontSize: 10, letterSpacing: 2.2, fontWeight: '900', marginTop: 16 },
  orbStatus: { color: colors.faint, fontSize: 11, marginTop: 5 },
  inputCard: { backgroundColor: colors.surface, borderWidth: 1, borderColor: colors.border, borderRadius: radii.card, padding: 17, marginBottom: 25 },
  inputTitle: { color: colors.text, fontWeight: '800', fontSize: 15 },
  inputDescription: { color: colors.muted, fontSize: 12, marginTop: 4, marginBottom: 14 },
  chatCta: { flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', backgroundColor: colors.surfaceRaised, borderRadius: radii.control, paddingLeft: 14, paddingRight: 7, paddingVertical: 7 },
  chatCtaText: { color: colors.muted, fontSize: 13 },
  sendCircle: { width: 34, height: 34, borderRadius: 17, alignItems: 'center', justifyContent: 'center', backgroundColor: colors.red },
  sectionHeader: { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center', marginBottom: 12 },
  sectionTitle: { color: colors.text, fontSize: 17, fontWeight: '800' },
  sectionSubtitle: { color: colors.faint, fontSize: 11, marginTop: 3 },
  promptList: { gap: 9 },
  promptCard: { flexDirection: 'row', alignItems: 'center', gap: 12, backgroundColor: colors.surface, borderWidth: 1, borderColor: colors.border, padding: 11, borderRadius: radii.control },
  promptIcon: { width: 34, height: 34, borderRadius: 11, alignItems: 'center', justifyContent: 'center' },
  promptLabel: { flex: 1, color: colors.text, fontSize: 13, fontWeight: '600' },
  notice: { flexDirection: 'row', alignItems: 'center', gap: 8, marginTop: 18, padding: 12, backgroundColor: '#13211C', borderRadius: 13 },
  noticeText: { flex: 1, color: '#A8CBB8', fontSize: 10, lineHeight: 15 },
});
