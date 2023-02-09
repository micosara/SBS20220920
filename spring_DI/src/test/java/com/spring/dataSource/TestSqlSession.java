package com.spring.dataSource;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
@Transactional
public class TestSqlSession {

	@Autowired
	private SqlSession session;
	
	@Test
	public void testGetMember() throws SQLException{
		String testId="mimi";
		MemberVO member = session.selectOne("Member-Mapper.selectMemberById",testId);
		
		Assert.assertNotNull(member);
	}
	
	@Test
	@Rollback
	public void testInsertMember() throws SQLException{
		MemberVO member = new MemberVO();
		member.setId("dongking");
		member.setPwd("dongking");
		member.setEmail("dongking@naver.com");
		member.setName("동킹");
		member.setPhone("010-0000-0000");
		member.setPicture("notImage.jpg");
		member.setAuthority("ROLE_USER");
		
		session.update("Member-Mapper.insertMember",member);
		
		MemberVO result = session.selectOne("Member-Mapper.selectMemberById",member.getId());
		
		Assert.assertEquals(member.getId(), result.getId());
		
	}
	
}
