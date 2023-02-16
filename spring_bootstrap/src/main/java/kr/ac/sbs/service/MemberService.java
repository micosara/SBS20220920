package kr.ac.sbs.service;

import java.sql.SQLException;

import kr.ac.sbs.exception.InvalidPasswordException;
import kr.ac.sbs.exception.NotFoundIdException;

public interface MemberService extends com.jsp.service.MemberService{

	void login(String id, String pwd) throws NotFoundIdException,InvalidPasswordException,
											 SQLException; 
}
