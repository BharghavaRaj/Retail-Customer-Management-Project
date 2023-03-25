package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	
	private static final String dburl = "jdbc:mysql://localhost:3306/customermanagementdb";
	private static final String dbusername = "root";
	private static final String dbpassword ="admin";

	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static final String validateQry = "select * from admin_login where username=? and password=?";
	
	public static boolean getValidate(String username, String password) 
	{
		boolean b = false;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dburl, dbusername, dbpassword);
			
			ps = con.prepareStatement(validateQry);
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			b = rs.next();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e){
			e.printStackTrace();
		}
		return b;
	}
}
