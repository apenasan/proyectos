<%-- 
    Document   : login.jsp
    Created on : 06-dic-2012, 19:59:25
    Author     : Antonio Pena Santiso
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="java.lang.String"%>
<%@page import="java.io.PrintWriter"%>

        <% Usuario userlogin = (Usuario)request.getSession().getAttribute("user");
            if(userlogin == null){
        %>
        
        <div>
            
        <h1>Iniciar sesión</h1><br>
        
        <%  String errorLogin = (String)request.getSession().getAttribute("errorLogin");
            if(errorLogin != null){ %>
            <span id="errorLogin" class="error"><%=(String)request.getSession().getAttribute("errorLogin")%></span>
        <%  request.getSession().setAttribute("errorLogin",null);
            } %>
        
        <form class ="floatLeft" id="loginform" method="POST" >
            <p>
                <label for="f_email">Email</label>
                <input type="text" name="f_email" id="f_email" class="width10"/>
            </p>
            <p>
                <label for="f_contrasena">Contraseña</label>
                <input type="password" name="f_contrasena" id="f_contrasena" class="width10"/>
            </p>
            <p>
                <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/LoginServlet'" name="submit" value="enviar" class="button"/>
                <input type="submit" onclick="this.form.action = '${pageContext.request.contextPath}/open/registro.jsp'" name="registro" value="Registrarse" class="button"/>
            </p>
        </form> 
                
        </div>
        
        
       <% } else{
        %>
            <h2>Sesion iniciada</h2>
            <div id="loginname">
                <p>
                Usuario: <%=userlogin.getEmail()%>
                </p>
            </div>
            <form action="${pageContext.request.contextPath}/LogoutServlet" method="Post">
                <p>
                    <input type ="submit" name="close" value="Cerrar sesión" class ="button">
                </p>
            </form>
                
            
       <%
             }
        %>
        
