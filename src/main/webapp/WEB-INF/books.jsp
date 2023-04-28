<%@ page import="java.util.List" %>
<%@ page import="library.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<%
    List<Book> bookList = (List<Book>) request.getAttribute("books");
%>
<a href="/homePage">Home</a><br><br><br>
<form action="/books" method="post">
    <input type="text" name="searchedName" placeholder="Searched book name"/><br>
    <input type="submit" value="SEARCH">
</form>
<table border="1">
    <tr>
        <th> id</th>
        <th>Image</th>
        <th> title</th>
        <th> description</th>
        <th> price</th>
        <th> author name and surname</th>
        <th> action</th>
    </tr>
    <% for (Book book : bookList) {
    %>
    <tr>
        <td><%=book.getId()%>
        </td>
        <td>
            <% if (book.getBookPic() == null || book.getBookPic().length() == 0) { %>
            <img src="/images/11.jpg" width="50">
            <% } else { %>
            <img src="/getBookImage?bookPic=<%=book.getBookPic()%>" width="50">
            <% } %>
        </td>
        <td><%=book.getTitle()%>
        </td>
        <td><%=book.getDescription()%>
        </td>
        <td><%=book.getPrice()%>
        </td>
        <td>
            <% if (book.getAuthor() != null) { %>
            <%=book.getAuthor().getName()  %>    <%=book.getAuthor().getSurname()%>
            <%} else {%>
            <span style=" color: red"> There is no author </span>
            <%}%>
        </td>
        <td>
            <a href="/books/remove?bookId=<%=book.getId()%>"> Remove </a> |
            <a href="/books/edit?bookId=<%=book.getId()%>"> Edit </a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
