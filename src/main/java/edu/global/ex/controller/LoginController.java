package edu.global.ex.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.global.ex.service.UserService;
import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder; // SecurityConfig.java의 Bean bCryptPasswordEncoder를 꺼내옴

	@GetMapping("/login")
	public String login() {
		log.info("login()..");
		return "login/login";
	}

	@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
	public String loginInfo(Authentication authentication, Principal principal, Model model) {
		// session에 저장된 시큐리티 객체에서 불러온다.(UserDetails 기반으로 만들었음)
		// Controller함수에서만 받아올 수 있음

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

	// restful방식 /{id}/{pw}
	@GetMapping("/addUser/{id}/{pw}") // url을 파라미터로 받겠다. @PathVariable에 {id}와 {pw}값을 넘김
	public String addUser(@PathVariable String id, @PathVariable String pw) throws Exception {
		System.out.println(id + ":" + pw);

		UserVO user = new UserVO();
		user.setEnabled(1);
		user.setUsername(id);
		user.setPassword(encoder.encode(pw.toString().trim())); // encoder : 암호화 객체

		System.out.println(user);
//		userService.addUser(user);
//		userService.addUser2(user);	
//		userService.addUser3(user);	
//		userService.addUser4(user);	
		userService.addUser5(user);	

		return "redirect:/";
	}
}