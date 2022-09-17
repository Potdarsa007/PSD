import React, { useState,useEffect } from 'react'
import './addprofessionform.css';
import validation from './validation';
import adminService from '../../services/Admin.service';
import { useNavigate } from 'react-router-dom';

const AddProfession = () => {
    const navigate=useNavigate();

    const sleep=(milliseconds)=>{
        return new Promise(resolve=>setTimeout(resolve,milliseconds))
      }

    const [profession,setProfession] = useState({
        professionName:"",
        basicCharge:""
    });
const [errors, setErrors] = useState({});
const [dataIsCorrect, setDataIsCorrect] = useState(false);
const [dataAdded, setDataAdded] = useState('');

const handleChange = (event) =>{
    setProfession({
        ...profession,
        [event.target.name]: event.target.value,
    });
}

const handleFormSubmit = (event) =>{
    event.preventDefault();
    setErrors(validation(profession));
    // if(Object.keys(errors).length === 0){
    //     setDataIsCorrect(true);
    // }
    console.log("sending", profession);
    if(dataIsCorrect === true){
        adminService.addProfession(profession)
        .then(response => {
            console.log('Printing response data', response.data);
            setDataAdded("Profession Added Successfully");
            sleep(3000).then(r=>{
                navigate('/api/psd/user/Profile');
            });
            //window.location.reload();
        })
        .catch(error => {
            console.log('Something went wrong', error);
            setDataAdded("Error While Adding Profession");
        }); 
    };
}
useEffect(() =>{
    if(Object.keys(errors).length === 0 & !dataIsCorrect){
        setDataIsCorrect(true);
    }
}, [dataIsCorrect, errors]);
  return (
      <>
    {dataAdded && <h3 class="alert alartbox alert-primary" role="alert">{dataAdded}</h3>}
      <div className='box-wrapper'>
          <div className='card-wrapper'>
              <div>
                  <h2 className='title'>Add Profession</h2>
              </div>
              <form className='form-wrapper'>
                  <div className='email'>
                      <label className='label'>Profession Name</label>
                      <input className='input' type='text' name='professionName' value={profession.professionName} onChange={handleChange}/>
                      {errors.professionName && <p className='error'>{errors.professionName}</p>}
                  </div>
                  <div className='name'>
                      <label className='label'>Basic Charge</label>
                      <input className='input' type='number' min='100' max='5000' name='basicCharge' value={profession.basicCharge} onChange={handleChange}/>
                      {errors.basicCharge && <p className='error'>{errors.basicCharge}</p>}
                  </div>
                  <div>
                      <button className='button' onClick={handleFormSubmit}>ADD</button>
                  </div>
              </form>
          </div>
      </div>
      </>
  )
}

export default AddProfession