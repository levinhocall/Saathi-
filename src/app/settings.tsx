import { Ionicons } from '@expo/vector-icons';
import { Alert, Pressable, ScrollView, StyleSheet, Text, View } from 'react-native';
import { ScreenFrame } from '../components/ScreenFrame';
import { colors, radii } from '../lib/theme';

const rows = [
  { icon: 'language-outline', title: 'Language', detail: 'Hinglish · more options later', color: colors.purple },
  { icon: 'mic-outline', title: 'Voice assistant', detail: 'Not connected · microphone off', color: colors.red },
  { icon: 'location-outline', title: 'Location', detail: 'Not requested', color: colors.green },
  { icon: 'notifications-outline', title: 'Notifications', detail: 'Not requested', color: colors.amber },
  { icon: 'lock-closed-outline', title: 'Privacy & history', detail: 'Demo chat stays in memory only', color: colors.green },
] as const;

export default function SettingsScreen() {
  return (
    <ScreenFrame>
      <ScrollView contentContainerStyle={styles.content} showsVerticalScrollIndicator={false}>
        <Text style={styles.eyebrow}>MAKE IT YOURS</Text>
        <Text style={styles.title}>Settings</Text>
        <Text style={styles.subtitle}>Your controls should stay clear and in your hands.</Text>

        <View style={styles.profileCard}>
          <View style={styles.profileMark}><Ionicons name="sparkles" size={23} color={colors.text} /></View>
          <View style={styles.profileCopy}><Text style={styles.profileTitle}>Saathi AI</Text><Text style={styles.profileDetail}>Personal companion · Preview build</Text></View>
          <View style={styles.previewPill}><Text style={styles.previewPillText}>DEMO</Text></View>
        </View>

        <Text style={styles.sectionTitle}>Preferences & access</Text>
        <View style={styles.rows}>
          {rows.map((row) => (
            <Pressable
              key={row.title}
              style={styles.row}
              onPress={() => Alert.alert(row.title, `${row.detail}. Settings will become active when the related feature is implemented.`)}
              accessibilityRole="button"
            >
              <View style={[styles.rowIcon, { backgroundColor: `${row.color}1A` }]}><Ionicons name={row.icon} size={18} color={row.color} /></View>
              <View style={styles.rowCopy}><Text style={styles.rowTitle}>{row.title}</Text><Text style={styles.rowDetail}>{row.detail}</Text></View>
              <Ionicons name="chevron-forward" size={17} color={colors.faint} />
            </Pressable>
          ))}
        </View>

        <View style={styles.privacyNote}>
          <Ionicons name="shield-checkmark" size={17} color={colors.green} />
          <Text style={styles.privacyText}>No microphone, contacts, location, or notification access is enabled in this version.</Text>
        </View>

        <Text style={styles.version}>SAATHI · EARLY PROTOTYPE</Text>
      </ScrollView>
    </ScreenFrame>
  );
}

const styles = StyleSheet.create({
  content: { paddingHorizontal: 19, paddingTop: 22, paddingBottom: 26 },
  eyebrow: { color: colors.red, fontSize: 9, letterSpacing: 1.7, fontWeight: '800', marginBottom: 6 },
  title: { color: colors.text, fontSize: 29, fontWeight: '800', letterSpacing: -0.7 },
  subtitle: { color: colors.muted, fontSize: 12, marginTop: 6, lineHeight: 18 },
  profileCard: { flexDirection: 'row', alignItems: 'center', gap: 12, padding: 15, backgroundColor: colors.surface, borderWidth: 1, borderColor: colors.border, borderRadius: radii.card, marginTop: 21, marginBottom: 25 },
  profileMark: { width: 46, height: 46, borderRadius: 15, backgroundColor: colors.red, alignItems: 'center', justifyContent: 'center' },
  profileCopy: { flex: 1 },
  profileTitle: { color: colors.text, fontSize: 14, fontWeight: '800' },
  profileDetail: { color: colors.faint, fontSize: 10, marginTop: 4 },
  previewPill: { backgroundColor: '#30251B', paddingHorizontal: 8, paddingVertical: 5, borderRadius: radii.pill },
  previewPillText: { color: colors.amber, fontSize: 8, fontWeight: '900', letterSpacing: 0.6 },
  sectionTitle: { color: colors.text, fontSize: 16, fontWeight: '800', marginBottom: 10 },
  rows: { borderWidth: 1, borderColor: colors.border, borderRadius: radii.card, backgroundColor: colors.surface, overflow: 'hidden' },
  row: { flexDirection: 'row', alignItems: 'center', gap: 11, paddingHorizontal: 12, paddingVertical: 13, borderBottomWidth: 1, borderBottomColor: colors.border },
  rowIcon: { width: 35, height: 35, borderRadius: 12, alignItems: 'center', justifyContent: 'center' },
  rowCopy: { flex: 1 },
  rowTitle: { color: colors.text, fontSize: 12, fontWeight: '700' },
  rowDetail: { color: colors.faint, fontSize: 10, marginTop: 3 },
  privacyNote: { flexDirection: 'row', gap: 9, alignItems: 'flex-start', padding: 13, backgroundColor: '#13211C', borderRadius: 14, marginTop: 18 },
  privacyText: { flex: 1, color: '#A8CBB8', fontSize: 10, lineHeight: 15 },
  version: { color: colors.faint, fontSize: 9, letterSpacing: 1.4, textAlign: 'center', marginTop: 22 },
});
