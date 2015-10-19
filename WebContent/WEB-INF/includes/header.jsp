<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tags/user/customtag.tld" prefix="b"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currLocale}" scope="request"/>
<fmt:setBundle basename="transls" scope="request"/> 
<link href="<c:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">

