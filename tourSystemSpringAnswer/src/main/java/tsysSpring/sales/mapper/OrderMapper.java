/**
 * OrderMapper.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import tsysSpring.sales.entity.Order;
import tsysSpring.sales.entity.OrderDetail;

/**
 * ORDERテーブルとORDERDETAILテーブルを利用するMapperインターフェイス
 * @author FLM
 * @version 1.0.0
 */
@Mapper
public interface OrderMapper {

	/**
	 * 引数で渡された注文情報をORDERテーブルに追加する。
	 * @param order 注文情報
	 * @return
	 */
	public void insertOrder(Order order);

	/**
	 * 引数で渡された注文明細情報をORDERDETAIL追加する。
	 * @param orderDetail 注文明細情報
	 * @return
	 */
	public void insertOrderDetail(OrderDetail orderDetail);

	/**
	 * 最後に使用された注文番号を取得する
	 * @return
	 */
	public int lastInsertOrderNo();

	/**
	 * 引数で渡されたメンバーコードに一致する注文情報リストを検索する。
	 * @param memberCode メンバーコード
	 * @return 注文情報リスト
	 */
	public List<Order> findOrderList(String memberCode);

	/**
	 * 引数で渡された注文番号に一致する注文情報を検索する。
	 * @param orderNo 注文番号
	 * @return
	 */
	public Order findOrder(int orderNo);

	/**
	 * 引数で渡された注文番号に一致する注文明細情報リストを検索する。
	 * @param orderNo 注文番号
	 * @return
	 */
	public List<OrderDetail> findOrderDetail(int orderNo);

	/**
	 * 引数で渡された注文番号に一致する注文情報を削除する。
	 * @param order 注文情報
	 * @return
	 */
	public int deleteOrder(Order order);

	/**
	 * 引数で渡された注文番号に一致する注文明細情報を削除する。
	 * @param order 注文情報
	 * @return
	 */
	public int deleteOrderDetail(Order order);

}
