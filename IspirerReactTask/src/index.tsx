import React, { createContext } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom'
import { RootStore, store } from './store/root';

const root = ReactDOM.createRoot(
  document.getElementById('app') as HTMLElement
);

export const StoreContext = createContext<RootStore>({} as RootStore);

root.render(
  <React.StrictMode>
    <BrowserRouter>
      <StoreContext.Provider value={store}>
        <App/>
      </StoreContext.Provider>
    </BrowserRouter>
  </React.StrictMode>,
);

reportWebVitals();
