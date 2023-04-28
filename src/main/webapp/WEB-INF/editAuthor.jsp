<%@ page import="java.util.List" %>
<%@ page import="library.model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Author</title>
</head>
<body>
<%
    Author author = (Author) request.getAttribute("author");
    List<Author> authorList = (List<Author>) request.getAttribute("authors");
%>
<h1> Please edit author`s data:</h1>
<a href="/homePage">Home</a>
<form action="/authors/edit" method="post" enctype="multipart/form-data">
    <input type="hidden" name="authorId" value="<%=author.getId()%>"/>
    <input type="text" name="name" value="<%=author.getName()%>"/><br>
    <input type="text" name="surname" value="<%=author.getSurname()%>"/><br>
    <input type="text" name="email" value="<%=author.getEmail()%>"/><br>
    <input type="number" name="age" value="<%=author.getAge()%>"/><br>
    <input type="file" name="authorPic" value="<%=author.getAuthorPic()%>">
    <input type="submit" value="UpDate">
</form>
</body>
</html>
