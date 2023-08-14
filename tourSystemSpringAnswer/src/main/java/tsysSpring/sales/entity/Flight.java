/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Flight.java
 */
package tsysSpring.sales.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* フライト情報を管理するエンティティクラス
* @author FLM
* @version 1.0.0
*/

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends Item  implements Serializable {

	/**
	 * フライトコード
	 */
	private String flightCode;

	/**
	 * 運航日
	 */
	private String date;

	/**
	 * 出発時刻
	 */
	private String departureTime;

	/**
	 * 到着時刻
	 */
	private String arrivalTime;

	/**
	 * 出発空港コード
	 */
	private String departureAirportCode;

	/**
	 * 出発空港名
	 */
	private String departureAirportName;

	/**
	 * 到着空港コード
	 */
	private String arrivalAirportCode;

	/**
	 * 到着空港名
	 */
	private String arrivalAirportName;

	/**
	 * 基本料金
	 */
	private int basicPrice;

}
