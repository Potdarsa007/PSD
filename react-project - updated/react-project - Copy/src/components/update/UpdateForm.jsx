import React, { useState,useEffect } from "react";
import validation from "./validation";
import userService from "../../services/user.service";
import { useNavigate } from 'react-router-dom';
import "./style.css";

const RegisterForm = () => {
  const navigate=useNavigate();

  const userobj=localStorage.getItem('user');
  const myuser=JSON.parse(userobj);
  const id=myuser.id;
  const [firstName, setFirstName] = useState(myuser.firstName);
  const [lastName, setLastName] = useState(myuser.lastName);
  const [contactNumber, setContactNumber] = useState(myuser.contactNumber);
  const [userEmail, setUserEmail] = useState(myuser.userEmail);
  const password = myuser.password;
  const [plotNumber, setPlotNumber] = useState(myuser.address.plotNumber);
  const [landmark, setLandmark] = useState(myuser.address.landmark);
  const [city, setCity] = useState(myuser.address.city);
  const [state, setState] = useState(myuser.address.state);
  const [pincode, setPincode] = useState(myuser.address.pincode);
  const [role, setRole] = useState(myuser.role);
    const [errors, setErrors] = useState({});
  const [dataIsCorrect, setDataIsCorrect] = useState(false);
  const [dataAdded, setDataAdded] = useState('');
  const address = { plotNumber, landmark, city, state, pincode };
  const user = {id,firstName,lastName,contactNumber,userEmail,role,address,password};

  const handleFormSubmit = (event) => {
    event.preventDefault();
    //setRole("CUSTOMER");
    setErrors(validation(user));
    // if(Object.keys(errors).length === 0){
    //   setDataIsCorrect(true);
    // }
    console.log("sending",user);
    if(dataIsCorrect === true){
      userService.updateUser(user)
      .then(response => {
        setDataAdded("Updation Done Successfully");
          localStorage.removeItem('user');
          localStorage.setItem('user',JSON.stringify(response.data));
      })
      .catch(error => {
        setDataAdded("Error While Updating Account");
      })
    } 
};
useEffect(() => {
  if(Object.keys(errors).length === 0 && !dataIsCorrect){
      setDataIsCorrect(true);
  }
}, [dataIsCorrect, errors]);
  
return (
  <>
  {dataAdded && <h3 class="alert alartbox alert-primary" role="alert">{dataAdded}</h3>}
    <div className="wrapper">
      <div className="app-wrapper">
        <div>
          <h2 className="title">Update Profile</h2>
        </div>
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
                  type="text"
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
              <div className="email">
                <label className="label">Email</label>
                <input
                  className="input"
                  type="text"
                  name="userEmail"
                  value={userEmail}
                  onChange={(e) => setUserEmail(e.target.value)}
                />
                {errors.userEmail && <p className="error">{errors.userEmail}</p>}
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
            <div className="col"></div>
          </div>
          <div className="row">
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
          </div>
          <div className="row">
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
          </div>
          <div>
            <button className="submit" onClick={handleFormSubmit}>
              Update
            </button>
          </div>
        </form>
      </div>
    </div>
    </>
  );
};

export default RegisterForm;
