<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.delete.publisher"/>
<db:wrapper title="${title}" id="${requestScope.publisher.id}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="txt.delete.publisherLeftPart"/>
                    <span> ${requestScope.publisher.title.value(requestScope.currLocale)}</span>? <fmt:message
                        key="txt.delete.publisherRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="/dashboard/publishers/delete?id=${requestScope.publisher.id}"
                      method="post">
                    <utl:confirm link="/dashboard/publishers"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>
