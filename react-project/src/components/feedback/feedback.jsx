import React, {useState,useEffect } from "react";
// Import Swiper React components
import { Swiper, SwiperSlide } from "swiper/react";
import adminService from '../../services/Admin.service';

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

import "./mystyle.css";

// import required modules
import { Autoplay, Pagination, Navigation } from "swiper";

export default function Feedback() {
 
  const [myfeedbacks, setMyfeedbacks] = useState([]);

  const init =() =>{
   adminService.getAllFeedbacks()
    .then(response => {
        console.log('Printing Feedbacks', response.data);
        setMyfeedbacks(response.data);
        console.log(myfeedbacks);
      })
      .catch(error => {
        console.log('Something went wrong', error);
      }) 
  }

useEffect(() =>{
init()
},[]);

  return (
    <>
    <br/>
     {myfeedbacks.length>0?<><h1 className="myfeedback">Feedbacks from precious customers</h1>
      <Swiper
        slidesPerView={2}
        spaceBetween={10}
        centeredSlides={true}
        autoplay={{
          delay: 2500,
          disableOnInteraction: false
        }}
        pagination={{
          clickable: true
        }}
        navigation={true}
        modules={[Autoplay, Pagination, Navigation]}
        className="mySwiper">
       { myfeedbacks.map((feedback)=>(
       <SwiperSlide key={feedback.feedback}>
        <div className='mycard'>
        <div className='mycard-body text-dark'>
           <h4 className='mycard-title'>{feedback.customerName}</h4>
           <h4 className="mycard-rating">Rating: {feedback.rating}</h4>
           <p className="mycard-text">
           {feedback.feedback}
           </p>
        </div>
      </div>
        </SwiperSlide>
  ))}       
      </Swiper></>:""}
      <br/>
    </>
  );
}
