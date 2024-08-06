<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
%>

<% request.setAttribute("headerTitle", "Product Browsing - GoGreen"); %>
<%@ include file="resources/includes/header.jsp" %>

<main class="flex-1 bg-cover bg-center" style="background-image: url('resources/image/background.png');">

    <div class="flex flex-col px-8 py-12">
        <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Product Browsing Page</h2>

        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
                <tr>
                    <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Image</th>
                    <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                    <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Description</th>
                    <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Price</th>
                </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <img src="${product.imageURL}" alt="${product.productName}" class="w-20 h-20 object-cover"/>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm font-medium text-gray-900">${product.productName}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-500">${product.productDesc}</div>
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap">
                            <div class="text-sm text-gray-900">${product.price}</div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</main>

<%@ include file="resources/includes/footer.jsp" %>
