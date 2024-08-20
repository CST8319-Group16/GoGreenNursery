<%--
  Created by IntelliJ IDEA.
  User: 10172
  Date: 2024/8/18
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
%>

<% request.setAttribute("headerTitle", "Order Detail page - GoGreen"); %>
<%@ include file="resources/includes/header.jsp" %>
<main class="flex-1 bg-cover bg-center" >

    <div>
        <%--title--%>
        <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Order Detail</h2>
    </div>
    <div id="main-content">
        <div class="container">
            <div>
                <h2>User Order Details</h2>
                <p><strong>Order ID:</strong> ${userOrder.orderId}</p>
                <p><strong>User ID:</strong> ${userOrder.userId}</p>
                <p><strong>Order Time:</strong> ${userOrder.orderTime}</p>
                <p><strong>Total Price:</strong> ${userOrder.totalPrice}</p>
            </div>
            <table class="table table-striped table-hover table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th class="col-1">Item ID</th>
                    <th class="col-1">User ID</th>
                    <th class="col-1">Product ID</th>
                    <th class="col-1">Order ID</th>
                    <th class="col-1">Quantity</th>
                    <th class="col-1">Price</th>
                    <th class="col-1">Total Price</th>
                    <th class="col-1">Order Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${orderItems}">
                    <tr>
                        <td>${item.itemId}</td>
                        <td>${item.userId}</td>
                        <td>${item.productId}</td>
                        <td>${item.orderId}</td>
                        <td>${item.quantity}</td>
                        <td>${item.price}</td>
                        <td>${item.itemTotalPrice}</td>
                        <td>${item.orderStatus}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- Return Back Button -->
            <button onclick="history.back()" class="btn btn-success">Return Back</button>
        </div>
    </div>
</main>


<%@ include file="resources/includes/footer.jsp" %>
