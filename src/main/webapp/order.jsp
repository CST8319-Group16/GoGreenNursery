<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
</head>
<body>
<h2>Order List</h2>
<table border="1">
    <thead>
        <tr>
            <th>orderId</th>
            <th>userId</th>
            <th>plantId</th>
            <th>totalPrice</th>
            <th>orderTime</th>
            <th>orderStatus</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${order.orderId}</td>
            <td>${order.userId}</td>
            <td>${order.plantId}</td>
            <td>${order.totalPrice}</td>
            <td>${order.orderTime}</td>
            <td>${order.orderStatus}</td>
        </tr>
    </tbody>
</table>
<a href="Item.jsp">Return Item</a>
</body>
</html>
