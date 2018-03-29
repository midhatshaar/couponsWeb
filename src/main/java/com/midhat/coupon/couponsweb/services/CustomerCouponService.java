package com.midhat.coupon.couponsweb.services;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Coupon;
import enums.CouponType;
import exceptions.ApplicationException;
import facades.CustomerFacade;

@Path("/customercoupon")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class CustomerCouponService {
	@POST
	@Path("/purchaseCoupon/{couponId}")
	public void purchaseCoupon(@PathParam ("couponId") long couponId, @Context HttpServletRequest request) throws ApplicationException {
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		long customerId = (Long) request.getSession().getAttribute("customerId");
		customerFacade.purchaseCoupon(couponId, customerId);
	}
	
	@GET
	@Path("/AllPurchasedCoupons")
	public ArrayList<Coupon> getAllPurchasedCoupons(@Context HttpServletRequest request) throws ApplicationException{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		long customerId = (Long) request.getSession().getAttribute("customerId");
		ArrayList<Coupon> allPurchasedCoupons = customerFacade.getAllPurchasedCoupons(customerId);
		return allPurchasedCoupons;
	}
	
	@GET
	@Path("/AllPurchasedCouponsByPrice/{couponPrice}")
	public ArrayList<Coupon> getAllPurchasedCouponsBtPrice(@PathParam("couponPrice") double couponPrice,@Context HttpServletRequest request) 
			throws ApplicationException{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		long customerId = (Long) request.getSession().getAttribute("customerId");
		ArrayList<Coupon> allPurchasedCouponsByPrice = customerFacade.getAllPurchasedCouponsByPrice(couponPrice, customerId);
		return allPurchasedCouponsByPrice;
	}
	
	@GET
	@Path("/AllPurchasedCouponsByType/{couponType}")
	public ArrayList<Coupon> getAllPurchasedCouponsBtType(@PathParam("couponType") CouponType couponType, @Context HttpServletRequest request) 
			throws ApplicationException{
		CustomerFacade customerFacade = (CustomerFacade) request.getSession().getAttribute("customerFacade");
		long customerId = (Long) request.getSession().getAttribute("customerId");
		ArrayList<Coupon> allPurchasedCouponsByType = customerFacade.getAllPurchasedCouponsByType(couponType, customerId);
		return allPurchasedCouponsByType;
	}
	



}
