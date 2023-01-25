<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Content Wrapper. Contains page content -->
<div class="">
  	 <section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>회원등록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="#">
				        	<i class="fa fa-dashboard">회원관리</i>
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	등록
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
  	</section>
	<!-- Main content -->
	<section class="content register-page">
		<div class="register-box">
			<div class="login-logo">
    			<a href=""><b>회원 등록</b></a>
  			</div>
			<!-- form start -->
			<div class="card">				
				<div class="register-card-body">
					<form role="form" class="form-horizontal" action="regist.do" method="post">						
						<input type="hidden" name="picture" />
						<div class="input-group mb-3">
							<div class="mailbox-attachments clearfix" style="text-align: center;">
								<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;"></div>
								<div class="mailbox-attachment-info">
									<div class="input-group input-group-sm">
										<label for="inputFile" class=" btn btn-warning btn-sm btn-flat input-group-addon">파일선택</label>
										<input id="inputFileName" class="form-control" type="text" name="tempPicture" disabled/>
										<span class="input-group-append-sm">											
											<button type="button" class="btn btn-info btn-sm btn-append" onclick="upload_go();">업로드</button>											
										</span>
									</div>
								</div>
							</div>
							<br />
						  </div>	
						  <div class="form-group row">
							 <label for="id" class="col-sm-3" style="font-size:0.9em;" >
							 	<span style="color:red;font-weight:bold;">*</span>아이디</label>	
							<div class="col-sm-9 input-group input-group-sm">
								<input name="id" 
									onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, &#39;&#39;);"
								type="text" class="form-control" id="id" placeholder="13글자 영문자,숫자 조합">
								<span class="input-group-append-sm">	
									<button type="button" onclick="idCheck_go();"  class="btn btn-info btn-sm btn-append">중복확인</button>
								</span>								
							</div>								
						</div>
						<div class="form-group row">
							<label for="pwd" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>패스워드</label>
							<div class="col-sm-9 input-group-sm">								
								<input class="form-control" name="pwd" type="password" class="form-control" id="pwd"
										placeholder="20글자 영문자,숫자,특수문자 조합" />
							</div>
							
						</div>
						<div class="form-group row">
							<label for="name" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>이 름</label>
							<div class="col-sm-9 input-group-sm">								
								<input class="form-control" name="name" type="text" class="form-control" id="name"
										placeholder="이름을 입력하세요"
										onkeyup="this.value=this.value.trim();" />
							</div>
							
						</div>		
						<div class="form-group row">
							<label for="authority" class="col-sm-3" style="font-size:0.9em;" >권 한</label>
							<div class="col-sm-9">
								<select name="authority" class="form-control" style="font-size:0.9em;">
									<option value="ROLE_USER">사용자</option>
									<option value="ROLE_MANAGER">운영자</option>
									<option value="ROLE_ADMIN">관리자</option>
								</select>
							</div>
						</div>					
						<div class="form-group row">
							<label for="email" class="col-sm-3" style="font-size:0.9em;">이메일</label>
							<div class="col-sm-9 input-group-sm">
								<input name="email" type="email" class="form-control" id="email"
										placeholder="example@naver.com">
							</div>
						</div>
						<div class="form-group row">
							<label for="phone" class="col-sm-3 control-label">전화번호</label>
							<div class="col-sm-9">	
								<div class="input-group-sm">
									<select style="width:75px;" name="phone" id="phone" class="form-control float-left">
										<option value="">-선택-</option>
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="017">017</option>
										<option value="018">018</option>
									</select>							
									<label class="float-left" style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label>										
									<input style="width:68px;" name="phone" type="text" class="form-control float-left" />
									<label class="float-left" style="padding: 0; text-align: center;">&nbsp;-</label>
									<input style="width:68px;" name="phone" type="text" class="form-control float-right" />						
								</div>
							</div>
						</div>
						
						<div class="card-footer">
							<div class="row">								
								<div class="col-sm-6">
									<button type="button" id="registBtn" onclick="regist_go();" class="btn btn-info">등&nbsp;&nbsp;록</button>
							 	</div>
							 	
							 	<div class="col-sm-6">
									<button type="button" id="cancelBtn" onclick="CloseWindow();"
										class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
								</div>
							
							</div>
						</div>
					</form>					
				</div><!-- register-card-body -->
			</div>
		</div>
	</section>		<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<!-- picture upload form  -->
