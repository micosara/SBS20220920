<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
    <div class="p-3">
      <h5>Title</h5>
      <p>Sidebar content</p>
    </div>
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Anything you want
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<script>
	function subMenu_go(mCode){
		//alert(mCode);
		if(mCode!="M000000"){			
			$.ajax({
				url:"<%=request.getContextPath()%>/subMenu.do?mCode="+mCode,
				type:"get",				
				success:function(data){
					//console.log(JSON.stringify(data));
					printData(data,$('.subMenuList'),$('#subMenu-list-template'),'.subMenu');
				},
				error:function(error){
					AjaxErrorSecurityRedirectHandler(error.status);	
				}				
			});
			
		}else{
			$('.subMenuList').html("");		
		}
	}
	
	//handelbars printElement (args : data Array, appent target, handlebar template, remove class)
	function printData(dataArr,target,templateObject,removeSelector){
		
		var template=Handlebars.compile(templateObject.html());
		
		var html = template(dataArr);
		
		$(removeSelector).remove();
		target.append(html);
	}
	
	function goPage(url,mCode){
		//alert(url);
		$('iframe[name="ifr"]').attr("src",url);
		
		  // HTML5 지원브라우저에서 사용 가능
		if (typeof(history.pushState) == 'function') {
		    //현재 주소를 가져온다.
		    var renewURL = location.href;
		    //현재 주소 중 .do 뒤 부분이 있다면 날려버린다.
		    renewURL = renewURL.substring(0, renewURL.indexOf(".do")+3);
		    
		    if (mCode != 'M000000') {
		        renewURL += "?mCode="+mCode;
		    }
		    //페이지를 리로드하지 않고 페이지 주소만 변경할 때 사용
		    history.pushState(mCode, null, renewURL);
		 
		} else {
		    location.hash = "#"+mCode;
		}
	}
	
	window.onload=function(){
		goPage('<%=request.getContextPath()%>${menu.murl}','${menu.mcode}');
		subMenu_go('${menu.mcode}'.substring(0,3)+"0000");
	}
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<script type="text/x-handlebars-template"  id="subMenu-list-template" >
{{#each .}}
	<li class="nav-item subMenu" >
    	<a href="javascript:goPage('<%=request.getContextPath()%>{{murl}}','{{mcode}}');"	class="nav-link">
        	<i class="{{micon}}"></i>
            <p>{{mname}}</p>
       	</a>
	</li>
{{/each}}
</script>


<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
<!-- common.js  -->
<script src="<%=request.getContextPath() %>/resources/js/common.min.js"></script>

</body>
</html>


