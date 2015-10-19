<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.delete.book"/>
<db:wrapper title="${title}" id="${requestScope.book.id}">
  <jsp:attribute name="body">
        <div class="row">
          <div class="col-lg-12">
            <p>
              <fmt:message key="txt.delete.bookLeftPart"/>
              <span> ${requestScope.book.title.value(requestScope.currLocale)}</span>? <fmt:message
                    key="txt.delete.bookRightPart"/>
            </p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <form action="/dashboard/books/delete?id=${requestScope.book.id}" method="post">
            <utl:confirm link="/dashboard/books"/>
            </form>
          </div>
        </div>
  </jsp:attribute>
</db:wrapper>
