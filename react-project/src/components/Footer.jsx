import React from 'react'
import "../App.css";
import logo from "../images/logo.png";
const Footer = () => {
  return (
    <div>
        
		<footer>
			
			<div className="footer-top">
				<div className='container'>
					<div className='row'>
					
						<h2><img
                  src={logo}
                  className="d-inline-block align-top logo"
                  alt="psd logo"
                /> Professional Services at Doorstep</h2>
						<div className="col-md-4">
							<h5 className="title-sm">Navigation</h5>
							<div className="footer-links">
								<a href="/">Services</a>
								<a href="/Contact">Contact Us</a>
								<a href="/About">About Us</a>
								<a href="/">Blog</a>
							</div>
						</div>
						<div className="col-md-4">
							<h5 className="title-sm">More</h5>
							<div className="footer-links">
								<a href="/">FAQ</a>
								<a href="/"> Privacy & Policy</a>
								<a href="/">Licences</a>
							</div>
						</div>
						<div className="col-md-4">
							<h5 className="title-sm">Contact</h5>
							<div className="footer-links">
								<p className="mb">25 Diamond Tower, Bhosale Street, Pune, 411001</p>
								<p className='mb'>+91-8446691693</p>
								<p className='mb'>teampsd@gmail.com</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
    </div>
  )
}

export default Footer