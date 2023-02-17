<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   
<head>
</head>
<title>게시글 수정</title>
<body>   
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>수정하기</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>자유게시판
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	수정하기
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
  <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header row">
						<h4 class="col-md-8">글 수정</h4>
						<div class="col-md-4">
							<div class="float-right">
								<button type="button" class="btn btn-warning" id="modifyBtn" onclick="modify_go();">수 정</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-default " id="cancelBtn" onclick="history.go(-1);">취 소</button>
							</div>
						</div>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="modify.do" name="modifyForm">
							<input type="hidden" name="bno" value="${board.bno }" />
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title" name='title' class="form-control" value="${board.title }">
							</div>
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" id="writer" readonly	name="writer" class="form-control"  value="${board.writer }">
							</div>
							<div class="form-group">
								<label for="content">내 용</label>		
								<textarea class="form-control" name="content" id="content" rows="3"
									placeholder="500자 내외로 작성하세요.">${fn:escapeXml(board.content) }</textarea>						
							</div>
						</form>
					</div><!--end card-body  -->
					
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->    
     
    <script>
    	window.onload=function(){
    		summernote_go($("#content"),'<%=request.getContextPath()%>');
    	}
    	
    	function modify_go(){
    		$("form[role='form']").submit();
    	}
    </script>
    
  
</body>    