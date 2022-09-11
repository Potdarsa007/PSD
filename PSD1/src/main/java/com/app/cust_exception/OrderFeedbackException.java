package com.app.cust_exception;

public class OrderFeedbackException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderFeedbackException(String msg)
	{
		super(msg);
	}
}
