/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Persistencia.BaseDeDatos;
import Modelo.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Antonio Pena Santiso
 */
public class SubirCurriculoServlet extends HttpServlet {

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
        String nombreFichero = null;
        String usuario = null;
        InputStream stream = null;        
        if (((Usuario)request.getSession().getAttribute("user") == null) || (!ServletFileUpload.isMultipartContent(request))){
            response.sendRedirect("/index.jsp");
        } else {
           DiskFileItemFactory factory = new DiskFileItemFactory();
           factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
           ServletFileUpload upload = new ServletFileUpload(factory);
           
           try {
             List items = upload.parseRequest(request);
             Iterator iter = items.iterator();
             while(iter.hasNext()){
                 FileItem item = (FileItem) iter.next();
                 if(item.isFormField()){
                     usuario = item.getString();
                 } else{
                     nombreFichero = item.getName();                     
                     stream = item.getInputStream();
                 }
                 if((usuario != null) && (stream != null)){
                     if(!(nombreFichero.endsWith(".pdf"))){
                        request.getSession().setAttribute("errorMod", "El curriculo debe estar en formato pdf");
                     } else {
                       base.setCurriculoUsuario(usuario, stream);
                     }
                 }                 
             }
            } catch (FileUploadException ex) {
                Logger.getLogger(SubirCurriculoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           Boolean admin = (Boolean)request.getSession().getAttribute("admin");
           if((admin != null) && (admin == true)){
               response.sendRedirect(getServletContext().getContextPath() + "/admin/detalleUsuario.jsp?email=" + usuario);
           } else {
               response.sendRedirect(getServletContext().getContextPath() + "/open/perfil.jsp");
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
