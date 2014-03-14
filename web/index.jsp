
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- 
    Document   : index
    Created on : 25-nov-2012, 21:21:09
    Author     : Antonio Pena
--%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  
  <title>Seleccion Personal</title>


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


          <h1>Página principal</h1>
          
          <br>
                        
          <p>
              Bienvenido a Selección Personal.
          </p>
          
          <p>
              Esta aplicación permite gestionar el proceso de reclutamiento y selección de empleados de una empresa.
          </p>
          
          <p>
              Puede registrarse o iniciar sesión si ya está registrado en el formulario de la parte derecha de la página.
          </p>

        </div>

      </div>
        

    </div>

  </div>
  
  
  


  <%@include file="/WEB-INF/include/pie.jsp" %>


</div>


</body>
</html>