const validation = (values) => {
    
    let errors = {};

    if(!values.userEmail){
        errors.userEmail = "Email is Required."
    }else if(!/\S+@\S+\.\S+/.test(values.userEmail)){
        errors.userEmail = "Email is invalid."
    }

    if(!values.password){
        errors.password = "Password is Required."
    }else if(values.password.length < 5){
        errors.password = "Password must be more then 5 charecters."
    }
  return errors;
}
export default validation;