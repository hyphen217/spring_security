package edu.global.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.global.ex.mapper.UserMapper;
import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public int addUser(UserVO user) {
		log.info("addUser()..");
		return userMapper.insertUser(user);
	}
}
