<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="../../../includes/main/mainHead.jsp" %>
<fmt:message var="title" key="txt.books"/>
<us:wrapper title="${title}">
	<jsp:attribute name="body">
<c:choose>
    <c:when test="${empty param.search}">
        <c:set var="searchParam" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="searchParam" value="&search=${param.search}"/>
    </c:otherwise>
</c:choose>
<c:if test="${!(empty sessionScope.registrationSucceed)}">
		<c:choose>
		       <c:when test="${sessionScope.registrationSucceed}">
		       		<div class = "success"><h2><fmt:message key = "txt.registrationSucceedText"/></h2></div>	
		       </c:when>
		       <c:otherwise>
		       		<div class = "errors"><h2><fmt:message key = "txt.registrationFailed"/></h2></div>	
		       </c:otherwise>
		</c:choose>
</c:if>
<c:remove var="registrationSucceed"/>
<div id= "over"> 
		<div class="over clearfix" id = "over">
           <div class="browse-tags over">            
           	<div class="sort-box over">
            	<span class="separator"><fmt:message key="txt.sortBy"/>:</span>
	            <div class="btn-group select-dropdown over">
	                <button type="button" class="btn dropdown-toggle over"
	                        data-toggle="dropdown">
	                        <c:choose>
	                          <c:when test="${(empty param.orderBy) || param.orderBy eq 'default'}"><fmt:message key="orderByBook.default"/></c:when>
	                          <c:otherwise><fmt:message key="orderByBook.${param.orderBy}"/></c:otherwise>
	                        </c:choose>
	                    <i class="fa fa-angle-down"></i>
	                </button>
	                <ul class="dropdown-menu over" role="menu">
	                    <c:forEach items="${requestScope.sortKeys}" var="sortOrder">
	                        <c:if test="${(empty param.orderBy && sortOrder!='default')||(!empty param.orderBy&&sortOrder!=param.orderBy) }">
	                              <li>
	                                <a href="${pageContext.request.contextPath}/books?page=${requestScope.page}&orderBy=${sortOrder}${search}">
	                                    <fmt:message key="orderByBook.${sortOrder}"/>
	                                </a>
	                          	  </li>
	                       		 </c:if>
	                   		 </c:forEach>
	               		 </ul>
	            	</div>
        	</div>
           </div>
         </div>
   	     <br>
   	     <c:choose>
   	     		<c:when test="${! (empty requestScope.books)}">
   	     			<c:forEach items="${requestScope.books}" var="book">
		     			<div class = "behind">
		      				 <b:book book="${book}" lang="${sessionScope.currLocale}" reff = "${pageContext.request.contextPath}" />
		     			</div>  
	    			 </c:forEach>  	
   	     		</c:when>
   	     		<c:otherwise>
   	     			<h2><fmt:message key="txt.nothingFound"/></h2>
   	     		</c:otherwise>
   	     </c:choose> 
	  </div>  
		<us:pages count= "${requestScope.count}"/>
	</jsp:attribute>
</us:wrapper>
