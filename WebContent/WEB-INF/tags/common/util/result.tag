<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="result" required="true" type="ua.nure.lisyak.SummaryTask4.util.ProcessResult" %>
<link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>" type="text/css"/>
<c:if test="${!empty result}">
    <div class="${result.succeeded ? "success" : "errors"}">
        <h2>${result.message}</h2>
    	<c:remove var="registrationSucceed"/>
    </div>
</c:if>