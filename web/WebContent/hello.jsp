<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.DateFormat,java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello</title>
</head>
<body>
	<h1>
		<%
		//Несмотря на то, что кодировка страницы указана выше, она повлияет только 
		//на кодировку response, поэтому для request кодировку необходимо указать явно
		request.setCharacterEncoding("UTF-8");

		String userName = "";
		String result;
		if (request.getMethod().equals("POST")) {
			userName = request.getParameter("userName");
			result = String.format("Hello, %s!", userName);
		} else {
			result = "Hello!";
		}
		out.println(result);
		%>
	</h1>
	<h2>
		<%
			out.println(DateFormat.getDateTimeInstance().format(new Date()));
		%>
	</h2>
	
	<form method="POST">
	<!--При нажатии кнопки submit запрос будет отправлен с использованием
	метода "GET", в котором по умолчанию данные отправляются через адресную строку.
	Поэтому задаем параметр method="POST".
	Есть возможность явно указать, какая программа будет обрабатывать запрос, для этого
	следует добавить параметр action-->
		<label>Name: </label> 
		<!-- для того, чтобы после нажатия кнопки submit текст из поля не пропадал
		необходимо задать параметру value значение %out.println(userName);%
		или сокращённо ниже-->
		<input type=text name="userName" value="<%=userName%>"> 
		<input type=submit value="Hello">
	</form>
</body>
</html>