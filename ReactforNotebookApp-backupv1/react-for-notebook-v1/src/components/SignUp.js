
import React, { useRef } from 'react';
import axiosInstance from '../axiosConfig';
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';

export default function App() {
  const nameRef = useRef();
  const passRef = useRef();
  const user = {
    userName: "",
    userPassword:""
  };
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    user.userName = nameRef.current.value
    user.userPassword = passRef.current.value
    console.log('A name was submitted: ' + user.userName + user.userPassword);

    try {
          const response = await axiosInstance.post('/signUp', user);   
          if(response.status===200 && response.data){
            console.log('register status:', response.status);
            console.log('User registered:', response.data);  
            navigate('/v1/notebook/login');
          }
          else console.log("Either the response status or the response data is faulty");
          // Handle successful registration (e.g., redirect to login page)
        } catch (error) {
          console.error('Error registering user:', error);
          //setError('Error registering user. Please try again.');
        }
    
  };

  return (

    <>
    {/*
  Author: W3layouts
  Author URL: http://w3layouts.com
  */}
    <title>Sign Up Page</title>
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
            <h3>Sign Up</h3>
            <h5>Welcome!</h5>
            <form onSubmit={handleSubmit} className="signin-form">
              <div className="form-input">
                <input
                  type="text"
                  name="username"
                  placeholder="Enter Username"
                  required=""
                  autofocus=""
                  ref={nameRef}
                />
              </div>
              <div className="form-input">
                <input
                  type="password"
                  name="password"
                  placeholder="Enter Password"
                  required=""
                  ref={passRef}
                />
              </div>
              <button type="submit" className="btn btn-success theme-button mt-4">
                Sign Up
              </button>
            </form>
            <p class="signup"><Link to={"/v1/notebook/login"} className="signup">Login Here if You Already Have an Account!</Link></p>
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
  
  //   <div className="d-flex justify-content-center align-items-center bg-info vh-100">
  //   <div className="bg-white p-3 rounded w-25">
  //     <h2>Welcome!</h2>
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
  //       <button type="submit" className="btn btn-success w-100 mb-2">Sign Up</button>
  //       <Link to={"/v1/notebook/login/"} className="btn btn-primary border w-100">Login here if you have an account!</Link>
  //     </form>
  //   </div>
  // </div>
  );
}

