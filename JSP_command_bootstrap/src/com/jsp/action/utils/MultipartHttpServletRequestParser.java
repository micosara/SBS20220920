package com.jsp.action.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.exception.NotMultipartFormDataException;

public class MultipartHttpServletRequestParser {

	private Map<String, List<String>> paramString = new HashMap<String, List<String>>();
	private Map<String, List<FileItem>> paramFile = new HashMap<String, List<FileItem>>();
	
	public MultipartHttpServletRequestParser(HttpServletRequest request, 
											 int memory_threshold, 
											 int max_file_size,
											 int max_request_size)
													throws NotMultipartFormDataException, 
														   UnsupportedEncodingException,		
														   FileUploadException {
		
		ServletFileUpload upload = 
				ServletFileUploadBuilder.build(request, memory_threshold, max_file_size,
												max_request_size);
		
		List<FileItem> formItems = upload.parseRequest(request);
		if(formItems !=null) for (FileItem item : formItems) {
			// 1.1 필드
			String paramName = item.getFieldName();
			if (item.isFormField()) { //일반 parameter : text
				List<String> paramValueList = this.paramString.get(paramName);
				if(paramValueList==null) {
					paramValueList = new ArrayList<String>();
					this.paramString.put(paramName, paramValueList); 
				}
				paramValueList.add(item.getString("utf-8"));
			}else {  //1.2 file
				List<FileItem> files = this.paramFile.get(paramName);
				
				if (files == null) {
					files = new ArrayList<FileItem>();
					this.paramFile.put(paramName, files);
				}
				
				files.add(item);
			}
			
		}
	}
	
	
	
	public String getParameter(String paramName) {
		List<String> paramValueList = paramString.get(paramName);
		
		String paramValue = paramValueList.get(0);
		
		return paramValue;
	}
	
	public String[] getParameterValues(String paramName) {
		List<String> paramValueList = paramString.get(paramName);
		String[] paramValueArray=null;
		if(paramValueList!=null) {
			paramValueArray = new String[paramValueList.size()];
			paramValueList.toArray(paramValueArray);
		}
		
		return paramValueArray;
	}
	
	public Enumeration<String> getParameterNames() {
		Enumeration<String> result = Collections.enumeration(paramString.keySet());		
		return result;
	}
	
	public FileItem getFileItem(String paramName) {
		List<FileItem> itemList = paramFile.get(paramName);
		FileItem result = null;
		
		if(itemList != null) result = itemList.get(0);
			
		return result;
	}
	
	public FileItem[] getFileItems(String paramName) {
		List<FileItem> items = paramFile.get(paramName);
		FileItem[] files =null;
		if(items!=null) {
			files = new FileItem[items.size()];
			items.toArray(files);
		}
		return files;
	}
}
