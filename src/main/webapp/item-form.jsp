<%--
  Created by IntelliJ IDEA.
  User: 10172
  Date: 2024/8/17
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Item Form</title>
</head>
<body>
<h2>Item Form</h2>
<form action="item" method="post">
    <input type="hidden" name="itemId" value="${item.itemId}" readonly/>
    <p>User ID: <input type="text" name="userId" value="${item.userId}" readonly/></p>
    <p>Product ID: <input type="text" name="productId" value="${item.productId}" readonly/></p>
    <p>Order ID: <input type="text" name="orderId" value="${item.orderId}" readonly/></p>
    <p>Quantity: <input type="text" name="quantity" value="${item.quantity}"/></p>
    <p>Price: <input type="text" name="price" value="${item.price}" readonly/></p>
    <p>Total Price: <input type="text" name="itemTotalPrice" value="${item.itemTotalPrice}" readonly/></p>
    <p>Order Status: <input type="text" name="orderStatus" value="${item.orderStatus}" readonly/></p>
    <input type="submit" value="Save"/>
    <input type="hidden" name="action" value="${empty item ? 'save' : 'update'}"/>
</form>
<br>
<a href="item?action=list">Back to list</a>
</body>
</html>
