/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * OrderTotalByMember.java
 */
package tsysSpring.sales.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 受注集計情報を管理するエンティティクラス
 * @author FLM
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotalByMember implements Serializable{

	/**
	 * メンバーコード
	 */
	private String memberCode;

	/**
	 * メンバー名
	 */
	private String memberName;

	/**
	 * 受注合計
	 */
	private int orderTotal;

}
