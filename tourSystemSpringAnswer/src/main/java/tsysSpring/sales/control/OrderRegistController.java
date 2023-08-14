/**
 * OrderRegistController.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tsysSpring.sales.entity.AuthMember;
import tsysSpring.sales.entity.Member;
import tsysSpring.sales.entity.Order;
import tsysSpring.sales.entity.ShoppingCart;
import tsysSpring.sales.form.ItemDetailForm;
import tsysSpring.sales.form.OrderForm;
import tsysSpring.sales.service.OrderRegistService;
import tsysSpring.sales.service.OrderRegistServiceImpl;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
@RequestMapping("/orderRegist")
@SessionAttributes(value = {"authMember","shoppingCart","member"})
@Controller
public class OrderRegistController {

	/** 認証済みメンバー情報 */
	@Autowired
	AuthMember authMember;

	/**
	 * セッションの認証済みメンバー情報をModelに追加
	 * @return AuthMember
	 */
	@ModelAttribute("authMember")
	public AuthMember authMemberSetup() {
		return authMember;
	}

	@Autowired
	OrderRegistService orderRegistService;

	@RequestMapping(value = "/goShoppingCart", method = RequestMethod.GET)
	public String goShoppingCart(ShoppingCart shoppingCart,
			Model model) {

		model.addAttribute("shoppingCart", shoppingCart);
		model.addAttribute("pageUrl", "/orderRegist/goShoppingCart");

		return "V0201_01ShoppingCartView";
	}

	@RequestMapping(value = "/addShoppingCart", method = RequestMethod.POST)
	public String addShoppingCart(@ModelAttribute("itemDetailForm") ItemDetailForm itemDetailForm,
			ShoppingCart shoppingCart,
			Model model) {

		String itemCode = itemDetailForm.getItemCode();
		int quantity = itemDetailForm.getQuantity();

		if(shoppingCart==null) {
			shoppingCart = new ShoppingCart();
		}

		shoppingCart = orderRegistService.addShoppingCart(shoppingCart,itemCode,quantity);

		model.addAttribute("shoppingCart", shoppingCart);

		return "V0201_01ShoppingCartView";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String confirm(ShoppingCart shoppingCart,
			AuthMember authMember,
			Model model) {

		if(authMember.getMemberName()==null) {
			return "redirect:/goLogin?pageUrl=cart";
		}

		String memberCode = authMember.getMemberCode();

		Member member = orderRegistService.confirm(memberCode);

		model.addAttribute("member", member);
		model.addAttribute("orderForm", new OrderForm());

		return "V0201_02OrderRegistConfirmView";
	}

	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public String execute(	OrderForm orderForm,
										ShoppingCart shoppingCart,
										Member member,
										AuthMember authMember,
										SessionStatus status,
										Model model) {
		String payment = orderForm.getPayment();

		Order order = orderRegistService.execute(shoppingCart, member, payment);

		model.addAttribute("shoppingCart", new ShoppingCart());
		model.addAttribute("member", new Member());
		model.addAttribute("order", order);
		model.addAttribute("authMember", authMember);

		return "V0201_03OrderRegistResultView";
	}
}
