import type { ReactNode } from 'react';
import { StatusBar } from 'expo-status-bar';
import { SafeAreaView } from 'react-native-safe-area-context';
import { TabBar } from './TabBar';
import { colors } from '../lib/theme';

export function ScreenFrame({ children }: { children: ReactNode }) {
  return (
    <SafeAreaView edges={['top', 'right', 'bottom', 'left']} style={{ flex: 1, backgroundColor: colors.background }}>
      <StatusBar style="light" />
      {children}
      <TabBar />
    </SafeAreaView>
  );
}
