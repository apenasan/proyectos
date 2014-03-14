
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%-- 
    Document   : error404
    Created on : 18-jun-2013, 19:15:17
    Author     : Antonio Pena Santiso
--%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  
  <title>Seleccion Personal Error 404</title>


  <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/html.css" />
  <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/layout.css" />


</head>

<body>

<div class="width100">

  
  <%@include file="/WEB-INF/include/cabecera.jsp"%>

  <div id="content">

    <div class="floatLeft width100">

      <div class="post floatLeft">

        <div class="meta floatRight width25">

            <%@include file="/WEB-INF/include/login.jsp"%>

        </div>

        <div class="text floatLeft width75">


          <h1>Error 404</h1>
          
          <br>
                        
          <p>
              No se ha encontrado el recurso solicitado. Por favor, revise su petición.
          </p>
          
        </div>

      </div>
        

    </div>

  </div>
  
  
  


  <%@include file="/WEB-INF/include/pie.jsp" %>


</div>


</body>
</html>
