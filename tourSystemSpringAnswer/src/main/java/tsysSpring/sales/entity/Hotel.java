/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Hotel.java
 */
package tsysSpring.sales.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* ホテル情報を管理するエンティティクラス
* @author FLM
* @version 1.0.0
*/

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends Item implements Serializable {

	public Hotel (String itemCode, String itemName,int price,int quantity,int stock,String hotelCode,String date, String cityCode,String cityName,String grade,int basicPrice) {
		super(itemCode, itemName,price,quantity, stock,0);
		this.hotelCode=hotelCode;
		this.date=date;
		this.cityCode=cityCode;
		this.cityName=cityName;
		this.grade = grade;
		this.basicPrice = basicPrice;
	}
	/**
	 * ホテルコード
	 */
	private String hotelCode;

	/**
	 * 宿泊日
	 */
	private String date;

	/**
	 * 都市コード
	 */
	private String cityCode;

	/**
	 * 都市名
	 */
	private String cityName;

	/**
	 * グレード
	 */
	private String grade;

	/**
	 * 基本料金
	 */
	private int basicPrice;

}
