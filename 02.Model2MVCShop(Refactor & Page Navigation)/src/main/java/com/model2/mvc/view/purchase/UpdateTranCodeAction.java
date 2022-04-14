package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction 	extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
		String tranCode = request.getParameter("tranCode");
		PurchaseService service=new PurchaseServiceImpl();
		Purchase purchase = new Purchase();
		purchase.setTranNo(tranNo);		

// 여기서 trancode가 0,1,2일때 어떤 상태로 갈 것인지 설정해주고
// jsp에선 그 값을 받아서 if문으로 그 숫자일때 구매완료,배송중,배송완료가 뜨도록 상태를 변경시켜주면 된다.
		
		
		
		
		service.updateTranCode(purchase);
		
		
		
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/updatePurchase.jsp";
	}

}
