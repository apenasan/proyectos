<%-- 
    Document   : interesados
    Created on : 03-ene-2013, 18:46:23
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Interesado"%>
<%@page import="Modelo.Oferta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        

        <title>Seleccion Personal - Ofertas</title>


        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/html.css" />
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/layout.css" />

        <script>
            function estadoActual(id){
                document.getElementById(id).selected=true;
            }
        </script>
        
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

                                <% Oferta oferta = (Oferta)request.getSession().getAttribute("oferta"); %>
                                
                                <h1>Interesados en el puesto de <%=oferta.getPuesto()%></h1>
                                
                                <br><br>
                                        
                                
                                         
                                <% ArrayList<Interesado> listaInteresados;
                                   listaInteresados = (ArrayList<Interesado>)request.getSession().getAttribute("listaInteresados");
                                   if(listaInteresados.isEmpty()){ %>
                                        <p>
                                            No hay usuarios inscritos a esta oferta
                                        </p>
                                   <%     
                                   } else { %>
                                   
                                     <table summary="Esta tabla lista las ofertas según el filtro aplicado">
                                     <thead>
                                         <tr>
                                             <th class ="width30">Nombre</th>
                                             <th class ="width30">Apellidos</th>
                                             <th class ="width30">Email</th>
                                             <th class ="width30">Estado</th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                     
                                     <%
                                                                     
                                        for(int i = 0;i<listaInteresados.size();i++){
                                    
                                        %>
                                         <tr>
                                             <form action="${pageContext.request.contextPath}/CambiarEstadoServlet?loop=<%=String.valueOf(i)%>" method="POST">
                                                 <input type="hidden" name="paginaOrigen" value="oferta">
                                                 <input type="hidden" name="i_usuario" value="<%=listaInteresados.get(i).getEmail()%>">
                                                 <input type="hidden" name="i_oferta" value="<%=oferta.getId()%>">    
                                             <td><%=listaInteresados.get(i).getNombre()%></td>
                                             <td><%=listaInteresados.get(i).getApellidos()%></td>
                                             <td><a href="mailto:<%=listaInteresados.get(i).getEmail()%>"><%=listaInteresados.get(i).getEmail()%></td>
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
                                             <td><a href="${pageContext.request.contextPath}/PerfilServlet?email=<%=listaInteresados.get(i).getEmail()%>">Detalle</a></td>
                                             <td><a href="${pageContext.request.contextPath}/MostrarRespuestasCuestionarioServlet?email=<%=listaInteresados.get(i).getEmail()%>&id=<%=oferta.getId()%>">Cuestionario</a></td>
                                             </form>
                                         </tr>
                                         
                                         <script>
                                             <% String est = listaInteresados.get(i).getEstado() + String.valueOf(i);%>
                                             var est ="<%=est%>";
                                             estadoActual(est);
                                         </script>

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
