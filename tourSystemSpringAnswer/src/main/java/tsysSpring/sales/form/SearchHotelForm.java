/**
 * SearchHotelForm.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ホテル検索情報フォーム
 * @author FLM
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchHotelForm implements Serializable {

	/** 年 */
	@Min(2000)
	@NotNull
	private int year;

	/** 月 */
	@Min(1)
	@Max(12)
	@NotNull
	private int month;

	/** 宿泊年月 */
	private String date;

	/** 都市コード */
	@Size(min=2,max = 2)
	@NotBlank
	private String cityCode;

	/** 都市名 */
	private String cityName;

}
