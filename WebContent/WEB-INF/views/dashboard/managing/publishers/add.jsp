<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.add.publisher"/>
<fmt:message var="formTitle" key="txt.title"/>
<db:wrapper title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <form action="/dashboard/authors/add" method="post" enctype="multipart/form-data">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:text label="txt.fullName" locale="${locale}" id="name"
                                value="${requestScope.author.name.value(locale.key)}"
                                required="true"/>
                    </c:forEach>
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:textarea label="txt.description" locale="${locale}" id="description"
                                    value="${requestScope.author.description.value(locale.key)}"/>
                    </c:forEach>
                    <db:imageUpload id="image"/>
                    <utl:confirm link="/dashboard/authors"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>