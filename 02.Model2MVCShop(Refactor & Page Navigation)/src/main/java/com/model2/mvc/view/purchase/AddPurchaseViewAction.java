package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class AddPurchaseViewAction extends Action{
	
	public String execute(	HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
//		Product product = new Product();
//		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
//		
//		
//		Purchase purchase = new Purchase();
//		PurchaseService service=new PurchaseServiceImpl();
//		service.addPurchase(purchase);
//		System.out.println(purchase);
//		
//		
//		
//		request.setAttribute("purchase", purchase);
//		
//		return "forword:/purchase/addPurchaseView.jsp";
//		
//		
	
		
		ProductService productService = new ProductServiceImpl();

		Product product = productService.getProduct(Integer.parseInt(request.getParameter("prodNo")));

		request.setAttribute("product", product);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	

}
