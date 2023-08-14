/**
 * HotlMapper.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tsysSpring.sales.entity.Hotel;
import tsysSpring.sales.entity.Item;

/**
 * HOTELテーブルとHOTELMASTERテーブルを利用するMapperインターフェイス
 * @author FLM
 * @version 1.0.0
 */
@Mapper
public interface HotelMapper {

	/**
	 * 引数で指定した商品コードに一致するホテル情報を1件検索する。
	 * @param itemCode 商品コード
	 * @return ホテル情報
	 */
	public Hotel findHotel(String itemCode);

	/**
	 * 引数で指定した宿泊年月と都市コードに一致するホテル情報リストを検索する
	 * @param date 宿泊年月（YYYY/MM）
	 * @param cityCode 都市コード
	 * @return ホテル情報リスト
	 */
	public List<Hotel> findHotelList(@Param("date") String date, @Param("cityCode") String cityCode);

	/**
	 * 引数で指定したホテル情報の数量を在庫からマイナスする。
	 * @param item ホテル情報
	 * @return 更新したレコード数
	 */
	public int updateStockByQuantity(Item item);

}
