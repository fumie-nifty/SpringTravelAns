/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 * OrderTotalByItem.java
 */
package tsysSpring.sales.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品別受注集計情報を管理するエンティティクラス
 * @author FLM
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotalByItem implements Serializable {

	/**
	 * 商品コード
	 */
	private String itemCode;

	/**
	 * 商品名
	 */
	private String itemName;

	/**
	 * 料金
	 */
	private int price;

	/**
	 * 数量合計
	 */
	private int quantityTotal;

	/**
	 * 受注合計
	 */
	private int orderTotal;

}
