
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
%>

<% request.setAttribute("headerTitle", "Product Browsing - GoGreen"); %>
<%@ include file="resources/includes/header.jsp" %>

<main class="flex-1 bg-cover bg-center" style="background-image: url('resources/image/background.png');">

    <div class="flex flex-col px-8 py-12">
        <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Product Browsing Page</h2>
    </div>

</main>

<%@ include file="resources/includes/footer.jsp" %>

