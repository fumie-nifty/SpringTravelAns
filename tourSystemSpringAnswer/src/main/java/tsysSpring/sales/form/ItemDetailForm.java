/**
 * ItemDetailForm.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.form;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailForm implements Serializable {

	/** 商品コード */
	private String itemCode;

	/** 数量 */
	@Min(1)
	@NotNull
	private int quantity;

}
