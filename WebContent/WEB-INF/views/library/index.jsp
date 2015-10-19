<%@include file="../../includes/library/libHead.jsp"%>
<fmt:setLocale value="${sessionScope.currLocale}" scope="request"/>
<fmt:setBundle basename="transls" scope="request"/>
<fmt:message key="txt.library" var="title"/>
<lb:wrapper title="${title}">
	<jsp:attribute name="body">
         <div class="row">
        	 <cmn:pillow color="yellow" icon="users" 
        	 reff="/library/orders/reading-rooms" count="${requestScope.readingRoomsCount}" 
        	 label="txt.orders.readingRooms"/>
             
             <cmn:pillow color="green" icon="book" count="${requestScope.checkOutsCount}"
             reff="/library/orders/checked-out"
             label="txt.books.checkOuts"/>
             
             <cmn:pillow color="primary" icon="shopping-cart" count="${requestScope.ordersCount}"
             reff="/library/orders/ordered"
             label="txt.books.orders" />
             
             <cmn:pillow color="red" icon="check-square-o" count="${requestScope.completedCount}"
             label="txt.orders.completed" reff="/library/orders/completed"/>
         </div>
    </jsp:attribute>
</lb:wrapper>