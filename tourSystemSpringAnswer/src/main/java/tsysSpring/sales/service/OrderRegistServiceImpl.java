/**
 * OrderRegistServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tsysSpring.sales.common.exception.SalesBusinessException;
import tsysSpring.sales.common.exception.SalesSystemException;
import tsysSpring.sales.entity.Item;
import tsysSpring.sales.entity.Member;
import tsysSpring.sales.entity.Order;
import tsysSpring.sales.entity.OrderDetail;
import tsysSpring.sales.entity.ShoppingCart;
import tsysSpring.sales.mapper.HotelMapper;
import tsysSpring.sales.mapper.MemberMapper;
import tsysSpring.sales.mapper.OrderMapper;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
@Service
public class OrderRegistServiceImpl implements OrderRegistService {

	/** Mapper */
	@Autowired
	HotelMapper hotelMapper;

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	MemberMapper memberMapper;


	/**
	 * @see tsysSpring.sales.service.OrderRegistService#addShoppingCart(tsysSpring.sales.entity.ShoppingCart, java.lang.String)
	 */
	@Override
	public ShoppingCart addShoppingCart(ShoppingCart shoppingCart ,String itemCode,int quantity) {

		Item item = null;

		String category = itemCode.substring(0,3);

		if(category.equals("HTL")) {
			item = hotelMapper.findHotel(itemCode);
		}

		if(item==null) {
			throw new SalesSystemException();
		}

		item.setQuantity(quantity);
		item.setTotal(item.getPrice()*quantity);
		shoppingCart.addItem(item);
		shoppingCart.calc();

		return shoppingCart;
	}


	/**
	 * @see tsysSpring.sales.service.OrderRegistService#confirm(java.lang.String)
	 */
	@Override
	public Member confirm(String memberCode) {
		Member member = null;

		member = memberMapper.findMember(memberCode);

		if (member==null) {
			throw new SalesSystemException();
		}
		return member;
	}


	/**
	 * @see tsysSpring.sales.service.OrderRegistService#execute(tsysSpring.sales.entity.ShoppingCart, tsysSpring.sales.entity.Member)
	 */
	@Transactional
	@Override
	public Order execute(ShoppingCart shoppingCart, Member member,String payment) {

		List<Item> itemList = shoppingCart.getItemList();

		if (itemList.isEmpty()) {
			throw new SalesSystemException();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(new Date());

		Order order = new Order();
		order.setMember(member);
		order.setMemberCode(member.getMemberCode());
		order.setOrderDate(nowDate);
		order.setOrderTotal(shoppingCart.getTotal());
		order.setPayment(payment);

		orderMapper.insertOrder(order);
		int orderNo = orderMapper.lastInsertOrderNo();
		order.setOrderNo(orderNo);

		List<OrderDetail> orderDetailList = new ArrayList();

		for(Item item : itemList) {

			String itemCode = item.getItemCode();
			String category = itemCode.substring(0,3);
			Item checkItem = null;
			if(category.equals("HTL")) {
				checkItem = hotelMapper.findHotel(itemCode);
			}

			if(checkItem==null) {
				throw new SalesSystemException();
			}

			if(checkItem.getStock()<item.getQuantity()) {
				throw new SalesSystemException();
			}

			item.setStock(checkItem.getStock());

			if(category.equals("HTL")) {
				int count = hotelMapper.updateStockByQuantity(item);

				if(count==0) {
					throw new SalesSystemException();
				}
			}

			OrderDetail orderDetail =new OrderDetail();
			orderDetail.setOrderNo(orderNo);
			orderDetail.setItemCode(item.getItemCode());
			orderDetail.setItemName(item.getItemName());
			orderDetail.setPrice(item.getPrice());
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setSubTotal(item.getTotal());

			orderMapper.insertOrderDetail(orderDetail);

			orderDetailList.add(orderDetail);

		}

		order.setOrderDetailList(orderDetailList);

		return order;
	}



}
