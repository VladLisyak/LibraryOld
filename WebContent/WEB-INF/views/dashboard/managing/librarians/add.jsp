<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.add.librarian"/>
<db:wrapper title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <form action="/dashboard/users/librarians/add" method="post">
                    <utl:text label="txt.firstName" id="firstName" value="${requestScope.librarian.firstName}"/>
                    <utl:text label="txt.lastName" id="lastName" value="${requestScope.librarian.lastName}"/>
                    <utl:text label="txt.email" id="email" value="${requestScope.librarian.email}"/>
                    <utl:text label="txt.login" id="login" value="${requestScope.librarian.login}"/>
                    <utl:password label="txt.password" id="password" value="${requestScope.librarian.password}"/>
                    <utl:confirm link="/dashboard/users/librarians"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
</db:wrapper>