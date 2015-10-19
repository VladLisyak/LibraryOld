<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="role.librarianM"/>
<db:wrapper title="${title}">
     <jsp:attribute name="actionButtons">
        <a href="/dashboard/users/librarians/add">
        <button type="button" class="btn  btn-info link-button">
                <fmt:message key="txt.add.librarian"/>  
        </button>
        </a>
    </jsp:attribute>
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <utl:result result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.librarians}">
                  <div class="table-responsive">
                      <table class="table table-bordered table-hover">
                          <thead>
                          <tr>
                              <th>№</th>
                              <th><fmt:message key="txt.firstName"/></th>
                              <th><fmt:message key="txt.lastName"/></th>
                              <th><fmt:message key="txt.email"/></th>
                              <th><fmt:message key="txt.login"/></th>
                              <th></th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${requestScope.librarians}" var="librarian">
                              <tr>
                                  <td>${librarian.id}</td>
                                  <td>${librarian.firstName}</td>
                                  <td>${librarian.lastName}</td>
                                  <td>${librarian.email}</td>
                                  <td>${librarian.login}</td>
                                  <td>
                                      <a href="/dashboard/users/librarians/edit?id=${librarian.id}">
                                          <fmt:message key="txt.edit"/>
                                      </a>
                                      <a href="/dashboard/users/librarians/delete?id=${librarian.id}">
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