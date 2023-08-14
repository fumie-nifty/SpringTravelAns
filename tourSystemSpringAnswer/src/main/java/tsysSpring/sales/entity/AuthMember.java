/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * Member.java
 */

package tsysSpring.sales.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 認証したメンバー情報を管理するエンティティクラス
 * @author FLM
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@SessionScope
public class AuthMember implements Serializable {

	/** メンバーコード */
	private String memberCode;

	/** 権限 */
	private String role;

	/** メンバー名 */
	private String memberName;

}
