<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 25.04.2024
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>

<div class="relative overflow-x-auto shadow-md mt-4">

    <div class="mb-2 grid grid-cols-6 gap-x-6 gap-y-1 sm:grid-cols-6">
        <div class="sm:col-span-1 py-1.5">
            <c:if test="${user.getRole().toString() eq 'CASHIER'}">
                <a href="${pageContext.request.contextPath}/controller/createSale" class=" m-1.5">
                    <button type="button" class="w-full h-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" aria-expanded="false" aria-haspopup="true">
                        <span class="text-white">Create Sale</span>
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
                Receipt id
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Product Name
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Quantity
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Selling price
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Action
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.sales}" var="sale" varStatus="status">
            <tr class="border-b border-gray-200 dark:border-gray-900">
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">
                        ${status.index + 1}
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    <c:forEach items="${requestScope.receipts}" var="receipt" varStatus="status">
                        <c:if test="${sale.getPk().getReceiptId() eq receipt.getId()}">${receipt.getId()}</c:if>
                    </c:forEach>
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">
                    <c:forEach items="${requestScope.storeProducts}" var="storeProduct" varStatus="status">
                        <c:if test="${sale.getPk().getUPC() eq storeProduct.getId()}">
                            <c:forEach items="${requestScope.products}" var="product" varStatus="stat">
                                <c:if test="${storeProduct.getProductId() eq product.getId()}">${product.getName()}</c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                        ${sale.getProductQuantity()}
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">
                        ${sale.getSellingPrice()}
                </td>

                <td class="flex justify-center px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    <c:if test="${user.getRole().toString() eq 'CASHIER'}"><label class="w-full relative rounded-lg bg-gray-700 text-gray-700 text-sm">t</label></c:if>
                    <c:if test="${user.getRole().toString() eq 'MANAGER'}">
                        <a href="${pageContext.request.contextPath}/controller/deleteSale?receipt=${sale.getPk().getReceiptId()}&storeProductId=${sale.getPk().getUPC()}" class="w-1/3 m-1.5">
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

