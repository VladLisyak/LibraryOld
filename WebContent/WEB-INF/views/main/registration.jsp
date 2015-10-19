<%@include file="../../includes/main/mainHead.jsp" %>
<fmt:message var="title" key="txt.registration"/>
<us:wrapper title="${title}">
<jsp:attribute name="body">
 <!-- Begin content-->
 <div id="transparency" class="wrapper">
    <div class="row">
      <!-- Begin right navigation -->
      
      <!-- End right navigation -->

      <!-- Begin below navigation -->   

      <section id="nav" class="row">
        <div class="span12">
      <!-- Begin content-->
      
        <div id="customer-register">
  <div class="row">
   <div class="errors">${requestScope.messages.error}</div>
    <div id="register" class="span12">
      <form accept-charset="UTF-8" action="/registration" id="create_customer" method="post">
        <div class="errors">${requestScope.messages.firstName}</div>
        <div id="first_name" class="clearfix large_form">
          <label><fmt:message key="txt.firstName"/></label>
          <input type="text" value="${requestScope.user.firstName}" name = "firstName" id="first_name" class="text" size="30">
        </div>
		<div class="errors">${requestScope.messages.lastName}</div>
        <div id="last_name" class="clearfix large_form">
          <label><fmt:message key="txt.lastName"/></label>
          <input type="text" value="${requestScope.user.lastName}" name="lastName" id="last_name" class="text" size="30">
        </div>
 		<div class="errors">${requestScope.messages.email}</div>
        <div id="email" class="clearfix large_form">
          <label><fmt:message key="txt.email"/></label>
          <input type="email"  value="${requestScope.user.email}" name="email"  class="text" size="30">
        </div>
		<div class="errors">${requestScope.messages.login}</div>
		<div id="password" class="clearfix large_form">
          <label><fmt:message key="txt.login"/></label>
          <input type="text" value="${requestScope.user.login}" id="login"  name ="login" class="text" size="30">
        </div>
		<div class="errors">${requestScope.messages.password}</div>
        <div id="password" class="clearfix large_form">
          <label><fmt:message key="txt.password"/></label>
          <input type="password" value="${requestScope.user.password}" name="password"  class="text" size="30">
        </div>

        <div class="action_bottom">
          <input class="btn" type="submit" value='<fmt:message  key="txt.createAccount"/>'>
          <span class="note"> - <a href="${pageContext.request.contextPath}/books"><fmt:message key = "txt.backToBooks"/></a></span>
        </div>

      </form>
      </div>
    </div><!-- #register -->
  </div><!-- .row -->
</div><!-- #customer-register -->

      </section>
      <!-- End content-->

    </div>
    
  </div>
 
</jsp:attribute>
</us:wrapper> 
