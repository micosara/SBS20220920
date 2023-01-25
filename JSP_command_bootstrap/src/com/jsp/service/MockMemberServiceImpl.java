package com.jsp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public class MockMemberServiceImpl implements MemberService{

	@Override
	public Map<String, Object> getMemberListForPage(SearchCriteria cri) throws Exception {
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
				
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		dataMap.put("memberList", memberList);
		
		
		for(int i=0;i<10;i++) {
			MemberVO member = new MemberVO();
			member.setId("mimi"+i);
			member.setPwd("mimi"+i);
			member.setName("mimi");
			
			memberList.add(member);
		}
		
		return dataMap;
	}

	@Override
	public MemberVO getMember(String id) throws Exception {
		MemberVO member = new MemberVO();
		
		member.setId("test");
		member.setPwd("test");
		member.setName("Test");
		
		return member;
	}

	@Override
	public void regist(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
