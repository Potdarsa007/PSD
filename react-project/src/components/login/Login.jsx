import React from 'react'
import { useState } from 'react'
import LoginForm from './LoginForm'

const Login = () => {
    const [formIsSubmitted, setFormIsSubmitted] = useState(false);

    const submitForm = () =>{
      setFormIsSubmitted(true);
    };
  
    return (
      <div>
        {(!formIsSubmitted)?
          <LoginForm submitForm={submitForm}/>:""
        }
      </div>
    )
}

export default Login