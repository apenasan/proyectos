/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Oferta;
import Modelo.Usuario;
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
public class DetalleOfertaServlet extends HttpServlet {

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
        BaseDeDatos base = BaseDeDatos.getInstancia();        
        int id = Integer.parseInt(request.getParameter("id"));
        Oferta oferta = base.getOferta(id);
        Usuario usuario = (Usuario)request.getSession().getAttribute("user");
        Boolean admin = (Boolean)request.getSession().getAttribute("admin");
        
        request.getSession().setAttribute("oferta",oferta);
        
        if((admin != null) && (admin == true)){
            request.getSession().setAttribute("numeroInteresados",base.contarInteresados(id));
            response.sendRedirect(getServletContext().getContextPath() + "/admin/editarOferta.jsp");
        } else {
        
            if (usuario == null){
                request.getSession().setAttribute("estaInteresado", false);
            } else {
                String email = usuario.getEmail();         
               Boolean estaInteresado = base.estaInteresado(email,id);
                request.getSession().setAttribute("estaInteresado",estaInteresado);
            }
        
            response.sendRedirect(getServletContext().getContextPath() + "/open/detalleOferta.jsp");
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
