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
  <div className="w3l-login">
  <div className="overlay"  >
    <div className="wrapper">
      <div className="form-section" >
          <h3>Login</h3>
          <h5>Welcome Back!</h5>
          <form onSubmit={handleSubmit} className="signin-form">
            <div className="form-input">
              <input
                type="text"
                name="username" 
                placeholder="Enter Username"
                required
                autoFocus
                ref={nameRef}
              />
            </div>
            <div className="form-input">
              <input
                type="password"
                name="password"
                placeholder="Enter Password"
                required
                ref={passRef}
              />
            </div>
            <button type="submit" className="btn btn-success theme-button mt-4 w-70">
              Log in
            </button>
          </form>
          <p class="signup"><Link to={"/v1/notebook/signUp"} className="signup">Sign Up Here to Create an Account!</Link></p>
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
  </div>  
</>
  );
}

export default Login;
