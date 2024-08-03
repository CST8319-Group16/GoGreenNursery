<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>Item List</title>
</head>
<body>
    <h1>Item List</h1>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <table border="1">
        <tr>
            <th>Item ID</th>
            <th>User ID</th>
            <th>Plant ID</th>
            <th>Plant Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total Price</th>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.itemId}</td>
                <td>${item.userId}</td>
                <td>${item.plantId}</td>
                <td>${item.plantName}</td>
                <td>${item.quantity}</td>
                <td>${item.price}</td>
                <td>${item.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>