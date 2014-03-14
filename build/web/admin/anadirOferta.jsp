<%-- 
    Document   : anadirOferta
    Created on : 25-dic-2012, 20:42:31
    Author     : Antonio Pena Santiso
--%>

<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Nueva Oferta</title>
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/html.css" />
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/layout.css">
        <script src="${pageContext.request.contextPath}/scripts/gen_validatorv4.js" type="text/javascript"></script>
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


          <h1>Nueva oferta</h1><br>
          
          
          <% String errorRegistro = (String)request.getSession().getAttribute("errorRegistro");
             if (errorRegistro != null) { %>
             <span class="error">
                 <%=errorRegistro%>
             </span><br>
          <% request.getSession().setAttribute("errorRegistro",null);
             } %>
             
          <form name="registrar" action="${pageContext.request.contextPath}/AnadirOfertaServlet" id="registroOfertaform" method="POST">
              <p>
                  <label for="r_puesto">Puesto</label>
                    <input type="text" name="r_puesto" id="r_puesto" maxlength="50" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_descripcion">Descripción</label>
                  <textarea xml:lang ="es" type="textarea" rows="10" cols="30" name="r_descripcion" id="r_descripcion" class="width50"/></textarea>
                  <br><br>
              </p>
              <p>
                  <label for="r_pais">País</label>
                  <input type="text" name="r_pais" id="r_pais" maxlength="50" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_ubicacion">Ubicación</label>
                  <input type="text" name="r_ubicacion" id="r_ubicacion" maxlength="50" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_estudios">Estudios mínimos</label>
                  <input type="text" name="r_estudios" id="r_estudios" maxlength="100" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_requisitos">Requisitos mínimos</label>
                  <input type="text" name="r_requisitos" id="r_requisitos" maxlength="100" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_experiencia">Experiencia mínima</label>
                  <input type="text" name="r_experiencia" id="r_experiencia" maxlength="100" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_contrato">Tipo de contrato</label>
                  <input type="text" name="r_contrato" id="r_contrato" maxlength="50" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_sueldo">Sueldo</label>
                  <input type="text" name="r_sueldo" id="r_sueldo" maxlength="20" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_duracion">Duración del contrato</label>
                  <input type="text" name="r_duracion" id="r_duracion" maxlength="30" class="width25"/>
                  <br><br>
              </p>
              <p>
                  <label for="r_destinatarios">Destinatarios</label>
                  <select name="r_destinatarios" id="r_destinatarios">
                      <option value="Abierta" selected="true">Abierta</option>
                      <option value="Empleados">Empleados</option>
                      <option value="Cerrada">Cerrada</option>
                  </select>
              </p>
              <p>
                  <input type="submit" value="Publicar" class="button"/>
              </p>
          </form>
             
        <script>
            var validator = new Validator("registroOfertaform");
            validator.EnableMsgsTogether();
            
            validator.addValidation("r_puesto","req","Por favor, añade un nombre para el puesto");
            validator.addValidation("r_puesto","alpha_s");
            
        </script>  

        </div>

      </div>

      
      </div>        

    </div>


  <%@include file="/WEB-INF/include/pie.jsp" %>


</div>


</body>
    
</html>
