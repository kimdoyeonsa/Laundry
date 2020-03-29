<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   <script>
   function reload(){
	location.href="<%=request.getContextPath()%>/index.php";
   }
   </script>
    <center>
  <pre>
<h1>
<font color="#ff0000">
${msg}
</font>
</h1>
</pre>
</center>
<button onclick="reload()">되돌아가기</button>