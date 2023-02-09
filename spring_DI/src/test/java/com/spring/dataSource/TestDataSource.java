package com.spring.dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsp.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
public class TestDataSource {
	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;

	@Before
	public void init()throws SQLException {
		conn=dataSource.getConnection();
	}
	
	@After
	public void destroy()throws SQLException {
		conn.close();
	}
	
	@Test
	public void testConnection() throws SQLException {
		Assert.assertNotNull(conn);
		
		Statement stmt = conn.createStatement();
		
		String sql = "select * from member";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		while(rs.next()) {
			MemberVO member = new MemberVO();
			member.setId(rs.getString("id"));
			member.setPwd(rs.getString("pwd"));
			
			memberList.add(member);
		}
		rs.close();
		stmt.close();
		Assert.assertEquals(22, memberList.size());
	}
	
}
