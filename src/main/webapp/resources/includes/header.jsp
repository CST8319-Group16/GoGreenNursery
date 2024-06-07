<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.cst8319.gogreen.DTO.User" %>
<%@ page import="org.cst8319.gogreen.Utils" %>

<%

    User u = Utils.getCurrentUser(session);

    String ut = u == null ? "" : u.getUserType();
    String headerTitle = (String) request.getAttribute("headerTitle");
    headerTitle = headerTitle == null ? "Food Waste Reduction Platform" : headerTitle;
%>


<html class="h-full bg-gray-100">
<head>
    <title><%= headerTitle %></title>

    <%--tailwind css--%>
    <script src="${pageContext.request.contextPath}/resources/js/tailwind.js"></script>

</head>
<body class="min-h-full flex flex-col">

<%--top nav--%>
<nav class="bg-teal-800 group">
    <div class="mx-auto max-w-6xl px-6 flex h-20 items-center justify-between">

        <div class="flex space-x-1">

            <%--user name--%>
            <div class="<%= u == null ? "hidden" : "" %> text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium flex items-center space-x-1">
                <svg class="h-6 w-6 text-gray-300" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true">
                    <path fill-rule="evenodd"
                              d="M18.685 19.097A9.723 9.723 0 0021.75 12c0-5.385-4.365-9.75-9.75-9.75S2.25 6.615 2.25 12a9.723 9.723 0 003.065 7.097A9.716 9.716 0 0012 21.75a9.716 9.716 0 006.685-2.653zm-12.54-1.285A7.486 7.486 0 0112 15a7.486 7.486 0 015.855 2.812A8.224 8.224 0 0112 20.25a8.224 8.224 0 01-5.855-2.438zM15.75 9a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0z"
                              clip-rule="evenodd"></path>
                </svg>
                <span>
                <%= u == null ? "" : u.getUsername() %>
                </span>
            </div>

            <%--login--%>
            <a href="login.jsp"
               class="<%= u == null ? "" : "hidden" %> text-white hover:bg-teal-900 rounded-md px-3 py-2 font-medium">Login</a>

            <%--logout--%>
            <form action="UserServlet" method="post" class="m-0 <%= u != null ? "" : "hidden" %>">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="Logout"
                       class="text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium">
            </form>
        </div>

    </div>
</nav>
