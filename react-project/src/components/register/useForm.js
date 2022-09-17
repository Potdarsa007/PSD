import {useState,useEffect} from "react";
import validation from "./validation";

const useForm = (submitForm) => {
 
const [errors, setErrors] = useState({});
const [dataIsCorrect, setDataIsCorrect] = useState(false);

const handleFormSubmit = (event) =>{
    event.preventDefault();
    setErrors(validation(values));
    setDataIsCorrect(true);
};

useEffect(() => {
    if(Object.keys(errors).length === 0 && dataIsCorrect){
        submitForm(true);
    }
}, [dataIsCorrect, errors, submitForm]);

return {handleFormSubmit,errors};
}
export default useForm

