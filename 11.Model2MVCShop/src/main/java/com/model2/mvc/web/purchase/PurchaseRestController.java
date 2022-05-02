package com.model2.mvc.web.purchase;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;



import com.model2.mvc.service.domain.Purchase;

import com.model2.mvc.service.product.ProductService;

import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {

	/// Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public PurchaseRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="json/addPurchase", method=RequestMethod.GET)
	public Purchase addPurchaseView(@RequestBody Purchase purchase) throws Exception {

		purchaseService.addPurchase(purchase);
		
		return purchase;
	}

	@RequestMapping(value = "json/getPurchase/{tranNo}", method = RequestMethod.GET)
	public Purchase getPurchase (@PathVariable int tranNo) throws Exception {
		
		return purchaseService.getPurchase(tranNo);
	}

	@RequestMapping(value="json/updatePurchase", method = RequestMethod.POST)
	public Purchase updatePurchase (@RequestBody Purchase purchase, HttpSession session) throws Exception {
		
		Purchase tranNo = purchaseService.getPurchase(purchase.getTranNo());
		
		if(purchase.getTranNo()==tranNo.getTranNo()){
			session.setAttribute("purchase", tranNo);
		}
		
		return tranNo;
	}
	
	
	@RequestMapping(value = "json/deletePurchase/{trnaNo}",method = RequestMethod.POST)
	public Purchase deletePurchase(@RequestBody Purchase purchase, @PathVariable int tranNo) throws Exception {
		
		purchaseService.deletePurchase(tranNo);
		
		return purchase;
	}
	
	
}
