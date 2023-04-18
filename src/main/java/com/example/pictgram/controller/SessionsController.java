package com.example.pictgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*ログイン画面の制御*/
@Controller
public class SessionsController {

	/*ログイン画面の表示*/
    @GetMapping(path = "/login")
    public String index() {
        return "sessions/new";
    }

    /*ログイン失敗画面の表示*/
    @GetMapping(path = "/login-failure")
    public String loginFailure(Model model) {
        model.addAttribute("hasMessage", true);
        model.addAttribute("class", "alert-danger");
        model.addAttribute("message", "Emailまたはパスワードに誤りがあります。");

        return "sessions/new";
    }

    /*ログアウト画面の表示*/
    @GetMapping(path = "/logout-complete")
    public String logoutComplete(Model model) {
        model.addAttribute("hasMessage", true);
        model.addAttribute("class", "alert-info");
        model.addAttribute("message", "ログアウトしました。");

        return "layouts/complete";
    }
}