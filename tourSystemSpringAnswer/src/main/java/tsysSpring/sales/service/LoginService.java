/**
 * LoginService.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.service;

import tsysSpring.sales.entity.AuthMember;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public interface LoginService {

	/**
	 * メンバーの認証を行う。
	 * 認証に失敗した場合はSalesBusinessExceptionをスローする。
	 * @param memberCode メンバーコード
	 * @param password パスワード
	 * @return
	 */
	public AuthMember login(String memberCode,String password) ;

}
