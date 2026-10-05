import { Ionicons } from '@expo/vector-icons';
import { Link, usePathname, type Href } from 'expo-router';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import { colors } from '../lib/theme';

const tabs = [
  { label: 'Home', route: '/', icon: 'home-outline', activeIcon: 'home' },
  { label: 'Chat', route: '/chat', icon: 'chatbubble-ellipses-outline', activeIcon: 'chatbubble-ellipses' },
  { label: 'Tools', route: '/tools', icon: 'flash-outline', activeIcon: 'flash' },
  { label: 'Settings', route: '/settings', icon: 'settings-outline', activeIcon: 'settings' },
] as const;

export function TabBar() {
  const pathname = usePathname();

  return (
    <View style={styles.bar} accessibilityRole="tablist">
      {tabs.map((tab) => {
        const active = pathname === tab.route;
        return (
          <Link key={tab.route} href={tab.route as Href} asChild>
            <Pressable
              accessibilityRole="tab"
              accessibilityState={{ selected: active }}
              accessibilityLabel={tab.label}
              style={styles.item}
            >
              <Ionicons
                name={active ? tab.activeIcon : tab.icon}
                size={20}
                color={active ? colors.red : colors.faint}
              />
              <Text style={[styles.label, active && styles.activeLabel]}>{tab.label}</Text>
              {active ? <View style={styles.activeDot} /> : <View style={styles.dotSpacer} />}
            </Pressable>
          </Link>
        );
      })}
    </View>
  );
}

const styles = StyleSheet.create({
  bar: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-around',
    paddingTop: 10,
    paddingBottom: 4,
    paddingHorizontal: 8,
    borderTopWidth: 1,
    borderTopColor: colors.border,
    backgroundColor: colors.background,
  },
  item: { flex: 1, alignItems: 'center', gap: 4, minHeight: 48, justifyContent: 'center' },
  label: { color: colors.faint, fontSize: 10, fontWeight: '600' },
  activeLabel: { color: colors.red },
  activeDot: { width: 3, height: 3, borderRadius: 2, backgroundColor: colors.red },
  dotSpacer: { width: 3, height: 3 },
});
