<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 26.04.2024
  Time: 21:40
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

    <form class="w-4/5" action="./calculateStatistics" method="POST" role="form">
        <div class="space-y-12">
            <div class=" pb-12">

                <div class=" pb-12">

                    <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                        <div class="sm:col-span-6">
                            <label for="user" class="block text-xl font-medium leading-6 text-gray-900">First name</label>
                            <div class="mt-2">
                                <select id="user" name="user" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                    <option selected value="null">Choose a cashier</option>

                                    <c:forEach items="${requestScope.users}" var="user" varStatus="status">
                                        <option value="${user.getId()}">Name:${user.getName()} Surname:${user.getSurname()} Email:${user.getEmail()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="sm:col-span-6">
                            <label for="storeProduct" class="block text-xl font-medium leading-6 text-gray-900">Product</label>
                            <div class="mt-2">
                                <select id="storeProduct" name="storeProduct" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                    <option <c:if test="${empty requestScope.storeProductView.getPromotionalId()}">selected</c:if> value="null">Choose a sale store product</option>

                                    <c:forEach items="${requestScope.storeProducts}" var="saleStoreProduct" varStatus="status">
                                        <option <c:if test="${requestScope.storeProductView.getPromotionalId() eq saleStoreProduct.getId()}">selected</c:if> value="${saleStoreProduct.getId()}">
                                            <c:forEach items="${requestScope.products}" var="product" varStatus="status">
                                                <c:if test="${saleStoreProduct.getProductId() eq product.getId()}">${product.getName()} Price:${saleStoreProduct.getSellingPrice()} Quantity:${saleStoreProduct.getProductQuantity()}</c:if>
                                            </c:forEach>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>


                        <div class="sm:col-span-3">
                            <label for="startDate" class="block text-xl font-medium leading-6 text-gray-900">Start date</label>
                            <div class="mt-2">
                                <input type="date" name="startDate" id="startDate" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6">
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="endDate" class="block text-xl font-medium leading-6 text-gray-900">End date</label>
                            <div class="mt-2">
                                <input type="date" name="endDate" id="endDate" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6">
                            </div>
                        </div>

                    </div>
                </div>

                <div class="mt-6 flex items-center justify-center gap-x-6">
                    <button type="submit" class="w-2/6 rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Save</button>
                </div>

            </div>
        </div>
    </form>
</div>

