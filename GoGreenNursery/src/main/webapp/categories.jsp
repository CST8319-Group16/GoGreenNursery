<%--
  Created by IntelliJ IDEA.
  User: Jianbo Tan
  Date: 2024/7/30
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<h1>Categories</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.categoryId}</td>
            <td>${category.categoryName}</td>
        </tr>
    </c:forEach>
</table>
<h2>Add a new Category</h2>
<form action="categories" method="post">
    <label for="categoryName">Name:</label>
    <input type="text" id="categoryName" name="categoryName"/><br/>
    <input type="submit" value="Add"/>
</form>
</body>
</html>


