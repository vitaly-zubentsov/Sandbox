<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Курсы</title>
</head>
<body>
	<h1>Курсы</h1>
	
	<%
		request.setCharacterEncoding("UTF-8");
	
	String search = "";
	if(request.getMethod().contains("POST")){
		search = request.getParameter("searchInput");
	}
	%>
	
	<form method="POST">
		<input type=text name="searchInput" value="<%=search%>"> 
		<input type=submit value="Поиск">
	</form>
	
	
	<%
	
	String DRIVER_NAME = "com.mysql.jdbc.Driver";
	String CONNETION_STRING = 
			"jdbc:mysql://localhost/web?user=root&password=demo";
	Class.forName(DRIVER_NAME);
	Connection conn = DriverManager.getConnection(CONNETION_STRING); 

	String sql = "SELECT name, description, lenght FROM courses WHERE name LIKE ? ORDER BY lenght";
	PreparedStatement cmd = conn.prepareStatement(sql);
	//Подставляем данный в параметризированный sql запрос
	
	
	cmd.setString(1, "%" + search + "%" );
	ResultSet result = cmd.executeQuery();

	out.print("<table border=1>");
	while (result.next())
	{
		out.print("<tr>");
		String name = result.getString("name");
		String description = result.getString("description");
		Integer  lenght = result.getInt("lenght");
		out.print("<td>");
		out.print(name);
		out.print("</td>");
		out.print("<td>");
		out.print(description);
		out.print("</td>");
		out.print("<td>");
		out.print(lenght);
		out.print("</td>");
		out.print("</tr>");
	}
	result.close();
	out.print("</table>");
	conn.close();
				
	%>
</body>
</html>