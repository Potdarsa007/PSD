import React from 'react'
import UpdateForm from './UpdateForm';

const Register = () => {
// const [formIsSubmitted, setFormIsSubmitted] = useState(true);

// const submitForm = () =>{
//   setFormIsSubmitted(true);
// };
  return (
    <div>        
      {/* {!formIsSubmitted ? (<RegisterForm />):(<RegistrationSuccess/>)} */}
        <UpdateForm />
    </div>
  )
}

export default Register