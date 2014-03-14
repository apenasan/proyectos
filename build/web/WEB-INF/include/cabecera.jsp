<%-- 
    Document   : cabecera
    Created on : 05-dic-2012, 11:55:58
    Author     : Antonio Pena Santiso
--%>
<%@page import="Modelo.Usuario"%>
<div id="header" class="floatLeft width100">

    <div class="floatLeft width25 rightBorder">

      <div id="title">
        <h1>Seleccion Personal</h1>
        <p>
          Una aplicacion para apoyar los procesos de  reclutamiento y contratación
        </p>
      </div>

      <ul id="menu">
        <li>
          <a href="${pageContext.request.contextPath}/index.jsp" title="Pagina principal">Principal</a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/MostrarOfertasServlet" title="Listado de Ofertas">Ofertas</a>
        </li>
        <% Boolean admin = (Boolean)request.getSession().getAttribute("admin");
           Usuario userheader = (Usuario)request.getSession().getAttribute("user");
        if(userheader != null){
              if((admin != null) && (admin == true)){%>
                <li>
                    <a href="${pageContext.request.contextPath}/MostrarUsuariosServlet" title="Acceder a la lista de usuarios">Usuarios</a>
                </li>
              <% } else if((admin == null) || (admin == false)){ %>
                <li>
                    <a href="${pageContext.request.contextPath}/InteresesUsuarioServlet?email=<%=userheader.getEmail()%>" title="Acceder a mis intereses">Intereses</a>
                </li>
              <% } %>
                <li>
                    <a href="${pageContext.request.contextPath}/PerfilServlet?email=<%=userheader.getEmail()%>" title="Acceder a mi perfil">Perfil</a>
                </li>
                
        <% } %>
        
        <li>
          <a href="${pageContext.request.contextPath}/acercade.jsp" title="Acerca de la aplicación">Acerca de</a>
        </li>
      </ul>
    </div>

  </div>
