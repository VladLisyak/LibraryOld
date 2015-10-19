<%@include file="../../includes/dashboard/dashHead.jsp" %>
<fmt:message key="txt.dashboard" var="title"/>
<db:wrapper title="${title}">
    <jsp:attribute name="body">
        <div class="row">
        	<cmn:pillow color="primary" icon="pencil-square-o" count="${requestScope.authorsCount}" 
            label="txt.authors" reff="/dashboard/authors"/>
            
            <cmn:pillow color="yellow" icon="book" count="${requestScope.booksCount}" 
            label="txt.books"  reff="/dashboard/books"/>
            
            <cmn:pillow color="red" icon="print" count="${requestScope.publishersCount}" 
            label="txt.publishers" reff="/dashboard/publishers"/>
            
            <cmn:pillow color="green" icon="users" count="${requestScope.readersCount}" 
            label="role.userM" reff="/dashboard/users"/>
        </div>
    </jsp:attribute>
</db:wrapper>