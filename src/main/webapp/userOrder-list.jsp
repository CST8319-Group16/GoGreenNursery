<%--
  Created by IntelliJ IDEA.
  User: 10172
  Date: 2024/8/17
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
%>

<% request.setAttribute("headerTitle", "Plants page - GoGreen"); %>
<%@ include file="resources/includes/header.jsp" %>
<main class="flex-1 bg-cover bg-center">
    <div>
        <%--title--%>
        <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">User Orders</h2>
    </div>
    <div id="main-content">
        <div class="container">
<table class="table table-striped table-hover table-bordered">
    <thead  class="thead-dark">
    <tr>
        <th class="col-1">Order ID</th>
        <th class="col-1">User ID</th>
        <th class="col-1">Order Time</th>
        <th class="col-1">Total Price</th>
        <th class="col-1">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${listUserOrders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.userId}</td>
            <td>${order.orderTime}</td>
            <td>${order.totalPrice}</td>
            <td>
                <a href="userOrder?action=detail&orderId=${order.orderId}" class="btn btn-outline-success btn-sm">Detail</a>
                <a href="userOrder?action=delete&orderId=${order.orderId}" class="btn btn-outline-success btn-sm">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
        </div>
    </div>

</main>

<%@ include file="resources/includes/footer.jsp" %>
