<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.delete.author"/>
<db:wrapper title="${title}" id="${requestScope.author.id}" >
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="txt.delete.authorLeftPart"/>
                    <span> ${requestScope.author.name.value(requestScope.currLocale)}</span>? <fmt:message
                        key="txt.delete.authorRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="/dashboard/authors/delete?id=${requestScope.author.id}" method="post">
                    <utl:confirm link="/dashboard/authors"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>
