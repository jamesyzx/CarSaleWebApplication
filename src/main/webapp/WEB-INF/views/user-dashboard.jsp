<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" import="java.text.*"%>

<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<script type="text/javascript">
	
<%Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");%>
	function ajaxEvent() {

		var xmlHttp;
		try // Firefox, Opera 8.0+, Safari
		{
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try // Internet Explorer
			{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser does not support AJAX!");
					return false;
				}
			}
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {

			}
		}

		var queryString = document.getElementById("queryString").value;

		xmlHttp.open("POST", "checkvalidate.htm?validatecode=" + queryString,
				true);
		xmlHttp.send();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Dashboard</title>
<style type="text/css">
.align-center {
	margin: 20px auto;
	width: 500px;
	background: white;
	text-align: center;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#finish").hide();

	});
	$(document).ready(function() {
		$("#validatesumbit").click(function() {
			var v = $("#querystring").val();
			var order = $("#orderid").val();
			$.ajax({
				type : "GET",
				url : "checkvalidate.htm",
				data : {
					"validatecode" : v,
					"orderid" : order
				},
				dataType : "text",
				success : function(data) {
					alert(data);
					$("#validate").hide();
					$("#finish").toggle();
				},
				error : function(data, status, e) {
					alert("Information missing");
				}

			});

		});

	});
</script>



<script>
	var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope, $timeout, $interval, $http) {
		$scope.theTime = new Date().toLocaleTimeString();
		$scope.myHeader = "Hello!";
		$interval(function() {
			$scope.theTime = new Date().toLocaleTimeString();
		}, 1000);
		$timeout(function() {
			$scope.myHeader = "How are you today?";
		}, 2000);
		$http.get("searchcarbyall.htm").then(function(response) {
			$scope.payWayList = response.data;

		});

		$scope.payWayCHange = function(select1) {
			$scope.select2 = $scope.select1.model[0];
		};

		$scope.submit = function() {

			var postData = "?mark=" + $scope.select1.mark + "&" + "model="
					+ $scope.select2.model;
			var url = "searchcar.htm" + postData;
			$http.post(url).then(function(response) {
				$scope.names = response.data.records;

			});
		};

		$scope.submitusedcar = function() {
			var postData = "?mark=" + $scope.select1.mark + "&" + "model="
					+ $scope.select2.model;
			var url = "searchusedcar.htm" + postData;
			$http.post(url).then(function(response) {
				$scope.names = response.data.records;

			});
		};
		$scope.addtocart = function() {
			alert($scope.cart);
			var postData = "?mark=" + JSON.parse($scope.names);
			var url = "buynewcar.htm" + postData;
			$http.post(url).then(function(response) {
				//$scope.names = response.data.records;

			});
		};
		$scope.remove = function(array, index) {
			alert(index, 1);
			array.splice(index, 1);
		};

	});
