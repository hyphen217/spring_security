package edu.global.ex.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DataSourceTest {

	@Autowired
	private DataSource datasource; // 소스코드 확인

	@Test // Junit이 제공하는 기본적인 어노테이션
	// Connection Pool에서 기본적으로 제공하는 datasource
	//datasource는 connection 객체를 갖고 있음
	void dataSourcetest() {
		System.out.println(datasource);
		assertNotNull(datasource);
	}

	@Test
	void testConnection() throws Exception {
		System.out.println("DS=" + datasource);

		try (Connection conn = datasource.getConnection()) { // 해당 코드 꼭 넣기
			assertNotNull(conn); // toString 오버라이딩되었으면 문자 출력
			System.out.println("Cooooooooonn=" + conn);
			
			assertThat(conn).isInstanceOf(Connection.class);
			//Connection객체가 원본이 Connection.class인지 확인하는 문구

			assertEquals(100, getLong(conn, "select 100 from dual"));
			// assertTrue(0 < getLong(conn, "select count(*) from User"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private long getLong(Connection conn, String sql) {
		long result = 0;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
