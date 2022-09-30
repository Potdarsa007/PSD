import React, { useState,useEffect, useRef } from "react";
import customer from "../services/customer.service";
import { useNavigate } from "react-router-dom";
import './bookservice.css';
import userService from '../services/user.service'

const BookServices = () => {
  const userObj=localStorage.getItem('user');
  const user=JSON.parse(userObj);

  const current=new Date();
  const date=current.getFullYear() + '-' + (current.getMonth()+1) + '-'+current.getDate();

  const [userEmail, setUserEmail] = useState(user.userEmail);
  const [professionName, setProfessionName] = useState("");
  const [dataAdded, setDataAdded] = useState("");
  const[basiccharge,setBasiccharge]=useState("");
  const [errors, setErrors] = useState({});
  
  
  //const user = useSelector((state) => state.user)
  //const dispatch = useDispatch();

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
useEffect(() =>{
  init();
},[]);


useEffect(()=>
{
  console.log(professionName);
  setBasiccharge(professions.filter(prof=>prof.professionName==professionName).map(prof=>prof.basicCharge));
},[professionName]);

  const navigate = useNavigate();

  const saveUser = (e) => {
    e.preventDefault();
    const data = { userEmail, professionName };
    console.log("sending ", data);
    customer
      .bookService(data)
      .then((response) => {
        console.log("Printing user data", response.data);
        setDataAdded("Service Booked Successfully");
      })
      .catch((error) => {
        console.log("Something went wrong", error);
        setDataAdded(" Error while booking Service");
      });
  };
  return (
    <>
    <br/>
      {dataAdded && (
        <h3 class="alert alartbox alert-primary" role="alert">
          {dataAdded}
        </h3>
      )}
      <br/><br/><br/>
      <div className="box-wrapper">
        <div className="card-wrapper">
          <div>
            
            <h2 className="title">Book Service</h2>
          </div>
          <span className="myspan">
            {" "}
            As a leading online service provider in India,
            <br /> we are bound to provide happiness for every client.
            <br /> We make sure that you will be 100% satisfied
            <br /> with any favor you get from us.
          </span>
          
          <form className="form-wrapper">
            <div className="email">
            <br/>
              <label className="label">Email</label>
              <input
                className="input"
                type="text"
                name="userEmail"
                value={userEmail}
                onChange={(e) => setUserEmail(e.target.value)}
              />
              <label className="label">Booking Date</label>
               <input
                className="input"
                type="text"
                name="serviceDate"
                value={date}
                readOnly
              />
              
              {errors.professionName && (
                <p className="error">{errors.professionName}</p>
              )}
            </div>
            <div className="col">
                <div className="name">
                  <label className="label">Profession Name</label>
                  <div className="col">
                  <select onChange={(e)=>setProfessionName(e.target.value)} >
                  <option >Choose...</option>
                 {professions.map((profession) => ( <option key={profession.professionName} value={profession.professionName}>{profession.professionName}</option>
                ))}
                       </select>
                       </div>
                       </div>
                       {errors.basicCharge && (
                <p className="error">{errors.basicCharge}</p>
              )}
                       </div>
            <br/>
            <label className="label">Service Basic Charge</label>
               <input
                className="input"
                type="text"
                name="basicCharge"
                value={basiccharge}
                readOnly
              />
            <div>
              <button className="button" onClick={saveUser}>
                Book Now
              </button>
            </div>
          </form>
        </div>
      </div>
      <br/>
      <br/>
    </>
  );
};

export default BookServices;
