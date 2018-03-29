package com.midhat.coupon.couponsweb.services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import couponsSystem.CouponSystem;
import enums.ClientType;
import exceptions.ApplicationException;
import facades.AdminFacade;
import facades.CustomerFacade;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CustomerService {

	@POST
	public void createCustomer(Customer customer, @Context HttpServletRequest request) throws ApplicationException {
		//CouponSystem couponSystem = CouponSystem.getInstance(); 
		//AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);	
		
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.createCustomer(customer);
	}

	@GET
	@Path("/{customerId}")
	public Customer getCustomer(@PathParam("customerId") long customerId, @Context HttpServletRequest request) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);		
		
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		Customer resCustomer = adminFacade.getCustomer(customerId);
		return resCustomer;
	}
	

	@DELETE
	@Path("/{customerId}")
	public void removeCustomer(@PathParam("customerId") long customerId, @Context HttpServletRequest request) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.removeCustomer(customerId);
	}

	@PUT
	public void updateCompany(Customer customer, @Context HttpServletRequest request) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.updateCustomer(customer);
	}

	@GET
	@Path("/getAllCustomers")
	public ArrayList<Customer> getAllCustomer(@Context HttpServletRequest request) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		ArrayList<Customer> allCustomers = adminFacade.getAllCustomers();
		return allCustomers;
	}
	
	@GET
	@Path("/getAllCoupons")
	public ArrayList<Coupon> getAllCoupons(@Context HttpServletRequest request) throws ApplicationException {
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		ArrayList<Coupon> allCoupons = customerFacade.getAllCoupons();
		return allCoupons;
	}
	


	
	
	
	
	
	
}