import { useContext } from 'react';

import { RootStore } from '../store/root';
import { StoreContext } from '../context/store-context';

export const useStores = (): RootStore => useContext(StoreContext);
