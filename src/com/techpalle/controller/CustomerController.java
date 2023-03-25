package com.techpalle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.AdminDAO;
import com.techpalle.dao.CustomerDAO;
import com.techpalle.model.Customer;

@WebServlet("/")//It is an annotation and it can specify the url patterns that should be mapped to the servlet.

//A servlet class that runs on a web server and generates a response to HTTP requests from clients such as web browser.
public class CustomerController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//This method can obtain the URL pattern that was used to map the servlet and perform different actions based on the pattern.
		String path = request.getServletPath();
		
		switch(path) 
		{
		case"/delete":
			getDeletePage(request,response);
			break;
		case"/update":
			getUpdatePage(request,response);
			break;
		case"/edit":
			editFormPage(request,response);
			break;
		case"/formPage":
			getFormPage(request,response);
			break;
		case "/add":
			getAddCustomer(request,response);
			break;
		case"/list":
			getValidate(request,response);
			break;
		case"/listPage":
			getCustomerListPage(request,response);
			break;
		default:
			getStartUpPage(request,response);
			break;
		}
	}
	
	// retrieves all customers from the database and displays them in a JSP page.
	private void getCustomerListPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			ArrayList<Customer> allCustomers = CustomerDAO.getAllCustomersList();
			RequestDispatcher rd = request.getRequestDispatcher("customer-list.jsp");
			request.setAttribute("al", allCustomers);
			rd.forward(request, response);
		}
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void getValidate(HttpServletRequest request, HttpServletResponse response) 
	{
		String u = request.getParameter("tbUser");
		String p = request.getParameter("tbPass");
		boolean res = AdminDAO.getValidate(u, p);
		
		//Use Condition to redirect admin to list page
		if(res) 
		{
			getCustomerListPage(request,response);
		}
		else 
		{
			try 
			{
				response.sendRedirect("/default");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void getDeletePage(HttpServletRequest request, HttpServletResponse response) 
	{
		int i = Integer.parseInt(request.getParameter("id")); //Read the id from url
		CustomerDAO.deleteCustomer(i);
		
		getCustomerListPage(request,response);
	}

	private void getUpdatePage(HttpServletRequest request, HttpServletResponse response) 
	{
		int c2id = Integer.parseInt(request.getParameter("tbId"));
		String c2name = request.getParameter("tbName");
		String c2city = request.getParameter("tbCity");
		String c2email = request.getParameter("tbEmail");
		long c2contact = Long.parseLong(request.getParameter("tbContact"));
		
		Customer cu = new Customer(c2id,c2name,c2city,c2email,c2contact);
		CustomerDAO.updateCustomer(cu);
		
		getCustomerListPage(request,response);
	}

	private void editFormPage(HttpServletRequest request, HttpServletResponse response) 
	{
		int iD = Integer.parseInt(request.getParameter("id"));
		Customer c = CustomerDAO.getCustomerById(iD);
		
		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("customer-form.jsp");
			request.setAttribute("customer", c);
			rd.forward(request, response);
		}
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void getFormPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("customer-form.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getAddCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		String c1name = request.getParameter("tbName");
		String c1city = request.getParameter("tbCity");
		String c1email = request.getParameter("tbEmail");
		long c1contact = Long.parseLong(request.getParameter("tbContact"));
		
		Customer cust = new Customer(c1name,c1city,c1email,c1contact);
		CustomerDAO.addCustomer(cust);
		
		getCustomerListPage(request,response);
	}

	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("admin-login.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
