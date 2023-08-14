/**
 * OrderRegistService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.service;

import tsysSpring.sales.entity.Member;
import tsysSpring.sales.entity.Order;
import tsysSpring.sales.entity.ShoppingCart;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public interface OrderRegistService {

	/**
	 * @param shoppingCart
	 * @param itemCode
	 * @return
	 */
	public ShoppingCart addShoppingCart(ShoppingCart shoppingCart ,String itemCode,int quantity) ;

	public Member confirm(String MemberCode) ;

	public Order execute(ShoppingCart shoppingCart,Member member,String payment);

}
