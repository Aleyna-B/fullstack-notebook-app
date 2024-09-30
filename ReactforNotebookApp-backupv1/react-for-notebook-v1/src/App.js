import React from 'react';
import Login from './components/Login'
// import './App.css';
import {BrowserRouter, Routes,Route} from 'react-router-dom'
import SignUp from './components/SignUp'
import UserNotes from './components/UserNotes'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/v1/notebook/login/' element={<Login/>}></Route>
        <Route path='/v1/notebook/signUp/' element={<SignUp/>}></Route>
        <Route path='/v1/notebook/user/notepage' element={<UserNotes/>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
