package tsysSpring.sales.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tsysSpring.sales.common.exception.SalesBusinessException;
import tsysSpring.sales.entity.AuthMember;
import tsysSpring.sales.form.LoginForm;
import tsysSpring.sales.service.LoginService;

/**
 * ユースケースID		UC101、UC102
 * ユースケース名		ログイン、ログアウト
 * ログイン、ログアウトを管理するコントローラクラス
 * @author FLM
 * @version 1.0.0 2022/03/20
 */
@SessionAttributes(value = {"authMember","shoppingCart"})
@Controller
public class TopController {

	/** 認証済みメンバー情報 */
	@Autowired
	AuthMember authMember;

	/**
	 * セッションの認証済みメンバー情報をModelに追加
	 * @return AuthMember
	 */
	@ModelAttribute("authMember")
	public AuthMember authMemberSetup() {
		return authMember;
	}

	/** Service */
	@Autowired
	private LoginService loginService;

	/**
	 * 初期表示
	 * @return トップ画面（M01TopMenu.html）
	 */
	@RequestMapping("/")
	public String goTop() {
		return "/M01TopMenu";
	}

	/**
	 * ログイン画面表示
	 * @param model
	 * @return ログイン画面（V0101_01Login.html）
	 */
	@RequestMapping(value = "/goLogin", params = "pageUrl")
	public String goLogin(@RequestParam String pageUrl,Model model) {

		LoginForm loginForm = new LoginForm();
		//ログイン後の遷移先ページ設定
		loginForm.setPageUrl(pageUrl);

		model.addAttribute("loginForm", loginForm);

		return "/V0101_01Login";
	}

	/**
	 * ログイン処理
	 * @param loginForm ログインフォーム
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Validated LoginForm loginForm,BindingResult result,Model model) {

		AuthMember authMember = null;

		//検証エラーがあった場合は、ログイン画面に遷移する
		if (result.hasErrors()) {
			return "/V0101_01Login";
		}

		//入力パラメータの取得
		String memberCode = loginForm.getMemberCode();
		String password = loginForm.getPassword();
		String pageUrl = loginForm.getPageUrl();

		if(pageUrl==null) {
			pageUrl = "/";
		}

		try {
			//Serviceの業務処理起動
			authMember = loginService.login(memberCode, password);
		}catch(SalesBusinessException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("loginForm", loginForm);
			return "/V0101_01Login";
		}
		//認証情報をセッションに格納
		model.addAttribute("authMember", authMember);

		return "redirect:" + pageUrl;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Model model ,SessionStatus status) {

		//セッション情報の解放
		status.setComplete();
		//model.addAttribute("loginForm", new LoginForm());

		//「/」にフォワード
		return "redirect:/";
	}

	/**
	 * 業務例外のハンドリング
	 * ハンドリングする例外クラス： SalesBusinessException.class
	 */
	@ExceptionHandler(SalesBusinessException.class)
	public String catchBizException(Model model, Exception e) {
		//modelからフォームオブジェクト取得
		LoginForm form =(LoginForm)model.getAttribute("loginForm");

		//フォームオブジェクトがない場合は、フォームオブジェクトを新規作成する
		if(form==null) {
			form = new LoginForm();
		}

		// エラーメッセージをModelに設定
		model.addAttribute("message", e.getMessage());
		// フォームオブジェクトをModelに設定
		model.addAttribute("loginForm", form);

		return "/V0101_01Login";
	}

}
