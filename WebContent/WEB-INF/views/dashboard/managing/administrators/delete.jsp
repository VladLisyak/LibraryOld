<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.delete.admin"/>
<db:wrapper title="${title}" id="${requestScope.admin.id}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="txt.delete.adminLeftPart"/>
                <span>
                    ${requestScope.admin.login}
                    (${requestScope.admin.firstName}&nbsp;${requestScope.admin.lastName})?
                </span> <fmt:message key="txt.delete.adminRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="/dashboard/users/administrators/delete?id=${requestScope.admin.id}" method="post">
                    <utl:confirm link="/dashboard/users/administrators"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>
