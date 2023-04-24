package edu.global.ex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.global.ex.mapper.UserMapper;
import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	void addUserTest() {
		UserVO vo = new UserVO();
		log.info("객체 생성" + vo);

		vo.setUsername("홍길동2");
		vo.setPassword("1234");

		System.out.println(vo);

		assertNotNull(vo); // NotNull이면 초록창을 띄워라
		assertEquals(vo.getUsername(), "홍길동2");

		userService.addUser(vo);
	}
}
