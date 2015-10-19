<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="cmn" tagdir = "/WEB-INF/tags/common"%>
<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@attribute name="body" fragment="true" required="true" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="bottom" fragment="true" %>
<%@attribute name="actionButtons" fragment="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="id" %>

<cmn:navigation title="${title}" home="/dashboard">
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
            <a href="/dashboard"><i class="fa fa-dashboard fa-fw"></i>
                <fmt:message key="txt.dashboard"/>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-users fa-fw"></i>
                <fmt:message key="txt.users"/><span class="fa arrow"></span>
            </a>
            <ul class="nav nav-second-level">
                <li>
                    <a href="/dashboard/users/not-confirmed">
                        <fmt:message key="role.notConfirmedM"/>
                    </a>
                </li>
                <li>
                    <a href="/dashboard/users">
                        <fmt:message key="role.userM"/>
                    </a>
                </li>
                <li>
                    <a href="/dashboard/users/librarians">
                        <fmt:message key="role.librarianM"/>
                    </a>
                </li>
                <li>
                    <a href="/dashboard/users/administrators">
                        <fmt:message key="role.adminM"/>
                    </a>
                </li>
                <li>
                    <a href="/dashboard/users/black-list">
                        <fmt:message key="role.bannedM"/>
                    </a>
                </li>
                <li>
                    <a href="/dashboard/users/overdue">
                        <fmt:message key="role.overdueU"/>
                    </a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-book fa-fw"></i>
                <fmt:message key="txt.library"/><span class="fa arrow"></span>
            </a>
            <ul class="nav nav-second-level">
                <li>
                    <a href="/dashboard/books">
                        <fmt:message key="txt.books"/>
                    </a>
                </li>
                <li>
                    <a href="/dashboard/authors">
                        <fmt:message key="txt.authors"/>
                    </a>
                </li>
                <li>
                    <a href="/dashboard/publishers">
                        <fmt:message key="txt.publishers"/>
                    </a>
                </li>
            </ul>
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
    <jsp:attribute name="actionButtons">
        <jsp:invoke fragment="actionButtons"/>
    </jsp:attribute>
</cmn:navigation>