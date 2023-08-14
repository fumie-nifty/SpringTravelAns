/**
 * LoginServiceImpl.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tsysSpring.sales.common.exception.SalesBusinessException;
import tsysSpring.sales.entity.AuthMember;
import tsysSpring.sales.entity.Member;
import tsysSpring.sales.mapper.MemberMapper;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
@Service
public class LoginServiceImpl implements LoginService {

	/** Mapper */
	@Autowired
	MemberMapper memberMapper;

	/**
	 * @see tsysSpring.sales.service.LoginService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public AuthMember login(String memberCode, String password) {
		AuthMember authMember = null;

		Member member = memberMapper.findMemberPassword(memberCode, password);

		if(member==null) {
			throw new SalesBusinessException("メンバーコードまたはパスワードが不正です。");
		}

		authMember = new AuthMember(memberCode,member.getRole(),member.getMemberName());

		return authMember;
	}

}
