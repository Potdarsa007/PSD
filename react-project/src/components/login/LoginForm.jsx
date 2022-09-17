import React from 'react'
import useForm from './useForm';
import { Link } from 'react-router-dom';
import './style.css'

const LoginForm = ({submitForm}) => {
    const {handleChange,handleFormSubmit,values,errors,dataAdded} = useForm(submitForm);
    
    return (
        <>
       {dataAdded && <h3 class="alert alartbox alert-primary" role="alert">{dataAdded}</h3>}
      <div className='login-wrapper'>
          <div className='login-app-wrapper'>
              <div>
                  <h2 className='title'>Log in</h2>
              </div>
              <form className='form-wrapper'>
                  <div className='email'>
                      <label className='label'>Email</label>
                      <input className='input' type='text' name='userEmail' value={values.userEmail} onChange={handleChange}/>
                      {errors.userEmail && <p className='error'>{errors.userEmail}</p>}
                  </div>
                  <div className='name'>
                      <label className='label'>Password</label>
                      <input className='input' type='password' name='password' value={values.password} onChange={handleChange}/>
                      {errors.password && <p className='error'>{errors.password}</p>}
                  </div>
                  <div>
                      <button className='login-button' onClick={handleFormSubmit}>Log in</button>
                  </div>
                  <div className="form-group">
                      <Link to='/api/psd/forgetpassword'>forget password?</Link>
                  </div>
                  <div className="form-group">
                      New User? Register here <Link to='/api/psd/register'>Register Now</Link>
                  </div>
                  
                  <div>
                  <Link to="/">Home</Link>
                  </div>
              </form>
          </div>
      </div>
      </>
    )
}

export default LoginForm