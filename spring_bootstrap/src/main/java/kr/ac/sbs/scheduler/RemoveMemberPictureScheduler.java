package kr.ac.sbs.scheduler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.ac.sbs.dao.MemberDAO;



public class RemoveMemberPictureScheduler {
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	private String picturePath;
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	private static final Logger logger = 
			LoggerFactory.getLogger(RemoveMemberPictureScheduler.class);
	
	public void removePicture() throws Exception {
		File dir = new File(picturePath);
		File[] files = dir.listFiles();

		if (files != null)
			for (File file : files) {
				if(memberDAO.selectMemberByPicture(file.getName())==null) {
					file.delete();
					logger.info("delete file : "+file.getName());
				}
			}

	}

}
