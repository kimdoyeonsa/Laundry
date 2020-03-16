<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList,com.example.web.model.LaundryDTO" %>
    <jsp:include page="/head.php"></jsp:include>
    <% 
    String keyField = (String) request.getAttribute("keyField");
   String search = (String) request.getAttribute("search");
    ArrayList<LaundryDTO> items=(ArrayList<LaundryDTO>)request.getAttribute("items");
    %>
    <script>
   
    function add(){
	$("#container").load("<%=request.getContextPath()%>/Form/insert.php","");
	}
function search(){
	var data=$("#form").serialize();
		$("#container").load("<%=request.getContextPath()%>/Form/list.php",data);

}

$("#form").keydown(function (event)
	    {
	        if (event.keyCode == '13') {
	            if (window.event)
	            {
	            		 
	                event.preventDefault();
	                return;
	            }
	        }
	 });

function edit(idx){
	var data="idx="+idx;
	$("#container").load("<%=request.getContextPath()%>/Form/update.php",data);
}
</script>
    <div id="container" class="container">
    <form id="form" name="form" method="post">
  
   <div class="row">
<div class="form-group">
<div class="col-md-3 col-md-5">
<select class="form-control" id="keyField" name="keyField" >
<option value="dong" id="dong" <%if(keyField.equals("dong")){ %>selected<%} %>>동</option>
<option value="name" id="name" <%if(keyField.equals("name")){ %>selected<%} %>>이름</option>
<option value="hosu" id="hosu"<%if(keyField.equals("hosu")){ %>selected<%} %>>호수</option>
<option value="phone" id="phone" <%if(keyField.equals("phone")){ %>selected<%} %>>연락처</option>
</select>
<input type="text" class="form-control" size="20" name="search" id="search" value="<%if(search!=""){out.println(search);} %>" placeholder="호수를 검색하세요."> 
    </div> <a href="javascript:search()" class="btn btn-default">검색</a>&nbsp;&nbsp;&nbsp;<a href="javascript:add()" class="btn btn-default">추가</a>
     </div>
</div>

<ul class="list-group">
<%
if(items.isEmpty()){
	%>
		
	<div class="card">
  <div class="card-body">
<li class="list-group-item">데이터가 없습니다.</li>	
</div>
</div>
<% 
}
else{		
for(LaundryDTO ldto:items){
	
	%>
		
	<div class="card">
  <div class="card-body">
			
		<h3><a href="javascript:edit('<%=ldto.getId() %>')"><%=ldto.getDong()+"동" %>   <%=ldto.getHosu()+"호" %>   <%=ldto.getName() %>   <%=ldto.getPhone() %>   <%=ldto.getAmount()+" 개" %>   <%=ldto.getWork() %>   <%=ldto.getDate() %>   <%=ldto.getOutput() %>   <%=ldto.getPay() %></a></h3>

</div>
</div>
<%
}
}
%>
</ul>

</form>

</div>
