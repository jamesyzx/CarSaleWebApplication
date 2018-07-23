<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#manager").hide();
	$("#admin").hide();	
});
$(document).ready(function(){
$("#show").click(function(){
	$("#manager").toggle();
	$("#admin").toggle();	
	});	
});
</script>
</head>
<body>
<div align="center"><h1>Welcome to Car Dealer Application</h1></div> 

	<div style="border:1px dashed white; line-height:60px; margin:30px;">
    
    </div>
    <div id="ma" align="right" >
    <p style="color: red" id="show">Manager Account</p>
    <a id="manager" href="manager/manager.htm">Manager</a>
     <a id="admin" href="admin/admin.htm">Admin</a>
    </div>
     <div align="center"><c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/user/login.htm" method="POST">
		<table>
		<tr>
		    <td>Email:</td>
		    <td><input type="email" name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		   <td> </td> <td colspan="5"><input type="submit" value="Login" /></td>
		</tr>
				
		</table>
	</form>
	<a href="${contextPath}/user/forgotpassword.htm">Forgot password?</a>
	<a href="${contextPath}/user/create.htm">Register User</a></div>
	
</body>
</html>