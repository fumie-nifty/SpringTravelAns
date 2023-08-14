/**
 * SalesSystemExceptionHandler.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package tsysSpring.sales.common.exception;


import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * システム例外をハンドリングするクラス
 * すべてのControllerクラスで発生するシステム例外をハンドリングする
 * @author FLM
 * @version 1.0 2022/03/20
 */
@ControllerAdvice
public class SalesSystemExceptionHandler {

	/**
	 * システム例外（SalesSystemException・DataAccessException）をハンドリングする
	 * レスポンスステータスコード： HttpStatus.INTERNAL_SERVER_ERROR
	 * ハンドリングする例外クラス： { SalesSystemException.class, DataAccessException.class }
	 * @param model モデル
	 * @return エラー画面（/V901_01SystemErrorPage）
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ SalesSystemException.class, DataAccessException.class })
	public String handleError(Model model) {
		String message = "システムエラーです。システム管理者に連絡してください。";
		model.addAttribute("message", message);
		return "/V901_01SystemErrorPage";
	}
}
