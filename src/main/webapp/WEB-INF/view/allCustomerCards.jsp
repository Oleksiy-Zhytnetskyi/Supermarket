<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 23.04.2024
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>

<div class="relative overflow-x-auto shadow-md mt-4">

    <div class="mb-2 grid grid-cols-6 gap-x-6 gap-y-1 sm:grid-cols-6">
        <div class="sm:col-span-1 py-1.5">
            <a href="${pageContext.request.contextPath}/controller/createCustomerCard" class=" m-1.5">
                <button type="button" class="w-full h-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" aria-expanded="false" aria-haspopup="true">
                    <span class="text-white">Create Customer Card</span>
                </button>
            </a>
        </div>
        <div class="sm:col-span-5">
            <form action="./sortCustomerCards" method="GET" class="flex flex-nowrap justify-center items-center mr-8 ml-8 h-full">
                <input type="text" name="customersCardSurname" id="customersCardSurname" class="w-4/5 mr-6 block rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                       <c:if test="${ not empty requestScope.customersCardSurname}">value="${requestScope.customersCardSurname}"</c:if>
                >

                <input type="number" name="customersCardPercent" id="customersCardPercent" class="w-4/5 mr-6 block rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" min="0" max="30"
                       <c:if test="${ not empty requestScope.customersCardPercent}">value="${requestScope.customersCardPercent}"</c:if>
                >

                <button type="submit" class="flex w-4/5 justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Sort</button>
            </form>
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
                Surname
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Patronymic
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Phone
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Percent
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Action
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customerCards}" var="customerCard" varStatus="status">
            <tr class="border-b border-gray-200 dark:border-gray-900">
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">${status.index + 1}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">${customerCard.getCustomerName()}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">${customerCard.getCustomerSurname()}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">${customerCard.getCustomerPatronymic()}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">${customerCard.getPhoneNumber()}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">${customerCard.getPercent()}</td>
                <td class="flex justify-center px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    <a href="${pageContext.request.contextPath}/controller/viewCustomerCard?id=${customerCard.getId()}" class="w-1/3 m-1.5">
                        <button type="button" class="w-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" id="view-button" aria-expanded="false" aria-haspopup="true">
                            <span>View</span>
                        </button>
                    </a>
                    <a href="${pageContext.request.contextPath}/controller/updateCustomerCard?id=${customerCard.getId()}" class="w-1/3 m-1.5">
                        <button type="button" class="w-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" id="update-button" aria-expanded="false" aria-haspopup="true">
                            <span>Edit</span>
                        </button>
                    </a>
                    <a href="${pageContext.request.contextPath}/controller/deleteCustomerCard?id=${customerCard.getId()}" class="w-1/3 m-1.5">
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
