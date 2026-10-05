import { Ionicons } from '@expo/vector-icons';
import { Alert, Pressable, ScrollView, StyleSheet, Text, View } from 'react-native';
import { ScreenFrame } from '../components/ScreenFrame';
import { colors, radii } from '../lib/theme';

const tools = [
  { icon: 'timer-outline', title: 'Alarm & Timer', detail: 'Local reminders and countdowns', color: colors.amber, status: 'PLANNED' },
  { icon: 'search-outline', title: 'Search & Browser', detail: 'Web queries and page summaries', color: colors.purple, status: 'PLANNED' },
  { icon: 'map-outline', title: 'Maps & Places', detail: 'Directions after your permission', color: colors.green, status: 'PLANNED' },
  { icon: 'call-outline', title: 'Contacts & Calls', detail: 'Open dialer; confirm before calling', color: colors.red, status: 'PLANNED' },
  { icon: 'document-text-outline', title: 'Files & OCR', detail: 'Find or read files only when asked', color: colors.purple, status: 'LATER' },
  { icon: 'notifications-outline', title: 'Notifications', detail: 'Optional notification access', color: colors.amber, status: 'LATER' },
] as const;

export default function ToolsScreen() {
  return (
    <ScreenFrame>
      <ScrollView contentContainerStyle={styles.content} showsVerticalScrollIndicator={false}>
        <View style={styles.headingRow}>
          <View><Text style={styles.eyebrow}>SAATHI TOOLBOX</Text><Text style={styles.title}>Your tools</Text></View>
          <View style={styles.headingIcon}><Ionicons name="flash" size={20} color={colors.red} /></View>
        </View>
        <View style={styles.privacyCard}>
          <View style={styles.privacyIcon}><Ionicons name="shield-checkmark-outline" size={18} color={colors.green} /></View>
          <View style={styles.privacyCopy}><Text style={styles.privacyTitle}>Nothing connected yet</Text><Text style={styles.privacyText}>This preview has not requested access to your phone.</Text></View>
        </View>
        <Text style={styles.sectionTitle}>Planned capabilities</Text>
        <Text style={styles.sectionSub}>We’ll connect these one by one, with clear permission prompts.</Text>
        <View style={styles.toolList}>
          {tools.map((tool) => (
            <Pressable
              key={tool.title}
              style={styles.toolCard}
              onPress={() => Alert.alert(tool.title, `${tool.detail}. This feature is not active in the current preview.`)}
              accessibilityRole="button"
            >
              <View style={[styles.toolIcon, { backgroundColor: `${tool.color}1A` }]}><Ionicons name={tool.icon} size={19} color={tool.color} /></View>
              <View style={styles.toolCopy}><Text style={styles.toolTitle}>{tool.title}</Text><Text style={styles.toolDetail}>{tool.detail}</Text></View>
              <View style={[styles.status, tool.status === 'LATER' && styles.laterStatus]}><Text style={styles.statusText}>{tool.status}</Text></View>
            </Pressable>
          ))}
        </View>
        <Text style={styles.footer}>Actions that send messages, start calls, read private data, or control the screen will need explicit setup and user confirmation.</Text>
      </ScrollView>
    </ScreenFrame>
  );
}

const styles = StyleSheet.create({
  content: { paddingHorizontal: 19, paddingTop: 19, paddingBottom: 24 },
  headingRow: { flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', marginBottom: 20 },
  eyebrow: { color: colors.red, fontSize: 9, letterSpacing: 1.7, fontWeight: '800', marginBottom: 6 },
  title: { color: colors.text, fontSize: 29, fontWeight: '800', letterSpacing: -0.7 },
  headingIcon: { width: 43, height: 43, borderRadius: 15, backgroundColor: colors.surfaceRaised, borderWidth: 1, borderColor: colors.border, alignItems: 'center', justifyContent: 'center' },
  privacyCard: { flexDirection: 'row', gap: 11, padding: 15, borderRadius: radii.card, backgroundColor: '#13211C', marginBottom: 26 },
  privacyIcon: { width: 34, height: 34, borderRadius: 11, backgroundColor: '#1D3629', alignItems: 'center', justifyContent: 'center' },
  privacyCopy: { flex: 1 },
  privacyTitle: { color: '#D9F2E3', fontSize: 13, fontWeight: '700' },
  privacyText: { color: '#A8CBB8', fontSize: 11, lineHeight: 16, marginTop: 3 },
  sectionTitle: { color: colors.text, fontSize: 17, fontWeight: '800' },
  sectionSub: { color: colors.muted, fontSize: 11, marginTop: 4, marginBottom: 13 },
  toolList: { gap: 9 },
  toolCard: { flexDirection: 'row', alignItems: 'center', gap: 11, padding: 12, borderRadius: radii.control, borderWidth: 1, borderColor: colors.border, backgroundColor: colors.surface },
  toolIcon: { width: 38, height: 38, borderRadius: 12, alignItems: 'center', justifyContent: 'center' },
  toolCopy: { flex: 1 },
  toolTitle: { color: colors.text, fontSize: 12, fontWeight: '700' },
  toolDetail: { color: colors.faint, fontSize: 10, marginTop: 3 },
  status: { backgroundColor: '#30251B', borderRadius: radii.pill, paddingHorizontal: 7, paddingVertical: 5 },
  laterStatus: { backgroundColor: colors.surfaceSoft },
  statusText: { color: colors.amber, fontSize: 8, fontWeight: '900', letterSpacing: 0.5 },
  footer: { color: colors.faint, fontSize: 10, lineHeight: 16, marginTop: 17 },
});
