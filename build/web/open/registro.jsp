<%-- 
    Document   : registro
    Created on : 07-dic-2012, 21:52:52
    Author     : Antonio Pena Santiso
--%>
    
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="/css/html.css" />
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="/css/layout.css">
        <script src="/scripts/gen_validatorv4.js" type="text/javascript"></script>
    </head>
    
    <body>

<div class="width100">

  
  <%@include file="/WEB-INF/include/cabecera.jsp"%>

  <div id="content">

    <div class="floatLeft width100">

      <div class="post floatLeft">

        <div class="meta floatRight width25">

          
        </div>

        <div class="text floatLeft width75">


          <h1>Registro de usuarios</h1><br>
          
          
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
                  <input type="hidden" name="r_permisos" id="r_permisos" value="c"/>
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
            
            validator.addValidation("r_email","email","Por favor, introduce una dirección de correo válida");
            validator.addValidation("r_email","req","Por favor introduce tu dirección de correo");
            
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
  
  
  


  <%@include file="/WEB-INF/include/pie.jsp" %>


</div>


</body>
    
</html>
