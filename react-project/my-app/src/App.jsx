import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import HomePage from './components/HomePage';
import SignUp from './components/SignUp';
import Login from './components/Login';
import HomeAfterRegister from './components/HomeAfterRegisret';
import Flowers from './components/Flowers';
import FlowerDetails from './components/FlowerDetails';
function App() {
  return (

    <BrowserRouter>
    <Routes>
    <Route path="/" element={<HomePage />} />

        <Route path="/home" element={<HomePage />} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/Login" element={<Login />} />
        <Route path='homeAfterRegister' element={<HomeAfterRegister/>}/>
        <Route path="flowers" element={<Flowers/>}/>
       <Route path="/flowers/:id/details" element={<FlowerDetails />} />


    </Routes>
</BrowserRouter>
  );
}

export default App;