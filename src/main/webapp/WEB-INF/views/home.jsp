<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<input type="text" id="num1" name="num1"/>
<input type="text" id="operator" name="operator" width="10px"/>
<input type="text" id="num2" name="num2"/>
=
<input type="text" id="result" name="result" />

<input type="button" id="operate" value="operate" />

<script>
	$(document).ready(function() {
		  $('#operate').click(function() {
	            $.ajax({
	                type: "POST",
	                url: "/calcurator",
	                data: {    "num1" : $('#num1').val(),
	                    "num2" : $('#num2').val()},
	                success: function() {
	                    alert('로그인 성공');
	                    location.reload();
	                }, error: function() {
	                    alert('로그인 정보가 올바르지 않습니다.');
	                }
	            });
	        });
	});
</script>


</body>
</html>
