<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>REGISTRATION</title>
</head>

<body>
<%
    String msg = (String) request.getAttribute("msg");
%>

<%if (msg !=null) {%>
<p style=" color: red "><%=msg%></p>
<% } %>

<form action="/registration" method="post" enctype="multipart/form-data" >
    <input type="text" name="username" placeholder="Please input user name"/><br>
    <input type="text" name="usersurname" placeholder="Please input user surname"/><br>
    <input type="email" name="useremail" placeholder="Please input user email"/><br>
    <input type="password" name="userpassword" placeholder="Please input user password"/><br>

    User picture <br>
    <input type="file" name="userPic" ><br>
   <br>
    <select name="role">
        <option value="ADMIN">ADMIN</option>
        <option value="CLIENT" >CLIENT</option>

    </select>


    <input type="submit" value="Register">


</form>

</body>
</html>
