<%-- 
    Document   : respuestas
    Created on : 14-jun-2013, 23:00:34
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Respuesta"%>
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
                                   ArrayList<Respuesta> listaRespuestas = (ArrayList<Respuesta>)request.getSession().getAttribute("listaRespuestas");
                                %>
                                
                                <h1>Respuestas al cuestionario de <%= oferta.getPuesto() %></h1>
                                
                               
                                <br>
                                
                                <p>
                                    <h2><%= cuestionario.getNombre()%></h2>
                                </p>
                                
                                <% for(int i=0;i < cuestionario.getListaPreguntas().size();i++){ %>
                                
                                    <p>
                                        Pregunta <%=i+1%>:
                                        <br>
                                        <textarea rows="5" cols="30" class="width50" readonly><%=cuestionario.getListaPreguntas().get(i).getEnunciado()%></textarea>
                                        <br>
                                        Respuesta:
                                        <br>
                                            <textarea rows="5" cols="30" class="width50" readonly><%=listaRespuestas.get(i).getContenido()%></textarea>
                                    </p>
                                    
                                    <br><br>
                                        
                                <% } %>
                                       
                                                     
                                     
                                <br>
                                
                            </div>

                        </div>

                        <%@include file="/WEB-INF/include/pie.jsp" %>

                    </div>
                </div>
            </div>
            


        </body>
</html>