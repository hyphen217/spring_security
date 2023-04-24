package edu.global.ex.vo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DataSourceTest {

	@Autowired
	private DataSource datasource;
	
	@Test // Junit이 제공하는 기본적인 어노테이션
	void dataSourcetest() {
		System.out.println(datasource);
		assertNotNull(datasource);
	}
}
