package com.techpalle.model;

//Create a bean or java class with properties that match the fields in the "customers" table.
public class Customer {
	
	private int id;
	private String name;
	private String city;
	private String email;
	private long contact;
	
	//This Constructor is used for insert operation. 
	public Customer(String name, String city, String email, long contact) {
		super();
		this.name = name;
		this.city = city;
		this.email = email;
		this.contact = contact;
	}
	
	//This Constructor is used for update and read operation.
	public Customer(int id, String name, String city, String email, long contact) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.email = email;
		this.contact = contact;
	}
	//{For delete operation we don't need constructor.}	

	//Implement getter and setter methods for each property.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	
	
}
