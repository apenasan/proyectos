/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Oferta;
import Persistencia.BaseDeDatos;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Pena Santiso
 */
public class BorrarOfertaServlet extends HttpServlet {

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
        int id = Integer.parseInt((String)request.getParameter("id"));
        ArrayList<Oferta> listaOfertas = (ArrayList<Oferta>)request.getSession().getAttribute("listaOfertas");
        BaseDeDatos base = BaseDeDatos.getInstancia();
        base.borrarOferta(id);
        base.borrarInteresesOferta(id);
        int i = 0;
        while((listaOfertas.get(i).getId()!= id)) {
                    i++;
                }
        if (listaOfertas.get(i).getId() == id){
                    listaOfertas.remove(i);
            request.getSession().setAttribute("listaOfertas", listaOfertas);
        }
        response.sendRedirect(getServletContext().getContextPath() + "/open/ofertas.jsp");
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
