<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.add.book"/>
<jsp:useBean id="today" class="java.util.Date" scope="page"/>
<fmt:formatDate value="${today}" pattern="yyyy" var="yearValue"/>
<db:wrapper title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <form action="/dashboard/books/add" method="post" enctype="multipart/form-data">
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:text label="txt.title" locale="${locale}" id="title"
                                value="${requestScope.book.title.value(locale)}"
                                required="true"/>
                    </c:forEach>
                    <c:forEach items="${applicationScope.locales}" var="locale">
                        <utl:textarea label="txt.description" locale="${locale}"
                                    id="description"
                                    value="${requestScope.book.description.value(locale)}"/>
                    </c:forEach>
                    <utl:num label="txt.bookAmount" id="amount" min="0" value="${requestScope.book.amount}"
                           required="true"/>
                    <utl:num label="txt.bookYear" id="year" min="0" max="${yearValue}"
                           value="${requestScope.book.year}" required="true"/>
                    <utl:num label="txt.pagesCount" id="pages" min="1" value="${requestScope.book.pages}"
                           required="true"/>
                    <db:chosen label="txt.authors" id="authors" placeholder="txt.chooseAuthors" required="true"
                              multiple="true">
                        <jsp:attribute name="body">
                               <c:forEach items="${requestScope.authors}" var="author">
                                   <c:set var="contains" value="false"/>
                                   <c:forEach var="bookAuthor" items="${requestScope.book.authors}">
                                       <c:if test="${author.id eq bookAuthor.id}">
                                           <c:set var="contains" value="true"/>
                                       </c:if>
                                   </c:forEach>
                                   <option value="${author.id}"
                                           <c:if test="${contains==true}">selected</c:if>>
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
                    <db:imageUpload id="image"/>
                    <utl:confirm link="/dashboard/books"/>
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