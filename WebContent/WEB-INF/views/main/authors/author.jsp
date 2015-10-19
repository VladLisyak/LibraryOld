<%@include file="../../../includes/main/mainHead.jsp"%>
<fmt:message var="title" key="txt.author" />
<us:wrapper id = "${requestScope.author.id}" title="${title}">
	<jsp:attribute name="body">
	<div class="row clearfix">
    <div class="span6">
      <!-- Begin featured image -->
      <div class="image featured">
        <c:choose>
             <c:when test="${empty requestScope.author.image}">
                 <img id="placeholder" src="<c:url value="/assets/image/author.jpg"/>">
             </c:when>
             <c:otherwise>
                 <img id="placeholder" src="<c:url value="/uploads/authors/${requestScope.author.image}"/>">
             </c:otherwise>
         </c:choose>
      </div>
    </div>

    <!-- Begin description -->
    <div class="span6">
      <h1 class="title">${requestScope.book.title.value(sessionScope.currLocale)}</h1>
      <div class="purchase">   
	                <h1 class="title">${requestScope.author.name.value(sessionScope.currLocale)}</h1>
      </div>

        <!-- Begin product options -->
        <div class="product-options">

         

          
          <div class="selector-wrapper">
            <h3><fmt:message  key ="txt.description"/></h3>
                  <div class="description">
       				 <p>${requestScope.author.description.value(sessionScope.currLocale)}</p>
      		</div>
          </div>
        </div>
        <!-- End product options -->

    </div>
    
    <table>
          <thead>
              <tr>
                  <th class="image">&nbsp;</th>
                  <th class="item"><h3><fmt:message key ="txt.title"/></h3></th>
                  <th class="price"><h3><fmt:message key ="txt.publisher"/></h3></th>
                  <th class="remove">&nbsp;</th>
              </tr>
              </thead> 
              <tbody>
              <c:forEach items="${requestScope.books}" var="book">
              <tr>
                  <td class="image col-md-2 col-sm-2 col-xs-2">
                      <div class="product_image">
                          <a href="${pageContext.request.contextPath}/book?id=${book.id}">
                              <c:choose>
                                  <c:when test="${empty book.image}">
                                      <img src="<c:url value="/assets/img/book.png"/>">
                                  </c:when>
                                  <c:otherwise>
                                      <img src="<c:url value="/uploads/books/${book.image}"/>"
                                           alt="item1">
                                  </c:otherwise>
                              </c:choose>
                          </a>
                      </div>
                  </td>
                  <td class="item">
                     <h4><a href="${pageContext.request.contextPath}/book?id=${book.id}">
                             <strong>${book.title.value(sessionScope.currLocale)}</strong>
                      </a></h4>
                  </td>
                  <td class="price"><h4><strong>${book.publisher.title.value(sessionScope.currLocale)}</strong></h4></td>                         
              </tr>
              </c:forEach>
              </tbody>
          </table>
    <!-- End description -->

  </div>  
	
	</jsp:attribute>
</us:wrapper>
