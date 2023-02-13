<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="memberList" value="${dataMap.memberList }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />

<!-- Content Wrapper. Contains page content -->    
 <div class="">
	 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>회원목록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>회원관리
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	목록
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
	 
	 
   	<section class="content">
   		<div class="card">
   			<div class="card-header with-border">
   				<button type="button" class="btn btn-primary" onclick="OpenWindow('registForm.do','회원등록',700,800);" >회원등록</button>
   				<div id="keyword" class="card-tools" style="width:550px;">
   					 <div class="input-group row">
   					 	<!-- search bar -->
   					 	<!-- sort num -->
					  	<select class="form-control col-md-3" name="perPageNum" id="perPageNum" onchange="list_go(1);">					  		  		
					  		<option value="10" ${cri.perPageNum eq 10 ? 'selected' : '' } >정렬개수</option>
					  		<option value="2" ${cri.perPageNum eq 2 ? 'selected' : '' }>2개씩</option>
					  		<option value="3" ${cri.perPageNum eq 3 ? 'selected' : '' }>3개씩</option>
					  		<option value="5" ${cri.perPageNum eq 5 ? 'selected' : '' }>5개씩</option>
					  	</select>
					  	
					  	
					  	<!-- search bar -->
					 	<select class="form-control col-md-3" name="searchType" id="searchType">
					 		<option value=""  >검색구분</option>
							<option value="i" ${cri.searchType=='i' ? 'selected':'' } >아이디</option>
							<option value="n" ${cri.searchType=='n' ? 'selected':'' }>이 름</option>
							<option value="p" ${cri.searchType=='p' ? 'selected':'' }>전화번호</option>
							<option value="e" ${cri.searchType=='e' ? 'selected':'' }>이메일</option>				 									
						</select>
						<!-- keyword -->
   					 	<input  class="form-control" type="text" name="keyword" 
   					 			placeholder="검색어를 입력하세요." value="${cri.keyword }"/>
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" 
									id="searchBtn" data-card-widget="search" onclick="list_go(1);">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					<!-- end : search bar -->		
   					 </div>
   				</div>   			
   			</div>
   			<div class="card-body" style="text-align:center;">
    		  <div class="row">
	             <div class="col-sm-12">	
		    		<table class="table table-bordered">
		    			<tr>
		    				<th>사진</th>
		                	<th>아이디</th>
		                	<th>패스워드</th>
		                	<th>이 름</th>
		                	<th>이메일</th>
		                	<th>전화번호</th>
		                	<th>등록날짜</th> <!-- yyyy-MM-dd  -->
		               	</tr>
		               	<c:if test="${!empty memberList }"  >
		            		<c:forEach items="${memberList }" var="member">
		            			<tr style="cursor:pointer;"  onclick="OpenWindow('detail.do?id=${member.id}','회원상세',700,800);">
		            				<td style="margin:0;padding:0;padding-top:5px;">
		            					<span class="manPicture" data-id="${member.id }" style="display:block;width:40px;height:40px;margin:0 auto;"></span>
		            				</td>
		            				<td>${member.id }</td>
				              		<td>${member.pwd }</td>
				              		<td>${member.name }</td>
				              		<td>${member.email }</td>
		            		  	   	<td>${member.phone.replace('-','')  }</td>
		            		  	   	<td><fmt:formatDate value="${member.regDate }" pattern="yyyy-MM-dd"/></td>
		            			</tr>
		            		</c:forEach>
		            	</c:if>
		            	
		     			<c:if test="${empty memberList }" >
		     				<tr>
		            			<td colspan="7" class="text-center">
		            				해당내용이 없습니다.
		            			</td>
		            		</tr>
		     			</c:if>	
		            </table>
    		     </div> <!-- col-sm-12 -->
    	       </div> <!-- row -->
    		</div> <!-- card-body -->
    		<div class="card-footer">
    			<!-- pagination -->
    			<div style="display:${!empty memberList ? 'visible':'none' };">
	    			<%@ include file="/WEB-INF/module/pagination.jsp" %>
	    		</div>
    		</div>
	     </div>
   	</section>
  </div>

  
<script>

  window.onload=function(){
  	MemberPictureThumb('<%=request.getContextPath()%>');
  }
</script>
  
  
  
  


