<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 25.04.2024
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>
<c:if test="${not empty param.success}">

    <div class="p-4 mb-4 mt-4 text-sm text-green-800 rounded-lg bg-green-50" role="alert">
        <span class="font-medium">Info: </span> <fmt:message key="${param.success}" bundle="${rb}"/>
    </div>
</c:if>

<div class="relative overflow-x-auto shadow-md mt-4">

    <div class="mb-2 grid grid-cols-6 gap-x-6 gap-y-1 sm:grid-cols-6">
        <div class="sm:col-span-1 py-1.5">
            <a href="${pageContext.request.contextPath}/controller/createReceipt" class=" m-1.5">
                <button type="button" class="w-full h-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" aria-expanded="false" aria-haspopup="true">
                    <span class="text-white">Create Receipt</span>
                </button>
            </a>
        </div>
    </div>

    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase dark:text-gray-400">
        <tr>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">Id</th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                User Name
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Client name
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Date
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Total sum
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Action
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.receipts}" var="receipt" varStatus="status">
            <tr class="border-b border-gray-200 dark:border-gray-900">
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">
                        ${receipt.getId()}
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    <c:forEach items="${requestScope.users}" var="user" varStatus="status">
                        <c:if test="${receipt.getUserId() eq user.getId()}">${user.getEmail()}</c:if>
                    </c:forEach>
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    <c:forEach items="${requestScope.customerCards}" var="card" varStatus="status">
                        <c:if test="${receipt.getCardId() eq card.getId()}">${card.getCustomerName()} ${card.getCustomerSurname()}</c:if>
                    </c:forEach>
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    ${receipt.getPrintDate()}
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    ${receipt.getSumTotal()}
                </td>
                <td class="flex justify-center px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    <a href="${pageContext.request.contextPath}/controller/viewReceipt?id=${receipt.getId()}" class="w-1/3 m-1.5">
                        <button type="button" class="w-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" id="view-button" aria-expanded="false" aria-haspopup="true">
                            <span>View</span>
                        </button>
                    </a>
                    <a href="${pageContext.request.contextPath}/controller/deleteReceipt?id=${receipt.getId()}" class="w-1/3 m-1.5">
                        <button type="button" class="w-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" id="delete-button" aria-expanded="false" aria-haspopup="true">
                            <span>Delete</span>
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
