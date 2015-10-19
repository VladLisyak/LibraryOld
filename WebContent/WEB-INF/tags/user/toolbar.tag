<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="id"%>
<fmt:setLocale value="${sessionScope.currLocale}"/>
<fmt:setBundle basename="transls"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <div class="toolbar-wrapper">
    <div class="toolbar clearfix">
      <div id="menu-button" class="menu-icon"><i class="fa fa-bars"></i>Menu</div>
      <ul class="unstyled">
      <li>
	      <form action="${pageContext.request.contextPath}">
				<input type="hidden" name="id" value="${id}"> 
	          <select name="lang" onchange="if (this.form) this.form.submit();">
			      <c:forEach items="${applicationScope.locales}" var="locale"
	                                                       varStatus="status">                                        
			      		<option <c:if test="${locale eq sessionScope.currLocale}">selected</c:if>>${locale}</option>
			      </c:forEach>
		      </select>
		   </form>
	   </li>
        <c:if test="${!empty sessionScope.user}">  
        	<li class="fr"><a href="${pageContext.request.contextPath}/cart" class="cart" title="Cart"><i class="fa fa-shopping-cart"></i><fmt:message key="txt.inCart"/>: ${requestScope.orderedBooksCount}</a></li>
      	</c:if>
  	   <li class="search-field fr">
          <form class="search" action="${pageContext.request.contextPath}/books" accept-charset="UTF-8" method="get">
          <input type="hidden" name="orderBy" class="form-control"
                                                     value="${param.orderBy}">
            <button type="submit" class="go"><i class="fa fa-search"></i></button>
            <input type="text" name="search" class="search_box" placeholder="<fmt:message key="txt.search"/>" value="${param.search}">
          </form>
        </li>
	    <c:choose>
		     <c:when test="${!empty sessionScope.currUser}">
			      <li class="customer-links">
			      <a href="${pageContext.request.contextPath}/logout" id="customer_login_link"><fmt:message key="txt.log-out"/></a>
		     	 
		     	  <span class="or">&nbsp;/&nbsp;</span>
			      <a href="${pageContext.request.contextPath}/profile" id="customer_register_link"><fmt:message key="txt.profile"/></a>
			      </li>
		     </c:when>
		     <c:otherwise>
			      <li class="customer-links">
			      <a href="${pageContext.request.contextPath}/index" id="customer_login_link"><fmt:message key="txt.login"/></a>
			      
			      <span class="or">&nbsp;/&nbsp;</span>
			      <a href="${pageContext.request.contextPath}/registration" id="customer_register_link"><fmt:message key="txt.registration"/></a>
			    </li>
		     </c:otherwise>
	  	</c:choose>
    
      </ul>
    </div>
  </div>