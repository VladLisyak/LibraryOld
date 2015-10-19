<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.books"/>
<db:wrapper title="${title}">
    <jsp:attribute name="actionButtons">
        <a href="/dashboard/books/add">
        <button type="button" class="btn  btn-info link-button">
                <fmt:message key="txt.add.book"/>
        </button>
        </a>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <c:if test="${!empty requestScope.books}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>№</th>
                                <th><fmt:message key="txt.title"/></th>
                                <th><fmt:message key="txt.description"/></th>
                                <th><fmt:message key="txt.bookAmount"/></th>
                                <th><fmt:message key="txt.bookYear"/></th>
                                <th><fmt:message key="txt.pagesCount"/></th>
                                <th><fmt:message key="txt.authors"/></th>
                                <th><fmt:message key="txt.publisher"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.books}" var="book">
                                <tr>
                                    <td>${book.id}</td>
                                    <td>${book.title.value(sessionScope.currLocale)}</td>
                                    <td>${book.description.value(sessionScope.currLocale)}</td>
                                    <td>${book.amount}</td>
                                    <td>${book.year}</td>
                                    <td>${book.pages}</td>
                                    <td>
                                        <c:forEach items="${book.authors}" var="author" varStatus="status">
   	                                         <a href="/dashboard/authors/edit?id=${author.id}">
                                                    ${author.name.value(sessionScope.currLocale)}
                                            </a><c:if test="${!status.last}">, </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <a href="/dashboard/publishers/edit?id=${book.publisher.id}">
                                                ${book.publisher.title.value(sessionScope.currLocale)}
                                        </a>
                                    </td>
                                    <td>
                                        <a href="/dashboard/books/edit?id=${book.id}">
                                            <fmt:message key="txt.edit"/>
                                        </a>

                                        <a href="/dashboard/books/delete?id=${book.id}">
                                            <fmt:message key="txt.delete"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>