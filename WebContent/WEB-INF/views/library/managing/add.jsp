<%@include file="../../../includes/library/libHead.jsp" %>
<fmt:message var="title" key="txt.checkOutBook"/>
<lb:wrapper title="${title} ${requestScope.order.id}">
  <jsp:attribute name="body">
       <form action="/library/orders/add" method="post">
           <div class="row">
               <div class="col-lg-12">
                   <utl:num label="txt.book" id="bookId" min="1" required="true" value="${requestScope.bookId}"/>
                   <utl:num label="txt.subscription.id" id="subscriptionId" min="1" required="true"
                          value="${requestScope.subscriptionId}"/>
               </div>
           </div>
           <div class="row">
               <div class="col-lg-12">
                   <input type="hidden" name="id" value="${requestScope.order.id}">
                   <utl:confirm link="/library/orders/add"/>
               </div>
           </div>
       </form>
  </jsp:attribute>
</lb:wrapper>
