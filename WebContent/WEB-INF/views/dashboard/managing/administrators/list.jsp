<%@include file="../../../../includes/dashboard/dashHead.jsp" %>
<fmt:message var="title" key="role.adminM"/>
<db:wrapper title="${title}">
     <jsp:attribute name="actionButtons">
        <a href="/dashboard/users/administrators/add">
	        <button type="button" class="btn btn-info link-button">   
	                <fmt:message key="txt.add.admin"/>  
	        </button>
        </a>
    </jsp:attribute>
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <utl:result result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.administrators}">
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
                          <c:forEach items="${requestScope.administrators}" var="admin">
                              <tr>
                                  <td>${admin.id}</td>
                                  <td>${admin.firstName}</td>
                                  <td>${admin.lastName}</td>
                                  <td>${admin.email}</td>
                                  <td>${admin.login}</td>
                                  <td>
                                      <a href="/dashboard/users/administrators/edit?id=${admin.id}">
                                          <fmt:message key="txt.edit"/>
                                      </a>
                                      <c:if test="${sessionScope.currentAdmin.id!=admin.id}">
                                          <a href="/dashboard/users/administrators/delete?id=${admin.id}">
                                              <fmt:message key="txt.delete"/>
                                          </a>
                                      </c:if>
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