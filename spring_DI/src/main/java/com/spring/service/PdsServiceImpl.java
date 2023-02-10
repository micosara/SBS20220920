package com.spring.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.spring.dao.AttachDAO;
import com.spring.dao.PdsDAO;

public class PdsServiceImpl implements PdsService{
	
	private PdsDAO pdsDAO;
	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}
	
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
	}
	
	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		
		List<PdsVO> pdsList = pdsDAO.selectPdsCriteria(cri);

		if (pdsList != null)
			for (PdsVO pds : pdsList)
				addAttachList(pds);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(pdsDAO.selectPdsCriteriaTotalCount(cri));

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("pdsList", pdsList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public PdsVO getPds(int pno) throws SQLException {

		PdsVO pds = pdsDAO.selectPdsByPno(pno);
		addAttachList(pds);

		return pds;
	}

	@Override
	public void regist(PdsVO pds) throws SQLException {

		int pno = pdsDAO.selectSeqNextValue();

		pds.setPno(pno);
		pdsDAO.insertPds(pds);

		if (pds.getAttachList() != null)
			for (AttachVO attach : pds.getAttachList()) {
				attach.setPno(pno);
				attach.setAttacher(pds.getWriter());
				attachDAO.insertAttach(attach);
			}

	}

	@Override
	public void modify(PdsVO pds) throws SQLException {

		pdsDAO.updatePds(pds);
		// attachDAO.deleteAllAttach(pds.getPno());

		if (pds.getAttachList() != null)
			for (AttachVO attach : pds.getAttachList()) {
				attach.setPno(pds.getPno());
				attach.setAttacher(pds.getWriter());
				attachDAO.insertAttach(attach);

			}

	}

	@Override
	public void remove(int pno) throws SQLException {

		pdsDAO.deletePds(pno);
	}

	@Override
	public PdsVO read(int pno) throws SQLException {

		PdsVO pds = pdsDAO.selectPdsByPno(pno);
		pdsDAO.increaseViewCnt(pno);

		addAttachList(pds);

		return pds;
	}

	@Override
	public AttachVO getAttachByAno(int ano) throws SQLException {

		AttachVO attach = attachDAO.selectAttachByAno(ano);

		return attach;
	}

	@Override
	public void removeAttachByAno(int ano) throws SQLException {

		attachDAO.deleteAttach(ano);

	}

	private void addAttachList(PdsVO pds) throws SQLException {

		if (pds == null)
			return;

		int pno = pds.getPno();
		List<AttachVO> attachList = attachDAO.selectAttachesByPno(pno);

		pds.setAttachList(attachList);
	}

}
