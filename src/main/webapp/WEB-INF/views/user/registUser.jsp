<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="<c:url value="/resources/jquery-1.11.0.min.js" />"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<form id="userInfo" name="userInfo">
	<input type="text" id="id" name="id"/>
	<input type="text" id="password" name="password" width="10px"/>
	<input type="text" id="name" name="name"/>
	
	<input type="button" id="regist" value="regist" />
</form>
<script>
	$(document).ready(function() {
		  $('#regist').click(function() {
			  var sendData = $("#userInfo").serialize();
			  //console.log("sendData : " + sendData);
	            $.ajax({
	                type: "GET",
	                url: "/registUser",
	                data: sendData,
	                dataType: "json",
	                contentType: "application/json;charset=UTF-8",
	                success: function(data, status, xhr) {
	                    console.log(data.res);
	                    //$("#result").val(data.res);
	                },
	                error: function(jqXHR, textStatus, errorThrown) {
	                    alert(jqXHR.responseText);
	                }
	            });
	        });
	});
</script>


</body>
</html>
