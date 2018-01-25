package com.example.demo;

public class FileProps {
	
	String owner;
	String price;
	String validator;
	String author;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getValidator() {
		return validator;
	}
	public void setValidtor(String validator) {
		this.validator = validator;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return String.format("FileProps [owner=%s, price=%s, validtor=%s, author=%s]", owner, price, validator, author);
	}
	
	

}
