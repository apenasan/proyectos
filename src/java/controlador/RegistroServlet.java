/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Persistencia.BaseDeDatos;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Pena Santiso
 */
public class RegistroServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("r_nombre");
        String apellidos = request.getParameter("r_apellidos");
        String contrasena = request.getParameter("r_contrasena");
        String contrasenaRep = request.getParameter("r_contrasenarep");
        String telefono = request.getParameter("r_telefono");
        String email = request.getParameter("r_email");
        String cv = request.getParameter("r_cv");        
        String permisos = request.getParameter("r_permisos");
        char per;
        String error = null;
        Boolean admin = (Boolean)request.getSession().getAttribute("admin");
        BaseDeDatos base = BaseDeDatos.getInstancia();
            if(base.getUsuario(email) != null){
                error = "Ya existe un usuario asociado a esta cuenta de correo";
                request.getSession().setAttribute("errorRegistro",error);
                if((admin != null) && (admin == true)){
                    response.sendRedirect(getServletContext().getContextPath() + "/admin/anadirUsuario.jsp");
                } else {
                    response.sendRedirect(getServletContext().getContextPath() + "/open/registro.jsp");
                }
            } else{
                if(permisos != null){
                    per = permisos.charAt(0);
                } else {
                    per = 'c';
                }
                Usuario usuario = new Usuario(nombre,apellidos,email,telefono,contrasena,per,cv);
                base.addUsuario(usuario);
                request.getSession().setAttribute("errorRegistro",error);
                if(request.getSession().getAttribute("user") != null){
                    if((admin != null) && (admin == true)){
                        response.sendRedirect(getServletContext().getContextPath() + "/MostrarUsuariosServlet");
                    } else {
                        response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                    }
                } else {
                    request.getSession().setAttribute("usuario", email);
                    request.getSession().setAttribute("user",usuario);
                    if (usuario.getTipo() == 'a'){
                        request.getSession().setAttribute("admin",true);
                    } else {
                        request.getSession().setAttribute("admin",false);
                    }
                    response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                }
            }        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(404);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
