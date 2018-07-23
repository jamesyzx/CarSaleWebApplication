<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div>
<div><h1>Order Situation</h1></div>
<table border="1">
                <tr>
                        <td>Orderid</td>
                        <td>Userid</td>
                        <td>Carid</td>
                        <td>Price</td>
                        <td>Date</td>
                        <td>Seller</td>
                         <td>Status</td>
                </tr> 

<c:forEach items="${requestScope.orderlist}"  var="order">
         <tr>
         <td>${order.orderid}</td>
         <td>${order.userid}</td>
          <td>${order.carid}</td>
            <td>${order.price}</td>
             <td>${order.date}</td>
             <td>${order.seller}</td>
                 <td>${order.status}</td>
                  <td><a id="finish" href="orderchangestatus.htm?carid=${order.carid}&userid=${order.userid}&orderid=${order.orderid}">Agree</a></td> 
                  <td><a id="remove" href="removeorder.htm?carid=${order.carid}&useremail=${order.userid}&orderid=${order.orderid}">disagree</a></td> 
         </tr>
</c:forEach>

</table>
</div>
</body>
</html>