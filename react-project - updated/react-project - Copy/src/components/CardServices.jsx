import React, { useEffect, useState } from "react";
import './CardServices.css'

const CardServices = (props)=>{

  const [userobj, setUserobj] = useState(false);

  useEffect(() => {
    const loggedIn = localStorage.getItem("user");
    if (loggedIn) {
      setUserobj(true);
    }
  }, []);

  const logout = () => {
    localStorage.removeItem("user");
    setUserobj(false);
  };
    return (
        <div className='card text-center shadow'>
        <div className='overflow'>
          <img src={props.imgsrc} alt="image1" className='card-img-top'/>
        </div>
        <div className='card-body text-dark'>
           <h4 className='card-title'>{props.title}</h4>
           <p className="card-text text-secondary">
           {props.content}
           </p>
          {userobj? <a href="/bookservice" className='btn btn-outline-primary'>Book Service</a>:<a href="/api/psd/user" className='btn btn-outline-primary'>Book Service</a>}
        </div>
      </div>
    )
}

export default CardServices;