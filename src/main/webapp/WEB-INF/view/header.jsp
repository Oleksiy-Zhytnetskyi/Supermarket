<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 17.04.2024
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="udt" uri="customtags"%>

<c:set var="lang" scope="session"
       value="${empty sessionScope.locale ? 'en_US' : sessionScope.locale}" />
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="/i18n/messages" var="rb" />

<html lang="${lang}">
<head>
    <title>Zlagoda</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp"></script>
</head>
<body>
<nav class="bg-gray-800">
    <div class="mx-auto max-w-full px-2 sm:px-6 lg:px-8">
        <div class="relative flex h-16 items-center justify-between">
            <div class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">

                <div class="flex flex-shrink-0 items-center">
                    <a href="${pageContext.request.contextPath}/controller/">
                        <button type="button" class="relative flex rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800" id="home-button" aria-expanded="false" aria-haspopup="true">
                            <span class="absolute -inset-1.5"></span>
                            <span class="sr-only">Open user menu</span>
                            <img class="h-8 w-8 rounded-full" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500" alt="">
                        </button>
                    </a>
                </div>

                <div class="hidden sm:ml-6 sm:block">
                    <div class="flex space-x-4">
                        <!-- Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white" -->
                        <c:if test="${user.getRole().toString() eq 'MANAGER'}">
                            <a href="${pageContext.request.contextPath}/controller/allUsers" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-xl font-medium">User</a>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/controller/allProducts" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-xl font-medium">Products</a>
                        <a href="${pageContext.request.contextPath}/controller/allStoreProducts" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-xl font-medium">Store Products</a>
                        <a href="${pageContext.request.contextPath}/controller/allCategories" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-xl font-medium">Product Category</a>
                        <a href="${pageContext.request.contextPath}/controller/allReceipts" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-xl font-medium">Receipts</a>
                        <a href="${pageContext.request.contextPath}/controller/allSales" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-xl font-medium">Sales</a>
                        <a href="${pageContext.request.contextPath}/controller/allCustomerCards" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-xl font-medium">Client card</a>
                        <a href="${pageContext.request.contextPath}/controller/statistics" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-xl font-medium">Client card</a>
                    </div>
                </div>
            </div>

            <div class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                <div class="flex justify-center w-28">
                    <a href="${pageContext.request.contextPath}/controller/logout" class="bg-gray-900 text-white rounded-md px-3 py-2 text-mg font-medium w-4/5 text-center" aria-current="page">Log Out</a>
                </div>



                <div class="flex flex-shrink-0 items-center">
                    <a href="${pageContext.request.contextPath}/controller/myProfile">
                        <div class="relative ml-3">
                            <img class="h-10 w-10 rounded-full" src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" alt="">
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</nav>
</body>
</html>
