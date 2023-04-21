package edu.global.ex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.global.ex.security.CustomUserDetailsService;
import edu.global.ex.vo.UserVO;

@Configuration // @Component + 의미(설정할 수 있는 파일)//(객체 생성해서 IOC컨테이너에 넣어라)
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨 = 스프링 시큐리티를 작동시키는 파일이라는 것을 알려줌 - 스프링한테.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	// IOC 컨테이너에 new 객체 생성함 ⇒ Spring 문법을 사용할 수 있다. @Bean에 등록된 데이터를 @Autowired 등으로
	// 불러온다.
	// 외부 라이브러리에서 가져오는 것은 Bean을 사용하면 좋다.
	public PasswordEncoder bCryptPasswordEncoder() { // UserMapperTest.java에 return
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserVO userVO() {
		return new UserVO();
	}

//	@Bean
//	UserVO의 username과 password 값이 "메롱" 하나로 고정된다.
//	public UserVO userVO() {
//		UserVO vo = new UserVO();
//		vo.setPassword("메롱");
//		vo.setUsername("메롱");
//		return vo;
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 우선 CSRF설정을 해제한다.
		// 초기 개발시만 해주는게 좋다.
//		http.csrf().disable(); // csrf를 끈다. 키려면 해당 구문을 삭제
		http.authorizeRequests().antMatchers("/user/**").hasAnyRole("USER") // DB상에서는 ROLE_USER이다 /user/**(URL) : user로
																			// 입력하여 들어오는 모든 것에 인증을 시키겠다.
				.antMatchers("/admin/**").hasAnyRole("ADMIN") // id: admin, pw:admin /admin/**(URL) : admin으로 들어오는 모든 것에
																// 인증을 시키겠다.
				.antMatchers("/**").permitAll();

//		http.formLogin(); // 스프링 시큐리티에 있는 기본 로그인 폼을 사용하겠다.
		http.formLogin().loginPage("/login") // loginPage() 는 말 그대로 로그인 할 페이지(LoginController.java) url 비교이고,
				.usernameParameter("id") // login.jsp의 name을 database의 이름인 "username"이 아닌 "id"로 설정
				.passwordParameter("pw").permitAll(); // 모든 유저가 로그인 화면을 볼 수 있게 한다.
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER").and()
//		.withUser("admin").password("{noop}admin").roles("ADMIN");

		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder()); // 암호화 모듈 지정
	}
}