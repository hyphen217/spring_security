package edu.global.ex.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

//	@Test
//	void testInserUser() {
//
////      @Insert("insert into users(username,password,enabled) values(#{username},#{password},#{enabled})")
////      public int insertUser(UserVO userVO);
////
////      @Insert("insert into AUTHORITIES (username,AUTHORITY) values(#{username},'ROLE_USER')")
////      public void insertAuthorities(UserVO UserVO);
//
//		UserVO user = new UserVO();
//		user.setUsername("kim4");
//		user.setPassword(new BCryptPasswordEncoder().encode("1234"));
//		user.setEnabled(1);
//
//		userMapper.insertUser(user);
//		userMapper.insertAuthorities(user);
//	}

	/*
	 * @Test void testInwerAdminsur() { UserVO user = new UserVO();
	 * user.setUsername("admin2"); user.setPassword(new
	 * BCryptPasswordEncoder().encode("admin2")); user.setEnabled(1);
	 * 
	 * userMapper.insertUser(user); // userMapper.insertAuthorities(user);
	 * userMapper.insertAdminAuthorities(user); }
	 */

	@Autowired
	private PasswordEncoder passwordEncoder; // SecurityConfig.java에서 받아옴
	
	@Autowired
	private UserVO userVO; // SecurityConfig.java에서 받아옴
		
	@Test
	void testPassWordEncoder() {
		String plainPW = "1234"; // 평문
		String encodedPW = passwordEncoder.encode(plainPW);
		
//		String encodedPW = new BCryptPasswordEncoder().encode(plainPW);
		// BCryptPasswordEncoder : 암호화 모듈
		System.out.println(plainPW + ":" + encodedPW);
		System.out.println(passwordEncoder.matches(plainPW, encodedPW));
		System.out.println(userVO);
		// ==================================================================
//		assertNotEquals(plainPW, encodedPW); // plainPW와 encodedPW가 같지 않다면 true → 결과 : true
//		assertEquals(plainPW, encodedPW); // plainPW와 encodedPW가 같다면 true → 결과 : false 암호화 하여 값이 같지 않음
//		assertTrue(new BCryptPasswordEncoder().matches(plainPW, encodedPW)); // boolean타입으로 plainPW와 encodedPW가 같다면 true → 결과 : true
//		matches(평문, DB의 암호화된 문) 암호화 키 복호화 키 둘 다 갖고 있음
//		SecurityConfig.java의 customUserDetailsService(암호화 모듈 지정) 객체 안에 matches 함수를 사용하면 평문과 DB의 암호화된 문을 비교하여 true인지 false인지 확인함
	}
}
