<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 24.04.2024
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>

<div class="flex justify-center h-full mt-16">

    <form class="w-4/5">
        <div class="space-y-12">
            <div class=" pb-12">
                <h2 class="text-3xl font-semibold leading-7 text-gray-900">Store product</h2>

                <div class=" pb-12">
                    <h2 class="text-2xl font-semibold leading-7 text-gray-900">Detail Information</h2>

                    <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                        <div class="sm:col-span-3">
                            <label for="name" class="block text-xl font-medium leading-6 text-gray-900">Product name</label>
                            <div class="mt-2">
                                <input type="text" name="name" id="name" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.productView.getName()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="category" class="block text-xl font-medium leading-6 text-gray-900">Product category</label>
                            <div class="mt-2">
                                <form class="max-w-sm mx-auto">
                                    <select id="category" name="category" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" disabled>
                                        <c:forEach items="${requestScope.categories}" var="category" varStatus="status">
                                            <option <c:if test="${requestScope.productView.getCategoryId() eq category.getId()}">selected</c:if> value="${category.getId()}">${category.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </form>
                            </div>
                        </div>

                        <div class="sm:col-span-6">
                            <label class="block text-xl font-medium leading-6 text-gray-900">Characteristics</label>
                            <div class="mt-2">
                                <textarea name="characteristics" id="characteristics" rows="4" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500" placeholder="Write your thoughts here..." <c:if test="${requestScope.view}">readonly</c:if> maxlength="500"><c:if test="${ not empty requestScope.productView}">${requestScope.productView.getCharacteristics()}</c:if></textarea>
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="sellingPrice" class="block text-xl font-medium leading-6 text-gray-900">Selling price</label>
                            <div class="mt-2">
                                <input type="text" name="sellingPrice" id="sellingPrice" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.storeProductView.getSellingPrice()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="productQuantity" class="block text-xl font-medium leading-6 text-gray-900">Product quantity</label>
                            <div class="mt-2">
                                <input type="text" name="productQuantity" id="productQuantity" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.storeProductView.getProductQuantity()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-1">
                            <label for="isPromotional" class="block text-xl font-medium leading-6 text-gray-900">Is promotional</label>
                            <div class="mt-2">
                                <input type="text" name="isPromotional" id="isPromotional" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.storeProductView.getIsPromotional()}" readonly>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
</form>
</div>

