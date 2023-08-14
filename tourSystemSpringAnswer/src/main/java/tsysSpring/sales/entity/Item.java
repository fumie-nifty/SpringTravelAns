/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Item.java
 */
package tsysSpring.sales.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 商品情報を管理するエンティティクラス
* @author FLM
* @version 1.0.0
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

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
	 * 数量
	 */
	private int quantity;

	/**
	 * 小計
	 */
	private int total;

	/**
	 * 在庫数
	 */
	private int stock;

}
