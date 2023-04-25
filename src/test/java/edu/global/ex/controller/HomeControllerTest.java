package edu.global.ex.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
// 웹 애플리케이션에서 컨트롤러를 테스트 할 때, 서블릿 컨테이너를 모킹하기 위해서는 
// @WebMvcTest 또는 @AutoConfigureMockMvc를 사용하면 된다.
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc; // 웹브라우저 역할

	@Disabled
	@Test
	void testHome() throws Exception {
		log.info("testHome()");

		// @GetMapping("/") 테스트
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andDo(print());
	}

	@Disabled
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER") // (roles="USER") 권한만 할당해도 실행됨
	void testUserHome() throws Exception {
		log.info("testUserHome()");

		// @GetMapping("/") 테스트
		mockMvc.perform(MockMvcRequestBuilders.get("/user/userHome"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andDo(print());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN") // (roles="ADMIN") 권한만 할당해도 실행됨
	void testAdminHome() throws Exception {
		log.info("testAdminHome()");

		// @GetMapping("/") 테스트
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/adminHome"))
			   .andExpect(MockMvcResultMatchers.status().isOk())
			   .andDo(print());
	}
}