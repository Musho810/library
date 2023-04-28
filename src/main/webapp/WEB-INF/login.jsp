<%@ page import="library.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOG IN</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
%>
<%
    String msg = (String) request.getAttribute("msg");
%>
<%if (msg != null) {%>
<p style=" color: red "><%=msg%>
</p>
<% } %>
<h1> Please input yor email and password</h1><br>
<form action="/login" method="post">
    <input type="email" name="useremail" placeholder="Please input email"/><br>
    <input type="password" name="password" placeholder="Please input password"/><br>
    <input type="submit" value="LOGIN">
</form>
</body>
</html>
