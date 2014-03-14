/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Cuestionario;
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
public class EditarCuestionarioServlet extends HttpServlet {

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
        
        int idOferta = Integer.parseInt((String)request.getParameter("o_id"));
        BaseDeDatos base = BaseDeDatos.getInstancia();
        
        Cuestionario cuestionario = base.getCuestionarioPorOferta(idOferta);
        
        
                
        if(cuestionario == null){
            cuestionario = new Cuestionario(0,"Cuestionario sin nombre",idOferta);
            base.addCuestionario(cuestionario);
        } else {
            String nombreCuestionario;
            cuestionario.setListaPreguntas(base.getPreguntasPorCuestionario(cuestionario.getId()));
            if((nombreCuestionario = request.getParameter("c_nombre")) != null){
                cuestionario.setNombre(nombreCuestionario);
            }                
            
        }
        
        request.getSession().setAttribute("cuestionario",cuestionario);
                
        response.sendRedirect(getServletContext().getContextPath() + "/admin/editarCuestionario.jsp");

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
