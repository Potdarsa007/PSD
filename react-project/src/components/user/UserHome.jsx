import { useState } from 'react';
import User from './User';
import './UserHome.css'
import UnverifiedList from '../Unverified';
import GetCustomers from './GetCustomers';
import VerifiedList from '../Verified';
import MyOrders from '../MyOrders';
import BookServices from '../BookServices';
import Register from '../update/UpdateForm'
import AllServices from '../AllServices';

function UserHome() {
    //const user = useSelector((state) => state.user.user)
    const userobj=localStorage.getItem('user');
    const user=JSON.parse(userobj);
    const [rightComponent,setRightComponent] = useState(<User/>)

if(user.role==="CUSTOMER")
{
    return (
        <div className="row">
            <div className="col-3 side-panel p-3">
                <nav className="mynav flex-column" >
                    <span className="nav-link mb-3"  onClick={() => {setRightComponent(<User/>)}}>My Profile</span>
                    <hr className='myline'/>
                    <span className="nav-link mb-3" onClick={() => {setRightComponent(<Register/>)}}>Update Profile</span>
                    <hr className='myline'/>
                    <span className="nav-link mb-3" onClick={() => {setRightComponent(<MyOrders/>)}}>My Orders</span>
                    <hr className='myline'/>
                    <span className="nav-link mb-3" onClick={() => {setRightComponent(<BookServices/>)}}>Book Service</span>
                </nav>
            </div>
            <div className="col-9 box-height">
                    {rightComponent}
            </div>
        </div>
    )
}
if(user.role==="ADMIN")
{
    return (
        <div className="row">
            <div className="col-3 side-panel p-3">
                <nav className="mynav flex-column">
                    <span className="nav-link mb-3 heading" onClick={() => {setRightComponent(<User/>)}}>My Profile</span>
                    <hr className='myline'/>
                    <span className="nav-link mb-3" onClick={() => {setRightComponent(<Register/>)}}>Update Profile</span>
                    <hr className='myline'/>
                    <span className="nav-link mb-3 heading" onClick={() => {setRightComponent(<UnverifiedList/>)}}>Unverified Employees</span>
                    <hr className='myline'/>
                    <span className="nav-link mb-3 heading" onClick={() => {setRightComponent(<VerifiedList/>)}}>Verified Employees</span>
                    <hr className='myline'/>
                    <span className="nav-link mb-3 heading" onClick={() => {setRightComponent(<AllServices/>)}}>Services</span>
                    <hr className='myline'/>
                    <span className="nav-link mb-3 heading" onClick={() => {setRightComponent(<GetCustomers/>)}}>All customers</span>

                </nav>


            </div>
            <div className="col-9 box-height">
                    {rightComponent}
            </div>
        </div>
    )
}
}
export default UserHome;