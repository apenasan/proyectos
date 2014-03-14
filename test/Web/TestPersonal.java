package Web;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Persistencia.BaseDeDatos;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Antonio Pena Santiso
 */
public class TestPersonal {
    
    private static BaseDeDatos base = BaseDeDatos.getInstancia();
    private static Connection conexion;
           
    @BeforeClass
    public static void setUp() {
        try {
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, 
                    "org.apache.naming");
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");
            
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUrl("jdbc:mysql://localhost:3306/personal");
            ds.setUser("root");
            ds.setPassword("ubuntu");
            
            ic.bind("java:/comp/env/jdbc/personal", ds);
            
            
            
            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");

            ds = (MysqlDataSource) webContext.lookup("jdbc/personal");
            
            base.setDs(ds);
            base.setNombreBase("personal");
                     
                    
        } catch (NamingException ex) {
            Logger.getLogger(TestPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testAcceso(){
         assertTrue(base.algunAdmin());
    }
}
