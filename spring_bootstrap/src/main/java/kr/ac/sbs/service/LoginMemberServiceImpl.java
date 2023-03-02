package kr.ac.sbs.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jsp.dto.MemberVO;
import com.spring.dao.MemberDAO;
import com.spring.service.MemberServiceImpl;

import kr.ac.sbs.exception.InvalidPasswordException;
import kr.ac.sbs.exception.NotFoundIdException;


public class LoginMemberServiceImpl extends MemberServiceImpl
								implements MemberService {


	private MemberDAO memberDAO;	

	public void setChildMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	
	@Override
	public void login(String id, String pwd) throws NotFoundIdException, InvalidPasswordException, SQLException {
		
		MemberVO member = memberDAO.selectMemberById(id);
		if (member == null)	throw new NotFoundIdException();
		if (!pwd.equals(member.getPwd())) throw new InvalidPasswordException();
		
	}

	
}








