/**
 * SearchHotelServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsysSpring.sales.common.exception.SalesBusinessException;
import tsysSpring.sales.entity.Hotel;
import tsysSpring.sales.mapper.HotelMapper;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
@Service
public class SearchHotelServiceImpl implements SearchHotelService {

	/** Mapper */
	@Autowired
	HotelMapper hotelMapper;
	/**
	 * @see tsysSpring.sales.service.SearchHotelService#searchHotelList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Hotel> searchHotelList(String date, String cityCode) {
		List<Hotel> hotelList=null;

		hotelList = hotelMapper.findHotelList(date, cityCode);

		if(hotelList.isEmpty()) {
			throw new SalesBusinessException("条件に該当するホテル情報がありません。");
		}

		return hotelList;
	}

	/**
	 * @see tsysSpring.sales.service.SearchHotelService#searchHotel(java.lang.String)
	 */
	@Override
	public Hotel searchHotel(String itemCode) {
		Hotel hotel = null;

		hotel = hotelMapper.findHotel(itemCode);

		if(hotel==null) {
			throw new SalesBusinessException("該当するホテル情報がありません。");
		}

		return hotel;
	}

}
