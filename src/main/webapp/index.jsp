<%@include file="WEB-INF/view/indexHeader.jsp"%>
<div class="container-fluid" align="center">

    <div class="row-fluid" align="center">
        <c:if test="${not empty param.success}">
            <div class="alert alert-success">
                <fmt:message key="${param.success}" bundle="${rb}" />
            </div>
        </c:if>
    </div>
    <div class="row-fluid">
        <c:if test="${not empty param.error}">


            <div class="mt-4 flex p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50 w-4/5" role="alert">
                <svg class="flex-shrink-0 inline w-4 h-4 me-3 mt-[2px]" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
                </svg>
                <span class="sr-only">Danger</span>
                <div>
                    <span class="font-medium">Caution</span>
                    <ul class="mt-1.5 list-disc list-inside">
                        <li><fmt:message key="${param.error}" bundle="${rb}"/></li>
                    </ul>
                </div>
            </div>
        </c:if>
    </div>

    <div class="row-fluid">
        <h2>
<%--            <fmt:message key="restaurant.greeting" bundle="${rb}" />--%>
        </h2>
    </div>

</div>
<%--<%@include file="WEB-INF/views/footer.jsp"%>--%>