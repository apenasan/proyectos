<%-- 
    Document   : anadirUsuario
    Created on : 26-dic-2012, 17:57:28
    Author     : Antonio Pena Santiso
--%>

<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Nuevo Usuario</title>
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="/css/html.css" />
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="/css/layout.css">
        <script src="${pageContext.request.contextPath}/scripts/gen_validatorv4.js" type="text/javascript"></script>
    </head>
    
    <body>

<!-- Full site width container -->
<div class="width100">

  <!-- #header: holds main image, menu and top actions bar -->
  
  <%@include file="/WEB-INF/include/cabecera.jsp"%>

  <!-- #content: holds site content -->
  <div id="content">

    <div class="floatLeft width100">

      <!-- .post: Made up of text and meta columns - meta column will have blue background and stretch entire height of text post -->
      <div class="post floatLeft">

        <!-- .meta: blue meta information column -->
        <div class="meta floatRight width25">

            <%@include file="/WEB-INF/include/login.jsp"%>
          
        </div>

        <!-- .text: content of post -->
        <div class="text floatLeft width75">


          <h1>Nuevo Usuario</h1><br>
          
          
          <% String errorRegistro = (String)request.getSession().getAttribute("errorRegistro");
             if (errorRegistro != null) { %>
             <span class="error">
                 <%=errorRegistro%>
             </span><br>
          <% request.getSession().setAttribute("errorRegistro",null);
             } %>   
          <form name="registrar" action="${pageContext.request.contextPath}/RegistroServlet" id="registroform" method="POST">
              <p>
                  <label for="r_nombre">Nombre</label>
                  <input type="text" name="r_nombre" id="r_nombre" maxlength="50" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_apellidos">Apellidos</label>
                  <input type="text" name="r_apellidos" id="r_apellidos" maxlength="50" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_email">Email</label>
                  <input type="text" name="r_email" id="r_email" maxlength="50" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_contrasena">Contraseña</label>
                  <input type="password" name="r_contrasena" id="r_contrasena" maxlength="20" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_contrasenarep">Repetir contraseña</label>
                  <input type="password" name="r_contrasenarep" id="r_contrasenarep" maxlength="20" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_telefono">Telefono</label>
                  <input type="text" name="r_telefono" id="r_telefono" maxlength="20" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_cv">CV</label>
                  <textarea xml:lang="es" rows="10" cols="30" name="r_cv" id="r_cv" class="width50"></textarea>
                  <br><br>
              </p>
              <p>
                  <label for="r_permisos">Permisos</label>
                  <select name="r_permisos" id="r_permisos">
                      <option value="a">Administrador</option>
                      <option value="e">Empleado</option>
                      <option value="c" selected="true">Candidato</option>
                  </select>
              </p>
              <p>
                  <input type="submit" value="Enviar" class="button"/>
              </p>
          </form>
             
        <script>
            var validator = new Validator("registroform");
            validator.EnableMsgsTogether();
            
            validator.addValidation("r_nombre","req","Por favor, añade un nombre");
            validator.addValidation("r_nombre","alpha_s");
            
            validator.addValidation("r_apellidos","req","Por favor, añade tus apellidos");
            validator.addValidation("r_apellidos","alpha_s");
            
            validator.addValidation("r_email","email","Por favor, introduce una dirreción de correo válida");
            validator.addValidation("r_email","req","Por favor introduce una dirección de correo");
            
            validator.addValidation("r_contrasena","req","Por favor, introduce una contraseña");
            validator.addValidation("r_contrasena","alnum","La contraseña solo puede incluir letras, numeros");
            validator.addValidation("r_contrasena","minlen=5","La contraseña debe contener al menos 5 caracteres");
            
            validator.addValidation("r_contrasenarep","eqelmnt=r_contrasena","La contraseña repetida no coincide");
            
            validator.addValidation("r_telefono","num","Por favor, escribre un teléfono válido");
        </script>  

        </div>

      </div>

       

    </div>

  </div>
  

  <!-- #footer: holds submenu and copyright info -->
  <%@include file="/WEB-INF/include/pie.jsp" %>
  <!-- end #footer -->


</div>


</body>
    
</html>
