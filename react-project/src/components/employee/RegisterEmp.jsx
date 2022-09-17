import React, { useState,useEffect } from "react";
import validation from "./validation";
import empService from '../../services/employee.service'
import userService from '../../services/user.service'
import "./style.css";
import { useNavigate } from "react-router-dom";

const RegisterEmp = () => {
    const navigate=useNavigate();
    
    const sleep=(milliseconds)=>{
      return new Promise(resolve=>setTimeout(resolve,milliseconds))
    }

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [contactNumber, setContactNumber] = useState("");
    const [empEmail, setempEmail] = useState("");
    //const [password, setPassword] = useState("");
    const [plotNumber, setPlotNumber] = useState("");
    const [landmark, setLandmark] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [pincode, setPincode] = useState("");
    const [experience,setExperience] = useState();
    const [professionName, setProfessionName] = useState("");

    const [errors, setErrors] = useState({});
    const [dataIsCorrect, setDataIsCorrect] = useState(false);
    const [dataAdded, setDataAdded] = useState('');

    const emp = {firstName,lastName,empEmail,/*password,*/contactNumber,experience,plotNumber, landmark, city, state, pincode,professionName};
  
    const [professions, setProfessions] = useState([]);
    const init = () =>{
      userService.getAllProfessions()
      .then(response => {
          console.log('Printing professionss', response.data);
          setProfessions(response.data);
        })
        .catch(error => {
          console.log('Something went wrong', error);
        }) 
    }

    const handleFormSubmit = (event) => {
      event.preventDefault();
      setErrors(validation(emp));
      // if(Object.keys(errors).length === 0){
      //   setDataIsCorrect(true);
      // }
      console.log("sending",emp);
      if( dataIsCorrect === true){
        empService.addNewEmployee(emp)
        .then(response => {
            console.log("Employee added successfully", response.data);
            setDataAdded("Registration Successful");
            sleep(3000).then(r=>{
            navigate("/");});
        })
        .catch(error => {
            console.log('Something went wrong', error);
            setDataAdded("Error While Registration");
        })
      } 
  };
  useEffect(() => {
    init();
    if(Object.keys(errors).length === 0 & !dataIsCorrect){
      setDataIsCorrect(true);
  }
  }, [dataIsCorrect, errors]);
  
    return (
      <>
       {dataAdded && <h3 class="alert alartbox alert-primary" role="alert">{dataAdded}</h3>}
      
      <div className="wrapper">
        <div className="app-wrapper">
          <div>
            <h2 className="title">Share Your Details</h2>
            <span className="myspan">We will get back to you soon</span>
          </div>
          <br/>
          <form className="form-wrapper">
            <div className="row">
              <div className="col">
                <div className="name">
                  <label className="label">First Name</label>
                  <input
                    className="input"
                    type="text"
                    name="firstName"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                  />
                  {errors.firstName &&  <p className="error">{errors.firstName}</p>}
                </div>
              </div>
              <div className="col">
                <div className="name">
                  <label className="label">Last Name</label>
                  <input
                    className="input"
                    type="text"
                    name="lastName"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                  />
                  {errors.lastName && <p className="error">{errors.lastName}</p>}
                </div>
              </div>
            </div>
            <div className="row">
              <div className="col">
                <div className="name">
                  <label className="label">Contact Number</label>
                  <input
                    className="input"
                    type="number"
                    min='7000000000'
                    max='9999999999'
                    name="contactNumber"
                    value={contactNumber}
                    onChange={(e) => setContactNumber(e.target.value)}
                  />
                  {errors.contactNumber && (
                    <p className="error">{errors.contactNumber}</p>
                  )}
                </div>
              </div>
              <div className="col">
                <div className="name">
                  <label className="label">Email</label>
                  <input
                    className="input"
                    type="text"
                    name="empEmail"
                    value={empEmail}
                    onChange={(e) => setempEmail(e.target.value)}
                  />
                  {errors.empEmail && <p className="error">{errors.empEmail}</p>}
                </div>
              </div>
            </div>
            <div className="row">
              <div className="col">
                <div className="name">
                  <label className="label">Plot No.</label>
                  <input
                    className="input"
                    type="text"
                    name="plotNumber"
                    value={plotNumber}
                    onChange={(e) => setPlotNumber(e.target.value)}
                  />
                  {errors.plotNumber && <p className="error">{errors.plotNumber}</p>}
                </div>
              </div>
              <div className="col">
                <div className="name">
                  <label className="label">Landmark</label>
                  <input
                    className="input"
                    type="text"
                    name="landmark"
                    value={landmark}
                    onChange={(e) => setLandmark(e.target.value)}
                  />
                  {errors.landmark && <p className="error">{errors.landmark}</p>}
                </div>
              </div>
              </div>
              <div className="row">
              <div className="col">
                <div className="name">
                  <label className="label">City</label>
                  <input
                    className="input"
                    type="text"
                    name="city"
                    value={city}
                    onChange={(e) => setCity(e.target.value)}
                  />
                  {errors.city && <p className="error">{errors.city}</p>}
                </div>
              </div>
              <div className="col">
                <div className="name">
                  <label className="label">State</label>
                  <input
                    className="input"
                    type="text"
                    name="state"
                    value={state}
                    onChange={(e) => setState(e.target.value)}
                  />
                  {errors.state && <p className="error">{errors.state}</p>}
                </div>
              </div>
              </div>
              <div className="row">
              <div className="col">
                <div className="name">
                  <label className="label">Pincode</label>
                  <input
                    className="input"
                    type="text"
                    name="pincode"
                    value={pincode}
                    onChange={(e) => setPincode(e.target.value)}
                  />
                  {errors.pincode && <p className="error">{errors.pincode}</p>}
                </div>
              </div>
              <div className="col">
                <div className="name">
                  <label className="label">Experience</label>
                  <input
                    className="input"
                    type="number"
                    min='0'
                    max='50'
                    name="experience"
                    value={experience}
                    onChange={(e) => setExperience(e.target.value)}
                  />
                  {errors.experience && <p className="error">{errors.experience}</p>}
                </div>
              </div>
              </div>
              <div className="row">
              <div className="col-6">
                <div className="name">
                  <label className="label">Profession Name</label>
                  <input
                    className="input"
                    list="service"
                    name="professionName"
                    value={professionName}
                    onChange={(e) => setProfessionName(e.target.value)}
                    
                  />
                  <datalist id="service">
                  <option selected>Choose...</option>
                 {professions.map(profession => ( <option key={profession.professionName} value={profession.professionName}>{profession.professionName}</option>
                ))}
                       </datalist>
                  {errors.professionName && <p className="error">{errors.professionName}</p>}
                </div>
              </div>
            </div>
            <div>
              <button className="submit" onClick={handleFormSubmit}>
                Register
              </button>
            </div>
          </form>
        </div>
      </div>
      </>
    );
}

export default RegisterEmp