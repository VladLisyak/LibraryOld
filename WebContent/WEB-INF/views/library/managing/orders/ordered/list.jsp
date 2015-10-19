<%@include file="../../../../../includes/library/libHead.jsp" %>
<fmt:message var="title" key="txt.books.orders"/>
<lb:wrapper title="${title}">
  <jsp:attribute name="body">
      <div class="row">
          <div class="col-lg-12">
              <utl:result result="${requestScope.result}"/>
              <c:if test="${!empty requestScope.orders}">
                  <div class="table-responsive">
                      <table class="table table-bordered table-hover">
                          <thead>
                          <tr>
                              <th>№</th>
                              <th><fmt:message key="txt.book"/></th>
                              <th><fmt:message key="txt.subscription.id"/></th>
                              <th></th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${requestScope.orders}" var="order">
                              <tr>
                                  <td>${order.id}</td>
                                  <td>${order.bookId}</td>
                                  <td>${order.subscriptionId}</td>
                                  <td>
                                      <a href="/library/orders/ordered/accept?id=${order.id}">
                                          <fmt:message key="txt.accept"/>
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
</lb:wrapper>