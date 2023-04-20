package edu.global.ex.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		log.info("login()..");
		return "login/login";
	}

	@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
	public String loginInfo(Authentication authentication, Principal principal, Model model) {
		// session에 저장된 시큐리티 객체에서 불러온다.(UserDetails 기반으로 만들었음)
		//Controller함수에서만 받아올 수 있음

		String user_id;

		// 1.SpringContextHolder를 통하여 가져오는 방법(일반적인 빈에서 사용 할수있음 )
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user_id = auth.getName();
		System.out.println("유저 아이디:" + user_id);

		// 2.authentication 객체로 가져오는 방법(유저이름, 권한 가져올 수 있음)
		System.out.println("authentication 유저 아이디:" + authentication.getName());
		System.out.println("authentication 권한들:" + authentication.getAuthorities());

		// 3.Principal 객체로 가져오는 방법(가져올수 있는게 getName() 정도)
		System.out.println("Principal 유저 아이디:" + principal.getName());

		return "redirect:/";
	}
}