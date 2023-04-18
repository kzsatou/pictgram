package com.example.pictgram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.example.pictgram.filter.FormAuthenticationProvider;
import com.example.pictgram.repository.UserRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 非推奨

	/*
	 *　//推奨
	 * @EnableWebSecurity public class SecurityConfig {
	 */

	protected static Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	UserDetailsService service;

	@Autowired
	private FormAuthenticationProvider authenticationProvider;

	private static final String[] URLS = { "/css/**", "/images/**", "/scripts/**", "/h2-console/**" };

	/**
	 * 認証から除外する
	 */

	/* 非推奨 */

	// 主に全体に対するセキュリティ設定を行う

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(URLS);
	}

	/*
	 * // 推奨、合ってるかは分からん
	 * 
	 * @Bean public SecurityFilterChain AuthorizeHttpRequestsConfigurer(HttpSecurity
	 * http) throws Exception { http.authorizeHttpRequests(authz ->
	 * authz.mvcMatchers(URLS).permitAll()); return http.build(); }
	 */

	/**
	 * 認証を設定する
	 */

	// 非推奨
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 推奨、合ってるかは分からん
		// @Bean
		// protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws
		// Exception {
		// @formatter:off
        http.authorizeRequests().antMatchers("/login", "/logout-complete", "/users/new", "/user").permitAll()
                .anyRequest().authenticated()
                // ログアウト処理
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout-complete").clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).permitAll().and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                // form
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/topics").failureUrl("/login-failure")
                .permitAll();
        // @formatter:on

		// return http.build();
	}

	// (4) 主に認証方法の実装の設定を行う←???
	@Override 
	// @Bean
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
