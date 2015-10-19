<%@include file="../../includes/main/mainHead.jsp" %>
<fmt:message var="title" key="txt.sign-in"/>
<us:wrapper title="${title}">
<jsp:attribute name="body">
 <!-- Begin content-->
      <section id="content" class="clearfix">
        <div id="customer-login">
  <div class="row">
    <div id="login" class="span7">
      <h2 ><fmt:message key="txt.enter"/></h2>
	  <c:if test="${requestScope.registrationSucceed}">
	  			<div class = "success"><fmt:message key = "txt.registrationSucceedMessage"/></div>
	            <c:remove var="registrationSucceed"/>
	  </c:if>
	   <c:if test="${requestScope.banned}">
	  			<div class = "errors"><fmt:message key = "role.banned"/></div>
	            <c:remove var="banned"/>
	  </c:if>
      <form accept-charset="UTF-8" action="/index" id="customer_login" method="post">
	
	  <div class="errors">${requestScope.messages.login}</div>
      <label><fmt:message key = "txt.login"/></label>
      
      <input id = "customer_email" type="text" name="login" value = "${requestScope.login}" class="text" />
      

      <div class="errors">${requestScope.messages.password}</div>
      <label><fmt:message key = "txt.password"/></label>
      <input id = "customer_password" type="password" name="password" value = "${requestScope.password}" class="text" size="16" />

      <div class="action_bottom">
        <input class="btn" type="submit" value="<fmt:message key="txt.sign-in"/>" />
      </div>
      </form>
    </div>

  </div>
</div>

      </section>
      </jsp:attribute>
</us:wrapper>
      <!-- End content-->