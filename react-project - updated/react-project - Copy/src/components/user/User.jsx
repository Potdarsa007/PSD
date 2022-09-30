import React from "react";
import "./style.css";
const User = () => {
  const userobj = localStorage.getItem("user");
  const user = JSON.parse(userobj);

  return (
    <>
      <br />
      <div className="container col-md-6 wrapper">
        <div className="app-wrapper">
        <table className="table table-borderless">
          <thead>
            <tr>
              <th className="title" colSpan={2}>
                Personal Details
                <hr className="myline2" />
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>FirstName</th>
              <td>{user.firstName}</td>
            </tr>
            <tr>
              <th>LastName</th>
              <td>{user.lastName}</td>
            </tr>
            <tr>
              <th>Email</th>
              <td>{user.userEmail}</td>
            </tr>
            <tr>
              <th>Contact Number</th>
              <td>{user.contactNumber}</td>
            </tr>
          </tbody>
          <thead>
            <tr>
              <th colSpan={2}>
                Address Details
                <hr className="myline2" />
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>Plot No.</th>
              <td>{user.address.plotNumber}</td>
            </tr>
            <tr>
              <th>Landmark</th>
              <td>{user.address.landmark}</td>
            </tr>
            <tr>
              <th>City</th>
              <td>{user.address.city}</td>
            </tr>
            <tr>
              <th>State</th>
              <td>{user.address.state}</td>
            </tr>
            <tr>
              <th>Pincode</th>
              <td>{user.address.pincode}</td>
            </tr>
          </tbody>
        </table>
        </div>
      </div>
    </>
  );
};

export default User;
