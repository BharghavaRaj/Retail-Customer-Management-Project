package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.techpalle.model.Customer;

//Create a Java class called "CustomerDAO" that includes methods to perform CRUD operations on the "customers" table using JDBC.
public class CustomerDAO {
	
	//Establish the connection with the specified  url ,username , password (Connect to the database).
	private static final String dburl = "jdbc:mysql://localhost:3306/customermanagementdb";
	private static final String dbusername = "root";
	private static final String dbpassword = "admin";

	//Declaring a default value of null because that the variable does not currently reference any object.
	private static Connection con = null;
	private static Statement st = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs =null;
	
	//Crud Operations (MySQL commands).
	private static final String customerDeleteQry = "delete from customers where id=?";
	private static final String customerUpdateQry = "update customers set name=?,city=?,email=?,contact=? where id=?";
	private static final String customerSelectQry ="select * from customers where id=?";
	private static final String customerInsertQry = "insert into customers (name,city,email,contact) values(?,?,?,?)";
	private static final String customersListQry = "select * from customers";
	
	//Creating a getConnection method to avoiding repeated jbc steps in every method, means we don't need to (load a driver class and establish connection).
	public static Connection getConnection() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");//Class class is used to register the driver class. This method is used to dynamically load the driver class.
			con = DriverManager.getConnection(dburl, dbusername, dbpassword); //DriverManager class is used to establish connection with the database.
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//returns a Connection to close the Connections for insert operation
	public static Connection closeConnection1() 
	{
		try 
		{
			if(ps!=null) 
			{
				ps.close();
			}
			if(con!=null) 
			{
				con.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	 
	//returns a Connection to close the Connections for update and read operation
	public static Connection closeConnection2() 
	{
		try 
		{
			if(rs!=null) 
			{
				rs.close();
			}
			if(ps!=null) 
			{
				ps.close();
			}
			if(con!=null) 
			{
				con.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//deletes a customer from the database by its ID.
	public static void deleteCustomer(int id) 
	{
		try 
		{
			con = getConnection();
			ps = con.prepareStatement(customerDeleteQry);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			closeConnection1();
		}
	}
	
	// updates an existing customer in the database.
	public static void updateCustomer(Customer customer) 
	{
		try 
		{
			con = getConnection();
			ps = con.prepareStatement(customerUpdateQry);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getCity());
			ps.setString(3, customer.getEmail());
			ps.setLong(4, customer.getContact());
			ps.setInt(5, customer.getId());
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			closeConnection1();
		}
	}
	
	//returns a Customer object by its ID.
	public static Customer getCustomerById(int id) 
	{
		Customer cust = null;
		try 
		{
			con = getConnection();
			ps = con.prepareStatement(customerSelectQry);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			rs.next(); 
			
			int cid = rs.getInt("id");
			String cname = rs.getString("name");
			String ccity = rs.getString("city");
			String cemail =rs.getString("email");
			long ccontact = rs.getLong("contact");
			
			cust = new Customer(cid,cname,ccity,cemail,ccontact);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			closeConnection2();
		}
		return cust;
	}
	
	//adds a new customer to the database.
	public static void addCustomer(Customer customer) 
	{
		try
		{
			con = getConnection();
			ps = con.prepareStatement(customerInsertQry);//retrieving the customers data from database
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getCity());
			ps.setString(3, customer.getEmail());
			ps.setLong(4, customer.getContact());
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			closeConnection1();
		}
	}
	
	//returns a List of all customers in the database.
	public static ArrayList<Customer> getAllCustomersList() 
	{
		ArrayList<Customer> al = new ArrayList<>();//Using arrrylist class i can store and manipulate the customers data
		try 
		{
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(customersListQry);//execute queries with the database.
			while(rs.next()) //read the all customers data by using while loop. 
			{
				int cid = rs.getInt("id");
				String cname = rs.getString("name");
				String ccity = rs.getString("city");
				String cemail = rs.getString("email");
				long ccontact = rs.getLong("contact");
				
				Customer cust = new Customer(cid,cname,ccity,cemail,ccontact); 
				al.add(cust); //Adding object in arraylist and i can maintain insertion order. 
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			closeConnection2();
		}
		return al;
	}

}
