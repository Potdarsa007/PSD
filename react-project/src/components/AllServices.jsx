import { useEffect, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import userService from '../services/user.service';
import employeeService from '../services/employee.service';
import "./AllServices.css";

const AllServices = () => {
    const [services, setServices] = useState([]);

    const init = () =>{
        userService.getAllProfessions()
        .then(response => {
            console.log('Printing all professions', response.data);
            setServices(response.data);
          })
          .catch(error => {
            console.log('Something went wrong', error);
          }) 
      }
useEffect(() =>{
    init();
},[]);

/*useEffect(()=>{
  setVerifiedemp(verifiedemp.filter(emp=>emp.empEmail==email));
},[email])*/

return(
    <div className="container">
        <br/>
        <Link to='/addprofession' className="mylink">Add Service</Link>
        <br/><br/>
    <h3>List of Services</h3>
    <hr/>
    <div>
      <br/>
    <table className="table table-bordered table-striped">
          <thead className="thead-dark">
            <tr>
              <th>Service Name</th>
              <th>Basic Charge</th>
            </tr>
          </thead>
          <tbody>
          {
            services.map(service => (
              <tr key={service.id}>
                <td>{service.professionName}</td>
                <td>{service.basicCharge}</td>
              </tr>
            ))
          }
          </tbody>
        </table>
        
    </div>

    </div>
)
        }
export default AllServices;
