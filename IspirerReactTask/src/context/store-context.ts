import { createContext } from 'react';
import { RootStore, store } from '../store/root';

export const StoreContext = createContext<RootStore>(store);
