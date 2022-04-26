package com.model2.mvc.web.product;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/getProduct/{prodNo}", method=RequestMethod.GET)
	public Product getProduct( @PathVariable int prodNo ) throws Exception{
		
		System.out.println("/product/json/getProduct : GET");
		
		//Business Logic
		return productService.getProduct(prodNo);
	}
	
	@RequestMapping(value="json/addProduct", method=RequestMethod.GET)
	public Product addProductView(@RequestBody Product product) throws Exception{
		
		System.out.println("/product/json/addProduct : GET ");
		
		productService.addProduct(product);
		
		return product;
	}
	
	@RequestMapping(value = "json/updateProduct",method = RequestMethod.POST)
	public Product updateProduct(@RequestBody Product product, HttpSession session) throws Exception{
		
		System.out.println("/product/json/updateProduct : POST ");
		
		Product prodNo = productService.getProduct(product.getProdNo());
	
		if(product.getProdNo()==product.getProdNo()){
			session.setAttribute("product", product);
		}
		
		return prodNo;
	}

	
	
	
	
	
}
