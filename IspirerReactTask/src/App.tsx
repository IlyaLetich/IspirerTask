import React from 'react';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import { Lobby } from './screens/lobby';
import { News } from './screens/news';
import { Error } from './screens/error';

function App() {
  return (
    <Routes>
      <Route path="/news/:id" element={<News />} />
      <Route path="/lobby" element={<Lobby />} />
      <Route path="/error" element={<Error />} />
      <Route path="*" element={<Lobby />} />
    </Routes>
  );
}


export default App;
