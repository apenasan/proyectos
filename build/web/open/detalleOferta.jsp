<%-- 
    Document   : detalleOferta
    Created on : 18-dic-2012, 20:16:21
    Author     : Antonio Pena Santiso
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Oferta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        

        <title>Seleccion Personal - Detalles de la Oferta</title>


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
                                   Boolean administrador = (Boolean)request.getSession().getAttribute("admin");
                                   Boolean estaInteresado = (Boolean)request.getSession().getAttribute("estaInteresado");
                                   
                                %>
                                
                                <h1><%= oferta.getPuesto() %></h1>
                                
                                <p>
                                    Fecha:
                                    <%=oferta.getFecha()%>
                                </p>
                                
                                <h2>Lugar</h2>
                                <p>
                                    Pais:
                                    <%=oferta.getPais() %>
                                </p>
                                <p>
                                    Ubicación:
                                    <%=oferta.getUbicacion() %>                              <br>
                                </p>
                                <h2>Descripción</h2>
                                <p>
                                    Puesto:
                                    <%=oferta.getPuesto() %>
                                </p>
                                <p>
                                    Descripción: 
                                    <%=oferta.getDescripcion() %>
                                </p>
                                <p>
                                    Sueldo:
                                    <%=oferta.getSueldo()%>
                                </p>
                                <p>
                                    Duración del contrato:
                                    <%=oferta.getDuracion()%>
                                </p>
                                <p>
                                    Tipo de Contrato:
                                    <%=oferta.getTipo() %>
                                </p>
                                <br>
                                <h2>Requisitos</h2>
                                <p>
                                    Estudios mínimos: 
                                    <%=oferta.getEstudios() %>
                                </p>
                                <p>
                                    Experiencia mínima: 
                                    <%=oferta.getExperiencia() %>
                                </p>
                                <p>
                                    Requisitos mínimos: 
                                    <%=oferta.getRequisitos() %>
                                </p>
                                <br>
                                <% if((usuario != null)){
                                    if((administrador != null) && (administrador == true)){ %>
                                                                                                                            
                                        <form action="${pageContext.request.contextPath}/MostrarUsuariosInteresadosServlet">
                                            <input type="hidden" name="o_id" value="<%=oferta.getId()%>">
                                            <input type="submit" class="button" value="Mostrar usuarios inscritos">
                                        </form>
                                    <%} else {
                                        if (!estaInteresado){
                                    %>
                                            <form action="${pageContext.request.contextPath}/IniciarCuestionarioServlet">
                                                <p>
                                                    <input type="hidden" value="<%=usuario.getEmail()%>" name="i_usuario">
                                                    <input type="hidden" value="<%=oferta.getId()%>" name="i_oferta">
                                                    <input type="submit" value="Inscribirse" class="button">
                                                </p>
                                            </form>
                                    <% } else { %>
                                            <form action="${pageContext.request.contextPath}/NoInteresadoServlet">
                                                <p>
                                                    <input type="hidden" value="<%=usuario.getEmail()%>" name="i_usuario">
                                                    <input type="hidden" value="<%=oferta.getId()%>" name="i_oferta">
                                                    <input type="submit" value="Ya no estoy interesado" class="button">
                                                </p>
                                            </form>
                                     <% }
                                    }
                                } %>

                            </div>

                        </div>

                        <%@include file="/WEB-INF/include/pie.jsp" %>

                    </div>
                </div>
            </div>
            


        </body>
</html>