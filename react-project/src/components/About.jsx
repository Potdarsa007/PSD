import React, { useRef, useState,useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Feedback from "../components/feedback/feedback.jsx";
import adminService from '../services/Admin.service';

const About = () => {

 

  return (
    <div>
        <section className='py-4 '>
        <div className='container-fluid'>
          <div className='row'>
              <h4>About Us</h4>
          </div>
        </div>
      </section>
      <br/>
      <section className='section'>
        <div className='container'>
          <div className="shadow p-3 mb-5 bg-white rounded"> 
               
                  <div className='col-12 text center'>
                    <h2 className='text-muted'>What We Do</h2>
                    <br/>
                   <p className="text-justify">"PSD - Professional Services at Doorstep" is Best Online Home Service Provider In India.
                  We provides a platform that allows skilled and experienced professionals to connect with users looking for specific services.
                  The platform helps customers book reliable and high quality services like Ac repair, Refrigerator Repair, House Cleaning,
                  Painting, Pest Control, Washing Machine Repair and many more deliver by trained professional conveniently at customer's home.
                  We provides professionals who are closest to user's requirement and available at the requested time and date.</p>
              
              </div>
            </div>
          </div>
      </section>
      <section className='section bg-light'>
        <div className='container'>
         <div className='row'>
          <div className='col-md-12 mb-5 text center'>
            <h3 className='text-muted'>Vision, Mission, Values</h3>
          </div>
          <div className='col-md-4 text center'>
          <div className='shadow-lg p-3 mb-5 bg-white rounded'>
            <h5>Our Vision</h5>
            <p>
            Our Vision is to provide practical solution to an age-old problem of finding top-rated, effective professionals for common household services
            </p>
          </div>
          </div>

          <div className='col-md-4 text center'>
          <div className='shadow-lg p-3 mb-5 bg-white rounded'>
            <h5>Our Mission</h5>
            <p>
            Our Mission is to build the easiest, most convenient way for people everywhere to book household services and aslo empower 
            millions of service professionals
            </p>
          </div>
          </div>

          <div className='col-md-4 text center'>
          <div className='shadow-lg p-3 mb-5 bg-white rounded'>
            <h5>Our Values</h5>
            <p>
            Quality<br/>
            Commitment<br/>
            Integrity<br/>
            People<br/>
            </p>
          </div>
          </div>

         </div>  
        </div>
      </section>

      <div>
        <p>You could be part of this excellent journey. Interested?</p>
        <a class="btn btn-primary" href="/api/psd/register" role="button">Join Now</a>
      </div>
      <br/>
       <Feedback/>
    </div>
  )
}

export default About