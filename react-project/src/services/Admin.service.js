import httpClient from './http-common';


export const getAllVerifiedEmps = () => {
    return httpClient.get(`admin/getallverifiedemployees`);
  };

  export const getAllUnverifiedEmps = () => {
    return httpClient.get(`admin/getallunverifiedemployees`);
  };

  export const addProfession = (profession) => {
    return httpClient.post('admin/addprofession' ,profession);
  };

  export const getAllCustomers = () => {
    return httpClient.get(`admin/getallcustomers`);
  };

  export const getAdminById = (id) => {
    return httpClient.get(`admin/${id}`);
  };

  export const employeeVerified = (id) => {
    return httpClient.get(`admin/employeeverified/${id}`);
  };

  export const getAllFeedbacks = () => {
    return httpClient.get("admin/getallfeedbacks");
  };

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  getAllVerifiedEmps, 
  getAllUnverifiedEmps,
  addProfession,
  getAllCustomers,
  getAdminById,
  employeeVerified,
  getAllFeedbacks
}