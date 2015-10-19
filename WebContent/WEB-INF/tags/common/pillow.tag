<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="color" required="true" %>
<%@attribute name="icon" required="true" %>
<%@attribute name="count" required="true" %>
<%@attribute name="label" required="true" %>
<%@attribute name="reff" required="true" %>
<div class="col-lg-3 col-md-6">
    <div class="panel panel-${color}">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-${icon} fa-4x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${count}</div>
                    <div><fmt:message key="${label}"/></div>
                </div>
            </div>
        </div>
        <div class="panel-footer">

        <a href="${reff}">
            
                <span class="pull-left"><fmt:message key="txt.viewAll"/></span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>            
        </a>
       		 <div class="clearfix"></div>
        </div>
    </div>
</div>