/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Order.java
 */
package tsysSpring.sales.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文情報を管理するエンティティクラス
 * @author FLM
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable{

	/**
	 * 注文番号
	 */
	private int orderNo;

	/**
	 * 注文日
	 */
	private String orderDate;

	/**
	 * 注文合計
	 */
	private int orderTotal;

	/**
	 * メンバーコート
	 */
	private String memberCode;

	/**
	 * 決済方法
	 */
	private String payment;

	/**
	 * 注文明細リスト
	 */
	private List<OrderDetail> orderDetailList;

	/**
	 * メンバー
	 */
	private Member member;

}
