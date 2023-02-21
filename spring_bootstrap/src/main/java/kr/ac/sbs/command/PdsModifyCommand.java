package kr.ac.sbs.command;

import com.jsp.dto.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{
	
	private int pno;
	private int[] deleteFile;
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(int[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public PdsVO toPdsVO(){
		PdsVO pds = super.toPdsVO();
		pds.setPno(this.pno);	
		
		return pds;
	}
}










