package com.midhat.coupon.couponsweb.services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.sun.enterprise.security.jauth.callback.PrivateKeyCallback.Request;
import com.trilead.ssh2.Session;

import beans.Company;
import beans.Coupon;
import couponsSystem.CouponSystem;
import enums.ClientType;
import enums.CouponType;
import exceptions.ApplicationException;
import facades.AdminFacade;
import facades.CompanyFacade;

@Path("/companycoupon")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyCouponService {

	@POST
	public void createCoupon(Coupon coupon, @Context HttpServletRequest request) throws ApplicationException {
		// CouponSystem couponSystem = CouponSystem.getInstance();
		// CompanyFacade companyFacade = (CompanyFacade)
		// couponSystem.login("WEB-COMPANY", "11111", ClientType.COMPANY);
		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		long companyId = (Long) request.getSession().getAttribute("companyId");

		companyFacade.createCoupon(coupon, companyId);
	}

	@GET
	@Path("{couponId}")
	public Coupon getCoupon(@PathParam("couponId") long couponId, @Context HttpServletRequest request)
			throws ApplicationException {
		// CouponSystem couponSystem = CouponSystem.getInstance();
		// CompanyFacade companyFacade = (CompanyFacade)
		// couponSystem.login("WEB-COMPANY", "11111", ClientType.COMPANY);

		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		Coupon resCoup = companyFacade.getCoupon(couponId);
		return resCoup;
	}

	@DELETE
	@Path("{couponId}")
	public void removeCoupon(@PathParam("couponId") long couponId, @Context HttpServletRequest request)
			throws ApplicationException {
		// CouponSystem couponSystem = CouponSystem.getInstance();
		// CompanyFacade companyFacade = (CompanyFacade)
		// couponSystem.login("WEB-COMPANY", "11111", ClientType.COMPANY);

		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		companyFacade.removeCoupon(couponId);
	}

	@PUT
	public void updateCoupon(Coupon coupon, @Context HttpServletRequest request) throws ApplicationException {
		// CouponSystem couponSystem = CouponSystem.getInstance();
		// CompanyFacade companyFacade = (CompanyFacade)
		// couponSystem.login("WEB-COMPANY", "11111", ClientType.COMPANY);

		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		companyFacade.updateCoupon(coupon);
	}

	@GET
	@Path("/AllCoupons")
	public ArrayList<Coupon> getAllCompanyCoupons(@Context HttpServletRequest request) throws ApplicationException {
		// CouponSystem couponSystem = CouponSystem.getInstance();
		// CompanyFacade companyFacade = (CompanyFacade)
		// couponSystem.login("WEB-COMPANY", "11111", ClientType.COMPANY);

		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		long companyId = (Long) request.getSession().getAttribute("companyId");
		ArrayList<Coupon> allCoupons = companyFacade.getAllCompanyCoupons(companyId);
		return allCoupons;
	}

	@GET
	@Path("/AllCouponByPrice/{price}")
	public ArrayList<Coupon> getAllCompanyCouponsByPrice(@PathParam("price") double couponPrice ,@Context HttpServletRequest request)
			throws ApplicationException {
		// CouponSystem couponSystem = CouponSystem.getInstance();
		// CompanyFacade companyFacade = (CompanyFacade)
		// couponSystem.login("WEB-COMPANY", "11111", ClientType.COMPANY);

		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		long companyId = (Long) request.getSession().getAttribute("companyId");
		ArrayList<Coupon> allCoupons = companyFacade.getAllCompanyCouponsByPrice(companyId, couponPrice);
		return allCoupons;
	}
	
	@GET
	@Path("/AllCouponByType/{couponType}")
	public ArrayList<Coupon> getAllCompanyCouponsByType(@PathParam("couponType") CouponType couponType,@Context HttpServletRequest request)
			throws ApplicationException {
		// CouponSystem couponSystem = CouponSystem.getInstance();
		// CompanyFacade companyFacade = (CompanyFacade)
		// couponSystem.login("WEB-COMPANY", "11111", ClientType.COMPANY);

		CompanyFacade companyFacade = (CompanyFacade) request.getSession().getAttribute("companyFacade");
		long companyId = (Long) request.getSession().getAttribute("companyId");
		ArrayList<Coupon> allCoupons = companyFacade.getAllCompanyCouponsByType(companyId, couponType);
		return allCoupons;
	}

}
