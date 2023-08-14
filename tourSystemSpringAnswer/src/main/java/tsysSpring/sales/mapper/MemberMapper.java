/**
 * MemberMapper.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tsysSpring.sales.entity.Member;

/**
 * MEMBERテーブルを利用するMapperインターフェイス
 * @author FLM
 * @version 1.0.0
 */
@Mapper
public interface MemberMapper {

	/**
	 * 引数で指定したメンバーコードとパスワードに一致するメンバーを1件検索する。
	 * @param memberCode メンバーコード
	 * @param password パスワード
	 * @return メンバー情報
	 */
	public Member findMemberPassword(@Param("memberCode") String memberCode, @Param("password") String password);

	/**
	 * 引数で指定したメンバーコードに一致するメンバーを件検索する。
	 * @param memberCode メンバーコード
	 * @return メンバー情報
	 */
	public Member findMember(String memberCode);

	/**
	 * 引数で指定したメンバーコードに一致するメンバーを件検索する。
	 * @param member メンバー情報
	 */
	public void insertMember(Member member);

	/**
	 * 引数で指定したメンバーコードに一致するメンバーを削除する。
	 * @param memberCode メンバーコード
	 * @return 削除したレコード数
	 */
	public int deleteMember(String memberCode);

	/**
	 * 引数で指定したメンバーを更新する。
	 * @param member
	 * @return 更新したレコード数
	 */
	public int updateMember(Member member);

	/**
	 * メンバーを全件検索する
	 * @return メンバー情報リスト
	 */
	public List findMemberList();
}
