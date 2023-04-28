
<%@ page import="java.util.List" %>
<%@ page import="library.model.Book" %>
<%@ page import="library.model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>

<%
    Book book = (Book) request.getAttribute("book");
    List <Author> authorList = (List<Author>) request.getAttribute("authors");

%>

<h1> Please edit book`s data:</h1>
<a href="/homePage">Home</a>
<form action="/books/edit" method="post" enctype="multipart/form-data">
    <input type="hidden" name="bookId" value="<%=book.getId()%>"/>
    <input type="text" name="title" value="<%=book.getTitle()%>"/><br>
    <input type="text" name="description" value="<%=book.getDescription()%>"/><br>
    <input type="number" name="price" value="<%=book.getPrice()%>"/><br>
    Author
    <select name="authorId">
        <% for (Author author : authorList) {
            if (author.equals(book.getAuthor())) {
        %>
        <option selected value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%> >
        </option>
        <%
        } else { %>
        <option value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%> >
        </option>

        <%
            }
        %>
        <%
            }
        %>

    </select> <br>
    <input type="file" name="bookPic" value="<%=book.getBookPic()%>"/><br>
    <input type="submit" value="Update">

</form>


</body>
</html>
