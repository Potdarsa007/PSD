import { useEffect, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import adminService from '../services/Admin.service';
import employeeService from '../services/employee.service';

const VerifiedList = () => {
    const [verifiedemp, setVerifiedemp] = useState([]);
    const [email,setEmail]=useState("");

    const init = () =>{
        adminService.getAllVerifiedEmps()
        .then(response => {
            console.log('Printing verified employees data', response.data);
            setVerifiedemp(response.data);
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
const handleSearch=(e)=>
{
  if(email!="")
  setVerifiedemp(verifiedemp.filter(emp=>emp.empEmail.includes(email)));
  else
  init();
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
return(
    <div className="container">
    <h3>List of Verified Employees</h3>
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
            verifiedemp.map(employee => (
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
export default VerifiedList;
