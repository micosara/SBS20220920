package com.jsp.service;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.jsp.command.SearchCriteria;
import com.jsp.context.ApplicationContext;
import com.jsp.context.ApplicationContextLoader;
import com.jsp.dto.MemberVO;


public class TestMemberService {

	private MemberService memberService;// = new MemberServiceImpl();
	{
		String beanConfigXml = "build/classes/com/jsp/context/application-context.xml";
		try {
			ApplicationContextLoader.build(beanConfigXml);
			Map<String,Object> container = ApplicationContext.getApplicationContext();			
			memberService=(MemberService)container.get("memberService");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testGetMember()throws Exception{
		String id = "mimi";
		
		MemberVO member = memberService.getMember(id);
		
		Assert.assertEquals(id, member.getId());
	} 	
	
	@Test
	public void testMemberList()throws Exception{
		SearchCriteria cri = new SearchCriteria();
		Map<String,Object> dataMap = memberService.getMemberListForPage(cri);

		List<MemberVO> memberList = (List<MemberVO>)dataMap.get("memberList");
		
		Assert.assertEquals(cri.getPerPageNum(),memberList.size() );
	}
	
}





