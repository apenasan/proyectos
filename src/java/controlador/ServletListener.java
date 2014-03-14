/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Persistencia.BaseDeDatos;
import Modelo.Usuario;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Antonio Pena Santiso
 */
public class ServletListener implements ServletContextListener {
    
    private BaseDeDatos base = BaseDeDatos.getInstancia();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String direccionBase = sce.getServletContext().getInitParameter("direccionBase");
        String nombreBase = sce.getServletContext().getInitParameter("nombreBase");
        base.inicializar(direccionBase,nombreBase);
        if ((base.algunAdmin()) == false){
           String adminEmail = sce.getServletContext().getInitParameter("adminEmail");
           String adminContrasena = sce.getServletContext().getInitParameter("adminContrasena");
           Usuario admin = new Usuario("Admin", "Admin",adminEmail,"",adminContrasena,'a',"");
           base.addUsuario(admin);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
