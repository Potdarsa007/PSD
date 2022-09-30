import {useState,useEffect} from "react";
import validation from "./validation";
import userService from '../../services/user.service'
import { useNavigate } from 'react-router-dom';

const useForm = (submitForm) => {
const navigate=useNavigate();

const [values,setValues] = useState({
    userEmail:"",
    password:"",
});

const [errors, setErrors] = useState({});
const [dataIsCorrect, setDataIsCorrect] = useState(false);
const [dataAdded, setDataAdded] = useState('');

//const dispatch = useDispatch();

const handleChange = (event) =>{
    setValues({
        ...values,
        [event.target.name]: event.target.value,
    });
}
const handleFormSubmit = (event) =>{
    event.preventDefault();
    setErrors(validation(values));
    setDataIsCorrect(true);
    userService.login(values)
        .then(response => {
            console.log('Printing user data', response.data);
            //dispatch(updateSuccess(response.data))
            setDataAdded("Login Successful");
            localStorage.setItem('user',JSON.stringify(response.data));
            navigate('/api/psd/user/Profile');
            window.location.reload();

        })
        .catch(error => {
            console.log('Something went wrong', error);
            setDataAdded("Invalid email or password ");
          }) 
};

useEffect(() => {
    if(Object.keys(errors).length === 0 && dataIsCorrect){
        submitForm(true);
    }
}, [dataIsCorrect, errors, submitForm]);

return {handleChange,handleFormSubmit,errors,values,dataAdded};
}
export default useForm

