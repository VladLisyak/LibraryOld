<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.edit.author"/>
<fmt:message var="formDescription" key="txt.description"/>
<db:wrapper title="${title}" id="${requestScope.author.id}">
    <jsp:attribute name="body">
<div class="errors">${requestScope.messages.error}</div>
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <form action="${pageContext.request.contextPath}/dashboard/authors/edit?id=${requestScope.author.id}" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="name_id" value="${requestScope.author.name.id}">
                    <input type="hidden" name="description_id" value="${requestScope.author.description.id}">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:text label="txt.fullName" locale="${locale}" id="name"
                                value="${requestScope.author.name.value(locale)}"
                                required="true"/>
                    </c:forEach>

                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:textarea label="txt.description" locale="${locale}" id="description"
                                    value="${requestScope.author.description.value(locale)}"/>
                    </c:forEach>
                    <db:imageUpload id="image"
                                   subFolder="authors"
                                   path="${requestScope.author.image}"/>

                    <utl:confirm link="/dashboard/authors"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
  <jsp:attribute name="head">
          <link href="<c:url value="/assets/dashboard/css/chosen.min.css"/>" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="bottom">
        <script src="<c:url value="/assets/dashboard/js/chosen.jquery.min.js"/>"></script>
    </jsp:attribute>
</db:wrapper>