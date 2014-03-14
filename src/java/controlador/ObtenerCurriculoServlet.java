/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Persistencia.BaseDeDatos;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Pena Santiso
 */
public class ObtenerCurriculoServlet extends HttpServlet {

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
        String usuario = request.getParameter("email");
        InputStream stream = null;
        
        if((stream = base.getCurriculoUsuario(usuario)) != null){
            
            
            BufferedInputStream input = null;
            BufferedOutputStream output = null;
        
            input = new BufferedInputStream(stream);
            output = new BufferedOutputStream(response.getOutputStream());
        
            int length = input.available();
            byte[] buffer = new byte[length];
            response.setHeader("Content-Disposition", "attachment; filename=\"CV_" + usuario + ".pdf");
            response.setIntHeader("Content-Length",length );
            response.setContentType("application/pdf");
        
                
            input.read(buffer);
            output.write(buffer,0,length);
        
            input.close();
            output.close();
        } else {
            request.getSession().setAttribute("errorMod", "No hay un currículo subido");
            response.sendRedirect(getServletContext().getContextPath() + "/PerfilServlet?email=" + usuario);
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
