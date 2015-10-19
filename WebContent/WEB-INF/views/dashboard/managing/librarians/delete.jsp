<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.delete.librarian"/>
<db:wrapper title="${title}" id = "${requestScope.librarian.id}">
  <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <p>
                    <fmt:message key="txt.delete.librarianLeftPart"/>
                <span>
                    ${requestScope.librarian.login}
                    (${requestScope.librarian.firstName}&nbsp;${requestScope.librarian.lastName})?
                </span> <fmt:message key="txt.delete.librarianRightPart"/>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <form action="/dashboard/users/librarians/delete?id=${requestScope.librarian.id}" method="post">
                    <utl:confirm link="/dashboard/users/librarians"/>
                </form>
            </div>
        </div>
  </jsp:attribute>
</db:wrapper>
