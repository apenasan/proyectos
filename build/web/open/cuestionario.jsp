<%-- 
    Document   : cuestionario
    Created on : 14-jun-2013, 22:03:50
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Cuestionario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Oferta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        

        <title>Seleccion Personal - Cuestionario</title>


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
                                
                                <h1>Por favor, rellene este cuestionario para inscribirse en la oferta <%= oferta.getPuesto() %></h1>
                                
                                                                
                                <form name="Completar Cuestionario" id="modificarform" action="${pageContext.request.contextPath}/EnviarRespuestasServlet" accept-charset="UTF-8ho" method="POST">
                                
                                                                     
                                <p>
                                    <h2><%=cuestionario.getNombre()%></h2>
                                        <br>
                                </p>
                                <% for(int i=0;i < cuestionario.getListaPreguntas().size();i++){ %>
                                
                                    <p>
                                        Pregunta <%=i+1%>:
                                        <br>
                                            <%=cuestionario.getListaPreguntas().get(i).getEnunciado()%>
                                        <br>
                                            <textarea rows="5" cols="30" name="p_respuesta" id="respuesta<%=i+1%>" class="width50"></textarea>
                                    </p>
                                    <p>
                                    
                                    <input type="hidden" name="p_id" value="<%=cuestionario.getListaPreguntas().get(i).getId()%>">
                                    
                                    </p>
                                    
                                    <br>
                                <% } %>
                                                               
                                <p>
                                     <input type="hidden" name="p_cuestionario" id="p_cuestionario" value="<%=cuestionario.getId()%>">
                                     <input type="hidden" name="p_email" value="<%=usuario.getEmail()%>">
                                     <input type="hidden" name="o_id" id="m_id" value="<%=oferta.getId()%>"/>
                                     <input type="submit" value="Enviar" class="button"/>
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