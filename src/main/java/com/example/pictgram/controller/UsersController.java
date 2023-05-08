package com.example.pictgram.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pictgram.entity.User;
import com.example.pictgram.entity.User.Authority;
import com.example.pictgram.form.UserForm;
import com.example.pictgram.repository.UserRepository;

@Controller
public class UsersController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	/* ユーザー登録画面への移行 */
	@GetMapping(path = "/users/new")
	public String newUser(Model model) {
		/* formにUserFormの値を渡して置き換える */
		model.addAttribute("form", new UserForm());
		return "users/new";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	/*
	 * public String create(@Validated @ModelAttribute("form") UserForm form,
	 * BindingResult result, Model model) {
	 */
	/* リダイレクト */
	/*
	 * public String create(@Validated @ModelAttribute("form") UserForm form,
	 * BindingResult result, Model model, RedirectAttributes redirAttrs) {
	 */
	/* 多言語化対応 */
	public String create(@Validated @ModelAttribute("form") UserForm form, BindingResult result, Model model,
			RedirectAttributes redirAttrs, Locale locale) {
		String name = form.getName();
		String email = form.getEmail();
		String password = form.getPassword();
		String passwordConfirmation = form.getPasswordConfirmation();

		if (repository.findByUsername(email) != null) {
			// FieldError fieldError = new FieldError(result.getObjectName(), "email", "その E
			// メールはすでに使用されています。");
			FieldError fieldError = new FieldError(result.getObjectName(), "email",
					messageSource.getMessage("users.create.error.1", new String[] {}, locale));
			result.addError(fieldError);
		}
		if (result.hasErrors()) {
			/* メッセージの表示 */
			model.addAttribute("hasMessage", true);
			model.addAttribute("class", "alert-danger");
			//model.addAttribute("message", "ユーザー登録に失敗しました。");
			model.addAttribute("message", messageSource.getMessage("users.create.flash.1", new String[] {}, locale));
			return "users/new";
		}

		User entity = new User(email, name, passwordEncoder.encode(password), Authority.ROLE_USER);
		/* データベースに保存 */
		repository.saveAndFlush(entity);

		/* メッセージの表示 */
		model.addAttribute("hasMessage", true);
		model.addAttribute("class", "alert-info");
		model.addAttribute("message", "ユーザー登録が完了しました。");
		return "layouts/complete";
	}
}