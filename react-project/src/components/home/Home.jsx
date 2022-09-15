import React from 'react'
import ac from '../../images/ac.jpg'
import ref from '../../images/ref.jpg'
import painting from '../../images/paint.jpg'
import housekeeping from '../../images/housekeeping.jpg'
import PestControl from '../../images/pest.jpg'
import WashingMachineRepair from '../../images/washing.jpg'
import CardServices from '../CardServices';
import './Home.css'


const Home = () => {
  return (
    <div>
      <div>
        <section id="home">
          <div className="container text-center">
            <div className="row justify-content-center">
              <div className='col-md-10'>
              <h1 className="text-white display-4">Best Online Home Service Providers In India</h1>
              <p className="text-white"> Experienced, hand-picked Professionals to serve you at your doorstep</p>
              <a href="/about" className="btn btn-brand">Know More</a>
              </div></div>
          </div>
          </section>
          <div>
            <br></br>
            <h1>Our Services</h1>
            <span>Get Professional Online Services</span>
          </div>
        </div>
      <div className='container d-flex justify-content-center'>
        <div className='row'>
          <div className='col-md-4'><CardServices imgsrc={ac} title="AC Repair" content="Are you looking for a technician for repairing your AC? If yes then you don’t need to go around and find the perfect technician for repairing the AC. Just Book your service with 'PSD'"/></div>
          <div className='col-md-4'><CardServices imgsrc={ref} title="Refrigerator Repair" content="It is essential to check on the cooling of your refrigerators as it keeps your food fresh and keeps your family healthy. So if you are looking out for the technicians to help you with repairing your refrigerator, then you are at the perfect place. Book your service with 'PSD'"/></div>
          <div className='col-md-4'><CardServices imgsrc={painting} title="Painting" content="Looking for the best painting service for your next wall painting project? Book your service with 'PSD'"/></div>
        </div>
        
      </div>
      <div className='container d-flex justify-content-center'>
        <div className='row'>
          <div className='col-md-4'><CardServices imgsrc={housekeeping} title="House Keeping" content="Your search for finding the best house keepers in the town ends here. We know how difficult it is to find the right housekeepers to do all the essential housekeeping work in your house. 'PSD' believes in providing with the best service to all our customers"/></div>
          <div className='col-md-4'><CardServices imgsrc={WashingMachineRepair} title="Washing Machine Repair" content="Are you looking for a technician for repairing your washing machine? Well, if yes then we have good news for you. We at 'PSD' help you to book a technician with expertise in servicing the washing machines on just a click"/></div>
          <div className='col-md-4'><CardServices imgsrc={PestControl} title="Pest Control" content="Protect your living and working area with the help of our pest control experts.Book your service with 'PSD' now"/></div>
        </div>
      </div>
      <div>
        <br></br>
      </div>
    </div>
  )
}

export default Home