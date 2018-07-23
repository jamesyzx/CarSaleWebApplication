<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

      <c:choose>
                <c:when test="${(requestScope.map.type eq 'addproduct')}">
                    <form action="addproducttodatabse.htm?pagenum=i" method="get">
                    <input type="submit" value="Add To DataBase">
            
         
               <%
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

                 
                       
            <table border="1">
                <tr>
                        <th>ProductID</th>
                        <th>Mark</th>
                        <th>Model</th>	
                        <th>Liter</th>	
                        <th>Color</th>	
                        <th>BodyType</th>
                </tr> 
                <c:forEach items="${requestScope.map.saleslist}" var="msg">
                    <tr>
                        <td>${msg.productId}/></td>
                        <td>${msg.mark}</td>
                        <td>${msg.model}</td>
                        <td>${msg.liter}</td>
                       <td>${msg.color}</td>
                       <td>${msg.bodytype}</td>
                    </tr> 
                </c:forEach>
            </table>
                        
                        </form>
                </c:when>
           <c:when test="${(requestScope.map.type eq 'add')}">
               <h2><%=session.getAttribute("quantity")%> items are added in database!</h2>
           </c:when>
            <c:when test="${(requestScope.map.type eq 'error')}">
               <h2>These Product has been bought!</h2>
           </c:when>
             <c:otherwise>
                    <form action="displayproduct.htm" method="get">
                 file name: <input type="text" name="file"/><br>
                    <input type="submit" value="submit"/>
            
                       </form>
             </c:otherwise>  
        </c:choose>
     

    
    

</body>
</html>