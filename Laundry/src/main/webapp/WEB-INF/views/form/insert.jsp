<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/head.php"></jsp:include>
<script>
function formSubmit(sel){
	  var params = $(sel).serialize(); 
	  if($("#name").val()==""||$("#phone").val()==""||$("#amount").val()==""||$("#work").val()==""||$("#pay").val()==""||$("#output").val()==""){
				 swal("비어있는 필드를 입력해주세요.");  
		  }
	  else{
		  $.ajax({
		        url: $(sel).attr('action'),
		        type: $(sel).attr('method'),
		        data:params,
		        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
		        dataType: 'json',
		  success: function (res) {		  		        
	            if (res.result==1){
	            	swal({
	         			title: "",
	         			  text: "등록 되었습니다",
	         			  closeOnConfirm: true,
	         			  showCancelButton: false
	         			},
	         			function(){
location.reload();
	             			});
	                
	            }
	            else{
	            	swal({
	         			title: "",
	         			  text: "등록 실패 하였습니다.",
	         			  closeOnConfirm: true,
	         			  showCancelButton: false
	         			},
	         			function(){
							
	             			});
		          }
	        }        
	    });

}
return false;
}
</script>
<div id="container" class="container">
<h2 class="text-center">등록화면</h2>

<form class="form-horizontal" method="post" name="form" id="form" action="<%=request.getContextPath()%>/insert/proc.php" onsubmit="return formSubmit(this)">

<div class="row">
<div class="form-group">
<div class="col-md-3 col-md-5"><label for="name">이름</label><input type="text" size="10" class="form-control" name="name" id="name" placeholder="이름"></div>
<div class="col-md-3 col-md-5"><label for="dong">동</label><input type="text" size="10" class="form-control" name="dong" id="dong" placeholder="동"></div>
<div class="col-md-3 col-md-5"><label for="hosu">호수</label><input type="text" size="10" class="form-control"  name="hosu" id="hosu" placeholder="호수"></div>
</div>
	</div>
<div class="row">
<div class="form-group">
<div class="col-md-5 col-md-7">
<label for="phone">연락처</label>
	<input type="tel" class="form-control" name="phone" id="phone" placeholder="연락처">
	</div>
	</div>
</div>
<div class="row">
<div class="form-group">	
<div class="col-md-5 col-md-7">
<label for="amount">수량</label>
<select class="form-control" id="amount" name="amount" >

<option>수량</option>
<%for(int i=1;i<100;i++){ %>
<option value="<%=i%>"><%=i%>개</option>
<%}%>
</select>
</div>
</div>
</div>
<div class="row">
<div class="form-group">
<div class="col-md-5 col-md-7">
<label for="amount">작업</label>
<input type="text" class="form-control" name="work" id="work" placeholder="작업">
	</div>
</div>
</div>
<div class="row">
<div class="form-group">
<div class="col-md-5 col-md-9">
<label for="output">입·출고</label>
 	<select class="form-control" id="output" name="output">
 	<option>입·출고</option>
 	<option value="입고">입고</option>
 	<option value="출고">출고</option>
 	</select>
 	</div>
<div class="col-md-5 col-md-9">
<label for="pay">선·후불</label>
 	<select class="form-control" id="pay" name="pay">
 	<option>선·후불</option>
 	<option value="선불">선불</option>
 	<option value="후불">후불</option>
 	</select> </div>
</div>
</div> 	
 <input type="submit" class="btn btn-default" value="등록">
 </form>
 </div>