package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dao.AttachDAO;
import com.jsp.dao.PdsDAO;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;

public class PdsServiceImpl implements PdsService {

	private SqlSessionFactory sqlSessionFactory;
	private PdsDAO pdsDAO;
	private AttachDAO attachDAO;

	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}

	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<PdsVO> pdsList = pdsDAO.selectPdsCriteria(session, cri);
			if (pdsList != null)
				for (PdsVO pds : pdsList)
					addAttachList(session, pds);
			
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(pdsDAO.selectPdsCriteriaTotalCount(session, cri));

			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("pdsList", pdsList);
			dataMap.put("pageMaker", pageMaker);

			return dataMap;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(PdsVO pds) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			int pno = pdsDAO.selectSeqNextValue(session);
			pds.setPno(pno);
			pdsDAO.insertPds(session, pds);

			if (pds.getAttachList() != null)
				for (AttachVO attach : pds.getAttachList()) {
					attach.setPno(pno);
					attach.setAttacher(pds.getWriter());
					attachDAO.insertAttach(session, attach);
				}

		} finally {
			session.close();
		}

	}

	@Override
	public PdsVO read(int pno) throws SQLException {

		SqlSession session = sqlSessionFactory.openSession();
		try {

			PdsVO pds = pdsDAO.selectPdsByPno(session, pno);
			pdsDAO.increaseViewCnt(session, pno);

			addAttachList(session, pds);
			
			return pds;

		} finally {
			session.close();
		}
	}

	@Override
	public PdsVO getPds(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			PdsVO pds = pdsDAO.selectPdsByPno(session, pno);

			addAttachList(session, pds);
			
			return pds;
		} finally {
			session.close();
		}
	}

	@Override
	public void modify(PdsVO pds) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			pdsDAO.updatePds(session, pds);

			if (pds.getAttachList() != null)
				for (AttachVO attach : pds.getAttachList()) {					
					attachDAO.insertAttach(session, attach);
				}

		} finally {
			session.close();
		}
	}

	@Override
	public void remove(int pno) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			pdsDAO.deletePds(session, pno);

		} finally {
			session.close();
		}
	}
	
	

	private void addAttachList(SqlSession session, PdsVO pds) throws SQLException {

		if (pds == null)
			return;

		int pno = pds.getPno();
		List<AttachVO> attachList = attachDAO.selectAttachesByPno(session, pno);

		pds.setAttachList(attachList);
	}

	@Override
	public AttachVO getAttachByAno(int ano) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			AttachVO attach = attachDAO.selectAttachByAno(session, ano);

			return attach;
		} finally {
			session.close();
		}
	}
	
	@Override
	public void removeAttachByAno(int ano) throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {

			attachDAO.deleteAttach(session, ano);

		} finally {
			session.close();
		}

	}
}