</script>
</head>
<body ng-app="myApp" ng-controller="myCtrl">


	<div class="align-center">
		<h1>Welcome to Car Dealer Application</h1>
	</div>

	<div style="border: 1px dashed white; line-height: 60px; margin: 30px;">
	</div>


	<div>
		<div>
			<h4>{{myHeader}}</h4>




			<%
				Date a = sdf.parse("12:00:00");
				Date c = sdf.parse("18:00:00");
				Date b = sdf.parse(sdf.format(date));
				if (b.before(a)) {
					out.println("Good morning!");
				} else if ((b.after(a)) && (b.before(c))) {
					out.println("Good afternoon!");
				} else
					out.println("Good evening!");
			%>
			<h4>{{theTime}}</h4>
			<p>
				<c:out value="${sessionScope.username}" />
			</p>
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<a href="${contextPath}/user/logout.htm">logout</a>
		</div>
	</div>
	<div id="finish" align="center">
		<p>
		<h1>Search Car:</h1>
		</p>
		<a id="buynewcar" href="buynewcar.htm">Buy new Car</a> <a
			id="buyusedcar" href="buyusedcar.htm">Buy Used Car</a> <a
			id="sellcar" href="sellcar.htm">Seller Car</a>
	</div>
	<c:choose>
		<c:when test="${(requestScope.map.user eq 'user')}">
			<div align="center">
				<p>
				<h1>Search Car:</h1>
				</p>
				<a id="buynewcar" href="buynewcar.htm">Buy new Car</a> <a
					id="buyusedcar" href="buyusedcar.htm">Buy Used Car</a> <a
					id="sellcar" href="sellcar.htm">Seller Car</a>
			</div>




		</c:when>
		<c:when test="${(requestScope.map.user eq 'checkusedcar')}">

			<div id="checkusedcar" align="center">
				<div>
					<div>
						<h1>Order Situation</h1>
					</div>
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

						<c:forEach items="${requestScope.map.orderlist}" var="order">
							<tr>
								<td>${order.orderid}</td>
								<td>${order.userid}</td>
								<td>${order.carid}</td>
								<td>${order.price}</td>
								<td>${order.date}</td>
								<td>${order.seller}</td>
								<td>${order.status}</td>
								<td><a id="finish"
									href="orderusedchangestatus.htm?carid=${order.carid}&seller=${order.seller}&orderid=${order.orderid}&userid=${order.userid}">Agree</a></td>
								<td><a id="remove"
									href="removeusedorder.htm?carid=${order.carid}&seller=${order.seller}&orderid=${order.orderid}">disagree</a></td>
							</tr>
						</c:forEach>

					</table>
				</div>






			</div>

		</c:when>
		<c:when test="${(requestScope.map.user eq 'validate')}">

			<div id="validate" align="center">
				<p>
				<h1>Congratulation! You can get car by entering the validate
					code</h1>
				</p>


				<fieldset id="commentForm">
					<legend>Entering your Code</legend>
					<p>
						<label for="cname">OrderID </label> <input id="orderid"
							name="name" minlength="2" maxlength="5" type="number"  required />
					</p>
					<p>
						<label for="cname">ValidateCode </label> <input id="querystring"
							name="name" minlength="2" maxlength="15" type="number" required />
					</p>

					<p>
						<input class="submit" id="validatesumbit" type="submit"
							value="Submit">
					</p>
				</fieldset>


			</div>

		</c:when>
		<c:when test="${(requestScope.map.type eq 'newcar')}">
			<div align="center">
				<p>
				<h1>Search Car:</h1>
				</p>
				<a id="buynewcar" href="buynewcar.htm">Buy new Car</a> <a
					id="buyusedcar" href="buyusedcar.htm">Buy Used Car</a> <a
					id="sellcar" href="sellcar.htm">Seller Car</a>
			</div>

			<div
				style="border: 1px dashed white; line-height: 60px; margin: 30px;">
			</div>
			<div align="center">
				<select ng-init="please" ng-model="select1"
					ng-change="payWayCHange()"
					ng-options="item.mark for item in payWayList">
				</select> <select ng-init="please" ng-model="select2"
					ng-options="data.model for data in select1.model">
				</select> <input type="button" value="search" ng-click="submit()" />
			</div>

			<div
				style="border: 1px dashed white; line-height: 60px; margin: 30px;">
			</div>
			<div align="center">
				<table border="1">
					<td>carid</td>
					<td>color</td>
					<td>bodytype</td>
					<td>mark</td>
					<td>model</td>
					<td>price</td>
					<tr ng-model="cart" ng-repeat="x in names">
						<td>{{ x.carid }}</td>
						<td>{{ x.color }}</td>
						<td>{{ x.bodytype }}</td>
						<td>{{ x.mark }}</td>
						<td>{{ x.model }}</td>
						<td>{{ x.price}}</td>
						<td><a id="addtocart"
							href="addtocartnewcar.htm?id={{x.carid}}">addtocart</a></td>

					</tr>
				</table>
			</div>
		</c:when>
		<c:when test="${(requestScope.map.type eq 'usedcar')}">
			<div align="center">
				<p>
				<h1>Search Car:</h1>
				</p>
				<a id="buynewcar" href="buynewcar.htm">Buy new Car</a> <a
					id="buyusedcar" href="buyusedcar.htm">Buy Used Car</a> <a
					id="sellcar" href="sellcar.htm">Seller Car</a>
			</div>

			<div
				style="border: 1px dashed white; line-height: 60px; margin: 30px;">
			</div>
			<div align="center">
				<select ng-init="please" ng-model="select1"
					ng-change="payWayCHange()"
					ng-options="item.mark for item in payWayList">
				</select> <select ng-init="please" ng-model="select2"
					ng-options="data.model for data in select1.model">
				</select> <input type="button" value="search" ng-click="submitusedcar()" />
			</div>

			<div
				style="border: 1px dashed white; line-height: 60px; margin: 30px;">
			</div>
			<div align="center">
				<table border="1">
					<td>carid</td>
					<td>color</td>
					<td>bodytype</td>
					<td>mark</td>
					<td>model</td>
					<td>year</td>
					<td>miles</td>
					<td>price</td>

					<tr ng-repeat="x in names">
						<td>{{ x.carid }}</td>
						<td>{{ x.color }}</td>
						<td>{{ x.bodytype }}</td>
						<td>{{ x.mark }}</td>
						<td>{{ x.model }}</td>
						<td>{{ x.year }}</td>
						<td>{{ x.miles }}</td>
						<td>{{ x.price}}</td>
						<td><a id="addtocart" href="addtocart.htm?id={{x.carid}}">addtocart</a></td>


					</tr>
				</table>
			</div>
		</c:when>
		<c:when test="${(requestScope.map.type eq 'sellcar')}">
			<div align="center">
				<p>
				<h1>Sell Your Car:</h1>
				</p>
				<a id="buynewcar" href="buynewcar.htm">Buy new Car</a> <a
					id="buyusedcar" href="buyusedcar.htm">Buy Used Car</a> <a
					id="sellcar" href="sellcar.htm">Seller Car</a>

			</div>

			<div
				style="border: 1px dashed white; line-height: 60px; margin: 30px;">
			</div>
			<div align="center">
				<form class="cmxform" id="commentForm" method="get"
					action="sellusercarform.htm">
					<fieldset>
						<legend>Entering Information</legend>
						<p>
							<label for="cname">BodyType </label>
							 <!-- <input id="cname"
								name="bodytype" minlength="2" type="text" required> -->
								<input type="radio" name="bodytype" value="sedan" checked>Sedan

                                  <input type="radio" name="bodytype" value="suv">SUV
						</p>
						<p>
							<label for="cname">Color </label>
                        <input id="cname" name="color" type="radio"  value="red" checked>red
                        <input id="cname" name="color" type="radio"  value="black">black
                        <input id="cname" name="color" type="radio"  value="yellow" >yellow
                        <input id="cname" name="color" type="radio"  value="white" >white
                        <input id="cname" name="color" type="radio"  value="grey" >grey
						</p>
						<p>
							<label for="cname">Liter </label> <input id="cname" name="liter"
								minlength="1" type="number" min="1.0" max="6.0" required>
						</p>
						<p>
							<label for="cname">Year </label> <input id="cname" name="year"
								minlength="2" type="number" min="1980" max="2018" required>
						</p>
						<p>
							<label for="cname">Miles </label> <input id="cname" name="miles"
								minlength="1" type="number" min="00" max="2000000" required>
						</p>
						<p>
							<label for="cname">Price </label> <input id="cname" name="price"
								minlength="2" type="number" min="00" max="2000000" required>
						</p>
						<p>
							<select ng-init="please" ng-model="select1"
								ng-change="payWayCHange()"
								ng-options="item.mark for item in payWayList">
							</select> <select ng-init="please" ng-model="select2"
								ng-options="data.model for data in select1.model">
							</select>
						</p>
						<p>{{select2}}</p>
						<input type="hidden" name="model" value={{select2}} />

						<p>
							<input class="submit" type="submit" value="Submit">
						</p>
					</fieldset>
				</form>


			</div>
		</c:when>
	</c:choose>





</body>
</html>