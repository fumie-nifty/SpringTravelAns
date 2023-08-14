/**
 * LoginForm.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class LoginForm implements Serializable {

	/** メンバーコード */
	@Size(min=9,max = 9)
	@NotBlank
	private String memberCode;

	/** パスワード */
	@Size(min=4,max = 15)
	@NotBlank
	private String password;

	/** 遷移先url */
	private String pageUrl;

}
