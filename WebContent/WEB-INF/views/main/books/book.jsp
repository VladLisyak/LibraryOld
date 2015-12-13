<%@include file="../../../includes/main/mainHead.jsp"%>
<fmt:message var="title" key="txt.book" />
<c:set scope="session" var="bookId" value = "${requestScope.book.id}"/>

<c:choose>
	<c:when test="${!(requestScope.book.inStock eq 0) && sessionScope.currUser eq 'user'}">
		<c:set scope="request" var="address" value = "${pageContext.request.contextPath}/book"/>
	</c:when>
	<c:otherwise>
		<c:set scope="request" var="address" value = "${pageContext.request.contextPath}/queue"/>
	</c:otherwise>
</c:choose>
<us:wrapper id = "${requestScope.book.id}" title="${title}">
	<jsp:attribute name="body">
	<div class="row clearfix">
    <div class="span6">
      <!-- Begin featured image -->
      <div class="image featured">
         <c:choose>
             <c:when test="${empty requestScope.book.image}">
                 <img id="placeholder" src="<c:url value="/assets/image/book.jpg"/>">
             </c:when>
             <c:otherwise>
                 <img id="placeholder" src="<c:url value="/uploads/books/${requestScope.book.image}"/>">
             </c:otherwise>
         </c:choose>
      </div>
    </div>

    <!-- Begin description -->
    <div class="span6">
      <h1 class="title">${requestScope.book.title.value(sessionScope.currLocale)}</h1>
      <div class="purchase">   
			<c:choose>
	            <c:when test="${requestScope.book.authors.size()>1}">
	                <h1><fmt:message key="txt.authors"/></h1>
	            </c:when>
	            <c:otherwise>
	                <h1><fmt:message key="txt.author"/></h1>
	            </c:otherwise>
	        </c:choose>     
        <c:forEach items="${requestScope.book.authors}" var = "author">
        	<h3 class="price" id="price-preview">	
        							<a href="${pageContext.request.contextPath}/author?id=${author.id}">
                                            ${author.name.value(sessionScope.currLocale)}
                                    </a>
            </h3>
        </c:forEach>
      </div>

      <hr>
      <h3><fmt:message key="txt.publisher"/></h3>
			<h4 class="price" id="price-preview">	
                          "${requestScope.book.publisher.title.value(sessionScope.currLocale)}"
            </h4>
      
      <form id="add-item-form" action="${requestScope.address}" method="post"
					class="variants clearfix">
			
        <!-- Begin product options -->
        <div class="product-options">

          <div class="select clearfix">
            				<div class="selector-wrapper">
								<label for="product-select-option-0"><fmt:message key="txt.pagesCount"/></label>
								<strong>${requestScope.book.pages}</strong>
							</div>
          </div>

          
          <div class="selector-wrapper">
            <h3><fmt:message  key ="txt.description"/></h3>
                  <div class="description">
       				 <p>${requestScope.book.description.value(sessionScope.currLocale)}</p>
      		</div>
          </div>
          

          

        </div>
        <!-- End product options -->
			<div class="purchase-section multiple">
            <div class="purchase">
            <c:choose>
            	<c:when test="${!(requestScope.book.inStock eq 0) && sessionScope.currUser eq 'user'}">
            	
            	  <c:choose>  
	            	        <c:when test="${requestScope.alrOrdered}" >
	            	                   <h3><fmt:message key="txt.book.ordered" /></h3>
	            	        </c:when>
	            	        <c:otherwise>
			            	        	<input type="hidden" name="id" value="${requestScope.book.id}"/>
			             						 <input type="submit" id="add-to-cart" class="btn"
												name="add" value="Add to cart">
	            	        </c:otherwise>
            	  </c:choose>	
            	</c:when>
            	<c:when test="${(requestScope.book.inStock eq 0) && sessionScope.currUser eq 'user'}">
            			<input type="submit" id="add-to-cart" style = "height: 40px;" class = "btn-success"
												name="add" value="<fmt:message key="txt.Available"/>">
            	</c:when>
            	<c:otherwise>
            	  		<c:if test="${!(empty sessionScope.currUser)}">
            	  				<h3><fmt:message key = "txt.Overdued"/></h3>
            	  		</c:if>
            	</c:otherwise>
            </c:choose>
            </div>
          </div>
      </form>
      

    </div>
    <!-- End description -->

  </div>  
	
	</jsp:attribute>
</us:wrapper>
