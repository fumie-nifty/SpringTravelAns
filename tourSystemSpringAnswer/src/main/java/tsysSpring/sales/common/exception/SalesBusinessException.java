/**
 * SalesBusinessException.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.common.exception;

/**
 * 業務例外を扱う例外クラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
public class SalesBusinessException extends RuntimeException {

	/**
	 * コンストラクター
	 * @param message エラーメッセージ
	 */
	public SalesBusinessException(String message) {
		super(message);
	}
}
