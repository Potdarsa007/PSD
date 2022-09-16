import httpClient from './http-common';

export const getCustomerdetails = (id) => {
  return httpClient.get(`customer/${id}`);
};

export const createAccount = (data) => {
  return httpClient.post('customer/newaccount', data);
};

export const deleteCustomer = (id) => {
  return httpClient.delete(`customer/delete/${id}`);
};


export const updatePassword = (data) => {
  return httpClient.post('customer/updatepassword' , data);
};

export const bookService = (data) => {
  return httpClient.post('customer/bookservice', data);
};

export const getAllCompletedOrders = (id) => {
  return httpClient.get(`customer/completedorders/${id}`);
};

export const getAllFeedbackNotGiven = (id) => {
  return httpClient.get(`customer/feedbackorders/${id}`);
};

export const getAllPendingOrders = (id) => {
  return httpClient.get(`customer/uncompletedorders/${id}`);
};

export const getAllOrders = (id) => {
  return httpClient.get(`customer/orders/${id}`);
};

export const cancelService = (id) => {
  return httpClient.get(`customer/cancelservice/${id}`);
};

export const generatedBill = (id) => {
  return httpClient.get(`customer/generatedbill/${id}`);
};

export const giveFeedback = (data) => {
  return httpClient.post('customer/feedback',data);
};

export default {createAccount, bookService,getAllOrders,cancelService,giveFeedback,deleteCustomer,generatedBill}