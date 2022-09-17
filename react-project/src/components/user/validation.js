const validation = (values) => {
    
    let errors = {};

    if(!values.professionName){
        errors.professionName = "Profession Name is Required."
    }else if(values.professionName.length > 30){
        errors.professionName = "profession Name must be less then 30 charecters."
    }else if(values.professionName.length <= 3){
        errors.professionName = "profession Name must be more then 3 charecters."
    }

    if(!values.basicCharge){
        errors.basicCharge = "Basic Charge is Required."
    }else if(values.basicCharge < 100){
        errors.basicCharge = "basicCharge can not be less then 100."
    }else if(values.basicCharge > 5000){
        errors.basicCharge = "basicCharge can not be more then 500."
    }
    
  return errors;
}
export default validation;