package com.jsp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jsp.command.SearchCriteria;
import com.jsp.datasource.MySqlSessionFactory;
import com.jsp.dto.MemberVO;


public class TestMemberDAOImpl {

	private MySqlSessionFactory sqlSessionFactory = new MySqlSessionFactory();
	private MemberDAO memberDAO = new MemberDAOImpl();
	private SqlSession session;
	
	@Before
	public void init() {
		session = sqlSessionFactory.openSession(false);
		session.commit();
	}
	
	@After
	public void destroy() {
		if(session!=null) {
			session.rollback();
			session.close();
		}
		
	}
	
	@Test
	public void testMemberList()throws Exception{
		
		SearchCriteria cri = new SearchCriteria(1,100,"p","7");
		
		List<MemberVO> memberList 
			= memberDAO.selectSearchMemberList(session, cri);
		
		Assert.assertEquals(3, memberList.size());
		
	}
	
	@Test
	public void testSelectMemberById()throws Exception {
		
		String id = "mimi";		
		
		MemberVO member = memberDAO.selectMemberById(session, id);
		
		Assert.assertEquals(id, member.getId());
	}
	
	@Test
	public void testInsertMember()throws Exception{
		MemberVO insertMember = new MemberVO();
		insertMember.setId("abc123");
		insertMember.setPwd("abc123");
		insertMember.setPhone("010-1234-4556");
		insertMember.setEmail("mimi@naver.com");
		insertMember.setPicture("c:\\");
		insertMember.setAuthority("ROLE_USER");
		insertMember.setName("mimi");
		
		memberDAO.insertMember(session, insertMember);
		
		MemberVO getMember 
				= memberDAO.selectMemberById(session, insertMember.getId());
		
		Assert.assertEquals(insertMember.getId(), getMember.getId());
		
	}
	
	@Test
	public void testUpdateMember()throws Exception{
		
		String targetID = "mimi";
		
		MemberVO targetMember = memberDAO.selectMemberById(session, targetID);
		targetMember.setName("kakaka");
		targetMember.setPwd("kakaka");
		
		memberDAO.updateMember(session, targetMember);
		MemberVO getMember = memberDAO.selectMemberById(session, targetID);
		
		Assert.assertEquals(targetMember.getName(), getMember.getName());		
		Assert.assertEquals(targetMember.getPwd(), getMember.getPwd());
	}
	
	@Test
	public void testDeleteMember()throws Exception {
		String target = "toto";
		
		MemberVO deleteMember = memberDAO.selectMemberById(session, target);		
		Assert.assertNotNull(deleteMember);
		
		memberDAO.deleteMember(session, target);
		
		MemberVO getMember =memberDAO.selectMemberById(session, target);
		Assert.assertNull(getMember);
	}
	
}










