<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 24.04.2024
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>

<div class="relative overflow-x-auto shadow-md mt-4">

    <div class="mb-2 grid grid-cols-6 gap-x-6 gap-y-1 sm:grid-cols-6">
        <div class="sm:col-span-1 py-1.5">
            <a href="${pageContext.request.contextPath}/controller/createStoreProduct" class=" m-1.5">
                <button type="button" class="w-full h-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" aria-expanded="false" aria-haspopup="true">
                    <span class="text-white">Create Product</span>
                </button>
            </a>
        </div>
        <div class="sm:col-span-5">
            <form action="./sortStoreProducts" method="GET" class="flex flex-nowrap justify-center items-center mr-8 ml-8 h-full">
                <input type="text" name="productsName" id="productsName" class="w-4/5 mr-6 block rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6"
                       <c:if test="${ not empty requestScope.productsName}">value="${requestScope.productsName}"</c:if>
                >

                <select id="sortStoreProductsBy" name="sortStoreProductsBy" class="w-4/5 mr-6 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                    <option <c:if test="${requestScope.sortStoreProductsBy eq 'byNumber'}">selected</c:if> value="byNumber">By number</option>
                    <option <c:if test="${requestScope.sortStoreProductsBy eq 'byName'}">selected</c:if> value="byName">By name</option>
                </select>

                <select id="storeProductsType" name="storeProductsType" class="w-4/5 mr-6 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                    <option <c:if test="${empty requestScope.storeProductsType}">selected</c:if> value="all">all</option>
                    <option <c:if test="${requestScope.storeProductsType eq 'non-promotional'}">selected</c:if> value="non-promotional">non-promotional</option>
                    <option <c:if test="${requestScope.storeProductsType eq 'promotional'}">selected</c:if> value="promotional">promotional</option>
                </select>

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
                Selling price
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Product quantity
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
                Is promotional
            </th>
            <th scope="col" class="px-6 py-3 bg-gray-50 dark:bg-gray-800 text-center">
               Action
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.storeProducts}" var="storeProduct" varStatus="status">
            <tr class="border-b border-gray-200 dark:border-gray-900">
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-600">${status.index + 1}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    <c:forEach items="${requestScope.products}" var="product" varStatus="status">
                        <c:if test="${storeProduct.getProductId() eq product.getId()}">${product.getName()}</c:if>
                    </c:forEach>
                </td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">${storeProduct.getSellingPrice()}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">${storeProduct.getProductQuantity()}</td>
                <td class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">${storeProduct.getIsPromotional()}</td>

                <td class="flex justify-center px-6 py-4 font-medium text-gray-900 whitespace-nowrap bg-gray-50 dark:text-white text-center dark:bg-gray-700">
                    <a href="${pageContext.request.contextPath}/controller/viewStoreProduct?id=${storeProduct.getId()}" class="w-1/3 m-1.5">
                        <button type="button" class="w-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" id="view-button" aria-expanded="false" aria-haspopup="true">
                            <span>View</span>
                        </button>
                    </a>
                    <a href="${pageContext.request.contextPath}/controller/updateStoreProduct?id=${storeProduct.getId()}" class="w-1/3 m-1.5">
                        <button type="button" class="w-full relative rounded-lg bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-300" id="update-button" aria-expanded="false" aria-haspopup="true">
                            <span>Edit</span>
                        </button>
                    </a>
                    <a href="${pageContext.request.contextPath}/controller/deleteStoreProduct?id=${storeProduct.getId()}" class="w-1/3 m-1.5">
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
