import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from "react-router-dom";
import { Navbar,Nav,Container } from "react-bootstrap";
import customerService from '../services/customer.service';
import employeeService from '../services/employee.service';


const MyOrders = () => {
 // const id = useSelector((state) => state.user.user.id)
 const navigate=useNavigate();
 const user=localStorage.getItem('user');
 const userobj=JSON.parse(user);
 const id=userobj.id;

    const [orderlist, setOrderlist] = useState([]);
    const init = () =>{
        customerService.getAllOrders(id)
        .then(response => {
            console.log('Printing verified employees data', response.data);
            setOrderlist(response.data);
          })
          .catch(error => {
            console.log('Something went wrong', error);
          }) 
      }
useEffect(() =>{
    init();
},[]);

const handleCancel = (id) => {
  console.log('Printing id', id);
  customerService.cancelService(id)
    .then(response => {
      console.log('employee deleted successfully', response.data);
      init();
    })
    .catch(error => {
      console.log('Something went wrong', error);
    })
}

const handlePayment = (id) => {
  console.log('Printing id', id);
  customerService.generatedBill(id)
    .then(response => {
      console.log('generated bill successfully', response.data);
      init();
    })
    .catch(error => {
      console.log('Something went wrong', error);
    })
}

const giveFeedback = (id) => {
  console.log('Printing id', id);
  localStorage.setItem('orderId',id);
  navigate("/feedback");
}
return(
    <div className="container">
    <h3>My Orders List</h3>
    <hr/>
    <div>
    <table className="table table-bordered table-striped">
          <thead className="thead-dark">
            <tr>
              <th>OrderID</th>
              <th>Amount</th>
              <th>Date</th>
              <th>Service</th>
              <th>Status</th>
              <th>Emp Assist</th>
            </tr>
          </thead>
          <tbody>
          {
            orderlist.map(order => (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td>{order.amount}</td>
                <td>{order.orderDate}</td>
                <td>{order.serviceName}</td>
                <td>{order.orderStatus}</td>
                <td>{order.emp.firstName} {order.emp.lastName}</td>
                {(order.orderStatus=='COMPLETED' && !order.feedbackGiven)?<td><button className="btn btn-info" onClick={() => {
                    giveFeedback(order.id);
                  }} >Feedback</button>
               </td>:""}
               {(order.orderStatus=='PENDING')?<td><button className="btn btn-info" onClick={()=>{handlePayment(order.id)}}>Payment</button></td>:""}
               {(order.orderStatus=='PENDING')?<td><button className="btn btn-danger" onClick={() => {
                    handleCancel(order.id);
                  }} >Cancel</button></td>:""}
              </tr>
            )).reverse()
          }
          </tbody>
        </table>
        
    </div>

    </div>
)
        }
export default MyOrders;
