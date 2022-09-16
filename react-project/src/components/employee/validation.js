const validation = (values) => {
    
    let errors = {};

    if(!values.firstName){
        errors.firstName = "FirstName is Required."
    }

    if(!values.lastName){
        errors.lastName = "LastName is Required."
    }

    if(!values.contactNumber){
        errors.contactNumber = "Contact Number is Required."
    }

    if(!values.empEmail){
        errors.empEmail = "Email is Required."
    }else if(!/\S+@\S+\.\S+/.test(values.empEmail)){
        errors.empEmail = "Email is invalid."
    }

    if(!values.password){
        errors.password = "Password is Required."
    }else if(values.password.length < 5){
        errors.password = "Password must be more then 5 charecters."
    }

    if(!values.plotNumber){
        errors.plotNumber = "Plot No. is Required."
    }

    if(!values.landmark){
        errors.landmark = "Landmark is Required."
    }

    if(!values.city){
        errors.city = "City is Required."
    }

    if(!values.state){
        errors.state = "State is Required."
    }

    if(!values.pincode){
        errors.pincode = "Pincode is Required."
    }
    if(!values.experience){
        errors.experience = "Experience is Required."
    }
    if(!values.professionName){
        errors.professionName = "Profession Name is Required."
    }
  return errors;
}
export default validation;