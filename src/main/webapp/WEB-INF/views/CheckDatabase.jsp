<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script>
	var app = angular.module('myApp', []);
	app.controller('customersCtrl', function($scope, $http) {
		$http.get("newcardata.htm").then(function(response) {
			$scope.names = response.data.records;
		});
	});
</script>
</head>
<body>
	<div>
		<div>
			<h1>Database Storage</h1>
		</div>
		<div>
			<a id="newcar" href="newcardata.htm">NEW CAR</a> <a id="usedcar"
				href="usedcardata.htm">USED CAR</a>
		</div>


		<c:choose>
			<c:when test="${(requestScope.map.type eq 'usedcar')}">
				<%-- <%
               int pu = (Integer)session.getAttribute("page");
               int p;
               if(session.getAttribute("present")!=null)
               {
               p= Integer.parseInt((String)session.getAttribute("present"));
               }
               else
              	 p=1;
               
               int maxp = pu/10;
                if (p <= 5) {
            %>
               <table><tr>  
                    <%
                        for (int i = 1; i <= Math.min(11,maxp); i++) {
                            if (i == p) {
                    %>
                    <td><%=i%></td>
                    <%
                    } else {
                    %>
                    <td>[<a href="viewproductbypage.htm?pagenum=<%=i%>"><%=i%></a >]</td>
                    <%
                            }
                        }
                    } else if (p > 5) { %>
                <table><tr>  
                        <%
                            for (int i = p - 5; i <= Math.min(p+5, maxp); i++) {
                                if (i == p) {
                        %>
                        <td><%=i%></td>
                        <%
                        } else {
                        %>
                        <td>[<a href="viewproductbypage.htm?pagenum=<%=i%>"><%=i%></a >]</td>
                        <%
                                    }
                                }
                            }
                        %> --%>

				<table border="1">
					<tr>
						<th>Car ID</th>
						<th>Mark</th>
						<th>Model</th>
						<th>Liter</th>
						<th>Color</th>
						<th>BodyType</th>
						<th>Miles</th>
						<th>Year</th>
						<th>Price</th>
					</tr>
					<c:forEach items="${requestScope.map.carlist}" var="msg">
						<tr>
							<td>${msg.carid}/></td>
							<td>${msg.mark}</td>
							<td>${msg.model}</td>
							<td>${msg.liter}</td>
							<td>${msg.color}</td>
							<td>${msg.bodytype}</td>
							<td>${msg.miles}</td>
							<td>${msg.year}</td>
							<td>${msg.price}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
			<%-- 	<%
               int pu = (Integer)session.getAttribute("page");
               int p;
               if(session.getAttribute("present")!=null)
               {
               p= Integer.parseInt((String)session.getAttribute("present"));
               }
               else
              	 p=1;
               
               int maxp = pu/10;
                if (p <= 5) {
            %>
               <table><tr>  
                    <%
                        for (int i = 1; i <= Math.min(11,maxp); i++) {
                            if (i == p) {
                    %>
                    <td><%=i%></td>
                    <%
                    } else {
                    %>
                    <td>[<a href="viewproductbypage.htm?pagenum=<%=i%>"><%=i%></a >]</td>
                    <%
                            }
                        }
                    } else if (p > 5) { %>
                <table><tr>  
                        <%
                            for (int i = p - 5; i <= Math.min(p+5, maxp); i++) {
                                if (i == p) {
                        %>
                        <td><%=i%></td>
                        <%
                        } else {
                        %>
                        <td>[<a href="viewproductbypage.htm?pagenum=<%=i%>"><%=i%></a >]</td>
                        <%
                                    }
                                }
                            }
                        %> 
 --%>
				<table border="1">
					<tr>
						<th>CarID</th>
						<th>Mark</th>
						<th>Model</th>
						<th>Liter</th>
						<th>Color</th>
						<th>BodyType</th>
						<th>Price</th>
					</tr>
					<c:forEach items="${requestScope.carlist}" var="msg">
						<tr>
							<td>${msg.carid}/></td>
							<td>${msg.mark}</td>
							<td>${msg.model}</td>
							<td>${msg.liter}</td>
							<td>${msg.color}</td>
							<td>${msg.bodytype}</td>
							<td>${msg.price}</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>

	</div>


</body>
</html>