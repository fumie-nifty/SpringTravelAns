/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * ShoppingCart.java
 */
package tsysSpring.sales.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ショッピングカート情報を管理するエンティティクラス
* @author FLM
* @version 1.0.0
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@SessionScope
public class ShoppingCartBack  implements Serializable {

	/**
	 * 合計
	 */
	private int total;

	/**
	 * 商品リスト
	 */
	private List<Item> itemList;

	/**s
	 * 商品の追加
	 * @param item
	 */
	public void addItem(Item item) {
		if(itemList == null) {
			itemList = new ArrayList<Item>();
		}
		itemList.add(item);
		this.calc();
	}

	/**
	 * 合計の計算
	 */
	public void calc() {
		int total = 0;
		for( Item item: itemList) {
			total += item.getTotal();
		}

		this.total = total;
	}

}