<form role="imageForm" action="upload/picture" method="post" enctype="multipart/form-data">
	<input id="inputFile" name="pictureFile" type="file" class="form-control" style="display:none;" onchange="picture_go();">
	<input id="oldFile" type="hidden" name="oldPicture" value="" />
	<input type="hidden" name="checkUpload" value="0" />	
</form>

<script>
function regist_go(){
	var uploadCheck = $('input[name="checkUpload"]').val();  	
	if(uploadCheck=='0'){
	    alert("사진업로드는 필수 입니다");      
	    return;
	}
	if(!$('input[name="id"]').val()){
	    alert("아이디는 필수입니다.");
	    return;
	}
	if($('input[name="id"]').val()!=checkedID){
	    alert("아이디는 중복 확인이 필요합니다.");
	    return;
	}
	if(!$('input[name="pwd"]').val()){
	   alert("패스워드는 필수입니다.");
	   return;
    }
	if(!$('input[name="name"]').val()){
       alert("이름은 필수입니다.");
       return;
    }
	if(!$('input[name="email"]').val()){
	   alert("이메일은 필수입니다.");
	   return;
	}
    
	var form = $('form[role="form"]');
	form.submit();
}
</script>

<script>





function picture_go(){	
	//업로드 확인변수 초기화 (사진변경)
	var form = $('form[role="imageForm"]');
	var picture = form.find('[name=pictureFile]')[0];
    form.find('[name="checkUpload"]').val(0);
    
    ChangeMemberPictureView(picture,$('#inputFileName')[0],$('div#pictureView')[0]);
}


function upload_go(){
	//alert("upload btn ");	
	 if(!$('input[name="pictureFile"]').val()){
	    alert("사진을 선택하세요.");
	    $('input[name="pictureFile"]').click();
	    return;
	 }  
	 
	 var formData = new FormData($('form[role="imageForm"]')[0]);
	  
		$.ajax({
			url:"picture.do",
			data:formData,
			type:"post",
		    processData:false,
	        contentType:false,
	        success:function(data){
	        	//업로드 확인변수 세팅
	            $('input[name="checkUpload"]').val(1);
	            //저장된 파일명 저장.
	            $('input#oldFile').val(data); // 변경시 삭제될 파일명	          
	            $('form[role="form"]  input[name="picture"]').val(data);	    	  
	      	 	alert("사진이 업로드 되었습니다.");
	        	
	        },
	        error:function(error){
	        	AjaxErrorSecurityRedirectHandler(error.status);	
	        }
		});
}



<!-- 아이디 중복체크 -->
var checkedID ="";
function idCheck_go(){
	//alert("id check btn click");
	
	var input_ID=$('input[name="id"]');
	
	if(!input_ID.val()){
       alert("아이디를 입력하시오");
       input_ID.focus();
       return;
	}
	
	 $.ajax({
		 url : "idCheck.do?id="+input_ID.val().trim(),    	 	
    	 success : function(result){
    	
    	  console.log(result);
   		  if(result.toUpperCase() == "DUPLICATED"){
                 alert("중복된 아이디 입니다.");
                 $('input[name="id"]').focus();
   		  }else{
              alert("사용가능한 아이디 입니다.");
              checkedID=input_ID.val().trim();
              $('input[name="id"]').val(input_ID.val().trim());             
           } 
    	 },
    	 error:function(error){
    		 AjaxErrorSecurityRedirectHandler(error.status);	
    	 }
	 });
}
</script>










