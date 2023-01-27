package com.jsp.command;

public class SearchCriteriaCommand {
	private String page;
	private String perPageNum;
	private String keyword;
	private String searchType;
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(String perPageNum) {
		this.perPageNum = perPageNum;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public SearchCriteria toSearchCriteria() {
		SearchCriteria cri = new SearchCriteria();
		
		if(page !=null && !page.isEmpty() ) {
			cri.setPage(Integer.parseInt(page));				
		}				
		if(perPageNum !=null && !perPageNum.isEmpty()) {
			cri.setPerPageNum(Integer.parseInt(perPageNum));
		}
		
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		
		return cri;
	}
}






