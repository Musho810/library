<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Author</title>
</head>
<body>
<h1> Please input author`s data:</h1>
<a href="/homePage">Home</a>
<form action="/authors/add" method="post" enctype="multipart/form-data">
    <input type="text" name="name" placeholder="Please input author name"/><br>
    <input type="text" name="surname" placeholder="Please input author surname"/><br>
    <input type="text" name="email" placeholder="Please input author email"/><br>
    <input type="number" name="age" placeholder="Please input author age"><br>
    Author picture <br>
    <input type="file" name="authorPic"><br>
    <input type="submit" value="ADD">
</form>
</body>
</html>
