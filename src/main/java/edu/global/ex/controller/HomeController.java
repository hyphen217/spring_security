package edu.global.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/user/userHome")
	public void userHome() { // void는 return값이 없지만, userHome.jsp를 리턴함
		log.info("userHome ...");
	}
//상기 userHome함수는 하기와 동일한 의미이다.
//	@GetMapping("/user/userHome")
//	public String userHome() {
//		log.info("userHome ...");
//		return "user/userHome";
//	}

	@GetMapping("/admin/adminHome")
	public void adminHome() {
		log.info("adminHome ...");
	}
}