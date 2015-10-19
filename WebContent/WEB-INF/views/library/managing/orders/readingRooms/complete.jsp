<%@include file="../../../../../includes/library/libHead.jsp" %>
<fmt:message var="title" key="txt.completeOrder"/>
<lb:wrapper title="${title} ${requestScope.order.id}" id = "${requestScope.order.id}">
  <jsp:attribute name="body">
       <form action="/library/orders/reading-rooms/complete" method="post">
           <div class="row">
               <div class="col-lg-12">
                   <p>
                       <fmt:message key="txt.completeOrderLeft"/> <span> ${requestScope.order.id}</span>?
                   </p>
               </div>
           </div>
           <div class="row">
               <div class="col-lg-12">
                   <input type="hidden" name="id" value="${requestScope.order.id}">
                   <utl:confirm link="/library/orders/reading-rooms"/>
               </div>
           </div>
       </form>
  </jsp:attribute>
</lb:wrapper>
