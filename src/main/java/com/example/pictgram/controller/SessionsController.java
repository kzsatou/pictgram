package com.example.pictgram.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*ログイン画面の制御*/
@Controller
public class SessionsController {

	@Autowired
	/* メッセージ多言語化のためのインターフェース */
	private MessageSource messageSource;

	/* ログイン画面の表示 */
	@GetMapping(path = "/login")
	public String index() {
		return "sessions/new";
	}

	/* ログイン失敗画面の表示 */
	@GetMapping(path = "/login-failure")
	/* public String loginFailure(Model model) { */
	/* 多言語対応 */
	public String loginFailure(Model model, Locale locale) {
		model.addAttribute("hasMessage", true);
		model.addAttribute("class", "alert-danger");
		// model.addAttribute("message", "Emailまたはパスワードに誤りがあります。");
		model.addAttribute("message", messageSource.getMessage("sessions.loginFailure.flash", new String[] {}, locale));

		return "sessions/new";
	}

	/* ログアウト画面の表示 */
	@GetMapping(path = "/logout-complete")
	/* public String logoutComplete(Model model) { */
	/* 多言語対応 */
	public String logoutComplete(Model model, Locale locale) {
		model.addAttribute("hasMessage", true);
		model.addAttribute("class", "alert-info");
		// model.addAttribute("message", "ログアウトしました。");
		model.addAttribute("message",
				messageSource.getMessage("sessions.logoutComplete.flash", new String[] {}, locale));

		return "layouts/complete";
	}
}