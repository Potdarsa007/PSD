/* eslint-disable import/no-anonymous-default-export */
import httpClient from './http-common';

export const login = (data) => {
    return httpClient.post('user/login', data);
  };

export const logout = () => {
    return httpClient.get('user/logout');
  }
  export const getAllProfessions = () => {
    return httpClient.get('user/getallprofessions');
  }

  export const getBasicCharge = (professionName) => {
    return httpClient.get(`user/getbasiccharge/${professionName}`);
  }

  export const forgetPassword = (email) => {
    return httpClient.get(`user/forgetpassword/${email}`);
  };

  export const updateUser = (data) => {
    return httpClient.post('user/updateaccount' , data);
  };

  export const ContactUsDetails=(data)=>
  {
    return httpClient.post(`user/contactus`,data);
  }

  export default {login, logout,getAllProfessions,getBasicCharge,forgetPassword,updateUser,ContactUsDetails}