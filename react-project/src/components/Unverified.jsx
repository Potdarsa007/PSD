import { useEffect, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import adminService from '../services/Admin.service';
import employeeService from '../services/employee.service';

const UnverifiedList = () => {

    const [unverifiedemp, setUnverifiedemp] = useState([]);
    const [email,setEmail]=useState("");

    const init = () =>{
        adminService.getAllUnverifiedEmps()
        .then(response => {
            console.log('Printing Unverified employees data', response.data);
            setUnverifiedemp(response.data);
          })
          .catch(error => {
            console.log('Something went wrong', error);
          }) 
      }
    
useEffect(() =>{
    init();
},[]);

const handleSearch=(e)=>
{
  setUnverifiedemp(unverifiedemp.filter(emp=>emp.empEmail.includes(email)));
}

const handleDelete = (id) => {
    console.log('Printing id', id);
    employeeService.deleteEmployee(id)
      .then(response => {
        console.log('employee deleted successfully', response.data);
        init();
      })
      .catch(error => {
        console.log('Something went wrong', error);
      })
  }
  const handleVerified = (id) => {
    console.log('Printing id', id);
    adminService.employeeVerified(id)
      .then(response => {
        console.log('employee verified successfully', response.data);
        init();
      })
      .catch(error => {
        console.log('Something went wrong', error);
      })
  }
return(
    <div className="container">
    <h3>List of UnVerified Employees</h3>
    <hr/>
    <div>
      <form className='form-inline col-6'>
        <div className='input-group'>
            <input type="text" className='form-control' placeholder='Search By Email' onChange={(e)=>setEmail(e.target.value)}></input>
            <div className='input-group-append'>
              <button type="button" className='btn btn-secondary' onClick={handleSearch}>
                <i className='fa fa-search'></i>
              </button>
            </div>
        </div>
      </form>
    </div>
    <div>
      <br/>
    <table className="table table-bordered table-striped">
          <thead className="thead-dark">
          <tr>
              <th>Firstname</th>
              <th>lastname</th>
              <th>Email</th>
              <th>ContactNumber</th>
              <th>Experience</th>
              <th>Profession</th>
            </tr>
          </thead>
          <tbody>
          {
            unverifiedemp.map(employee => (
              <tr key={employee.id}>
                <td>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.empEmail}</td>
                <td>{employee.contactNumber}</td>
                <td>{employee.experience}</td>
                <td>{employee.profession.professionName}</td>
                <td><button className="btn btn-danger" onClick={() => {
                    handleDelete(employee.id);
                  }}>Delete</button>
                  <br/>
                  <br/>
                  <button className="btn btn-info" onClick={() => {
                    handleVerified(employee.id);
                  }}>Verified</button>
               </td>
              </tr>
            ))
          }
          </tbody>
        </table>
        
    </div>

    </div>
)
        }
export default UnverifiedList;
