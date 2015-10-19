<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="link" required="true" %>
<div class="form-group">
    <input id="submit" type="submit" value="<fmt:message key="txt.conf"/>" class="btn btn-primary btn-default" />
    <button type="button" class="btn btn-default btn-outline link-button">
    </button>
     <a href="${link}"><fmt:message key="txt.back"/></a>
</div>
<br>
