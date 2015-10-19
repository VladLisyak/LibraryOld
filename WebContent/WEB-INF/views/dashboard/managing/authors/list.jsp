<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="txt.authors"/>
<db:wrapper title="${title}">
    <jsp:attribute name="actionButtons">
       <a href="/dashboard/authors/add">
        <button type="button" class="btn  btn-info link-button">
                <fmt:message key="txt.add.author"/>    
        </button>
         </a>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-lg-12">
                <utl:result result="${requestScope.result}"/>
                <c:if test="${!empty requestScope.authors}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>№</th>
                                <th><fmt:message key="txt.fullName"/></th>
                                <th><fmt:message key="txt.description"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.authors}" var="author">
                                <tr>
                                    <td>${author.id}</td>
                                    <td>${author.name.value(sessionScope.currLocale)}</td>
                                    <td>${author.description.value(sessionScope.currLocale)}</td>
                                    <td>
                                        <a href="/dashboard/authors/edit?id=${author.id}">
                                            <fmt:message key="txt.edit"/>
                                        </a>

                                        <a href="/dashboard/authors/delete?id=${author.id}">
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