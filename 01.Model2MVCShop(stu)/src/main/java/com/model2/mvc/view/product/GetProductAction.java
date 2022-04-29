package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		int prodNo =Integer.parseInt(request.getParameter("prodNo"));
		
		Cookie cookie = new Cookie("history",request.getParameter("prodNo"));
		cookie.setMaxAge(-1);
		cookie.setPath("/");
		response.addCookie(cookie);
		
//		Cookie[] cookies = request.getCookies();
//		 if (cookies != null) {
//		     for (int i = 0; i < cookies.length; i++) {
//		     }
//			 //for (Cookie cookie1 : cookies) {
//		    	  String history = cookie.getName();
//		    	  String prodName = cookie.getValue();
//		    	  }

		
		
		ProductService service=new ProductServiceImpl();
		ProductVO vo=service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);

		return "forward:/product/getProduct.jsp";
		
		 }
	}

