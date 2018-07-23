<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">
		<h1>Welcome to Car Dealer Application</h1>
	</div>
<div align="left"><p><h1>CART PAGE</h1></p></br>
   <a href="notbuycar.htm?id=<%=session.getAttribute("carid")%>">BACK TO SEARCH PAGE</a>
</div>

	<div style="border: 1px dashed white; line-height: 60px; margin: 30px;">
	</div>
	<div  align="center">
		<h1>Car Information</h1>
	</div>
	
	<c:choose>
			<c:when test="${(requestScope.map.type eq 'newcar')}">
			<div align="center">
		
			
	<table border="1" width="50%">
	 <c:forEach items="${requestScope.map.carlist}"  var="car">
    <tr>
      <th>CarId</th>
      <th>${car.carid}</th>
    </tr>
    <tr>
      <th>Color</th>
      <th>${car.color}</th>
    </tr>
    <tr>
      <th>BodyType</th>
       <th>${car.bodytype}</th>
    </tr>
    <tr>
      <th>Mark</th>
       <th>${car.mark}</th>
    </tr>
    <tr>
      <th>Model</th>
       <th>${car.model}</th>
    </tr>
    <tr>
      <th>Price</th>
       <th>${car.price}</th>
    </tr>
 </c:forEach>
</table>
</div>
			</c:when>
			<c:when test="${(requestScope.map.type eq 'usedcar')}">
			<div align="center">
		
			
	<table border="1" width="50%">
	 <c:forEach items="${requestScope.map.carlist}"  var="car">
    <tr>
      <th>CarId</th>
      <th>${car.carid}</th>
    </tr>
    <tr>
      <th>Color</th>
      <th>${car.color}</th>
    </tr>
    <tr>
      <th>BodyType</th>
       <th>${car.bodytype}</th>
    </tr>
    <tr>
      <th>Mark</th>
       <th>${car.mark}</th>
    </tr>
    <tr>
      <th>Model</th>
       <th>${car.model}</th>
    </tr>
    <tr>
      <th>Year</th>
       <th>${car.year}</th>
    </tr>
    <tr>
      <th>Miles</th>
       <th>${car.miles}</th>
    </tr>
    <tr>
      <th>Price</th>
       <th>${car.price}</th>
    </tr>
 </c:forEach>
</table>
</div>
			</c:when>
			</c:choose>
			
	
<div style="border: 1px dashed white; line-height: 30px; margin: 15px;">
	</div>
	<div align="center">
	<a href="notbuycar.htm?id=<%=session.getAttribute("carid")%>">Not want to buy</a>
	<div style="border: 1px dashed white; line-height: 60px; margin: 30px;">
	</div>
	</div>
	<div align="center">
	
	<a href="buycarnow.htm?id=<%=session.getAttribute("carid")%>">Buy</a> 
	
	</div>
	
</body>
</html>