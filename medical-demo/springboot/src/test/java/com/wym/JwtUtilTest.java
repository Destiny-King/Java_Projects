package com.wym;

import com.wym.common.utils.JwtUtil;
import com.wym.sys.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

;

@SpringBootTest
public class JwtUtilTest {
	@Autowired
	private JwtUtil jwtUtil;

	@Test
	public void testCreteJwt() {
		User user = new User();
		user.setUsername("zhangsan");
		user.setPhone("12345678987");
		String token = jwtUtil.createToken(user);
		System.out.println(token);
	}

	@Test
	public void testParseJwt() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxODE3MWUxMy1lYzI5LTQ3NzItOTExNS0wNjU4ZWRjNTI2YWIiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNDU2Nzg5ODdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4MTEyNjc0OSwiZXhwIjoxNjgxMTI4NTQ5fQ.zserR9iFp8TjukHRkxl50DUVCd9rxCWeB8TGjl-n7mw";
		Claims claims = jwtUtil.parseToken(token);
		System.out.println(claims);
	}

	@Test
	public void testParseJwt2() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxODE3MWUxMy1lYzI5LTQ3NzItOTExNS0wNjU4ZWRjNTI2YWIiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNDU2Nzg5ODdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4MTEyNjc0OSwiZXhwIjoxNjgxMTI4NTQ5fQ.zserR9iFp8TjukHRkxl50DUVCd9rxCWeB8TGjl-n7mw";
		User user = jwtUtil.parseToken(token, User.class);
		System.out.println(user);
	}
}
