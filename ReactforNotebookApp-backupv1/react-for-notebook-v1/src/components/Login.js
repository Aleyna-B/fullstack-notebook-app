//import React, { useState } from "react";
import React, { useRef } from 'react';
import { Link } from "react-router-dom";
import Validation from "../InputValidation";
import axiosInstance from '../axiosConfig';
import { useNavigate } from 'react-router-dom';
import axiosInstanceforLogin from '../axiosConfigforLogin';
import '../css/Login.css';

function Login() {
  const nameRef = useRef();
  const passRef = useRef();
  const user = {
    username: "",
    password:""
  };
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();   //bunun yukarıda olması önemli
    user.username = nameRef.current.value
    user.password = passRef.current.value
    console.log('A name was submitted: ' + user.username + user.password);

    try {
    const res = await axiosInstanceforLogin.post('/login',user)
                                          
    if (res.status === 200) {
      console.log("post method was succesful");
      console.log('Login data:', res.data);
      if (res.data==="success") {
      await axiosInstance.get('/user');
      navigate('/v1/notebook/user/notepage');        
      }
      else alert("Login data response is faulty")
    }
    else {
      alert("post unsuccessful");
      console.log('Response status:', res.status);
    }
    } catch (error) {
      console.error('Error registering user:', error);
    }
    console.log('Form submitted successfully');
    
  }

  return (
    <>
  {/*
Author: W3layouts
Author URL: http://w3layouts.com
*/}
  <title>Login Page</title>
  {/* Meta tags */}
  <meta charSet="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta httpEquiv="X-UA-Compatible" content="ie=edge" />
  {/* Style */}
  <link rel="stylesheet" href='../css/Login.css' type="text/css" media="all" />
  {/* login form */}
  <section className="w3l-login">
    <div className="overlay">
      <div className="wrapper">
        <div className="form-section">
          <h3>Login</h3>
          <h5>Welcome Back!</h5>
          <form onSubmit={handleSubmit} className="signin-form">
            <div className="form-input">
              <input
                type="text"
                name="username"
                placeholder="Enter Username"
                required=""
                autoFocus=""
                ref={nameRef}
              />
            </div>
            <div className="form-input">
              <input
                type="password"
                name="password"
                placeholder="Password"
                required=""
                ref={passRef}
              />
            </div>
            <button type="submit" className="btn btn-success theme-button mt-4">
              Log in
            </button>
          </form>
          <Link to={"/v1/notebook/signUp"} className="btn btn-primary border w-100">Sign Up if you haven't!</Link>
        </div>
      </div>
    </div>
    <div id="stars" />
    <div id="stars2" />
    <div id="stars3" />
    {/* copyright */}
    <div className="copy-right">
      <p>
        © 2020 Snow Login Form. All rights reserved | Design by{" "}
        <a href="http://w3layouts.com/" target="_blank">
          W3Layouts
        </a>
      </p>
    </div>
    {/* //copyright */}
  </section>
  {/* /login form */}
</>

    // <div className="d-flex justify-content-center align-items-center bg-info vh-100">
    //   <div className="bg-white p-3 rounded w-25">
    //     <h2>Welcome Back!</h2>
    //     <br />
    //     <form onSubmit={handleSubmit}>
    //       <div className="mb-3">
    //         <label htmlFor="username"><strong>Username</strong></label>
    //         <input 
    //           type="text" 
    //           name="username" 
    //           placeholder="Enter Username" 
    //           ref={nameRef}
    //           className="form-control rounded-0" 
    //         />
    //       </div>
    //       <div className="mb-3">
    //         <label htmlFor="password"><strong>Password</strong></label>
    //         <input 
    //           type="password" 
    //           name="password" 
    //           placeholder="Enter Password" 
    //           ref={passRef}
    //           className="form-control rounded-0" 
    //         />
    //       </div>
    //       <button type="submit" className="btn btn-success w-100 mb-2">Login</button>
    //       <Link to={"/v1/notebook/signUp/"} className="btn btn-primary border w-100">Sign Up here if you haven't!</Link>
    //     </form>
    //   </div>
    // </div>
  );
}

export default Login;
