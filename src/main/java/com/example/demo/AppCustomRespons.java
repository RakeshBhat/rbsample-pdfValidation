package com.example.demo;

public class AppCustomRespons {

	String service;
	String message;
	
	public AppCustomRespons(String service, String message){
		this.service = service;
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("AppCustomRespons [service=%s, message=%s]", service, message);
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
