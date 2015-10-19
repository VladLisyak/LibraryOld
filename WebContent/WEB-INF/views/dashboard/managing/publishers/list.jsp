<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.publishers"/>
<db:wrapper title="${title}">
    <jsp:attribute name="actionButtons">
         <a href="/dashboard/publishers/add">
	        <button type="button" class="btn  btn-info link-button">
	                <fmt:message key="txt.add.publisher"/>
	        </button>
        </a>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <c:if test="${!empty requestScope.publishers}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>№</th>
                                <th><fmt:message key="txt.title"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.publishers}" var="publisher">
                                <tr>
                                    <td>${publisher.id}</td>
                                    <td>${publisher.title.value(sessionScope.currLocale)}</td>
                                    <td>
                                        <a href="/dashboard/publishers/edit?id=${publisher.id}">
                                            <fmt:message key="txt.edit"/>
                                        </a>
                                        <a href="/dashboard/publishers/delete?id=${publisher.id}">
                                            <fmt:message key="txt.delete"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>