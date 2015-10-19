<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="cmn" tagdir = "/WEB-INF/tags/common"%>
<fmt:setLocale value="${sessionScope.currLocale}" scope="request"/>
<fmt:setBundle basename="transls" scope="request"/>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@attribute name="body" fragment="true" required="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="bottom" fragment="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="id"%>

<cmn:navigation title="${title}" home="/library">
    <jsp:attribute name="topPanel">
        <li class="dropdown">
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
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li>
                    <a href="/logout"><i class="fa fa-sign-out fa-fw"></i>
                        <fmt:message key="txt.log-out"/>
                    </a>
                </li>
            </ul>
        </li>
    </jsp:attribute>
    <jsp:attribute name="sidePanel">
        <li>
            <a href="/library">
                <i class="fa fa-dashboard fa-fw"></i>
                <fmt:message key="txt.library"/>
            </a>
        </li>
         <li>
             <a href="/library/orders/add">
                 <i class="fa fa-plus fa-fw"></i>
                 <fmt:message key="txt.checkOutBook"/>
             </a>
         </li>
        <li>
            <a href="/library/orders/ordered">
                <i class="fa fa-shopping-cart fa-fw"></i>
                <fmt:message key="txt.books.orders"/>
            </a>
        </li>
        <li>
            <a href="/library/user-books">
                <i class="fa fa-money fa-fw"></i>
                <fmt:message key="txt.books.overDue"/>
            </a>
        </li>
        <li>
            <a href="/library/orders/checked-out">
                <i class="fa fa-book fa-fw"></i>
                <fmt:message key="txt.books.checkOuts"/>
            </a>
        </li>
        <li>
            <a href="/library/orders/completed">
                <i class="fa fa-check-square-o fa-fw"></i>
                <fmt:message key="txt.orders.completed"/>
            </a>
        </li>
        <li>
            <a href="/library/orders/reading-rooms">
                <i class="fa fa-users fa-fw"></i>
                <fmt:message key="txt.orders.readingRooms"/>
            </a>
        </li>
    </jsp:attribute>
    <jsp:attribute name="body">
        <jsp:invoke fragment="body"/>
    </jsp:attribute>
    <jsp:attribute name="head">
        <jsp:invoke fragment="head"/>
    </jsp:attribute>
     <jsp:attribute name="bottom">
        <jsp:invoke fragment="bottom"/>
    </jsp:attribute>
</cmn:navigation>