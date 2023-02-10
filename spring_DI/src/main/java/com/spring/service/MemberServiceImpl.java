package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.spring.dao.MemberDAO;

public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}


	@Override
	public Map<String, Object> getMemberListForPage(SearchCriteria cri) throws Exception {

		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<MemberVO> memberList = memberDAO.selectSearchMemberList(cri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(memberDAO.selectSearchMemberListCount(cri));

		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}
	
	@Override
	public MemberVO getMember(String id) throws Exception {
		MemberVO member = memberDAO.selectMemberById(id);
		return member;
	}
	
	@Override
	public void modify(MemberVO member) throws Exception {
		memberDAO.updateMember(member);
	}

	@Override
	public void regist(MemberVO member) throws Exception {
		memberDAO.insertMember(member);
	}

	@Override
	public void remove(String id) throws Exception {
		memberDAO.deleteMember(id);

	}
	
}
