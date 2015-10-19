<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.edit.book"/>
<jsp:useBean id="today" class="java.util.Date" scope="page"/>
<fmt:formatDate value="${today}" pattern="yyyy" var="yearValue"/>
<db:wrapper title="${title}" id = "${requestScope.book.id}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <form action="/dashboard/books/edit?id=${requestScope.book.id}" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="title_id" value="${requestScope.author.name.id}">
                    <input type="hidden" name="description_id" value="${requestScope.author.description.id}">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:text label="txt.title" locale="${locale}"  id="title"
                                value="${requestScope.book.title.value(locale)}"
                                required="true"/>
                    </c:forEach>
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:textarea label="txt.description" locale="${locale}"
                                    id="description"
                                    value="${requestScope.book.description.value(locale)}"/>
                    </c:forEach>
                    <utl:num label="txt.bookAmount" id="amount" min="0" value="${requestScope.book.amount}"/>
                    <utl:num label="txt.bookYear" id="year" min="0" max="${yearValue}"
                           value="${requestScope.book.year}" required="true"/>
                    <utl:num label="txt.pagesCount" id="pages" min="1" value="${requestScope.book.pages}"
                           required="true"/>
                    <db:chosen label="txt.authors" placeholder="txt.chooseAuthors" required="true" id="authors"
                              multiple="true">
                        <jsp:attribute name="body">
                           <c:forEach items="${requestScope.authors}" var="author">
                               <option value="${author.id}"
                                       <c:if test="${fn:contains(requestScope.book.authors,author)==true}">selected</c:if>>
                                       ${author.name.value(sessionScope.currLocale)}
                               </option>
                           </c:forEach>
                        </jsp:attribute>
                    </db:chosen>
                    <db:chosen label="txt.publishers" placeholder="txt.choosePublisher" required="true"
                              id="publisherId">
                        <jsp:attribute name="body">
                           <c:forEach items="${requestScope.publishers}" var="publisher">
                               <option value="${publisher.id}"
                                       <c:if test="${publisher.id eq requestScope.book.publisher.id}">selected</c:if>>
                                       ${publisher.title.value(sessionScope.currLocale)}
                               </option>
                           </c:forEach>
                        </jsp:attribute>
                    </db:chosen>
                    <db:imageUpload id="image" subFolder="books" path="${requestScope.book.image}"/>
                    <utl:confirm link="/dashboard/books"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="head">
          <link href="<c:url value="/assets/dashboard/css/chosen.min.css"/>" rel="stylesheet">
          <script src="<c:url value="/assets/dashboard/js/chosen.jquery.min.js"/>"></script>
    </jsp:attribute>
</db:wrapper>