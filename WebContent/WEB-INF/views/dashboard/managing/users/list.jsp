<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="role.userM"/>
<db:wrapper title="${title}">
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <utl:result result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.users}">
                  <div class="table-responsive">
                      <table class="table table-bordered table-hover">
                          <thead>
                          <tr>
                              <th>№</th>
                              <th><fmt:message key="txt.firstName"/></th>
                              <th><fmt:message key="txt.lastName"/></th>
                              <th><fmt:message key="txt.email"/></th>
                              <th><fmt:message key="txt.login"/></th>
                              <th><fmt:message key="txt.subscription.expires.date"/></th>
                              <th></th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${requestScope.users}" var="user">
                              <tr>
                                  <td>${user.id}</td>
                                  <td>${user.firstName}</td>
                                  <td>${user.lastName}</td>
                                  <td>${user.email}</td>
                                  <td>${user.login}</td>
                                  <td>
                                      <fmt:formatDate value="${user.subscription.expirationDate}"
                                                      pattern="dd.MM.yyyy"/>
                                  </td>
                                  <td>
                                      <a href="/dashboard/users/ban?id=${user.id}">
                                          <fmt:message key="txt.ban"/>
                                      </a>
                                      <a href="/dashboard/users/extend?id=${user.id}">
                                          <fmt:message key="txt.subscription.extend"/>
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