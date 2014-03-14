<%-- 
    Document   : perfil
    Created on : 15-dic-2012, 16:45:39
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        

        <title>Seleccion Personal - Perfil</title>


        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/html.css" />
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/layout.css" />

        <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/gen_validatorv4.js"></script>

        
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


                                <h1>Tu Perfil</h1>
                                
                                <% String error = (String)request.getSession().getAttribute("errorMod");
                                if(error != null){ %>
                                
                                <span class="error">
                                    <%=error%>
                                </span>
                                <% }
                                request.getSession().setAttribute("errorMod",null);
                                %>

                                <% Usuario usuario = (Usuario) request.getSession().getAttribute("user");
                                  if (usuario == null) {%>
                                    <jsp:forward page="/index.jsp"></jsp:forward>
                                <% }%>
                                
                                <form name="Modificar" id="modificarform" action="${pageContext.request.contextPath}/ModificarUsuarioServlet" accept-charset="UTF-8" method="POST">
                                    <p>
                                        <image src="${pageContext.request.contextPath}/MostrarFotoServlet?email=<%=usuario.getEmail()%>" width="120" height="150" class="floatRight">
                                        <label for="r_nombre">Nombre</label>
                                        <input type="text" name="r_nombre" id="r_nombre" class="width25" maxlength="50" value="<%= usuario.getNombre()%>"/>
                                        
                                        <br><br>
                                    </p>
                                    <p class="floatright">
                                        <label for="r_apellidos">Apellidos</label>
                                        <input type="text" name="r_apellidos" id="r_apellidos" maxlength="50" class="width25" value="<%= usuario.getApellidos()%>"/>
                                        <br><br>
                                    </p>
                                    <p>
                                        <label for="r_email">Email</label>
                                        <input type="text" readonly="true" name="r_email" id="r_email" maxlength="50" class="width25" value="<%= usuario.getEmail()%>"/>
                                        <br><br>
                                    </p>
                                    <p>
                                        <label for="r_telefono">Telefono</label>
                                        <input type="text" name="r_telefono" id="r_telefono" class="width25" maxlength="20" value="<%= usuario.getTelefono()%>"/>
                                    <br><br>
                                    </p>
                                    <p>
                                        <label for="r_cv">Currículum Vitae</label>
                                        <textarea rows="10" cols="30" name="r_cv" id="r_cv" class="width50"><%= usuario.getCv()%></textarea>
                                        <br><br>
                                    </p>
                                        <input type="hidden" name="r_permisos" id="r_permisos" value="<%=usuario.getTipo()%>"/>  
                                    <p>
                                        <input type="submit" value="Modificar" class="rightbutton"/>
                                    </p>
                                </form>
                                    
                                <script>
                                    var validator = new Validator("modificarform");
                                    validator.EnableMsgsTogether();
                                    validator.addValidation("r_nombre","req","Por favor, añade un nombre");
                                    validator.addValidation("r_nombre","alpha_s");
            
                                    validator.addValidation("r_apellidos","req","Por favor, añade tus apellidos");
                                    validator.addValidation("r_apellidos","alpha_s");
            
                                    validator.addValidation("r_telefono","num","Por favor, escribe un telefono válido")
                                </script>
                                    
                                <form action="${pageContext.request.contextPath}/CambiarContrasenaServlet" method="POST">
                                    <p>
                                        <label for="c_contrasenaold">Contraseña actual</label>
                                        <input type="password" name="c_contrasenaold" id="c_contrasenaold" maxlength="20">
                                            <br><br>
                                    </p>
                                    <p>
                                        <label for="c_contrasena">Nueva Contraseña</label>
                                        <input type="password" name="c_contrasena" id="c_contrasena" maxlength="20">
                                        <br><br>
                                    </p>
                                    <p>
                                        <label for="c_contrasenarep">Repetir Contraseña</label>
                                        <input type="password" name="c_contrasenarep" id="c_contrasenarep" maxlength="20">
                                        <br><br>    
                                    </p>
                                    <p>
                                        <input type="hidden" name="c_email" value="<%=usuario.getEmail()%>">
                                        <input type="submit" class="rightbutton" value="Cambiar Contraseña">
                                    </p>
                                </form>
                                    
                                <form action="${pageContext.request.contextPath}/SubirFotoServlet" method="POST" enctype="multipart/form-data">
                                    <p>
                                        <label>Foto</label>
                                        <input type="hidden" name="f_usuario" value="<%=usuario.getEmail()%>" id="f_usuario"/>
                                        <input type="file" accept="image/*" name="f_foto" id="foto"/>
                                    </p>
                                    <p>
                                        <input type="submit" value="Cambiar foto" class="rightbutton"/>
                                    </p>
                                </form>
                                        
                                <form action="${pageContext.request.contextPath}/SubirCurriculoServlet" method="POST" enctype="multipart/form-data">
                                    <p>
                                        <label>Curriculo pdf</label>
                                        <input type="hidden" name="f_usuario" value="<%=usuario.getEmail()%>" id="f_usuario"/>
                                        <input type="file" accept="application/pdf" name="f_curriculo" id="curriculo"/>
                                    </p>
                                    <p>
                                        <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/SubirCurriculoServlet'" value="Subir CV" class ="rightbutton" style="display:inline"/>
                                        <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/ObtenerCurriculoServlet?email=<%=usuario.getEmail()%>'" value="Descargar CV" class="button" style="display:inline"/>
                                    </p>
                                </form>
                                    
                                <form action="${pageContext.request.contextPath}/BorrarUsuarioServlet" method="POST">
                                    <p>
                                        <input type="submit" value="Darse de Baja" class="rightbutton"/>
                                        <input type="hidden" name="b_email" value="<%=usuario.getEmail()%>"/>
                                    </p>        
                                </form>

                            </div>

                        </div>

                    </div>



                    <%@include file="/WEB-INF/include/pie.jsp" %>


                    
                </div>
            </div>
            


        </body>
</html>