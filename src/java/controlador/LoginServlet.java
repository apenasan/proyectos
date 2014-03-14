/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Persistencia.BaseDeDatos;
import Modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Pena Santiso
 */
public class LoginServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException, NamingException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Usuario usuario = null;
        String email = request.getParameter("f_email");
        String contrasena = request.getParameter("f_contrasena");
        String error = null;
        if(email.equals("")){
           error = "Escribe tu correo";
           request.getSession().setAttribute("errorLogin", error);
           response.sendRedirect("index.jsp");
        } else if(contrasena.equals("")) {
           error = "Escribe tu contraseña";
           request.getSession().setAttribute("errorLogin", error);
           response.sendRedirect("index.jsp");
        } else {    
            BaseDeDatos base = BaseDeDatos.getInstancia();
            usuario = base.getUsuario(email);
              if(base.getUsuario(email) != null){
                 if (contrasena.equals(usuario.getContrasena())){
                    request.getSession().setAttribute("usuario",usuario.getEmail());
                    request.getSession().setAttribute("user", usuario);
                    if(usuario.getTipo() == 'a'){
                        request.getSession().setAttribute("admin",true);
                    } else {
                        request.getSession().setAttribute("admin",false);
                    }
                    request.getSession().setAttribute("errorLogin", error);
                    response.sendRedirect("index.jsp");
                 } else {
                    error = "La contraseña es incorrecta";                    
                    request.getSession().setAttribute("errorLogin", error);
                    response.sendRedirect("index.jsp");
                 }                
              } else{
                    error = "El usuario " + email + " no existe";
                    request.getSession().setAttribute("errorLogin", error);
                    response.sendRedirect("index.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
