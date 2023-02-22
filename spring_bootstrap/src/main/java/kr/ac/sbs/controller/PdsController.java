package kr.ac.sbs.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.action.utils.MakeFileName;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

import kr.ac.sbs.command.PdsModifyCommand;
import kr.ac.sbs.command.PdsRegistCommand;

@Controller
@RequestMapping("/pds")
public class PdsController {
	
	@Resource(name="pdsService")
	private PdsService service;	
	
	@GetMapping("/main")
	public void main() throws Exception {}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws Exception {
		String url = "/pds/list";

		Map<String, Object> dataMap = service.getList(cri);

		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}
	
	@RequestMapping("/registForm")
	public String registForm() throws Exception {
		String url = "pds/regist";
		return url;
	}
	
	@Resource(name = "fileUploadPath")
	private String fileUploadPath;
	
	private List<AttachVO> saveFileToAttaches(List<MultipartFile> multiFiles,
											  String savePath )throws Exception{
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		//저장 -> attachVO -> list.add
		if (multiFiles != null) {
			for (MultipartFile multi : multiFiles) {
				String fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
				File target = new File(savePath, fileName);
				target.mkdirs();
				multi.transferTo(target);

				AttachVO attach = new AttachVO();
				attach.setUploadPath(savePath);
				attach.setFileName(fileName);
				attach.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1)
						.toUpperCase());
				
				attachList.add(attach);
			}
		}
		return attachList;
	}
	
	@PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
	public String regist(PdsRegistCommand registReq, HttpServletRequest request,RedirectAttributes rttr) 
																	throws Exception {
		String url = "redirect:/pds/list.do";		
				
		List<MultipartFile> multiFiles = registReq.getUploadFile();
		String savePath = this.fileUploadPath;		
		
		List<AttachVO> attachList = saveFileToAttaches(multiFiles,savePath);
		
		//DB 
		PdsVO pds = registReq.toPdsVO();
		String XSStitle = (String)request.getAttribute("XSStitle");
		if(XSStitle !=null) pds.setTitle(XSStitle);
		
		pds.setAttachList(attachList);
		service.regist(pds);
		
		//output
		rttr.addFlashAttribute("from", "regist");
		
		
		return url;
	}
	
	@GetMapping("/detail")
	public ModelAndView detail(int pno, String from, 
								RedirectAttributes rttr,
							    ModelAndView mnv) throws Exception {
		String url = "/pds/detail";

		PdsVO pds = null;
		if (from != null && from.equals("list")) {
			pds = service.read(pno);
			url = "redirect:/pds/detail.do";
			
			rttr.addAttribute("pno",pno);
			mnv.setViewName(url);
			return mnv;
		} 
		
		pds = service.getPds(pno);
		
		
		// 파일명 재정의
		if (pds != null) {
			List<AttachVO> attachList = pds.getAttachList();
			if (attachList != null) {
				for (AttachVO attach : attachList) {
					String fileName = attach.getFileName().split("\\$\\$")[1];
					attach.setFileName(fileName);
				}
			}
		}
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);

		return mnv;
	}
	
	@GetMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int pno,RedirectAttributes rttr) throws Exception {
		String url = "/pds/modify";
		
		mnv = detail(pno,"modify",rttr,mnv);
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping(value="/modify", produces = "text/plain;charset=utf-8")
	public String modifyPOST(PdsModifyCommand modifyReq,HttpServletRequest request,RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";
		
		// 파일 삭제
		if (modifyReq.getDeleteFile() != null && modifyReq.getDeleteFile().length > 0) {
			for (int ano : modifyReq.getDeleteFile()) {				
				AttachVO attach = service.getAttachByAno(ano);
				
				File deleteFile = new File(attach.getUploadPath(), attach.getFileName());
				
				if (deleteFile.exists()) {
					deleteFile.delete(); // File 삭제
				}
				service.removeAttachByAno(ano); // DB 삭제
				
			}
		}
		
		//파일저장
		List<AttachVO> attachList 
			= saveFileToAttaches(modifyReq.getUploadFile(), fileUploadPath);
		
		// PdsVO settting
		PdsVO pds = modifyReq.toPdsVO();	
		String XSStitle = (String)request.getAttribute("XSStitle");
		if(XSStitle !=null) pds.setTitle(XSStitle);
		pds.setAttachList(attachList);
		
		// DB 저장
		service.modify(pds);

		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("pno", pds.getPno());
		
		return url;
	}
	
	@GetMapping("/remove")
	public String remove(int pno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";

		// 첨부파일 삭제
		List<AttachVO> attachList = service.getPds(pno).getAttachList();
		if (attachList != null) {
			for (AttachVO attach : attachList) {
				File target = new File(attach.getUploadPath(), attach.getFileName());
				if (target.exists()) {
					target.delete();
				}
			}
		}
		// DB삭제
		service.remove(pno);
		
		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("pno", pno);
		
		return url;
	}
	
	@GetMapping("/getFile")
	public String getFile(int ano,Model model) throws Exception {
		
		String url="downloadFile"; //bean name
		
		AttachVO attach = service.getAttachByAno(ano);
		

		model.addAttribute("savedPath", attach.getUploadPath());
		model.addAttribute("fileName", attach.getFileName());		
	
		return url;
	}
}












