
<%-- 
    Document   : about
    Created on : 28-dic-2012, 13:50:23
    Author     : Antonio Pena Santiso
--%>

<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <title>Seleccion Personal - Acerca de</title>


  <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/html.css" />
  <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/layout.css" />


</head>

<body>

<div class="width100">

  
  <%@include file="/WEB-INF/include/cabecera.jsp"%>

  <div id="content">

    <div class="floatLeft width100">

      <div class="post floatLeft">

        <div class="text floatRight width100">


          <h1>Acerca de Selección Personal</h1>
          
          <br>
          
          <p>
            Aplicación web desarrollada por Antonio Pena Santiso
          </p>
          
          <p>
              Utiliza JavaServerPages, Java Enterprise, JavaScript y una base de datos MySQL con conectores JDBC.
              Desarrollada en Netbeans 7.2 y montada usando un servidor de aplicacions Glassfish 3 y un servidor web Apache 2.0.
          </p>

        </div>

      </div>



      <div class="post floatLeft">

        <div class="meta floatLeft width25">


        </div>

        
      </div>        

    </div>

  </div>
  
  
  


  <%@include file="/WEB-INF/include/pie.jsp" %>


</div>


</body>
</html>