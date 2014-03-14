/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Persistencia.BaseDeDatos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Pena Santiso
 */
public class CambiarEstadoServlet extends HttpServlet {

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
        String origen = request.getParameter("paginaOrigen");
        BaseDeDatos base = BaseDeDatos.getInstancia();
        String usuario = request.getParameter("i_usuario");
        int oferta = Integer.parseInt(request.getParameter("i_oferta"));
        String estado = request.getParameter("i_estado");
        base.cambiarEstadoInteresado(usuario, oferta, estado);
        if(origen.equals("oferta")){
            response.sendRedirect(getServletContext().getContextPath() + "/MostrarUsuariosInteresadosServlet?o_id=" + oferta);
        } else if(origen.equals("usuario")){
            response.sendRedirect(getServletContext().getContextPath() + "/InteresesUsuarioServlet?email=" + usuario);
        } else{
          response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
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
        processRequest(request, response);
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
