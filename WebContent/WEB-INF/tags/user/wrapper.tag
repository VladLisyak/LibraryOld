<%@taglib uri='http://java.sun.com/jstl/core' prefix='c'%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="tg" tagdir="/WEB-INF/tags/user" %>
<%@attribute name="body" fragment="true" required="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="reff"%>
<%@attribute name="id"%>
<!DOCTYPE html>

<html lang="${sessionScope.currLocale}">
<head>
 <link rel="stylesheet" href="<c:url value="/assets/css/styles1393507619900824498.css"/>" type="text/css"/>
  <link rel="stylesheet" href="<c:url value="/assets/css/social-icons.css"/>" type="text/css"/>
  <link rel="stylesheet" href="<c:url value="/assets/css/font-awesome.css"/>" type="text/css"/>
  <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>" type="text/css"/>
  <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Asap:300,400,700">
  
  <script src="assets/js/html5shiv1393507619900824498.js" type="text/javascript"></script>
  <script type="text/javascript" src="<c:url value="/assets/js/jquery.min.js"/>"></script>
  <script src="<c:url value="/assets/js/option_selection-87ab3c47afdd94e7292ed3925ae1bc31.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/assets/js/api.jquery-c602350dadbc8cd60f9559f7c451f118.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/assets/js/shopify_common-a62d651cd9d55e931edab6a20dd0d6fe.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/assets/js/customer_area-ab14943075ff500fb3e7f569e9c670fa.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/assets/js/bootstrap.min.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/assets/js/dropdown.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/assets/js/scripts.js"/>" type="text/javascript"></script>

  <link rel="shortcut icon" href="/assets/favicon1393507619900824498.png" type="image/png" />
  <meta http-equiv='Content-Type'>
  <title>
 	 ${title}
  </title>
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>

<body>
<tg:toolbar id = "${id}"/>
  <!-- Begin wrapper -->

      <!-- Begin below navigation -->
    <div id="transparency" class="wrapper">
     <div class="row">  
      <div class="span12 clearfix">
        <div class="logo">
          
          <h1>${title}</h1>
          
          
        </div>
      </div>

      <section id="nav" class="row">
        <div class="span12">
          <nav class="main">
            <ul class="horizontal unstyled clearfix ">
  
  
  
  
  
  
  <li class="">
    <a href="/Library" >
      <fmt:message key="txt.home"/>
    </a> 
    
  </li>
  
  
  
  
  
  
  <li class="dropdown">
    <a href="/books" >
       <fmt:message key="txt.books"/>
    </a> 
    
  </li>
  
  
</ul>

          </nav> <!-- /.main -->
<ul class="clearfix">

</ul>
</div>
          <!-- </nav> /.mobile
        </div> -->
      </section>
      
      <!-- End below navigation -->
    <section id="content">
        <jsp:invoke fragment="body"/>
    </section>   
	
    </div>
  </div>
  <!-- End wrapper -->

  <!-- Begin footer -->
  <div class="footer-wrapper">
    <footer>
      <div class="row">
       <div class="span12 full-border"></div>
     	<div class="span4 footer-blog">
          <div class="span4">
         
		  </div>
        </div>
                <div class="span12 tc copyright">
          
          <p>Copyright &copy; 2015, Library Service</p>
          
        </div>
        
      </div>
    </footer>
  </div>
  <!-- End footer -->
</body>
</html>    
 