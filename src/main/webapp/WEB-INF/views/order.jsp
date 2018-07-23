<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>Congratulation <%=session.getAttribute("username") %> buy a  <%=session.getAttribute("mark") %><%=session.getAttribute("model") %>!</h1>
  <h2>You can check our order at  <%=session.getAttribute("useremail") %> after the manager agree with your order</h2>
</body>
</html>