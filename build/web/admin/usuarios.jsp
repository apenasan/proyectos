<%-- 
    Document   : usuarios
    Created on : 26-dic-2012, 13:40:48
    Author     : Antonio Pena Santiso
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Oferta"%>
<%@page import="Modelo.Oferta"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        

        <title>Seleccion Personal - Usuarios</title>


        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/html.css" />
        <link rel="stylesheet" type="text/css" media="screen, tv, projection" href="${pageContext.request.contextPath}/css/layout.css" />

    </head>
        
        <% Usuario usuario = (Usuario)request.getSession().getAttribute("user"); %>
    
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


                                <h1>Listado de Usuarios</h1>
                                                   
                                    <form action="${pageContext.request.contextPath}/admin/anadirUsuario.jsp">
                                        <p>      
                                            <input type="submit" class="button" value="Nuevo Usuario">
                                        </p>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/FiltroUsuariosServlet">
                                        <p>        
                                            <input type="submit" id="b_submit" name="Filtrar" value="Filtrar" class="button">
                                        </p>
                                        <p>
                                            <label class="filtro" for="b_apellidos">Apellidos
                                                <input type="textarea" name="b_apellidos" id="b_apellidos">
                                            </label>    
                                            <label class="filtro" for="b_clave">Clave Currículo
                                                <input class="filtro" type="textarea" name="b_clave" id="b_clave">
                                            </label>
                                        </p>
                                    </form>
                                        
                                        <br><br>
                                        
                                <table summary="Esta tabla lista los usuarios según el filtro aplicado" id="tablaUsuarios">
                                     <thead>
                                         <tr>
                                             <th class ="width50">Nombre</th>
                                             <th class ="width50">Apellidos</th>
                                             <th class ="width50">Email</th>
                                             <th class ="width50">Telefono</th>
                                             <th class ="width50">Permisos</th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                         
                                <% ArrayList<Usuario> listaUsuarios;
                                   listaUsuarios = (ArrayList<Usuario>)request.getSession().getAttribute("listaUsuarios");
                                   for(int i = 0;i<listaUsuarios.size();i++){
                                    
                                 %>
                                         <tr>
                                             <td><%=listaUsuarios.get(i).getNombre()%></td>
                                             <td><%=listaUsuarios.get(i).getApellidos()%></td>
                                             <td><a href="mailto:<%=listaUsuarios.get(i).getEmail()%>"><%=listaUsuarios.get(i).getEmail()%></a></td>
                                             <td><%=listaUsuarios.get(i).getTelefono()%></td>
                                             <td><%=listaUsuarios.get(i).getTipoString()%></td>
                                             
                                             <td><a href="${pageContext.request.contextPath}/PerfilServlet?email=<%=listaUsuarios.get(i).getEmail()%>">Detalle</a></td>
                                             <td><a href="${pageContext.request.contextPath}/InteresesUsuarioServlet?email=<%=listaUsuarios.get(i).getEmail()%>">Inscripciones</a></td>
                                         </tr>
                                     

                                <% } %>
                                
                                    </tbody>
                                 </table>
                                                                

                            </div>

                        </div>

                      </div>
                </div>
                
                <%@include file="/WEB-INF/include/pie.jsp" %>
                                
            </div>
            


        </body>
</html>
