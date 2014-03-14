<%-- 
    Document   : interesesUsuario
    Created on : 21-feb-2013, 11:40:53
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Interesado"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Oferta"%>
<%@page import="java.util.*"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        

        <title>Seleccion Personal - Mis Intereses</title>


        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/html.css" />
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/layout.css" />

        <script>
            function estadoActual(id){
                document.getElementById(id).selected=true;
            }
        </script>
        
    </head>
        <% Usuario usuario = (Usuario)request.getSession().getAttribute("user");%>
    
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


                                <h1>Listado de Ofertas en las que está inscrito</h1>
                                
                                <br>
                                                                                              
                                <% ArrayList<Interesado> listaIntereses;
                                   listaIntereses = (ArrayList<Interesado>)request.getSession().getAttribute("listaIntereses");
                                   if(listaIntereses.isEmpty()){ %>
                                        <p>
                                           No está inscrito en ninguna oferta 
                                        </p>
                                   <%
                                   } else { %>
                                                                      
                                     <table summary="Esta tabla lista las ofertas según el filtro aplicado">
                                     <thead>
                                         <tr>
                                             <th class ="width30">Puesto</th>
                                             <th class ="width30">Fecha</th>
                                             <th class ="width30">Estado</th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                     
                                     <%
                                                                     
                                        for(int i = 0;i<listaIntereses.size();i++){
                                    
                                        %>
                                        <tr>
                                             <td><a href="${pageContext.request.contextPath}/DetalleOfertaServlet?id=<%=listaIntereses.get(i).getIdOferta()%>"><%=listaIntereses.get(i).getPuesto()%></td>
                                             <td class="alignRight"><%=listaIntereses.get(i).getStringFecha()%></td>
                                             
                                             <% if ((usuario != null) && (usuario.getTipo() == 'a')){ %>
                                                                                     
                                                <form action="${pageContext.request.contextPath}/CambiarEstadoServlet?loop=<%=String.valueOf(i)%>" method="POST">
                                                    <input type="hidden" name="paginaOrigen" value="usuario">
                                                    <input type="hidden" name="i_usuario" value="<%=listaIntereses.get(i).getEmail()%>">
                                                     <input type="hidden" name="i_oferta" value="<%=listaIntereses.get(i).getIdOferta()%>">    
                                             
                                                 <td>
                                                     <select name="i_estado">
                                                        <option id="<%=("Apuntado" + String.valueOf(i))%>">Apuntado</option>
                                                        <option id="<%=("Pendiente Entrevista" + String.valueOf(i))%>">Pendiente Entrevista</option>
                                                        <option id="<%=("Entrevistado" + String.valueOf(i))%>">Entrevistado</option>
                                                        <option id="<%=("Aceptado" + String.valueOf(i))%>">Aceptado</option>
                                                       <option id="<%=("Descartado" + String.valueOf(i))%>">Descartado</option>
                                                     </select>
                                                 </td>
                                                 <td><input type="submit" class="button" value="Cambiar Estado"></td>
                                                 <td><a href="detalleUsuario.jsp?email=<%=listaIntereses.get(i).getEmail()%>">Detalle</a></td>
                                                 </form>
                                             
                                                
                                             
                                         
                                             <script>
                                                <% String est = listaIntereses.get(i).getEstado() + String.valueOf(i);%>
                                                var est ="<%=est%>";
                                                estadoActual(est);
                                             </script>
                                                
                                          <% } else { %>
                                                
                                                    <td class="alignRight"><%=listaIntereses.get(i).getEstado()%></td>
                                                
                                          <% } %>
                                          
                                          </tr>
                                             
                                    <% } %>
                                
                                
                                    </tbody>
                                 </table>
                                
                                <% } %>                                   

                            </div>

                        </div>

                        <%@include file="/WEB-INF/include/pie.jsp" %>

                    </div>
                </div>
            </div>
            


        </body>
</html>