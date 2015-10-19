<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.edit.publisher"/>
<fmt:message var="formTitle" key="text.title"/>
<db:wrapper title="${title}"  id="${requestScope.publisher.id}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <form action="/dashboard/publishers/edit?id=${requestScope.publisher.id}"
                      method="post">
                    <input type="hidden" name="title_id" value="${requestScope.publisher.title.id}">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:text label="txt.title" locale="${locale}"  id="title"
                                value="${requestScope.publisher.title.value(locale)}"
                                required="true"/>
                    </c:forEach>
                    <utl:confirm link="/dashboard/publishers"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>