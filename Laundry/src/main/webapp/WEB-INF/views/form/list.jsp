<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList,com.example.web.model.LaundryDTO,com.example.web.util.CommonUtil,java.text.SimpleDateFormat" %>
    <jsp:include page="/head.php"></jsp:include>
    <% 
    String keyField = (String) request.getAttribute("keyField");
   String search = (String) request.getAttribute("search");
    ArrayList<LaundryDTO> items=(ArrayList<LaundryDTO>)request.getAttribute("items");
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
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
}
</script>
    <div id="container" class="container">
    <form id="form" name="form" method="post">
  
   <div class="row">
<div class="form-group">
<div class="col-md-3 col-md-5">
<select class="form-control" id="keyField" name="keyField" onchange="changekeyField()">
<option value="dong" id="dong" <%if(keyField.equals("dong")){ %>selected<%} %>>동</option>
<option value="name" id="name" <%if(keyField.equals("name")){ %>selected<%} %>>이름</option>
<option value="hosu" id="hosu"<%if(keyField.equals("hosu")){ %>selected<%} %>>호수</option>
<option value="phone" id="phone" <%if(keyField.equals("phone")){ %>selected<%} %>>연락처</option>
</select>
<input type="text" class="form-control" size="20" name="search" id="search" value="<%if(search!=""){out.println(search);} %>" placeholder="<%out.println(keyField); %>를 검색하세요."> 
    </div> <a href="javascript:search()" class="btn btn-default">검색</a>&nbsp;&nbsp;&nbsp;<a href="javascript:add()" class="btn btn-default">추가</a>
     </div>
</div>


<ul class="list-group">
<%
if(items.isEmpty()){
	%>
	<li class="list-group-item">	
	<div class="card" style="width:60rem">
  <div class="card-body">
<h3>데이터가 없습니다.</h3>	
</div>
</div>
</li>
<% 
}
else{		
for(LaundryDTO ldto:items){
	
	%>
		<li class="list-group-item">	
		<div class="card" style="width:60rem">
  <div class="card-body">
		
		<div class="card-text"><h3><a href="javascript:edit('<%=ldto.getId() %>')"><%if(!ldto.getDong().equals("")){%><%=ldto.getDong()+"동" %>&nbsp;&nbsp;&nbsp;<%}%><%if(ldto.getHosu()!=0){%><%=ldto.getHosu()+"호" %>&nbsp;&nbsp;&nbsp;<%}%><%=ldto.getName() %>&nbsp;&nbsp;&nbsp;<%=ldto.getPhone() %>&nbsp;&nbsp;&nbsp;<%=ldto.getAmount()+" 개" %>&nbsp;&nbsp;&nbsp;<%=ldto.getWork() %>&nbsp;&nbsp;&nbsp;<%=sdf.format(ldto.getDate()) %>&nbsp;&nbsp;&nbsp;<%=ldto.getOutput() %>&nbsp;&nbsp;&nbsp;<%=ldto.getPay() %>&nbsp;&nbsp;&nbsp;<%=ldto.getMsg() %></a></h3></div>

</div>
</div>
</li>
<%
}
}
%>
</ul>
</form>

</div>
