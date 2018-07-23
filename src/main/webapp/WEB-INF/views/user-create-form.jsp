<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
function checkusername(username){
	
    //1.create xmlhttprequest
	    var xmlhttp;
    if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
           xmlhttp=new XMLHttpRequest();
            }
        else
         {// code for IE6, IE5
           xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
    //2.create callback function
    //when xmh change the state, this function is invoked
    xmlhttp.onreadystatechange= function(){
        if(xmlhttp.readyState==4)
            {
            if(xmlhttp.status==200)
                {
             var  namemsg=document.getElementById("namemsg");
             namemsg.innerHTML=xmlhttp.responseText;
                }
            else
                    alert(xmlhttp.status);
        }
    }
    //3.open path 
    xmlhttp.open("GET","validatename.htm?username="+username,true);
    //4.send request
    xmlhttp.send();
}

</script>
</head>
<body>
<div align="center"><h1>Welcome to Car Dealer Application</h1></div> 

	<div style="border:1px dashed white; line-height:60px; margin:30px;">
    
    </div>
    <div align="center">
    <font color="red">${errorMessage}</font>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<form action="${contextPath}/user/create.htm" method="POST">
		<table>
		<tr>
		    <td>User Email:</td>
		    <td><input type="text" name="useremail" size="30" required="required" onblur="checkusername(this.value);"/></td>
		     <td><span id="namemsg"></span></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		<tr>
		    <td>Username:</td>
		    <td><input type="text" name="username" size="30" required="required"/></td>
		</tr>
		
		<tr>
				<td colspan="2">
				<label for="captchaCode" class="prompt">Retype the characters from the picture:</label> 
				<%
					// Adding BotDetect Captcha to the page
					Captcha captcha=Captcha.load(request,"CaptchaObject");
				    captcha.setUserInputID("captchaCode");
				    
				   String captchaHtml =captcha.getHtml();
				   out.write(captchaHtml);
					
				%> 
				<input id="captchaCode" type="text" name="captchaCode" required="required"/>
				</td>
			</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Submit" /></td>
		</tr>
				
		</table>
	</form>
    </div>

</body>
</html>