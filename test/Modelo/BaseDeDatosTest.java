/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Persistencia.BaseDeDatos;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import static junit.framework.Assert.*;

/**
 *
 * @author Antonio Pena Santiso
 */
public class BaseDeDatosTest {
    
    private static BaseDeDatos base = BaseDeDatos.getInstancia();
    
    public BaseDeDatosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {  
          Modelo.DBUnitUtils.generarXML("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test","root","ubuntu","datos");
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
            ds.setUrl("jdbc:mysql://localhost:3306/test");
            ds.setUser("root");
            ds.setPassword("ubuntu");
            
            ic.bind("java:/comp/env/jdbc/test", ds);
            
            
            
            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");

            ds = (MysqlDataSource) webContext.lookup("jdbc/test");
            
            base.setDs(ds);
            base.setNombreBase("test");
                     
                    
        } catch (NamingException ex) {
            Logger.getLogger(BaseDeDatosTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        Modelo.DBUnitUtils.crearDatos("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test","root","ubuntu","datos");
    }
    
    @Before
    public void setUp() throws SQLException {
        Modelo.DBUnitUtils.crearDatos("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test","root","ubuntu","datos");
    }
       
    
    @After
    public void tearDown() throws SQLException {
        Modelo.DBUnitUtils.borrarDatos("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test","root","ubuntu","datos");
    }

    @Test
    
    // Prueba que se obtengan los datos correctos
    
    public void TestGetUsuario(){
        Usuario usuario = base.getUsuario("pepram@gmail.com");
        assertEquals(usuario.getNombre(),"Pepote");
        assertEquals(usuario.getApellidos(),"Ramirez");
        assertEquals(usuario.getTelefono(),"555467634");
    }
    
    @Test 
    
    // Prueba que se inserten correctamente los datos de un usuario.
    
    public void TestAddUsuario(){
        Usuario usuario1 = new Usuario("Arturo","Tester","arturo@test.com","555555555","contrasenaTest",'c',"Curriculo Test");
        base.addUsuario(usuario1);
        Usuario usuario2 = base.getUsuario("arturo@test.com");
        assertEquals(usuario2.getNombre(),"Arturo");
        assertEquals(usuario2.getApellidos(),"Tester");
        assertEquals(usuario2.getTelefono(),"555555555");
        assertEquals(usuario2.getCv(),"Curriculo Test");
        assertEquals(usuario2.getContrasena(),"contrasenaTest");
        assertEquals(usuario2.getTipo(),'c');
        assertEquals(usuario2.getTipoString(),"Candidato");
    }
    
    @Test
    
    // Prueba que se borren correctamente usuarios
    
    public void TestBorrarUsuario(){
        base.borrarUsuario("pepram@gmail.com");
        Usuario usuario = base.getUsuario("pepram@gmail.com");
        assertTrue(usuario == null);
    }
    
    @Test
    
    // Prueba que se modifiquen correctamente usuarios
    
    public void TestModificarUsuario(){
        Usuario usuario1 = new Usuario("Arturito","Testeador","pepram@gmail.com","666666666","contrasenaTest",'c',"Curriculo Test Modificado");
        base.modificarUsuario(usuario1);
        Usuario usuario2 = base.getUsuario("pepram@gmail.com");
        assertEquals(usuario2.getNombre(),"Arturito");
        assertEquals(usuario2.getApellidos(),"Testeador");
        assertEquals(usuario2.getTelefono(),"666666666");
        assertEquals(usuario2.getCv(),"Curriculo Test Modificado");
    }
    
    @Test
    
    //Prueba que se extraiga correctamente la lista de usuarios
    
   public void TestGetListaUsuarios(){
        ArrayList<Usuario> lista = base.getListaUsuarios();
        Usuario usuario1 = lista.get(0);
        Usuario usuario2 = lista.get(1);
        assertEquals(usuario1.getEmail(),"jackjack@gmail.com");
        assertEquals(usuario2.getEmail(),"pepram@gmail.com");
    }
    
    @Test
    
    //Prueba que se extraiga correctamente una lista filtrada vacia
    
    public void TestFiltrarUsuarios1(){
        ArrayList<Usuario> lista = base.filtrarUsuarios("nadie","");
        assertTrue(lista.isEmpty());
    }
    
    @Test
    
    //Prueba que se extraiga correctamente una lista filtrada por apellidos
    
    public void TestFiltrarUsuarios2(){
        ArrayList<Usuario> lista = base.filtrarUsuarios("Ramirez","");
        Usuario usuario = lista.get(0);
        assertEquals(usuario.getEmail(),"pepram@gmail.com");
    }
    
    @Test
    
    //Prueba que se extraiga correctamente una lista filtrada por currículo
    
    public void TestFiltrarUsuarios3(){
        ArrayList<Usuario> lista = base.filtrarUsuarios("","Torero");
        Usuario usuario = lista.get(0);
        assertEquals(usuario.getEmail(),"pepram@gmail.com");
    }

}
