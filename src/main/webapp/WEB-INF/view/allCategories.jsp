<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 23.04.2024
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>

<div class="relative overflow-x-auto shadow-md mt-4">

    <div class="mb-2 grid grid-cols-6 gap-x-6 gap-y-1 sm:grid-cols-6">
        <div class="sm:col-span-1 py-1.5">
            <c:if test="${user.getRole().toString() eq 'MANAGER'}">
                <a href="${pageContext.request.contextPath}/controller/createCategory" class=" m-1.5">
                    <button type="button" class="w-full h-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" aria-expanded="false" aria-haspopup="true">
                        <span class="text-white">Create Category</span>
                    </button>
                </a>
            </c:if>
        </div>
    </div>

    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase dark:text-gray-400">
        <tr>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">#</th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Name
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Action
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categories}" var="category" varStatus="status">
            <tr class="border-b border-gray-200 dark:border-gray-900">
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">${status.index + 1}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">${category.getName()}</td>
                <td class="flex justify-center px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">
                    <c:if test="${user.getRole().toString() eq 'CASHIER'}"><label class="w-full relative rounded-lg bg-gray-700 text-gray-600 text-sm">t</label></c:if>
                    <c:if test="${user.getRole().toString() eq 'MANAGER'}">
                        <a href="${pageContext.request.contextPath}/controller/updateCategory?id=${category.getId()}" class="w-1/3 m-1.5">
                            <button type="button" class="w-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" id="update-button" aria-expanded="false" aria-haspopup="true">
                                <span>Edit</span>
                            </button>
                        </a>
                        <a href="${pageContext.request.contextPath}/controller/deleteCategory?id=${category.getId()}" class="w-1/3 m-1.5">
                            <button type="button" class="w-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" id="delete-button" aria-expanded="false" aria-haspopup="true">
                                <span>Delete</span>
                            </button>
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>