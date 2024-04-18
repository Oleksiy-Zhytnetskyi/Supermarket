<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 18.04.2024
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page session="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="lang" scope="session"
       value="${empty sessionScope.locale ? 'en_US' : sessionScope.locale}" />
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="/i18n/messages" var="rb" />

<c:set var="lang" scope="session"
       value="${empty sessionScope.locale ? 'en_US' : sessionScope.locale}" />
<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="/i18n/messages" var="rb" />

<html>
<head>
    <title>Zlagoda</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp"></script>
</head>
<body>
<nav class="bg-gray-800">
    <div class="mx-auto max-w-full px-2 sm:px-6 lg:px-8">
        <div class="relative flex h-20 items-center justify-end">
            <div class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
                <div class="flex flex-shrink-0 items-center">
                    <img class="h-16 w-16" src="images/logo.jpg" alt="Your Company">
                </div>
            </div>
            <div class="hidden sm:ml-6 sm:block <%--top-5 right-48--%> w-52">
                <div class="flex justify-center items-center">
                    <a href="${pageContext.request.contextPath}/controller/login" class="bg-gray-900 text-white rounded-md px-3 py-2 text-lg font-medium w-1/2 text-center" aria-current="page">Log In</a>
                </div>
            </div>
        </div>
    </div>
</nav>
</body>
</html>

