<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 24.04.2024
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>

<c:if test="${not empty requestScope.errors}">
    <div class="mt-4 flex p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400" role="alert">
        <svg class="flex-shrink-0 inline w-4 h-4 me-3 mt-[2px]" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
            <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
        </svg>
        <span class="sr-only">Danger</span>
        <div>
            <span class="font-medium">Ensure that these requirements are met:</span>
            <ul class="mt-1.5 list-disc list-inside">
                <c:forEach items="${errors}" var="error" varStatus="status">
                    <li><fmt:message key="${error}" bundle="${rb}"/></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:if>

<div class="flex justify-center h-full mt-16">

    <form class="w-4/5"
            <c:if test="${requestScope.create}"> action="./createCustomerCard" method="POST" role="form"</c:if>
            <c:if test="${requestScope.update}"> action="./updateCustomerCard?id=${requestScope.customerCardView.getId()}" method="POST" role="form"</c:if>>
        <div class="space-y-12">
            <div class=" pb-12">
                <h2 class="text-3xl font-semibold leading-7 text-gray-900">Customer card</h2>

                <div class=" pb-12">
                    <h2 class="text-2xl font-semibold leading-7 text-gray-900">Personal Information</h2>

                    <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                        <div class="sm:col-span-2">
                            <label for="name" class="block text-xl font-medium leading-6 text-gray-900">Name</label>
                            <div class="mt-2">
                                <input type="text" name="name" id="name" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       <c:if test="${ not empty requestScope.customerCardView}">value="${requestScope.customerCardView.getCustomerName()}"</c:if>
                                       <c:if test="${requestScope.view}">readonly</c:if>>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="surname" class="block text-xl font-medium leading-6 text-gray-900">Surname</label>
                            <div class="mt-2">
                                <input type="text" name="surname" id="surname" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       <c:if test="${ not empty requestScope.customerCardView}">value="${requestScope.customerCardView.getCustomerSurname()}"</c:if>
                                       <c:if test="${requestScope.view}">readonly</c:if>>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="patronymic" class="block text-xl font-medium leading-6 text-gray-900">Patronymic</label>
                            <div class="mt-2">
                                <input id="patronymic" name="patronymic" type="text" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       <c:if test="${ not empty requestScope.customerCardView}">value="${requestScope.customerCardView.getCustomerPatronymic()}"</c:if>
                                       <c:if test="${requestScope.view}">readonly</c:if>>
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="phone" class="block text-xl font-medium leading-6 text-gray-900">Phone</label>
                            <div class="mt-2">
                                <input type="text" name="phone" id="phone" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                   <c:if test="${ not empty requestScope.customerCardView}">value="${requestScope.customerCardView.getPhoneNumber()}"</c:if>
                                   <c:if test="${requestScope.view}">readonly</c:if>>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="percent" class="block text-xl font-medium leading-6 text-gray-900">Percent</label>
                            <div class="mt-2">
                                <input type="number" name="percent" id="percent" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" min="1" max="30"
                                       <c:if test="${ not empty requestScope.customerCardView}">value="${requestScope.customerCardView.getPercent()}"</c:if>
                                       <c:if test="${requestScope.view}">readonly</c:if>>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="city" class="block text-xl font-medium leading-6 text-gray-900">City</label>
                            <div class="mt-2">
                                <input type="text" name="city" id="city" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       <c:if test="${ not empty requestScope.customerCardView}">value="${requestScope.customerCardView.getCity()}"</c:if>
                                       <c:if test="${requestScope.view}">readonly</c:if>>
                            </div>
                        </div>

                        <div class="col-span-4">
                            <label for="street" class="block text-xl font-medium leading-6 text-gray-900">Street address</label>
                            <div class="mt-2">
                                <input type="text" name="street" id="street" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       <c:if test="${ not empty requestScope.customerCardView}">value="${requestScope.customerCardView.getStreet()}"</c:if>
                                       <c:if test="${requestScope.view}">readonly</c:if>>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="postal-code" class="block text-xl font-medium leading-6 text-gray-900">ZIP / Postal code</label>
                            <div class="mt-2">
                                <input type="text" name="zip-code" id="postal-code" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       <c:if test="${ not empty requestScope.customerCardView}">value="${requestScope.customerCardView.getZipCode()}"</c:if>
                                       <c:if test="${requestScope.view}">readonly</c:if>>
                            </div>
                        </div>
                    </div>

                    <c:if test="${requestScope.create || requestScope.update}">
                        <div class="mt-6 flex items-center justify-center gap-x-6">
                            <button type="submit" class="w-2/6 rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Save</button>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </form>
</div>
