<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList,com.example.web.model.LaundryDTO" %>
   <jsp:include page="/head.php"></jsp:include>
   <%
   ArrayList<LaundryDTO> items=(ArrayList<LaundryDTO>)request.getAttribute("items");
   %>
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
	         			  text: "수정 되었습니다",
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
	         			  text: "수정 실패 하였습니다.",
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

function del(idx){
	
	swal({
		title: "확인",
		  text: "삭제 하시겠습니까?",
		  closeOnConfirm: false,
		  showCancelButton: true,
		  confirmButtonText: '확인',
		  confirmButtonClass: 'btn-default',
		  cancelButtonText: '취소',
		  cancelButtonClass: 'btn-default'
		},
		function(){
			location.href="<%=request.getContextPath()%>/delete/proc.php?id="+idx;
		});
	return;
    	}
$("#hosu").keypress(function(event){
	if(event.which&&(event.which<=47||event.which>=58)&&event.which!=8){
		event.preventDefault();
	}	
	});
	$("#phone").keypress(function(event){
		if(event.which&&(event.which<=47||event.which>=58)&&event.which!=8){
			event.preventDefault();
		}	
		});

</script>
<div id="container" class="container">
<h2 class="text-center">수정화면</h2>

<form class="form-horizontal" method="post" name="form" id="form" autocomplete="off" action="<%=request.getContextPath()%>/update/proc.php" onsubmit="return formSubmit(this)">
<%for(LaundryDTO ldto:items){ %>

<div class="row">
<div class="form-group">
<div class="col-md-3 col-md-5"><label for="name">이름</label><input type="text" size="10" class="form-control" name="name" id="name" value="<%=ldto.getName() %>" placeholder="이름"></div>
<div class="col-md-3 col-md-5"><label for="dong">동</label><input type="text" size="10" class="form-control" name="dong" id="dong" value="<%=ldto.getDong() %>" placeholder="동"></div>
<div class="col-md-3 col-md-5"><label for="hosu">호수</label><input type="text" size="10" class="form-control"  name="hosu" id="hosu" value="<%if(ldto.getHosu()!=0){ %><%=ldto.getHosu() %><%} %>" placeholder="호수" style="ime-mode:disabled"></div>
</div>
	</div>
<div class="row">
<div class="form-group">
<div class="col-md-5 col-md-7">
<label for="phone">연락처</label>
	<input type="tel" class="form-control" name="phone" id="phone" maxlength="11" value="<%=ldto.getPhone() %>" placeholder="연락처">
	
	
	</div>
	</div>
</div>
<div class="row">
<div class="form-group">	
<div class="col-md-5 col-md-7">
<label for="amount">수량</label>
<select class="form-control" id="amount" name="amount"  >

<%for(int i=1;i<100;i++){ %>
<option value="<%=i%>" <%if(Integer.parseInt(ldto.getAmount())==i){ %>selected<%} %>><%=i%>개</option>
<%}%>
</select>
</div>
</div>
</div>
<div class="row">
<div class="form-group">
<div class="col-md-5 col-md-7">
<label for="work">작업</label>
<input type="text" class="form-control" name="work" id="work" value="<%=ldto.getWork() %>" placeholder="작업">
	</div>
</div>
</div>
<div class="row">
<div class="form-group">
<div class="col-md-5 col-md-9">
<label for="output">입·출고</label>
 	<select class="form-control" id="output" name="output">
 	<option value="입고" <%if(ldto.getOutput().equals("입고")){ %>selected<%} %>>입고</option>
 	<option value="출고" <%if(ldto.getOutput().equals("출고")){ %>selected<%} %>>출고</option>
 	</select>
 	</div>
<div class="col-md-5 col-md-9">
<label for="pay">선·후불</label>
 	<select class="form-control" id="pay" name="pay">
 	<option value="선불" <%if(ldto.getPay().equals("선불")){ %>selected<%} %>>선불</option>
 	<option value="후불" <%if(ldto.getPay().equals("후불")){ %>selected<%} %>>후불</option>
 	</select> </div>
</div>
</div> 	
<div class="row">
<div class="form-group">
<div class="col-md-5 col-md-7">
<input type="text" class="form-control" name="date" value="<%=ldto.getDate()%>" readonly>

 	</div>
</div>
</div> 	
 <input type="hidden" name="id" value="<%=ldto.getId() %>">
 <input type="submit" class="btn btn-default" value="수정"><a href="javascript:del('<%=ldto.getId() %>');" class="btn btn-default">삭제</a>
 <%} %> 
 </form>
 </div>