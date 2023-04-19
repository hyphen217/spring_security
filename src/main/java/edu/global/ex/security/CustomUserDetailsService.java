package edu.global.ex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.global.ex.mapper.UserMapper;
import edu.global.ex.vo.UserDetailsVO;
import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// id/pw 결과를 무전기로 알려주는 부분
		//throws는 id를 찾을 수 없을 때 login.jsp의 <c:if test="${param.error != null}">로 이동한다.
		log.warn("Load User By UserVO number: " + username);
		UserVO vo = userMapper.getUser(username);

		log.warn("queried by UserVO mapper: " + vo);

		return vo == null ? null : new UserDetailsVO(vo);
	}
}