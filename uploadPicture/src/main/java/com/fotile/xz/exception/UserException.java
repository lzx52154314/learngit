package com.fotile.xz.exception;

public class UserException extends BaseException {

	/** 
     *  
     */
	private static final long serialVersionUID = 1L;

	public UserException() {

	}

	public UserException(String... params) {
		super("hehe", params);
	}
}
