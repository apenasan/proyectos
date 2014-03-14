<%-- 
    Document   : editarOferta
    Created on : 21-mar-2013, 14:09:31
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
                                   int numeroInteresados = (Integer)request.getSession().getAttribute("numeroInteresados");
                                %>
                                
                                <h1><%= oferta.getPuesto() %></h1>
                                
                                <p>
                                    Fecha de publicación:
                                    <%=oferta.getFecha()%>
                                </p>
                                
                                <form name="Modificar" id="modificarform" action="${pageContext.request.contextPath}/ModificarOfertaServlet" accept-charset="UTF-8ho" method="POST">
                                
                                <h2>Lugar</h2>
                                
                                <br>
                                
                                <p>
                                        <label for="m_pais">Pais</label>
                                        <input type="text" name="m_pais" id="m_pais" class="width25" maxlength="50" value="<%= oferta.getPais()%>"/>
                                        <br><br>
                                </p>
                                
                                <p>
                                        <label for="m_ubicacion">Ubicacion</label>
                                        <input type="text" name="m_ubicacion" id="m_ubicacion" class="width25" maxlength="50" value="<%= oferta.getUbicacion()%>"/>
                                        <br><br>
                                </p>
                                        
                                <h2>Descripción</h2>
                                
                                <br>
                                
                                <p>
                                        <label for="m_puesto">Puesto</label>
                                        <input type="text" name="m_puesto" id="m_puesto" class="width25" maxlength="50" value="<%= oferta.getPuesto()%>"/>
                                        <br><br>
                                </p>
                                
                                <p>
                                        <label for="m_descripcion">Descripción</label>
                                        <textarea rows="10" cols="30" name="m_descripcion" id="m_descripcion" class="width50"><%= oferta.getDescripcion()%></textarea>
                                        <br><br>
                                </p>
                                        
                                <p>
                                        <label for="m_sueldo">Sueldo</label>
                                            <input type="text" name="m_sueldo" id="m_sueldo" class="width25" maxlength="20" value="<%=oferta.getSueldo()%>"/>
                                        <br><br>
                                </p>
                                <p>
                                        <label for="m_duracion">Duración del contrato</label>
                                            <input type="text" name="m_duracion" id="m_duracion"class="width25" maxlength="30" value="<%=oferta.getDuracion()%>"/>
                                        <br><br>
                                </p>        
                                
                                <p>
                                        <label for="m_tipo">Tipo</label>
                                        <input type="text" name="m_tipo" id="m_tipo" class="width25" maxlength="50" value="<%= oferta.getTipo()%>"/>
                                        <br><br>
                                </p>
                                    
                                <h2>Requisitos</h2>
                                
                                <br>
                                
                                <p>
                                        <label for="m_estudios">Estudios</label>
                                        <input type="text" name="m_estudios" id="m_estudios" class="width25" maxlength="100" value="<%= oferta.getEstudios()%>"/>
                                        <br><br>
                                </p>
                                
                                <p>
                                        <label for="m_experiencia">Experiencia</label>
                                        <input type="text" name="m_experiencia" id="m_experiencia" class="width25" maxlength="100" value="<%= oferta.getExperiencia()%>"/>
                                        <br><br>
                                </p>
                                
                                <p>
                                        <label for="m_requisitos">Requisitos</label>
                                        <input type="text" name="m_requisitos" id="m_requisitos" class="width25" maxlength="100" value="<%= oferta.getRequisitos()%>"/>
                                        <br><br>
                                </p>
                                        
                                <p>
                                        <label for="m_destinatarios">Destinatarios</label>
                                        <select name="m_destinatarios" id="m_destinatarios">
                                            <option value="<%=oferta.getDestinatarios()%>" selected="true"><%=oferta.getDestinatarios()%></option>
                                            <option value="Abierta">Abierta</option>
                                            <option value="Empleados">Empleados</option>
                                            <option value="Cerrada">Cerrada</option>
                                        </select>
                                </p>
                                <p>
                                     <input type="hidden" name="m_id" id="m_id" value="<%=oferta.getId()%>"/>
                                     <input type="submit" value="Modificar" class="button"/>
                                </p>
                                     
                                    <form>
                                     <p>
                                          Hay <%=numeroInteresados%> usuarios inscritos en esta oferta.
                                          <br><br>
                                          <input type="hidden" name="o_id" value="<%=oferta.getId()%>">
                                        <input type="submit" class="button" value="Ver Inscritos" onclick="this.form.action = '${pageContext.request.contextPath}/MostrarUsuariosInteresadosServlet'">
                                          <input type="submit" class="button" value="Editar Cuestionario" onclick="this.form.action = '${pageContext.request.contextPath}/EditarCuestionarioServlet'">
                                          <input type="submit" class="button" value="Eliminar Oferta" onclick="this.form.action = '${pageContext.request.contextPath}/BorrarOfertaServlet'">

                                     </p>
                                    </form>
                                                                    
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