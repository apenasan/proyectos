/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Respuesta;
import Persistencia.BaseDeDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Pena Santiso
 */
public class EnviarRespuestasServlet extends HttpServlet {

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
        Respuesta respuesta = null;
        int idOferta = Integer.parseInt(request.getParameter("o_id"));
        String email = request.getParameter("p_email");
        String[] idPreguntas = request.getParameterValues("p_id");
        String[] respuestas = request.getParameterValues("p_respuesta");
        for(int i = 0;i < idPreguntas.length; i++){
            respuesta = new Respuesta(email,Integer.parseInt(idPreguntas[i]),respuestas[i]);
            if(base.getRespuesta(email, Integer.parseInt(idPreguntas[i])) != null){
                base.borrarRespuesta(email, Integer.parseInt(idPreguntas[i]));
            }                
            
            base.addRespuesta(respuesta);
        }
        response.sendRedirect(getServletContext().getContextPath() + "InteresadoServlet?i_usuario=" + email + "&i_oferta=" + idOferta);
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
