<%-- 
    Document   : comentarios
    Created on : 17-jun-2013, 22:40:06
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Comentario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        

        <title>Seleccion Personal - Editar Cuestionario</title>


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

                                <p>
                                    <%@include file="/WEB-INF/include/login.jsp"%>
                                </p>

                            </div>

                            <div class="text floatLeft width75">


                                <%                                 
                                   Usuario usuario = (Usuario)request.getSession().getAttribute("user");
                                   String email = request.getParameter("email"); 
                                   ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>)request.getSession().getAttribute("listaComentarios");
                                %>
                                
                                <h1>Lista de comentarios sobre <%=email%></h1>
                                
                               
                                <br>
                                                                
                                <% for(int i=0;i < listaComentarios.size();i++){ %>
                                <form>
                                    <p>
                                        Escrito por <%=listaComentarios.get(i).getAutor()%> a las <%=listaComentarios.get(i).getStringFecha()%>:
                                        <br>
                                            <textarea rows="3" cols="30" name="c_texto" id="comentario<%=i+1%>" class="width50"><%=listaComentarios.get(i).getTexto()%></textarea>
                                            <% if(usuario.getEmail().equals(listaComentarios.get(i).getAutor())){ %>
                                            <br>
                                                <input type="hidden" value="<%=listaComentarios.get(i).getId()%>" name="c_id">
                                                <input type="hidden" value="<%=listaComentarios.get(i).getUsuario()%>" name="c_usuario">                                                    
                                                <input type="submit" value="Editar" class="button" onclick="this.form.action = '${pageContext.request.contextPath}/EditarComentarioServlet'">
                                                <input type="submit" value="Eliminar" class="button" onclick="this.form.action = '${pageContext.request.contextPath}/EliminarComentarioServlet'">                                                    
                                            <% } %> 
                                    </p>
                                    
                                </form>
                                    
                                <% } %>        
                                        
                                <form name="Add" id="addComentario" accept-charset="UTF-8ho" method="POST">
                                    <p>
                                        Añadir un comentario:
                                        <br>
                                                <textarea rows="5" cols="30" name="c_texto" id="c_texto" class="width50"></textarea>
                                    </p>
                                    <p>
                                        <input type="hidden" name="c_usuario" value="<%=email%>"/>
                                        <input type="hidden" name="c_autor" value="<%=usuario.getEmail()%>"/>
                                        <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/AnadirComentarioServlet'" value="Añadir Comentario" class="button">
                                    </p>
                                </form>
                                       
                                                     
                                     
                                <br>
                                
                            </div>

                        </div>

                        <%@include file="/WEB-INF/include/pie.jsp" %>

                    </div>
                </div>
            </div>
            


        </body>
</html>