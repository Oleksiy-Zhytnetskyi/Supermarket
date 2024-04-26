<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 26.04.2024
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>

<div class="flex justify-center mt-16">

    <form class="w-4/5">
        <div class="space-y-12">
            <div class=" pb-6">
                <h2 class="text-3xl font-semibold leading-7 text-gray-900">Cashier Information</h2>

                <div class=" pb-12 border-b-2">
                    <h2 class="text-2xl font-semibold leading-7 text-gray-900">Personal Information</h2>

                    <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                           <div class="sm:col-span-2">
                            <label for="userName" class="block text-xl font-medium leading-6 text-gray-900">Cashier name</label>
                            <div class="mt-2">
                                <input type="text" name="userName" id="userName" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.userView.getName()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="userSurname" class="block text-xl font-medium leading-6 text-gray-900">Cashier surname</label>
                            <div class="mt-2">
                                <input type="text" name="userSurname" id="userSurname" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.userView.getSurname()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="userPatronymic" class="block text-xl font-medium leading-6 text-gray-900">Cashier patronymic</label>
                            <div class="mt-2">
                                <input type="text" name="userPatronymic" id="userPatronymic" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.userView.getPatronymic()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-4">
                            <label for="userEmail" class="block text-xl font-medium leading-6 text-gray-900">Cashier email</label>
                            <div class="mt-2">
                                <input type="email" name="userEmail" id="userEmail" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.userView.getEmail()}" readonly>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<c:if test="${not empty requestScope.customerCardView}">
    <div class="flex justify-center mt-6">

        <form class="w-4/5">
            <div class="space-y-12">
                <div class=" pb-12">
                    <h2 class="text-3xl font-semibold leading-7 text-gray-900">Customer Information</h2>

                    <div class=" pb-12 border-b-2">
                        <h2 class="text-2xl font-semibold leading-7 text-gray-900">Personal Information</h2>

                        <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                            <div class="sm:col-span-2">
                                <label for="customerName" class="block text-xl font-medium leading-6 text-gray-900">Customer name</label>
                                <div class="mt-2">
                                    <input type="text" name="customerName" id="customerName" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                           value="${requestScope.customerCardView.getCustomerName()}" readonly>
                                </div>
                            </div>

                            <div class="sm:col-span-2">
                                <label for="customerSurname" class="block text-xl font-medium leading-6 text-gray-900">Customer surname</label>
                                <div class="mt-2">
                                    <input type="text" name="customerSurname" id="customerSurname" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                           value="${requestScope.customerCardView.getCustomerSurname()}" readonly>
                                </div>
                            </div>

                            <div class="sm:col-span-2">
                                <label for="customerPatronymic" class="block text-xl font-medium leading-6 text-gray-900">Customer patronymic</label>
                                <div class="mt-2">
                                    <input type="text" name="customerPatronymic" id="customerPatronymic" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                           value="${requestScope.customerCardView.getCustomerPatronymic()}" readonly>
                                </div>
                            </div>

                            <div class="sm:col-span-4">
                                <label for="customerEmail" class="block text-xl font-medium leading-6 text-gray-900">Customer phone</label>
                                <div class="mt-2">
                                    <input type="email" name="customerEmail" id="customerEmail" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                           value="${requestScope.customerCardView.getPhoneNumber()}" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</c:if>

<c:forEach items="${requestScope.sales}" var="sale" varStatus="status">
    <div class="flex justify-center mt-6">

        <form class="w-4/5">
            <div class="space-y-6">
                <div class=" pb-6">
                    <h2 class="text-3xl font-semibold leading-7 text-gray-900">Product ${status.index + 1}</h2>

                    <div class=" pb-12">
                        <h2 class="text-2xl font-semibold leading-7 text-gray-900">Detail Information</h2>

                        <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                            <div class="sm:col-span-6">
                                <label class="block text-2xl font-medium leading-6 text-gray-900">Product name:
                                    <c:forEach items="${requestScope.storeProducts}" var="storeProduct" varStatus="status">
                                    <c:if test="${sale.getPk().getUPC() eq storeProduct.getId()}">
                                        <c:forEach items="${requestScope.products}" var="product" varStatus="stat">
                                            <c:if test="${storeProduct.getProductId() eq product.getId()}">${product.getName()}</c:if>
                                        </c:forEach>
                                    </c:if>
                                    </c:forEach></label>
                            </div>

                            <div class="sm:col-span-2">
                                <label for="sellingPrice" class="block text-xl font-medium leading-6 text-gray-900">Selling Price</label>
                                <div class="mt-2">
                                    <input type="text" name="sellingPrice" id="sellingPrice" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                           value="${sale.getSellingPrice()}" readonly>
                                </div>
                            </div>

                            <div class="sm:col-span-2">
                                <label for="productQuantity" class="block text-xl font-medium leading-6 text-gray-900">Product Quantity</label>
                                <div class="mt-2">
                                    <input type="text" name="productQuantity" id="productQuantity" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                        value="${sale.getProductQuantity()}" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</c:forEach>

<div class="flex justify-center mt-16">

    <form class="w-4/5">
        <div class="space-y-12">
            <div class=" pb-6">
                <h2 class="text-3xl font-semibold leading-7 text-gray-900">Total</h2>

                <div class=" pb-12">

                    <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                        <div class="sm:col-span-2">
                            <label for="total" class="block text-xl font-medium leading-6 text-gray-900">Total sum</label>
                            <div class="mt-2">
                                <input type="text" name="total" id="total" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.receiptView.getSumTotal()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="vat" class="block text-xl font-medium leading-6 text-gray-900">Vat</label>
                            <div class="mt-2">
                                <input type="text" name="vat" id="vat" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                                       value="${requestScope.receiptView.getVat()}" readonly>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>