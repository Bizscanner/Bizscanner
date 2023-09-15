'use client';

import { store } from './store';
import { Provider } from 'react-redux';

export function ProviderWrapper({ children }) {
  return <Provider store={store}>{children}</Provider>;
}
