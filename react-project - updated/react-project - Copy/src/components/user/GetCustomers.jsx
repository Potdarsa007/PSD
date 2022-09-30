import { useEffect, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import adminService from '../../services/Admin.service';
import customerService from '../../services/customer.service';

const VerifiedList = () => {
    const [customers, setCustomers] = useState([]);
    const [email,setEmail]=useState("");

    const init = () =>{
        adminService. getAllCustomers()
        .then(response => {
            console.log('Printing all customers data', response.data);
            setCustomers(response.data);
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
  setCustomers(customers.filter(customer=>customer.userEmail.includes( email)));
  else
  init();
}

const handleDelete = (id) => {
    console.log('Printing id', id);
    customerService.deleteCustomer(id)
      .then(response => {
        console.log('customer deleted successfully', response.data);
        init();
      })
      .catch(error => {
        console.log('Something went wrong', error);
      })
  }
return(
    <div className="container">
    <h3>List of All Customers</h3>
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
              <th>City</th>
            </tr>
          </thead>
          <tbody>
          {
            customers.map(customer => (
              <tr key={customer.id}>
                <td>{customer.firstName}</td>
                <td>{customer.lastName}</td>
                <td>{customer.userEmail}</td>
                <td>{customer.contactNumber}</td>
                <td>{customer.address.city}</td>
                <td><button className="btn btn-danger" onClick={() => {
                    handleDelete(customer.id);
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
