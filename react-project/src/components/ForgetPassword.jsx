import React from 'react'
import { Link } from 'react-router-dom';
import validation from "./employee/validation";
import userService from '../services/user.service';
import {useState,useEffect} from 'react';
import './login/style.css'

const ForgetPassword = () => {

    const [userEmail, setUserEmail] = useState('');
    const [dataAdded, setDataAdded] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("sending",);
          userService.forgetPassword(userEmail)
          .then(response => {
              console.log("Reterive password", response.data);
              setDataAdded("Check Your Mail");
          })
          .catch(error => {
              console.log('Something went wrong', error);
              setDataAdded("Invalid Email");
          })
         
    };
    
    return (
        <>
       {dataAdded && <h3 class="alert alartbox alert-primary" role="alert">{dataAdded}</h3>
       }
      <div className='login-wrapper'>
          <div className='login-app-wrapper'>
              <div>
                  <h2 className='title'>Share your registered email</h2>
                  <span className="myspan">You will get a mail regarding your password</span>
              </div>
              <br/>
              <form className='form-wrapper'>
                  <div className='email'>
                      <label className='label'>Email</label>
                      <input className='input' type='email' name='userEmail' onChange={(e) => setUserEmail(e.target.value)} required/>
                      
                  </div>
                  <div>
              <button className="submit" onClick={handleSubmit}>
                Share
              </button>
            </div>
                  <div>
                  <Link to="/api/psd/user">Login</Link>
                  </div>
              </form>
          </div>
      </div>
      </>
    )
}

export default ForgetPassword