/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Oferta;
import Persistencia.BaseDeDatos;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Pena Santiso
 */
public class AnadirOfertaServlet extends HttpServlet {

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
        BaseDeDatos base = BaseDeDatos.getInstancia();
        String error = "";
        SimpleDateFormat formato = new SimpleDateFormat("ss/mm/hh/dd/MM/yyyy");
        String puesto = request.getParameter("r_puesto");
        String descripcion = request.getParameter("r_descripcion");
        String pais = request.getParameter("r_pais");
        String ubicacion = request.getParameter("r_ubicacion");
        java.util.Date date = new java.util.Date();
        formato.format(date);
        java.sql.Date fecha = new java.sql.Date(date.getTime());
        String estudios = request.getParameter("r_estudios");
        String experiencia = request.getParameter("r_experiencia");
        String requisitos = request.getParameter("r_requisitos");
        String contrato = request.getParameter("r_contrato");
        String sueldo = request.getParameter("r_sueldo");
        String duracion = request.getParameter("r_duracion");
        String destinatarios = request.getParameter("r_destinatarios");
        
        Oferta oferta = new Oferta(0,puesto,descripcion,pais,ubicacion,fecha,estudios,experiencia,requisitos,contrato,sueldo,duracion,destinatarios);
        
        base.addOferta(oferta);
        response.sendRedirect(getServletContext().getContextPath() + "/MostrarOfertasServlet");
        
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
