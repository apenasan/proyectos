<%-- 
    Document   : editarCuestionario
    Created on : 14-jun-2013, 18:58:11
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Cuestionario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Oferta"%>
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
                                   Oferta oferta = (Oferta)request.getSession().getAttribute("oferta");
                                   Usuario usuario = (Usuario)request.getSession().getAttribute("user");
                                   Cuestionario cuestionario = (Cuestionario)request.getSession().getAttribute("cuestionario");
                                   
                                %>
                                
                                <h1>Editar cuestionario de la oferta <%= oferta.getPuesto() %></h1>
                                
                                                                
                                <form name="Modificar" id="modificarform" action="${pageContext.request.contextPath}/EditarCuestionarioServlet" accept-charset="UTF-8ho" method="POST">
                                
                                                  
                                <br>
                                
                                    <p>
                                           <label for="c_nombre">Nombre</label>
                                           <input type="text" name="c_nombre" id="c_nombre" class="width25" maxlength="50" value="<%= cuestionario.getNombre()%>"/>
                                           <input type="hidden" name="o_id" id="m_id" value="<%=oferta.getId()%>"/>
                                           <input type="submit" value="Modificar" class="button"/>
                                         <br><br>
                                    </p>
                                                                                          
                                
                                </form>            
                                           
                                <% for(int i=0;i < cuestionario.getListaPreguntas().size();i++){ %>
                                <form>
                                    <p>
                                        Pregunta <%=i+1%>:
                                        <br><br>
                                            <textarea rows="5" cols="30" name="p_enunciado" id="pregunta<%=i+1%>" class="width50"><%=cuestionario.getListaPreguntas().get(i).getEnunciado()%></textarea>
                                    </p>
                                    <p>
                                    <input type="hidden" name="p_id" value="<%=cuestionario.getListaPreguntas().get(i).getId()%>">
                                    <input type="hidden" name="p_cuestionario" id="p_cuestionario" value="<%=cuestionario.getId()%>">
                                    <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/ModificarPreguntaServlet'" name="submit" value="Modificar" class="button"/>
                                    <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/EliminarPreguntaServlet'" name="submit" value="Eliminar" class="button"/>
                                    </p>
                                    
                                </form>
                                    <br><br>
                                <% } %>
                                
                                <form name="Add" id="addPregunta" action="${pageContext.request.contextPath}/AnadirPreguntaServlet" accept-charset="UTF-8ho" method="POST">
                                    <p>
                                        Añadir nueva Pregunta:
                                        <br><br>
                                                <textarea rows="5" cols="30" name="np_enunciado" id="np_enunciado" class="width50"></textarea>
                                    </p>
                                    <p>
                                        <input type="hidden" name="np_cuestionario" id="np_cuestionario" value="<%=cuestionario.getId()%>">
                                        <input type="submit" value="Añadir Pregunta" class="button">
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