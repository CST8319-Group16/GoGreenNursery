<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
    String email = (String) request.getAttribute("email");
%>

<% request.setAttribute("headerTitle", "Login - GoGreen"); %>
<%@include file="resources/includes/header.jsp" %>

<main class="flex-1 bg-cover bg-center" style="background-image: url('resources/image/background.png');">

    <div class="flex flex-col px-8 py-12">
        <%--title--%>
        <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Login</h2>

        <%--error from https://flowbite.com/docs/components/alerts/ --%>
        <div class="<%= error == null ? "hidden" : "" %> p-4 text-sm text-red-800 rounded-lg bg-red-50 max-w-sm w-full mx-auto">
            <span class="font-medium"><%= error %></span>
        </div>

        <%--info from https://flowbite.com/docs/components/alerts/ --%>
        <div class="<%= info == null ? "hidden" : "" %> p-4 text-sm text-green-800 rounded-lg bg-green-50 max-w-sm w-full mx-auto">
            <span class="font-medium"><%= info %></span>
        </div>

        <%--form--%>
        <div class="mt-5 sm:mx-auto sm:w-full sm:max-w-sm">
            <form class="space-y-6" action="UserServlet" method="POST">
                <%--action--%>
                <input type="hidden" name="action" value="login">
                <%--email--%>
                <div>
                    <label for="email" class="block text-sm font-medium leading-6 text-gray-900">Email address</label>
                    <div class="mt-2">
                        <input id="email" name="email" type="email" autocomplete="email" required
                               value="<%= email == null ? "" : email %>"
                               class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <%--password--%>
                <div>
                    <div class="flex items-center justify-between">
                        <label for="password" class="block text-sm font-medium leading-6 text-gray-900">Password</label>
                    </div>
                    <div class="mt-2">
                        <input id="password" name="password" type="password" autocomplete="current-password" required
                               class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <%--submit--%>
                <div>
                    <button type="submit"
                            class="flex w-full justify-center rounded-md bg-teal-800 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-800">
                        Sign in
                    </button>
                </div>
            </form>

            <%--register link--%>
            <p class="mt-10 text-center text-sm text-gray-500">
                Not a member?
                <a href="register.jsp" class="font-semibold leading-6 text-teal-800 hover:text-teal-600">Register
                    here</a>
            </p>
        </div>
    </div>
</main>

<%@include file="resources/includes/footer.jsp" %>