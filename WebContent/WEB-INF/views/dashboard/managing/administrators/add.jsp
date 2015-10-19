<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.add.admin"/>
<db:wrapper title="${title}">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>

                <form action="/dashboard/users/administrators/add" method="post">
                    <utl:text label="txt.firstName" id="firstName" value="${requestScope.admin.firstName}"/>
                    <utl:text label="txt.lastName" id="lastName" value="${requestScope.admin.lastName}"/>
                    <utl:text label="txt.email" id="email" value="${requestScope.admin.email}"/>
                    <utl:text label="txt.login" id="login" value="${requestScope.admin.login}"/>
                    <utl:password label="txt.password" id="password" value="${requestScope.admin.password}"/>
                    <utl:confirm link="/dashboard/users/administators"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
</db:wrapper>