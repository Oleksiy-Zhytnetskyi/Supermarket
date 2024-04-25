<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 24.04.2024
  Time: 21:37
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
            <c:if test="${requestScope.create}"> action="./createStoreProduct" method="POST" role="form"</c:if>
            <c:if test="${requestScope.update}"> action="./updateStoreProduct?id=${requestScope.storeProductView.getId()}" method="POST" role="form"</c:if>>
        <div class="space-y-12">
            <div class=" pb-12">
                <h2 class="text-3xl font-semibold leading-7 text-gray-900">Store product</h2>

                <div class=" pb-12">
                    <h2 class="text-2xl font-semibold leading-7 text-gray-900">Detail Information</h2>

                    <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                        <div class="sm:col-span-3">
                            <label for="productId" class="block text-xl font-medium leading-6 text-gray-900">Product</label>
                            <div class="mt-2">
                                <select id="productId" name="productId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                    <option <c:if test="${empty requestScope.storeProductView}">selected</c:if> value="null">Choose a product</option>

                                    <c:forEach items="${requestScope.products}" var="product" varStatus="status">
                                        <option <c:if test="${requestScope.storeProductView.getProductId() eq product.getId()}">selected</c:if> value="${product.getId()}">${product.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <div class="w-full h-full flex justify-center items-center">
                                <input <c:if test="${requestScope.storeProductView.getIsPromotional() eq 'true'}">checked</c:if> id="isPromotional" name="isPromotional" type="checkbox" value="true" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600">
                                <label for="isPromotional" class="block ms-2 text-xl font-medium text-gray-900">Is promotional</label>
                            </div>
                        </div>

                        <div class="sm:col-span-6">
                            <label for="saleStoreProductId" class="block text-xl font-medium leading-6 text-gray-900">Sale store product</label>
                            <div class="mt-2">
                                <select id="saleStoreProductId" name="saleStoreProductId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                <option <c:if test="${empty requestScope.storeProductView.getPromotionalId()}">selected</c:if> value="null">Choose a sale store product</option>

                                <c:forEach items="${requestScope.saleStoreProducts}" var="saleStoreProduct" varStatus="status">
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
                            <label for="sellingPrice" class="block text-xl font-medium leading-6 text-gray-900">Selling price</label>
                            <div class="mt-2">
                                <input type="text" name="sellingPrice" id="sellingPrice" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.storeProductView.getSellingPrice()}">
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="productQuantity" class="block text-xl font-medium leading-6 text-gray-900">Product quantity</label>
                            <div class="mt-2">
                                <input type="number" name="productQuantity" id="productQuantity" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" min="0"
                                       value="${requestScope.storeProductView.getProductQuantity()}">
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


