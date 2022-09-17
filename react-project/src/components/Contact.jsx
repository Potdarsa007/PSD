import React, { useState,useEffect, useRef } from "react";
import userService from '../services/user.service'
import { useNavigate } from "react-router-dom";
import './Contact.css'

const Contact = () => {
  const navigate=useNavigate();

  const [dataAdded, setDataAdded] = useState("");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [issue, setIssue] = useState("");

  const sleep=(milliseconds)=>{
    return new Promise(resolve=>setTimeout(resolve,milliseconds))
  }

  const saveData = (e) => {
    e.preventDefault();
    const data = { name,email, issue };
    console.log("sending ", data);
    userService
      .ContactUsDetails(data)
      .then((response) => {
        console.log("Printing response", response.data);
        setDataAdded("Query Saved Successfully");
        sleep(3000).then(r=>{
          navigate('/');
          });
      })
      .catch((error) => {
        console.log("Something went wrong", error);
        setDataAdded(" Oops... We are facing some issue right now");
      });
  };

  return (
    <div>
         {dataAdded && (
        <h3 class="alert alartbox alert-primary" role="alert">
          {dataAdded}
        </h3>
      )}
      <br/>
      <section className='py-4'>
        <div className='container-fluid'>
          <div className='row'>
              <h4>Contact Us</h4>
          </div>
        </div>
      </section>
      <div className='contactUs-wrapper'>
      <div className="form-box">
    <div className="name">
    <label className='label'>Enter Name</label>
      <input className="input" id="name" type="text" name="Name" value={name}
                onChange={(e) => setName(e.target.value)}/>
    </div>
    <div className="name">
    <label className='label'>Enter Email</label>
      <input className="input" id="email" type="text" name="email" value={email}
                onChange={(e) => setEmail(e.target.value)}/>
    </div>
    <div className="name">
    <label className='label'>Enter Message</label>
      <input className="input" id="message" type="text" name="message" value={issue}
                onChange={(e) => setIssue(e.target.value)}/>
    </div>
    <div>
              <button className="button" onClick={saveData}>
                Send
              </button>
            </div>
    </div>
    </div>
</div>
     
  )
}

export default Contact
