<%-- 
    Document   : ofertas
    Created on : 15-dic-2012, 19:12:59
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="Modelo.Oferta"%>
<%@page import="java.util.*"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        

        <title>Seleccion Personal - Ofertas</title>


        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/html.css" />
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/layout.css" />

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


                                <h1>Listado de Ofertas</h1>
                                
                                <% if(usuario != null){
                                       if(usuario.getTipo() == 'a'){%>
                                            <form action="${pageContext.request.contextPath}/admin/anadirOferta.jsp">
                                                <p>
                                                     <input type="submit" class="button" value="Nueva oferta">
                                                </p>
                                            </form>
                                <% } else{ %>
                                        <form action="${pageContext.request.contextPath}/MisInteresesServlet">
                                            <p>
                                                <input type="submit" class="button" value="Mis Intereses">
                                                <input type="hidden" name="o_email" value="<%=usuario.getEmail()%>">
                                            </p>
                                        </form>                                    
                                <% }
                                }%>
                                <!--Filtro de ofertas-->
                                    <form action="${pageContext.request.contextPath}/FiltroOfertasServlet">
                                        <p>
                                            <input type="submit" id="b_submit" name="Filtrar" value="Filtrar" class="button">
                                        </p>
                                        <p>
                                            <label class="filtro" for="b_puesto">Puesto
                                                <input type="textarea" name="b_puesto" id="b_puesto">
                                            </label>    
                                            <label class="filtro" for="b_clave">Descripción
                                                <input class="filtro" type="textarea" name="b_clave" id="b_clave">
                                            </label>
                                            <label class="filtro" for="b_pais">Pais
                                                <input type="textarea" name="b_pais" id="b_pais">
                                            </label>
                                        </p>               
                                    </form>
                                        <br><br>
                                        
                                
                                         
                                <% ArrayList<Oferta> listaOfertas;
                                   listaOfertas = (ArrayList<Oferta>)request.getSession().getAttribute("listaOfertas");
                                   if(listaOfertas.isEmpty()){ %>
                                        <p>
                                           No hay ofertas disponibles 
                                        </p>
                                   <%
                                   } else { %>
                                   
                                   <table summary="Esta tabla lista las ofertas según el filtro aplicado">
                                     <thead>
                                         <tr>
                                             <th class ="width10">Fecha</th>
                                             <th class ="width50">Puesto</th>
                                             <th class ="width50">Pais</th>
                                             <th class ="width50">Tipo</th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                   <%
                                   
                                   for(int i = 0;i<listaOfertas.size();i++){
                                     if(((usuario == null) || (usuario.getTipo() == 'c')) && !(listaOfertas.get(i).getDestinatarios().equals("Abierta"))){
                                        } else if((usuario != null) && (usuario.getTipo() =='e') && (listaOfertas.get(i).getDestinatarios().equals("Cerrada"))){
                                           } else {
                                 %>
                                                    <tr>
                                                        <td><%=listaOfertas.get(i).getStringFecha()%></td>
                                                        <td><a href="${pageContext.request.contextPath}/DetalleOfertaServlet?id=<%=listaOfertas.get(i).getId()%>"><%=listaOfertas.get(i).getPuesto()%></a></td>
                                                        <td><%=listaOfertas.get(i).getPais()%></td>
                                                        <td><%=listaOfertas.get(i).getTipo()%></td>
                                             
                                                        <%if (usuario!=null){
                                                            if ((usuario.getTipo())=='a'){ %>
                                                                <td><a href="${pageContext.request.contextPath}/BorrarOfertaServlet?id=<%=listaOfertas.get(i).getId()%>">Eliminar</a></td>
                                                                <td><a href="${pageContext.request.contextPath}/MostrarUsuariosInteresadosServlet?o_id=<%=listaOfertas.get(i).getId()%>">Inscritos</a></td>
                                                        <%}
                                                        }%>
                                                    </tr>
                                     

                                <%   }
                                   }
                                %>
                                
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