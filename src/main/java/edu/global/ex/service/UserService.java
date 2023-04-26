package edu.global.ex.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.global.ex.mapper.UserMapper;
import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	// 1.정상 동작 코드 => User를 insert를 넣은후 권한 까지 insert 해야 됨 =>
	// 2개가 정상적인 동작이 되어야 됨(트랜잭션단위)
	public void addUser(UserVO user) {
		log.info("addUser()..");

		// 반드시 같이 이루어져야하는 작업단위이므로 Service에서 함수로 묶음(트랜잭션)
		userMapper.insertUser(user); // User를 insert를 넣은후
		userMapper.insertAuthorities(user); // 권한 설정
	}

	// 2.아래는 select * from users; 에는 들어가 있으나
	// select * from authorities 권한에는 안 들어가 있음
	public void addUser2(UserVO user) {
		log.info("addUser2()..");

		userMapper.insertUser(user); // User를 insert를 넣은후
		user = null; // 일부러 에러냄
		userMapper.insertAuthorities(user); // 권한 설정
	}

	// 3. 아래처럼 @Transactional을 붙임 롤백되어 아무런 데이터가 들어가지않음
	@Transactional // insertUser가 안된 상태로 되돌려라
	public void addUser3(UserVO user) {
		log.info("addUser3()..");

		userMapper.insertUser(user); // User를 insert를 넣은후
		user = null; // 일부러 에러냄
		userMapper.insertAuthorities(user); // 권한 설정
	}

	// 4.checked Exception을 강제로 시켜 봄 //롤백 되지 않음
	@Transactional
	public void addUser4(UserVO user) throws Exception {
		log.info("addUser()..");

		userMapper.insertUser(user); // User를 insert를 넣은후
		userMapper.insertAuthorities(user);

		// throw Checked Exception
		throw new Exception("Exception (Checked Exception)");
	}

	// 5. unChecked Exception을 강제로 시켜 봄 // 롤백 됨
	@Transactional
	public void addUser5(UserVO user) throws Exception {
		log.info("addUser5()..");

		userMapper.insertUser(user); // User를 insert를 넣은후
		userMapper.insertAuthorities(user); // 해당 코드 때문에 롤백 됨

		// throw unChecked Exception
		throw new RuntimeException("RuntimeException (unChecked Exception)");
	}

	// ★추천 6. rollbackFor 옵션을 줌 // 롤백 됨
	@Transactional(rollbackFor = Exception.class)
	public void addUser6(UserVO user) throws Exception {
		log.info("addUser()..");

		userMapper.insertUser(user); // User를 insert를 넣은후
		userMapper.insertAuthorities(user);

		// throw Checked Exception
		throw new Exception("RuntimeException (Unchecked Exception)");
	}
}