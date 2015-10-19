<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.subscription.extend"/>
<db:wrapper title="${title}" id ="${requestScope.user.id}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="txt.subscription.extendLeftPart"/> <span> ${requestScope.user.login}</span>?
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="${requestScope.submitUrl}" method="post">
                    <input type="hidden" name="id" value="${requestScope.user.id}">
                    <utl:confirm link="${backUrl}"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>
