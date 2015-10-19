<%@include file="../../../includes/library/libHead.jsp" %>
<fmt:message var="title" key="txt.books.orders"/>
<lb:wrapper title="${title}">
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <utl:result result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.userBooks}">
                  <div class="table-responsive">
                      <table class="table table-bordered table-hover">
                          <thead>
                          <tr>
                              <th>№</th>
                              <th><fmt:message key="txt.users"/></th>
                              <th><fmt:message key="txt.subscription.id"/></th>
                              <th><fmt:message key="txt.books"/></th>
                              <th><fmt:message key="txt.fee"/></th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${requestScope.userBooks}" var="userBook">
                              <tr>
                                  <td>${userBook.id}</td>
                                  <td>${userBook.login}</td>
                                  <td>${userBook.subscription.id}</td>
                                  <td>
                                      <c:forEach items="${userBook.books}" var="book" varStatus="status">
                                          ${book.title.value(sessionScope.currLocale)}
                                          <c:if test="${!status.last}">, </c:if>
                                      </c:forEach>
                                  </td>
                                  <td>${userBook.feeSum}</td>
                              </tr>
                          </c:forEach>
                          </tbody>
                      </table>
                  </div>
              </c:if>
          </div>
      </div>
  </jsp:attribute>
</lb:wrapper>