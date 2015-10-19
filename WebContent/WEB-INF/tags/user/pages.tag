<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="count" required="true" type="java.lang.Integer" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<fmt:setLocale value="${sessionScope.currLocale}"/>
<fmt:setBundle basename="transls"/>
<c:choose>
    <c:when test="${empty param.search}">
        <c:set var="searchParam" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="searchParam" value="&search=${param.search}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${empty param.orderBy}">
        <c:set var="orderParam" value=""/>
        
    </c:when>
    <c:otherwise>
        <c:set var="orderParam" value="&orderBy=${param.orderBy}"/>
        
    </c:otherwise>
</c:choose>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
            <div class="row">
  			</div>
<nav>
  <ul class="pagination">
 <c:if test="${count >0}">
		  <c:choose>
		      <c:when test="${requestScope.currPage eq 1}">
		      		<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
		      </c:when>
		      <c:otherwise>
		      		<li><a href="${pageContext.request.contextPath}?page=${requestScope.currPage-1}${searchParam}${orderParam}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
		      </c:otherwise>
		 </c:choose>
		   	<c:forEach var="loopPage" begin="1" end="${count}">
		   		<c:choose>
		   		  <c:when test="${loopPage eq requestScope.currPage}">
		   		  	<li class="active"><a href="${pageContext.request.contextPath}?page=${loopPage}${searchParam}${orderParam}">${loopPage}<span class="sr-only">(current)</span></a></li> 
		   		  </c:when>
		   		  <c:otherwise>
		   		    <li><a href="${pageContext.request.contextPath}?page=${loopPage}${searchParam}${orderParam}">${loopPage}<span class="sr-only"></span></a></li> 
		   		  </c:otherwise>
		   		</c:choose>
		   			</c:forEach>
		    <c:choose>
			    <c:when test="${requestScope.currPage eq count}">
			      		<li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			      </c:when>
			      <c:otherwise>
			      		<li><a href="${pageContext.request.contextPath}?page=${requestScope.currPage+1}${searchParam}${orderParam}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			     </c:otherwise>
		     </c:choose>		     
   </c:if>
  </ul>
</nav>
