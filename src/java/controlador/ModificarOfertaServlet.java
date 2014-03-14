/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Persistencia.BaseDeDatos;
import Modelo.Oferta;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Pena Santiso
 */
public class ModificarOfertaServlet extends HttpServlet {

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
        Oferta oferta = new Oferta();
        
        int id = Integer.parseInt(request.getParameter("m_id"));
        String puesto = request.getParameter("m_puesto");
        String descripcion = request.getParameter("m_descripcion");
        String tipo = request.getParameter("m_tipo");
        String pais = request.getParameter("m_pais");
        String ubicacion = request.getParameter("m_ubicacion");
        String estudios = request.getParameter("m_estudios");
        String experiencia = request.getParameter("m_experiencia");
        String requisitos = request.getParameter("m_requisitos");
        String sueldo = request.getParameter("m_sueldo");
        String duracion = request.getParameter("m_duracion");
        String destinatarios = request.getParameter("m_destinatarios");
        
        oferta.setId(id);        
        oferta.setPuesto(puesto);
        oferta.setDescripcion(descripcion);
        oferta.setTipo(tipo);
        oferta.setPais(pais);
        oferta.setUbicacion(ubicacion);
        oferta.setEstudios(estudios);
        oferta.setRequisitos(requisitos);
        oferta.setExperiencia(experiencia);
        oferta.setSueldo(sueldo);
        oferta.setDuracion(duracion);
        oferta.setDestinatarios(destinatarios);
        
        base.modificarOferta(oferta);
                
        response.sendRedirect(getServletContext().getContextPath() + "/DetalleOfertaServlet?id=" + id);
        
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
