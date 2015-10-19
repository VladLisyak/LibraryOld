<%@include file="../../../../../includes/library/libHead.jsp" %>
<fmt:message var="title" key="txt.acceptOrder"/>
<lb:wrapper title="${title} ${requestScope.order.id}" id = "${requestScope.order.id}">
  <jsp:attribute name="body">
       <form action="/library/orders/ordered/accept" method="post">
           <div class="row">
               <div class="col-lg-12">
                   <utl:num label="txt.checkingOutDuration" id="days" max="${requestScope.max}" min="1" required="true"
                          value="${requestScope.days}"/>
               </div>
           </div>
           <div class="row">
               <div class="col-lg-12">
                   <input type="hidden" name="id" value="${requestScope.order.id}">
                   <utl:confirm link="/library/orders/ordered"/>
               </div>
           </div>
       </form>
  </jsp:attribute>
</lb:wrapper>
