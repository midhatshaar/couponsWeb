package com.midhat.coupon.couponsweb.services;


import java.io.IOException;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.midhat.coupon.couponsweb.user.User;


import couponsSystem.CouponSystem;
import enums.ClientType;
import exceptions.ApplicationException;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;

@Path("/LoginService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {

	@POST
	public void Login(@Context HttpServletRequest request, @Context HttpServletResponse response, User user)
			throws IOException {
		String userName = user.getUserName();
		String password = user.getUserPassword();
		try {
			switch (user.getUserType()) {
			case ADMIN:

				CouponSystem couponSystem = CouponSystem.getInstance(); 
				AdminFacade adminFacade = (AdminFacade) couponSystem.login(userName, password, ClientType.ADMIN);
				HttpSession session = request.getSession();
				session.setAttribute("adminFacade", adminFacade);
				session.setAttribute("userName", userName);
				//session.setAttribute("IsLoggedIn",true);	
				break;
				

			case CUSTOMER:

				CouponSystem couponSystem2 = CouponSystem.getInstance(); 
				CustomerFacade customerFacade = (CustomerFacade) couponSystem2.login(userName, password, ClientType.CUSTOMER);
				session = request.getSession();
				session.setAttribute("customerFacade", customerFacade);
				long customerId = customerFacade.getCustomerIdByName(userName);
				session.setAttribute("customerId", customerId);
				session.setAttribute("userName", userName);
				// session.setAttribute("IsLoggedIn",true);
				break;
				

			case COMPANY:

				CouponSystem couponSystem3 = CouponSystem.getInstance();
				CompanyFacade companyFacade = (CompanyFacade) couponSystem3.login(userName, password, ClientType.COMPANY);
				session = request.getSession();
				session.setAttribute("companyFacade", companyFacade);
				long companyId = companyFacade.getCompanyIdByName(userName);
				session.setAttribute("companyId", companyId);
				session.setAttribute("userName", userName);
				// session.setAttribute("IsLoggedIn",true);		
				break;

			}
		} catch (ApplicationException e) {
			response.sendError(401, e.getMessage());
		}
	}
	

}
