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

// ���⼭ trancode�� 0,1,2�϶� � ���·� �� ������ �������ְ�
// jsp���� �� ���� �޾Ƽ� if������ �� �����϶� ���ſϷ�,�����,��ۿϷᰡ �ߵ��� ���¸� ��������ָ� �ȴ�.
		
		
		
		
		service.updateTranCode(purchase);
		
		
		
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/updatePurchase.jsp";
	}

}
