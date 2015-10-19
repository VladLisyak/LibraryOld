<%@include file="../../../includes/main/mainHead.jsp" %>
<fmt:message var="title" key="txt.inCart"/>
<us:wrapper title="${title}">
	<jsp:attribute name="body">
<!-- Begin content-->
      <section id="content" class="clearfix">
          <section id="content" class="clearfix">
              <div id="cart">
                  <!-- Begin empty cart -->
                  <div class="row">
                      <div class="span12">
                          <h1>${title}</h1>
                          <div class="errors">${requestScope.result.message}</div>
						<c:choose>
                              	<c:when test="${!(empty requestScope.books)}">
		                           
		                              <table>
		                              <thead>
		                                  <tr>
		                                      <th class="image">&nbsp;</th>
		                                      <th class="item"><fmt:message key ="txt.title"/></th>
		                                      <th class="qty"><fmt:message key ="txt.authors"/></th>
		                                      <th class="price"><fmt:message key ="txt.publisher"/></th>
		                                      <th class="remove">&nbsp;</th>
		                                  </tr>
		                                  </thead> 
		                                  <tbody>
		                                  <c:forEach items="${requestScope.books}" var="book">
		                                  <tr>
		                                      <td class="image">
		                                          <div class="product_image">
		                                              <a
		                                                      href="${pageContext.request.contextPath}/book?id=${book.id}">
		                                                  <c:choose>
		                                                      <c:when test="${empty book.image}">
		                                                          <img src="<c:url value="/assets/img/book.png"/>">
		                                                      </c:when>
		                                                      <c:otherwise>
		                                                          <img width="80" height="60" src="<c:url value="/uploads/books/${book.image}"/>"
		                                                               alt="item1">
		                                                      </c:otherwise>
		                                                  </c:choose>
		                                              </a>
		                                          </div>
		                                      </td>
		                                      <td class="item">
		                                          <a href="${pageContext.request.contextPath}/book?id=${book.id}">
		                                                <strong>${book.title.value(sessionScope.currLocale)}</strong>
		
		                                            <span class="variant_title"><fmt:message var="title" key="txt.pagesCount"/> ${book.pages}</span>
		
		                                          </a>
		                                      </td>
		                                      <td class="qty">
		                                         <c:forEach items="${book.authors}" var = "author">
		                                         <a href="${pageContext.request.contextPath}/author?id=${author.id}">
		                                                    <strong>${author.name.value(sessionScope.currLocale)}</strong>
		                                         </a>
		                                         	
		                                         </c:forEach>
		                                          
		                                      </td>
		                                      <td class="price"><strong>${book.publisher.title.value(sessionScope.currLocale)}</strong></td>
		                                      <td class="remove"><button class="btn btn-custom-2 btn-lg md-margin" onclick="deleteTheItem(${book.id},'row', '${pageContext.request.contextPath}/cart/delete');"
		                                                                class="close-button"><fmt:message key="txt.delete"/></button></td>
		                                                                
		                                                                
		                                                                
		                                                               
		                                  </tr>
		
		                                  <tr class="summary">
		                                      <td class="image">&nbsp;</td>
		                                      <td>&nbsp;</td>
		                                      <td>&nbsp;</td>
		                                      <td>&nbsp;</td>
		                                  </tr>
		                                  </c:forEach>
		                                  </tbody>
		                                 
		                              </table>
									<form action="/cart" method="post" id="cartform">
		                              <div class="row">
		
		                                  <div class="cart-note span7 fr inner-left inner-right">
		                                      <div class="buttons clearfix">
		                                          <input type="submit" id="checkout" class="btn"
		                                                 name="checkout" value="Checkout">
		                                      </div>
		                                  </div>
		                              </div>
		                          </form>
                              	</c:when>
                              	<c:otherwise>
                              	  <h3><fmt:message key = "txt.emptyCart" /></h3>
                              	  <a href="${pageContext.request.contextPath}/books" class="btn btn-custom-2 btn-lg md-margin">
                                		<fmt:message key="txt.backToHomePage"/>
                           		  </a>
                              	</c:otherwise>
                        </c:choose>
                          
                  </div>


                  </div>
                  <!-- End cart -->
              </div>
          </section>

      </section>
      </jsp:attribute>
</us:wrapper>
