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

import com.midhat.coupon.couponsweb.user.User;
import com.trilead.ssh2.Session;

import beans.Company;
import couponsSystem.CouponSystem;
import enums.ClientType;
import exceptions.ApplicationException;
import facades.AdminFacade;

@Path("/company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CompanyService {
	User User = new User();

	@POST
	public void createCompany(Company company, @Context HttpServletRequest request ) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);	
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.createCompany(company);
	}

	@GET
	@Path("/{companyId}")
	public Company getCompany(@PathParam("companyId") long companyId, @Context HttpServletRequest request) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);		
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		Company resComp = adminFacade.getComapny(companyId);
		return resComp;
	}

	@DELETE
	@Path("/{companyId}")
	public void removeCompany(@PathParam("companyId") long companyId, @Context HttpServletRequest request) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.removeCompany(companyId);
	}

	@PUT
	public void updateCompany(Company company, @Context HttpServletRequest request) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		adminFacade.updateCompany(company);
	}

	@GET
	@Path("/getAllCompanies")
	public ArrayList<Company> getAllCompany(@Context HttpServletRequest request) throws ApplicationException {
//		CouponSystem couponSystem = CouponSystem.getInstance(); 
//		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		AdminFacade adminFacade = (AdminFacade) request.getSession().getAttribute("adminFacade");
		ArrayList<Company> allCompanies = adminFacade.getAllCompanies();
		return allCompanies;
	}
	



}
