const validation = (values) => {
    
    let errors = {};

    if(!values.firstName){
        errors.firstName = "firstName is Required."
    }

    if(!values.lastName){
        errors.lastName = "lastName is Required."
    }

    if(!values.contactNumber){
        errors.contactNumber = "contact Number is Required."
    }

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

    if(!values.address.plotNumber){
        errors.plotNumber = "plot No. is Required."
    }

    if(!values.address.landmark){
        errors.landmark = "landmark is Required."
    }

    if(!values.address.city){
        errors.city = "city is Required."
    }

    if(!values.address.state){
        errors.state = "state is Required."
    }

    if(!values.address.pincode){
        errors.pincode = "pincode is Required."
    }
    
  return errors;
}
export default validation;