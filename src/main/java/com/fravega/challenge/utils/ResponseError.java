package com.fravega.challenge.utils;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ResponseError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
    private String message;

    public ResponseError(HttpStatus status, String message) {

        this.status = status;
        this.message = message;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}

