/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Member.java
 */

package tsysSpring.sales.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * メンバー情報を管理するエンティティクラス
 * @author FLM
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {

	/** メンバーコード */
	private String memberCode;

	/** 権限 */
	private String role;

	/** メンバー名 */
	private String memberName;

	/** パスワード */
	private String password;

	/** 郵便番号 */
	private String zipCode;

	/** 都道府県 */
	private String prefecture;

	/** 住所 */
	private String address;

	/** 電話番号 */
	private String tel;

	/** メールアドレス */
	private String mail;

}
