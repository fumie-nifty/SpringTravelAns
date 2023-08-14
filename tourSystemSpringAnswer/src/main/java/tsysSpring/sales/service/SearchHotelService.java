/**
 * SearchHotelService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.service;

import java.util.List;

import tsysSpring.sales.entity.Hotel;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public interface SearchHotelService {

	/**
	 * 宿泊年月と都市コードを元にホテル情報リストを取得する
	 * @param date 宿泊年月
	 * @param cityCode 都市コード
	 * @return
	 */
	public List<Hotel> searchHotelList(String date,String cityCode);

	/**
	 * 商品コードを元にホテル情報を取得する
	 * @param itemCode 商品コード
	 * @return
	 */
	public Hotel searchHotel(String itemCode);
}
