import httpClient from './http-common';

export const addNewEmployee = (data) => {
    return httpClient.post('employee/addnewemployee' , data);
  };

  export const deleteEmployee = (id) => {
    return httpClient.delete(`employee/delete/${id}`);
  };

  export const getEmployeeDetails = (id) => {
    return httpClient.get(`employee/${id}`);
  };

  export const authenticateEmployee = (data) => {
    return httpClient.post(`employee/login`, data);
  };

  export const updateEmployee = (data) => {
    return httpClient.put('employee/updateaccount', data);
  };

  export const forgetPassword = (email) => {
    return httpClient.get(`employee/forgetpassword/${email}`);
  };
  
  export const updatePassword = (data) => {
    return httpClient.post('employee/updatepassword' , data);
  };

  export const getAllPendingOrders = (id) => {
    return httpClient.get(`employee/pendingorders/${id}`);
  };
  // eslint-disable-next-line import/no-anonymous-default-export
  export default {
    addNewEmployee,
    deleteEmployee,
    getEmployeeDetails,
    authenticateEmployee,
    updateEmployee,
    forgetPassword,
    updatePassword,
    getAllPendingOrders
  }