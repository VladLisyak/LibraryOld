<%@include file="../../includes/main/mainHead.jsp" %>
<fmt:message var="title" key="txt.profile"/>
<us:wrapper title="${title}">
<jsp:attribute name="body">
<header class="content-title">
                        <h1 class="title">${title}</h1>
</header>
                    
<ul class="nav nav-tabs" role="tablist">
  <li role="presentation" class="active"><a href="#tab1" role="tab" data-toggle="tab"><fmt:message key ="txt.personalInfo" /></a></li>
  <c:if test="${!empty requestScope.orders}">
  <li role="presentation"><a href="#tab2" role="tab" data-toggle="tab"><fmt:message key="txt.books.orders"/></a></li>
  </c:if>
  <c:if test="${!empty requestScope.checkOuts}"> 
  <li role="presentation"><a href="#tab3" role="tab" data-toggle="tab"><fmt:message key="txt.books.checkOuts"/></a></li>
  </c:if>
</ul>
<div class="tab-content">
 <div role="tabpanel" class="tab-pane active" id="tab1">
  <ul class="product-list">
                                    <li><span><fmt:message
                                            key="txt.firstName"/>:</span>${requestScope.reader.firstName}</li>
                                    <li><span><fmt:message
                                            key="txt.lastName"/>:</span>${requestScope.reader.lastName}</li>
                                    <li><span><fmt:message
                                            key="txt.login"/>:</span>${requestScope.reader.login}</li>
                                    <li><span><fmt:message
                                            key="txt.subscription.id"/>:</span>${requestScope.reader.subscription.id}
                                    </li>
                                    <li>
                                    	<c:choose>
                                    	<c:when test="${!(reader.role eq requestScope.userOrd)}">
                                    		  <div class = "errors"><span><fmt:message key="txt.subscription.expired"/></span></div>
                                    		</c:when>
                                    		<c:otherwise>
                                    		  <span><fmt:message key="txt.subscription.expires"/>:</span>
                                        	  <fmt:formatDate
                                                value="${requestScope.reader.subscription.expirationDate}"
                                                pattern="dd.MM.yyyy"/>
                                    		</c:otherwise>
                                    	</c:choose>
                                    	
                                    		  
                                    </li>
                                </ul>
 </div>
 <div role="tabpanel" class="tab-pane" id="tab2">
  <c:if test="${!empty requestScope.orders}">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th class="table-title">
                                                <fmt:message key="txt.book"/>
                                            </th>
                                             <th class="table-title">
                                                <fmt:message key="txt.title"/>
                                            </th>
                                            <th class="table-title">
                                                <fmt:message key="txt.takeBefore"/>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.orders}" var="order">
                                            <tr>
                                                <td>
                                                    <div style="max-width: 100px; display: inline-block">
                                                        <a href="${pageContext.request.contextPath}/book?id=${order.bookId}">
                                                            <img src="${pageContext.request.contextPath}/uploads/books/${order.book.image}">
                                                        </a>
                                                        
                                                        
                                                    </div>
                                                </td>
                                                <td>
                                                <a href="${pageContext.request.contextPath}/book?id=${order.bookId}">
                                                                ${order.book.title.value(sessionScope.currLocale)}
                                                        </a>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${order.dueDate}" pattern="dd.MM.yyyy"/>
                                                </td>
                                                <td>
			                                    	<form action="${pageContext.request.contextPath}/profile" method="post">
			                                    		<input type ="hidden" name = "orderID" value="${order.id}" />
			                                    		<div class="action_bottom">
        													<input class="btn" type="submit" value="<fmt:message key="txt.delete"/>" />
      													</div>
			                                    	</form>
                                    			</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                            </c:if>
 </div>

 <div role="tabpanel" class="tab-pane" id="tab3">
  <c:if test="${!empty requestScope.checkOuts}">
                                <div class="tab-pane" id="tab3">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th class="table-title">
                                                <fmt:message key="txt.book"/>
                                            </th>
                                            <th class="table-title">
                                                <fmt:message key="txt.returnBefore"/>
                                            </th>
                                            <th class="table-title">
                                                <fmt:message key="txt.fee"/>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.checkOuts}" var="order">
                                            <tr>
                                                <td>
                                                    <div style="max-width: 180px; display: inline-block">
                                                        <a href="${pageContext.request.contextPath}/book?id=${order.bookId}">
                                                            <img src="<c:url value="/uploads/books/${order.book.image}"/>">
                                                        </a>
                                                        <br>
                                                        <a href="${pageContext.request.contextPath}/book?id=${order.bookId}">
                                                                ${order.book.title.value(sessionScope.currLocale)}
                                                        </a>
                                                    </div>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${order.dueDate}" pattern="dd.MM.yyyy"/>
                                                </td>
                                                <td>${order.fee}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
 </div>
</div>
</jsp:attribute>
</us:wrapper>