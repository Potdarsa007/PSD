package com.app.cust_exception;

public class OrderCompletedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderCompletedException(String message)
	{
		super(message);
	}
}
