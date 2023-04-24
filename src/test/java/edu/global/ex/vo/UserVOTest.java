package edu.global.ex.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserVOTest {
	@Test // Junit이 제공하는 기본적인 어노테이션
	void userVOtest() {
		UserVO vo = new UserVO();
		log.info("객체 생성" + vo);

		vo.setPassword("1234");
		vo.setUsername("홍길동");
		
		System.out.println(vo);
		// 주소가 아닌 값이 나왔기에 toString 적용을 확인할 수 있다.
		// UserVO(username=null, password=null, enabled=0, authList=null)

		assertNotNull(vo); // NotNull이면 초록창을 띄워라
		assertEquals(vo.getUsername(),"홍길동"); // 
	}
}
