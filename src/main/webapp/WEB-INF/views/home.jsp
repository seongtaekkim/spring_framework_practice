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

<form id="calc_form" name="calc_form">
	<input type="text" id="num1" name="num1"/>
	<input type="text" id="operator" name="operator" width="10px"/>
	<input type="text" id="num2" name="num2"/>
	=
	<input type="text" id="result" name="result" />
	
	<input type="button" id="operate" value="operate" />
</form>
<script>
	$(document).ready(function() {
		  $('#operate').click(function() {
			  var sendData = $("#calc_form").serialize();
			  //console.log("sendData : " + sendData);
	            $.ajax({
	                type: "GET",
	                url: "/calculator",
	                data: sendData,
	                dataType: "json",
	                contentType: "application/json;charset=UTF-8",
	                success: function(data, status, xhr) {
	                    //console.log(data.res);
	                    $("#result").val(data.res);
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
