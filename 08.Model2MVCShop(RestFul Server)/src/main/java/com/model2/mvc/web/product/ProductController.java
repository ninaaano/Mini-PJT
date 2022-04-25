package com.model2.mvc.web.product;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")

public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());	
	}

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping(value="addProduct", method=RequestMethod.GET)
	public String addProductView() throws Exception{
		System.out.println("/product/addProduct : GET");
		
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, @RequestParam("manuDate") String manuDate) throws Exception {
		System.out.println("/product/addProduct : POST");
		
		String date = manuDate.replace("-", "");
		product.setManuDate(date);
		productService.addProduct(product);

		return "/product/addProduct.jsp";
	}
	
	@RequestMapping(value = "getProduct", method = RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("/product/getProduct");
				
		Product product = productService.getProduct(prodNo);
		System.out.println(product);
		model.addAttribute("product", product);
		
		String history;
		Cookie cookie = null;
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("history")) {
            	
					history = URLDecoder.decode(cookie.getValue(),"euc-kr");
					history +=","+product;
					System.out.println(history);
					cookie = new Cookie("history",URLEncoder.encode(history,"euc-kr"));
				}else {
					cookie = new Cookie("history", Integer.toString(prodNo));
				}
			}
		}
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping(value = "listProduct")
	public String listProduct(@ModelAttribute("search") Search search,Model model,HttpServletRequest request,@RequestParam("menu") String menu) throws Exception{
		System.out.println("/product/listProduct");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String , Object> map = productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", menu);
		
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping(value = "updateProduct",method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product,Model model , HttpSession session) throws Exception{
		System.out.println("/product/updateProduct");
		productService.updateProduct(product);
		
		int prodNo=((Product)session.getAttribute("prodNo")).getProdNo();
	
		if(prodNo==product.getProdNo()){
			session.setAttribute("product", product);
		}
		
		return "redirect:/product/getProduct?prodNo="+product.getProdNo();
	}
	
	@RequestMapping(value = "updateProduct",method = RequestMethod.GET)
	public String updateProductView(@RequestParam("prodNo")int prodNo, Model model) throws Exception {
		System.out.println("/product/updateProductView");
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		
		return "forward:/product/updateProduct.jsp";
	}

}
