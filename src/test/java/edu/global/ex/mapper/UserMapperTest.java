package edu.global.ex.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	@Test
	void testInwerAdminsur() {
		UserVO user = new UserVO();
		user.setUsername("admin2");
		user.setPassword(new BCryptPasswordEncoder().encode("admin2"));
		user.setEnabled(1);

		userMapper.insertUser(user);
//		userMapper.insertAuthorities(user);
		userMapper.insertAdminAuthorities(user);
	}
}
