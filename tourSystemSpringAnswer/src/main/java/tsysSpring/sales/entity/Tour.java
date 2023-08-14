package tsysSpring.sales.entity;

/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Tour.java
 */
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* ツアー情報を管理するエンティティクラス
* @author FLM
* @version 1.0.0
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Tour extends Item  implements Serializable {

	/**
	 * ツアーコード
	 */
	private String tourCode;

	/**
	 * 開催日
	 */
	private String date;

	/**
	 * 地区コード
	 */
	private String destinationCode;

	/**
	 * 地区名
	 */
	private String destinationName;

	/**
	 * 日数
	 */
	private int days;

	/**
	 * 宿泊日数
	 */
	private int nights;

	/**
	 * ホテル商品コード
	 */
	private String hotelItemCode;

	/**
	 * ホテルコート
	 */
	private String hotelCode;

	/**
	 * ホテル名
	 */
	private String hotelName;

	/**
	 * 往路便商品コート
	 */
	private String outwardFlightItemCode;

	/**
	 * 往路便コード
	 */
	private String outwardFlightCode;

	/**
	 * 往路便名
	 */
	private String outwardFlightName;

	/**
	 * 復路便商品コード
	 */
	private String homewardFlightItemCode;

	/**
	 * 復路便コード
	 */
	private String homewardFlightCode;

	/**
	 * 復路便名
	 */
	private String homewardFlightName;

	/**
	 * 基本料金
	 */
	private int basicPrice;

}
