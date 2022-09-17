import React,{useEffect,useState} from 'react'
import customerService from '../services/customer.service';
import { useNavigate } from "react-router-dom";


const FeedbackForm = () => {
  const userObj=localStorage.getItem('user');
  const user=JSON.parse(userObj);
  const customerName=user.firstName+" "+user.lastName;

  const [dataAdded, setDataAdded] = useState("");

  const orderId=localStorage.getItem('orderId');
  const[rating,setRating] = useState('');
  const[feedback, setFeedback] = useState('');
  const[output,setOutput] = useState({});
  const navigate=useNavigate();

  const sleep=(milliseconds)=>{
    return new Promise(resolve=>setTimeout(resolve,milliseconds))
  }

      const saveFeedback = (e) => {
        e.preventDefault();
        const data = {orderId,rating, feedback,customerName};
        console.log('sending ', data);
        customerService.giveFeedback(data)
            .then(response => {
                console.log('Printing user data', response.data);
                setOutput(response.data);
                setDataAdded("Feedback Saved Successfully");
                sleep(3000).then(r=>{
                  navigate('/api/psd/user/Profile');
                  });
            })
            .catch(error => {
                console.log('Something went wrong', error);
                setDataAdded("Opps! we are facing some issue right now");
              }) 
              console.log(output);
              //dispatch(updateFailure())
    }

  return (
    <>
    {dataAdded && (
      <h3 class="alert alartbox alert-primary" role="alert">
        {dataAdded}
      </h3>
    )}
    <br/>
     <div className='login-wrapper'>
          <div className='login-app-wrapper'>
              <div>
                  <h2 className='title'>FeedBack Form</h2>
              </div>
              <br/>
              <form className='form-wrapper'>
              <div className='email'>
                      <label className='label'>Customer Name</label>
              <div className="form-group">
              <input className="form-control" type="text"  id="customerName" name="customerName"  value={customerName} readOnly
                    ></input>
   </div>
   </div>
                  <div className='email'>
                      <label className='label'>Rating</label>
                      <input list="rating"  id="ratings"  className="form-control col-4"
                   value={rating} onChange={(e) => setRating(e.target.value)} 
                   placeholder="Rating"  required/>

                      <datalist id="rating">
                      <option value="1 Star"/>
                       <option value="2 Star"/>
                       <option value="3 Star"/>
                       <option value="4 Star"/>
                       <option value="5 Star"/>
                       </datalist>
      
    </div>
    <div className="form-group">
      <textarea className="form-control" placeholder="What's your Feedback" id="message" name="Message"  value={feedback}
                    onChange={(e) => setFeedback(e.target.value)}></textarea>
   </div>
   <br/>
    <div >
          <button onClick={(e) => saveFeedback(e)} className="btn btn-primary">Send</button>
    </div>
    </form>
</div>
   </div> 
   </>
  )
}

export default FeedbackForm
