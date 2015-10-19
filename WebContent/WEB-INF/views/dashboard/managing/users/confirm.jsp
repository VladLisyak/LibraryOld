<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.userConfirmation"/>
<db:wrapper title="${title}" id = "${requestScope.user.id}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="txt.confirmUserLeftPart"/> <span> ${requestScope.user.login}</span>? <fmt:message
                        key="txt.confirmUserRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="/dashboard/users/confirm" method="post">
                    <input type="hidden" name="id" value="${requestScope.user.id}">
                    <utl:confirm link="/dashboard/users/not-confirmed"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>
