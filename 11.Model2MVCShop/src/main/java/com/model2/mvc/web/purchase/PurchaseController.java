package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


@Controller
@RequestMapping("/purchase/*")

public class PurchaseController {

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

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	public PurchaseController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value="addPurchase", method=RequestMethod.GET)
	public String addPurchaseView(@ModelAttribute("user")User user,@RequestParam("prodNo") int prodNo, HttpSession session,Model model) throws Exception {
		
		model.addAttribute("user",user);
		model.addAttribute("product",productService.getProduct(prodNo));
		return "forward:/purchase/addPurchaseView.jsp";
	}

	@RequestMapping(value="addPurchase", method=RequestMethod.POST)
	public String addPurchase( @ModelAttribute("purchase") Purchase purchase,HttpSession session,
			 @RequestParam("prodNo") int prodNo) throws Exception {

		purchase.setBuyer((User)session.getAttribute("user"));
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		purchase.setTranCode("1");
		
		purchaseService.addPurchase(purchase);
		
		return "/purchase/addPurchase.jsp";
	}

	@RequestMapping(value = "getPurchase", method = RequestMethod.GET)
	public String getPurchase (@RequestParam("tranNo") int tranNo, Model model) throws Exception {
		Purchase purchase = purchaseService.getPurchase(tranNo);
		System.out.println(purchase);
		model.addAttribute("purchase", purchase);
		return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping(value="listPurchase")
	public String listPurchase (@ModelAttribute("search") Search search, HttpSession session, Model model,HttpServletRequest request) throws Exception {
		
		System.out.println("/purchase/listPurchase : controller");
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		String buyerId = ((User)session.getAttribute("user")).getUserId();
		Map<String, Object> map = purchaseService.getPurchaseList(search, buyerId);

		Page resultPage = new Page( search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);

		System.out.println("ListPurchase ::" + resultPage);
		System.out.println("ListPurchase ::" + search);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		
		return "/purchase/listPurchase.jsp";
	}
	
	@RequestMapping(value="updatePurchaseView", method = RequestMethod.GET)
	public String updatePurchaseView (@RequestParam("tranNo") int tranNo, Model model) throws Exception {
		Purchase purchase = purchaseService.getPurchase(tranNo);
		model.addAttribute("Purchase", purchase);
		return "forward:/purchase/updatePurchase.jsp";
	}
	
	@RequestMapping(value="updatePurchase", method = RequestMethod.POST)
	public String updatePurchase (@ModelAttribute("purchase") Purchase purchase,@RequestParam("tranNo") int tranNo,Model model , HttpSession session) throws Exception {
		
		System.out.println("purchase update :: controller");
		purchaseService.updatePurchase(purchase);
		
		Object purchaseCandidate = session.getAttribute("purchase");
		int trnaNo = -1;
		if(purchaseCandidate != null && purchaseCandidate instanceof Purchase) {
			tranNo=((Purchase)purchaseCandidate).getTranNo();
		}
	
		if(tranNo==purchase.getTranNo()){
			session.setAttribute("purchase", purchase);
		}
		
		return "redirect:/purchase/getPurchase?tranNo="+purchase.getTranNo();
	}
	
	@RequestMapping(value="updateTranCode",method = RequestMethod.POST)
	public String updateTranCode(@ModelAttribute("purchase") Purchase purchase) throws Exception {
		purchaseService.updateTranCode(purchase);
		return "redirect:/listPurchase.do";
	}
	
	
	@RequestMapping(value="updateTranCodeByProd",method = RequestMethod.POST)
	public String updateTranCodeByProd(@RequestParam("currentPage") int currentPage, @RequestParam("prodNo") int prodNo, @RequestParam("tranCode") String tranCode) throws Exception {
		Purchase purchase = purchaseService.getPurchase(prodNo);
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode(purchase);
		return "redirect:/listProduct.do?menu=manage&currentPage="+currentPage;
	}
	
	
	@RequestMapping(value = "deletePurchase",method = RequestMethod.POST)
	public String deletePurchase(@RequestParam("tranNo") int tranNo, Model model) throws Exception {
		
		purchaseService.deletePurchase(tranNo);
		
		return "redirect:/purchase/listPurchase";
	}
	
	
}
