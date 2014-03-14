/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Antonio Pena Santiso
 */
public class AdminFilter implements Filter {
    
       
    public AdminFilter() {
    }    
    
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestMod = ((HttpServletRequest) request);
        
                      
        Boolean admin = (Boolean) requestMod.getSession().getAttribute("admin");
        if((admin == null) || (admin == false)){
            requestMod.getSession().setAttribute("error", "No tiene permiso para acceder a " + requestMod.getRequestURL().toString());
            RequestDispatcher restringido = request.getRequestDispatcher("/error.jsp");
            restringido.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }


    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        }
}