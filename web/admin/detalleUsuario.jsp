<%-- 
    Document   : detalleUsuario
    Created on : 26-dic-2012, 14:29:56
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Comentario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        

        <title>Seleccion Personal - Datos de Usuario</title>


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


                                <h1>Editar Usuario </h1>
                                
                                <% String error = (String)request.getSession().getAttribute("errorMod");
                                if(error != null){ %>
                                
                                <span class="error">
                                    <%=error%>
                                </span>
                                <% }
                                request.getSession().setAttribute("errorMod",null);
                                %>
                                

                                <%  Usuario user = (Usuario)request.getSession().getAttribute("user");
                                    Usuario usuario = (Usuario)request.getSession().getAttribute("perfil");
                                    if (usuario == null) {%>
                                <jsp:forward page="/index.jsp"></jsp:forward>
                                <% }%>
                                <form name="Modificar" id="modificarform" action="${pageContext.request.contextPath}/ModificarUsuarioServlet" accept-charset="UTF-8ho" method="POST">
                                    <p>
                                        <image src="${pageContext.request.contextPath}/MostrarFotoServlet?email=<%=usuario.getEmail()%>" width="120" height="150" class="floatRight">
                                        <label for="r_nombre">Nombre</label>
                                        <input type="text" name="r_nombre" id="r_nombre" class="width25" maxlength="50" value="<%= usuario.getNombre()%>"/>
                                        <br><br>
                                    </p>
                                    <p>
                                        <label for="r_apellidos">Apellidos</label>
                                        <input type="text" name="r_apellidos" id="r_apellidos" class="width25" maxlength="50" value="<%= usuario.getApellidos()%>"/>
                                        <br><br>
                                    </p>
                                    <p>
                                        <label for="r_email">Email</label>
                                         <input type="text" readonly="true" name="r_email" id="r_email" class="width25" maxlength="50" value="<%= usuario.getEmail()%>"/>
                                        <br><br>
                                    </p>
                                    <p>
                                        <label for="r_telefono">Telefono</label>
                                        <input type="text" name="r_telefono" id="r_telefono" class="width25" maxlength="20" value="<%= usuario.getTelefono()%>"/>
                                    <br><br>
                                    </p>
                                    <p>
                                        <label for="r_cv">CV</label>
                                        <textarea rows="10" cols="30" name="r_cv" id="r_cv" class="width50"><%= usuario.getCv()%></textarea>
                                        <br><br>
                                    </p>
                                    <p>
                                        <label for="r_permisos">Permisos</label>
                                        <select name="r_permisos">
                                            <option value ="<%=usuario.getTipo()%>" selected="true"><%=usuario.getTipoString()%></option>
                                            <option value ="a">Administrador</option>
                                            <option value ="e">Empleado</option>
                                            <option value ="c">Candidato</option>
                                        </select>
                                    </p>
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
                                            
                                <form action="${pageContext.request.contextPath}/SubirFotoServlet" method="POST" enctype="multipart/form-data">
                                    <p>
                                        <label>Foto</label>
                                        <input type="hidden" name="f_usuario" value="<%=usuario.getEmail()%>" id="f_usuario"/>
                                        <input type="file" name="f_foto" id="foto"/>
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
                                        <input type="submit" value="Eliminar Usuario" class="rightbutton"/>
                                        <input type="hidden" name="b_email" value="<%=usuario.getEmail()%>"/>
                                    </p>        
                                </form>
                                    
                                    <br>
                                
                                       
                                        
                                <form name="Add" id="addComentario" accept-charset="UTF-8ho" method="POST">
                                    <p>
                                        Añadir un comentario:
                                        <br>
                                                <textarea rows="5" cols="30" name="c_texto" id="c_texto" class="width50"></textarea>
                                    </p>
                                    <p>
                                        <input type="hidden" name="c_usuario" value="<%=usuario.getEmail()%>"/>
                                        <input type="hidden" name="c_autor" value="<%=user.getEmail()%>"/>
                                        <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/AnadirComentarioServlet'" value="Añadir Comentario" class="button">
                                        <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/MostrarComentariosServlet'" value="Mostrar todos los comentarios" class="button">
                                    </p>
                                </form>
                                        



                                
                                <div class="post floatLeft">

                                                                                                    
                                    <div class="meta floatLeft width25">


                                    </div>

                                                                                                                                

                                </div>        

                            </div>

                        </div>





                        <%@include file="/WEB-INF/include/pie.jsp" %>


                    </div>
                </div>
            </div>
            


        </body>
</html>