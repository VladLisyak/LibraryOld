<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="path" %>
<%@attribute name="subFolder" %>
<%@attribute name="alt" %>
<%@attribute name="required" %>
<%@attribute name="id" required="true" %>
<c:set var="message" value="${requestScope.messages.get(id)}"/>
<div class='form-group ${empty message ? "" : "errors"}'>
    <label class="control-label">
        <fmt:message key="txt.image"/>
        <c:if test="${required=='true'}"><span class="asterix"> *</span></c:if>
    </label>
    <c:if test="${!empty path && !empty subFolder}">
        <div class="cross" onclick="hideImage(this,'#removable-${id}','#delete-${id}');">
            (<i class="fa fa-trash-o"> <fmt:message key="txt.delete"/></i>)
        </div>
        <input type="hidden" name="delete-${id}" id="delete-${id}" value="false"/>
        <div class="removable-image" id="removable-${id}">
            <img class="thumb" src="<c:url value="/uploads/${subFolder}/${path}"/>" alt="${alt}" title="${alt}"/>
        </div>
    </c:if>
    <label class="errors" for="${id}">${message}</label>
    <input type="file" accept="image/*" name="${id}" id="${id}">
    <br>
</div>
