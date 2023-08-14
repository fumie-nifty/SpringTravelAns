/**
 * SearchHotelController.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import tsysSpring.sales.entity.AuthMember;
import tsysSpring.sales.entity.Hotel;
import tsysSpring.sales.form.ItemDetailForm;
import tsysSpring.sales.form.SearchHotelForm;
import tsysSpring.sales.service.SearchHotelService;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
@RequestMapping("/hotelSearch")
@SessionAttributes(value = "authMember")
@Controller
public class SearchHotelController {

	/** 認証済みメンバー情報 */
	@Autowired
	AuthMember authMember;

	/**
	 * セッションの認証済みメンバー情報をModelに追加
	 * @return AuthMember
	 */
	@ModelAttribute("authMember")
	public AuthMember authMemberSetup() {
		return authMember;
	}

	/** Service */
	@Autowired
	SearchHotelService searchHotelService;

	/**
	 * ホテル商品検索画面の初期表示
	 * @param model
	 * @return ホテル商品検索画面（V0801_01HotelFindView.html）
	 */
	@RequestMapping("/input")
	public String input(Model model) {

		SearchHotelForm searchHotelForm = new SearchHotelForm();

		model.addAttribute("searchHotelForm", searchHotelForm);

		return "/V0801_01HotelFindView";
	}

	@RequestMapping(value = "/searchHotelList", method = RequestMethod.POST)
	public String searchHotelList(@ModelAttribute("searchHotelForm") @Validated SearchHotelForm form,
													Model model) {
		List<Hotel> hotelList=null;
		int year = form.getYear();
		int month = form.getMonth();
		String cityCode = form.getCityCode();
		String date = year + "/" + String.format("%02d", month);

		hotelList=searchHotelService.searchHotelList(date, cityCode);

		form.setDate(date);
		form.setCityName(hotelList.get(0).getCityName());

		model.addAttribute("hotelList", hotelList);

		return "/V0801_01HotelFindView";
	}

	@RequestMapping(value = "/searchHotel/{itemCode}",
			method = RequestMethod.GET)
	public String searchHotel(@PathVariable String itemCode,	Model model) {

		Hotel hotel = searchHotelService.searchHotel(itemCode);

		model.addAttribute("hotel", hotel);
		model.addAttribute("itemDetailForm",new ItemDetailForm());

		return "/V0801_02HotelDetailView";
	}

}
