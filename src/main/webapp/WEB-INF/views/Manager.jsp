<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
/* $(document).ready(function(){
	$("#16").hide();
	$("#admin").hide();	
});
$(document).ready(function(){
$("#13").click(function(){
	$("#16").toggle();
	
	});	
}); */
</script>


        <style>
#header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}
#nav {
    line-height:30px;
    background-color:#eeeeee;
    height:250px;
    width:125px;
    float:left;
    padding:5px;	      
}
#section {
    width:350px;
    float:left;
    padding:10px;	 	 
}
#footer {
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}
</style>
  

    </head>

    <body>
    <div align="left">
    <form action="backtohome.htm" method="post">
    <input type="submit" value="back to home page">
    </form>
   <!--  <a href="backtohome.htm">Back to Home Page</a> -->
    </div>
      <div align="center"><h1>Welcome to Car Dealer Application</h1></div> 

	<div style="border:1px dashed white; line-height:60px; margin:30px;">
     <div align="right">
    Hi, Manager
    </div>
     <div id="nav">
<a href="cardata.htm" target="myframe">Check Database</a><br><br><br>
<a href="carorder.htm"  target="myframe">Check Orders</a><br><br><br>
<a href="finishedorder.htm"  target="myframe">Finished Orders</a>
</div>

<div id="section">

    <iframe name="myframe" frameborder="0" width="950px" height="1000px"></iframe>

</div>
   
    
   
    
   
     
         
    </body>
</html>
