<%@ page import="java.util.List" %>
<%@ page import="library.model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<% List<Author> authorList = (List<Author>) request.getAttribute("authors");
%>
<h1> Please input book`s data:</h1>
<a href="/homePage">Home</a>
<form action="/books/add" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="Please input book title"/><br>
    <input type="text" name="description" placeholder="Please input book description"/><br>
    <input type="number" name="price" placeholder="Please input book price"/><br>
    Author
    <select name="authorId">
        <% for (Author author : authorList) {
        %>
        <option value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%>
        </option>
        <%
            }
        %>
    </select> <br>
    Book cover picture <br>
    <input type="file" name="bookPic"><br>
    <input type="submit" value="ADD">
</form>
</body>
</html>
