/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Comentario;
import Modelo.Cuestionario;
import Modelo.Interesado;
import Modelo.Oferta;
import Modelo.Pregunta;
import Modelo.Respuesta;
import Modelo.Usuario;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Antonio Pena Santiso
 */
public class BaseDeDatos {
    private static final BaseDeDatos instancia = new BaseDeDatos();
    private DataSource ds;
    private String nombreBase;
    private static final Logger logger = Logger.getLogger(BaseDeDatos.class.getCanonicalName());
    
    private BaseDeDatos() {
    }
    
    public static BaseDeDatos getInstancia() {
        return instancia;
    }
    
    public boolean inicializar(String direccionBase, String nombreBase){
        try {
            this.nombreBase = nombreBase;
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            ds = (DataSource) env.lookup(direccionBase);
            if(ds == null){
                logger.log(Level.SEVERE, "No se ha encontrado un DataSource");
                return false;
            } else{
                return true;
            }
        } catch (NamingException ex) {
            logger.log(Level.SEVERE, "No se ha podido abrir la conexión con la base de datos", ex);
            return false;
        }
    }
        
    public void setDs(DataSource ds){
        this.ds = ds;
    }
    
    public void setNombreBase(String nombreBase){
        this.nombreBase = nombreBase;
    }
    
    
    public boolean addUsuario(Usuario usuario){
            Connection conexion = null;
            PreparedStatement insert = null;
            Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                insert = conexion.prepareStatement("INSERT INTO " + nombreBase + ".Usuarios VALUES (?,?,?,?,?,?,?,?,?)");
                insert.setString(1, usuario.getNombre());
                insert.setString(2, usuario.getApellidos());
                insert.setString(3, usuario.getEmail());
                insert.setString(4, usuario.getTelefono());
                insert.setString(5, usuario.getContrasena());
                insert.setString(6, String.valueOf(usuario.getTipo()));
                insert.setObject(7, usuario.getCv());
                insert.setObject(8, null);
                insert.setObject(9, null);
                

                int filasAfectadas = insert.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido insertar el usuario", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(insert);
        }
        return exito;
    }
    
    
       
    public Usuario getUsuario(String email){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Usuario usuario = null;        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Usuarios WHERE Email=?");
            select.setString(1,email);
            resultado = select.executeQuery();
            while(resultado.next()){
                usuario = new Usuario(resultado.getString("Nombre"),resultado.getString("Apellidos"), resultado.getString("Email"),
                        resultado.getString("Telefono"), resultado.getString("Contraseña"),resultado.getString("Tipo").charAt(0), resultado.getString("CV"));
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el usuario", ex);
            usuario=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return usuario;
    }
    
    public boolean borrarUsuario(String email){
        Connection conexion = null;
        PreparedStatement delete = null;
        boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            delete = conexion.prepareStatement("DELETE FROM " + nombreBase + ".Usuarios WHERE Email=?");
            delete.setString(1,email);
            if((delete.executeUpdate()) == 1){
                exito = true;
            }
        } catch (SQLException ex) {    
            logger.log(Level.SEVERE, "No se ha podido eliminar el usuario", ex);
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(delete);
        }
        return exito;
    }
    
    public boolean modificarUsuario(Usuario usuario){
        Connection conexion = null;
        PreparedStatement update = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                update = conexion.prepareStatement("UPDATE " + nombreBase + ".Usuarios SET Nombre=?, Apellidos=?, Telefono=?, Cv=?, Tipo=? WHERE Email=?");
                update.setString(1, usuario.getNombre());
                update.setString(2, usuario.getApellidos());
                update.setString(3, usuario.getTelefono());
                update.setString(4, usuario.getCv());
                update.setString(5, String.valueOf(usuario.getTipo()));                
                update.setString(6, usuario.getEmail());
                
                int filasAfectadas = update.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido modificar el usuario", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(update);
        }
        return exito;
    }
    
    public Boolean cambiarContrasenaUsuario(Usuario usuario){
        Connection conexion = null;
        PreparedStatement update = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                update = conexion.prepareStatement("UPDATE " + nombreBase + ".Usuarios SET Contraseña=? WHERE Email=?");
                update.setString(1, usuario.getContrasena());
                update.setString(2, usuario.getEmail());
                
                int filasAfectadas = update.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido modificar la contraseña del usuario", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(update);
        }
        return exito;
    }
    
    public Boolean setFotoUsuario(String usuario, InputStream stream){
        Connection conexion = null;
        PreparedStatement update = null;
        Boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            update = conexion.prepareStatement("UPDATE " + nombreBase + ".Usuarios SET Foto=? WHERE Email=?");
            update.setBinaryStream(1,stream);
            update.setString(2,usuario);
            update.executeUpdate();
            exito = true;
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "No se ha podido insertar la foto del usuario", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(update);
        }
        return exito;
    }
    
    public InputStream getFotoUsuario(String email){
        Connection conexion = null;
        PreparedStatement select = null;
        InputStream stream = null;
        ResultSet resultado = null;
            
        try {    
            conexion = ds.getConnection();
            select = conexion.prepareStatement("SELECT Foto FROM " + nombreBase + ".Usuarios WHERE Email=?");
            select.setString(1, email);
            resultado = select.executeQuery();
            while(resultado.next()){
                stream = resultado.getBinaryStream(1);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la foto del usuario", ex);
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return stream;
    }
    
    public Boolean setCurriculoUsuario(String usuario, InputStream stream){
        Connection conexion = null;
        PreparedStatement update = null;
        Boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            update = conexion.prepareStatement("UPDATE " + nombreBase + ".Usuarios SET CVFichero=? WHERE Email=?");
            update.setBinaryStream(1,stream);
            update.setString(2,usuario);
            update.executeUpdate();
            exito = true;
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "No se ha podido insertar el curriculo del usuario", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(update);
        }
        return exito;
    }
    
        
    public InputStream getCurriculoUsuario(String email){
        Connection conexion = null;
        PreparedStatement select = null;
        InputStream stream = null;
        ResultSet resultado = null;
            
        try {    
            conexion = ds.getConnection();
            select = conexion.prepareStatement("SELECT CVFichero FROM " + nombreBase + ".Usuarios WHERE Email=?");
            select.setString(1, email);
            resultado = select.executeQuery();
            while(resultado.next()){
                stream = resultado.getBinaryStream(1);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el curriculo del usuario", ex);
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return stream;
    }
    
    
    public ArrayList<Usuario> getListaUsuarios(){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Usuario usuario = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try{
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Usuarios WHERE NOT Tipo='a'");
            resultado = select.executeQuery();
            while(resultado.next()){
                usuario = new Usuario(resultado.getString("Nombre"),resultado.getString("Apellidos"), resultado.getString("Email"), resultado.getString("Telefono"),
                        resultado.getString("Contraseña"),resultado.getString("Tipo").charAt(0), resultado.getString("CV"));
                listaUsuarios.add(usuario);
            }
         } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la lista de usuarios", ex);
            listaUsuarios=null;
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaUsuarios;
    }
    
    public ArrayList<Usuario> filtrarUsuarios(String apellidos,String clave){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        String consulta ="";
        Usuario usuario = null;
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try{
            conexion =ds.getConnection();
            consulta = consulta.concat(" WHERE Apellidos LIKE '%" + apellidos + "%' AND Cv LIKE '%" + clave + "%' AND NOT Tipo='a'");
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Usuarios" + consulta);
            resultado = select.executeQuery();
            while(resultado.next()){
                usuario = new Usuario(resultado.getString("Nombre"),resultado.getString("Apellidos"), resultado.getString("Email"), resultado.getString("Telefono"),
                        resultado.getString("Contraseña"),resultado.getString("Tipo").charAt(0), resultado.getString("CV"));
                listaUsuarios.add(usuario);
            }
            
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "No se ha podido obtener la lista filtrada de usuarios", ex);
            listaUsuarios=null;
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaUsuarios;
    }
    
    
    
    public boolean algunAdmin(){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Boolean exito = false;
        try {      
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT COUNT(*) AS count FROM " + nombreBase + ".Usuarios WHERE Tipo = 'a'");
            resultado = select.executeQuery();
            resultado.next();
            if((resultado.getInt("count")) > 0){
              exito = true;
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido completar búsqueda de administradores", ex);
        }    
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return exito;
    }
    
    
    public boolean addOferta(Oferta oferta){
            Connection conexion = null;
            PreparedStatement insert = null;
            Boolean exito = false;
        try {   
                conexion = ds.getConnection();
                insert = conexion.prepareStatement("INSERT INTO " + nombreBase +
                        ".Ofertas(Puesto,Descripcion,Pais,Ubicacion,Fecha, Estudios,Experiencia,Requisitos,Tipo_Contrato,Sueldo,Duracion,Destinatarios) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                insert.setString(1, oferta.getPuesto());
                insert.setString(2, oferta.getDescripcion());
                insert.setString(3, oferta.getPais());
                insert.setString(4, oferta.getUbicacion());
                insert.setObject(5, oferta.getFecha());
                insert.setString(6, oferta.getEstudios());
                insert.setString(7, oferta.getExperiencia());
                insert.setString(8, oferta.getRequisitos());
                insert.setString(9, oferta.getTipo());
                insert.setString(10, oferta.getSueldo());
                insert.setString(11, oferta.getDuracion());
                insert.setString(12, oferta.getDestinatarios());

                int filasAfectadas = insert.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido insertar la oferta", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(insert);
        }
        return exito;
    }
    
    public Oferta getOferta(int id){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Oferta oferta = null;        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Ofertas WHERE Id=?");
            select.setInt(1,id);
            resultado = select.executeQuery();
            while(resultado.next()){
                oferta = new Oferta(resultado.getInt("Id"),resultado.getString("Puesto"),resultado.getString("Descripcion"),resultado.getString("Pais"),resultado.getString("Ubicacion"),
                        resultado.getDate("Fecha"),resultado.getString("Estudios"),resultado.getString("Experiencia"),resultado.getString("Requisitos"),resultado.getString("Tipo_Contrato"),
                        resultado.getString("Sueldo"),resultado.getString("Duracion"),resultado.getString("Destinatarios"));
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la oferta", ex);
            oferta=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return oferta;
    }
    
    public boolean modificarOferta(Oferta oferta){
        Connection conexion = null;
        PreparedStatement update = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                update = conexion.prepareStatement("UPDATE " + nombreBase + ".Ofertas SET Puesto=?, Descripcion=?, Tipo_Contrato=?, Pais=?, Ubicacion=?, Estudios=?, Requisitos=?, Experiencia=?, Sueldo=?, Duracion=?, Destinatarios=? WHERE Id=?");
                update.setString(1, oferta.getPuesto());
                update.setString(2, oferta.getDescripcion());
                update.setString(3, oferta.getTipo());
                update.setString(4, oferta.getPais());
                update.setString(5, oferta.getUbicacion());
                update.setString(6, oferta.getEstudios());                
                update.setString(7, oferta.getRequisitos());
                update.setString(8, oferta.getExperiencia());
                update.setString(9, oferta.getSueldo());
                update.setString(10, oferta.getDuracion());
                update.setString(11, oferta.getDestinatarios());
                update.setInt(12,oferta.getId());
                
                int filasAfectadas = update.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido modificar la oferta", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(update);
        }
        return exito;
    }
    
    public boolean borrarOferta(int id){
        Connection conexion = null;
        PreparedStatement delete = null;
        boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            delete = conexion.prepareStatement("DELETE FROM " + nombreBase + ".Ofertas WHERE Id=?");
            delete.setInt(1,id);
            if((delete.executeUpdate()) == 1){
                exito = true;
            }
        } catch (SQLException ex) {    
            logger.log(Level.SEVERE, "No se ha podido eliminar la oferta", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(delete);
        }
        return exito;
    }
    
    public ArrayList<Oferta> getListaOfertas(){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Oferta oferta = null;
        ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Ofertas ORDER BY Fecha DESC");
            resultado = select.executeQuery();
            while(resultado.next()){
                oferta = new Oferta(resultado.getInt("Id"),resultado.getString("Puesto"),resultado.getString("Descripcion"),resultado.getString("Pais"),resultado.getString("Ubicacion"),
                        resultado.getDate("Fecha"),resultado.getString("Estudios"),resultado.getString("Experiencia"),resultado.getString("Requisitos"),resultado.getString("Tipo_Contrato"),
                        resultado.getString("Sueldo"),resultado.getString("Duracion"),resultado.getString("Destinatarios"));
                listaOfertas.add(oferta);
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la lista de ofertas", ex);
            listaOfertas=null;
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaOfertas;
    }
    
    public ArrayList<Oferta> filtrarOfertas(String puesto,String clave,String pais){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        String consulta ="";
        Oferta oferta = null;
        ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
        try{
            conexion =ds.getConnection();
            consulta = consulta.concat(" WHERE ");
            consulta = consulta.concat("Puesto LIKE '%" + puesto + "%' AND Descripcion LIKE '%" + clave + "%' AND Pais LIKE '%" + pais + "%'");
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Ofertas " + consulta + " ORDER BY Fecha DESC");
            resultado = select.executeQuery();
            while(resultado.next()){
                oferta = new Oferta(resultado.getInt("Id"),resultado.getString("Puesto"),resultado.getString("Descripcion"),resultado.getString("Pais"),resultado.getString("Ubicacion"),
                        resultado.getDate("Fecha"),resultado.getString("Estudios"),resultado.getString("Experiencia"),resultado.getString("Requisitos"),resultado.getString("Tipo_Contrato"),
                        resultado.getString("Sueldo"),resultado.getString("Duracion"),resultado.getString("Destinatarios"));
                listaOfertas.add(oferta);
            }
            
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "No se ha podido obtener la lista filtrada de ofertas", ex);
            listaOfertas=null;
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaOfertas;
    }
    
    public boolean estaInteresado(String usuario, int oferta){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Boolean exito = false;
        try{
            conexion = ds.getConnection();
            select = conexion.prepareStatement("SELECT COUNT(*) AS count FROM " + nombreBase + ".Interesados WHERE Oferta=? AND Email=?");
            select.setInt(1,oferta);
            select.setString(2,usuario);
            resultado = select.executeQuery();
            resultado.next();
            if((resultado.getInt("count")) == 1){
                exito = true;
            }
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido comprobar la condición de interesado del usuario", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return exito;
    }
    
    public ArrayList listaMisIntereses(String usuario){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        String email = usuario;
        Interesado interes = null;
        ArrayList<Interesado> listaIntereses = new ArrayList<Interesado>();
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Interesados WHERE Email=? ORDER BY Fecha DESC");
            select.setString(1,email);
            resultado = select.executeQuery();
            while(resultado.next()){
                interes = new Interesado(resultado.getString("Nombre"),resultado.getString("Apellidos"),email,resultado.getString("Estado"),resultado.getInt("Oferta"),resultado.getString("Puesto"),resultado.getDate("Fecha"));
                listaIntereses.add(interes);
            }
            
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la lista de ofertas del interesado", ex);
            listaIntereses=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaIntereses;
    }
    
    public ArrayList listaOfertasInteresado(String usuario){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        String email = usuario;
        Oferta oferta = null;
        ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Ofertas WHERE Id IN (SELECT Oferta FROM " + nombreBase + ".Interesados WHERE Email=?) ORDER BY Fecha DESC");
            select.setString(1,email);
            resultado = select.executeQuery();
            while(resultado.next()){
                oferta = new Oferta(resultado.getInt("Id"),resultado.getString("Puesto"),resultado.getString("Descripcion"),resultado.getString("Pais"),resultado.getString("Ubicacion"),
                        resultado.getDate("Fecha"),resultado.getString("Estudios"),resultado.getString("Experiencia"),resultado.getString("Requisitos"),resultado.getString("Tipo_Contrato"),
                        resultado.getString("Sueldo"),resultado.getString("Duracion"),resultado.getString("Destinatarios"));
                listaOfertas.add(oferta);
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la lista de ofertas del interesado", ex);
            listaOfertas=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaOfertas;
    }
            
    public ArrayList listaInteresados(int oferta){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;        
        Interesado interesado = null;
        ArrayList<Interesado> listaInteresados = new ArrayList<Interesado>();
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Interesados WHERE Oferta=?");
            select.setInt(1,oferta);
            resultado = select.executeQuery();
            while(resultado.next()){
                interesado = new Interesado(resultado.getString("Nombre"),resultado.getString("Apellidos"), resultado.getString("Email"), resultado.getString("Estado"),
                        resultado.getInt("Oferta"),resultado.getString("Puesto"),resultado.getDate("Fecha"));
                listaInteresados.add(interesado);
            }
         } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la lista de interesados", ex);
            listaInteresados=null;
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaInteresados;
    }
    
    public int contarInteresados(int oferta){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        int numeroInteresados = 0;
        try{
            conexion = ds.getConnection();
            select = conexion.prepareStatement("SELECT COUNT(*) AS count FROM " + nombreBase + ".Interesados WHERE Oferta=?");
            select.setInt(1,oferta);
            resultado = select.executeQuery();
            resultado.next();
            numeroInteresados = resultado.getInt("count");
        }
        catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el número de interesados en la oferta", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return numeroInteresados;
    }
    
    public boolean addInteresado(Usuario usuario,Oferta oferta){
        Connection conexion = null;
        PreparedStatement insert = null;
        Boolean exito = false;
        try {   
                conexion = ds.getConnection();
                insert = conexion.prepareStatement("INSERT INTO " + nombreBase +
                        ".Interesados(Oferta,Email,Nombre,Apellidos,Puesto,Fecha) VALUES (?,?,?,?,?,CURDATE())");
                insert.setInt(1, oferta.getId());
                insert.setString(2, usuario.getEmail());
                insert.setString(3, usuario.getNombre());                
                insert.setString(4, usuario.getApellidos());
                insert.setString(5, oferta.getPuesto());
                int filasAfectadas = insert.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido insertar el interesado", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(insert);
        }
        return exito;
    }
    
    public boolean borrarInteresesUsuario(String email){
        Connection conexion = null;
        PreparedStatement delete = null;
        boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            delete = conexion.prepareStatement("DELETE FROM " + nombreBase + ".Interesados WHERE Email=?");
            delete.setString(1,email);
            if((delete.executeUpdate()) == 1){
                exito = true;
            }
        } catch (SQLException ex) {    
            logger.log(Level.SEVERE, "No se ha podido eliminar el usuario de las listas de interesados", ex);
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(delete);
        }
        return exito;
    }
    
    public boolean borrarInteresesOferta(int id){
        Connection conexion = null;
        PreparedStatement delete = null;
        boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            delete = conexion.prepareStatement("DELETE FROM " + nombreBase + ".Interesados WHERE Oferta=?");
            delete.setInt(1,id);
            if((delete.executeUpdate()) == 1){
                exito = true;
            }
        } catch (SQLException ex) {    
            logger.log(Level.SEVERE, "No se ha podido eliminar la oferta de las listas de interesados", ex);
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(delete);
        }
        return exito;
    }
    
    public boolean cambiarEstadoInteresado(String usuario,int oferta,String estado){
        Connection conexion = null;
        PreparedStatement update = null;
        boolean exito = false;
        try {
            conexion = ds.getConnection();
            update = conexion.prepareStatement("UPDATE " + nombreBase + ".Interesados SET Estado=? WHERE EMAIL=? AND OFERTA=?");
            update.setString(1,estado);
            update.setString(2,usuario);
            update.setInt(3,oferta);
            update.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido cambiar el estado del interesado", ex);
        } finally{
            cerrarConexion(conexion);
            cerrarStatement(update);
        }
        return exito;
    }
    
    public boolean borrarInteresado(String usuario,int oferta){
        Connection conexion = null;
        PreparedStatement delete = null;
        boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            delete = conexion.prepareStatement("DELETE FROM " + nombreBase + ".Interesados WHERE Oferta=? AND Email=?");
            delete.setInt(1,oferta);
            delete.setString(2,usuario);
            if((delete.executeUpdate()) == 1){
                exito = true;
            }
        } catch (SQLException ex) {    
            logger.log(Level.SEVERE, "No se ha podido eliminar el interesado", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(delete);
        }
        return exito;
    }
    
    public boolean addCuestionario(Cuestionario cuestionario){
        Connection conexion = null;
        PreparedStatement insert = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                insert = conexion.prepareStatement("INSERT INTO " + nombreBase + ".Cuestionarios(Nombre,Oferta) VALUES (?,?)");
                insert.setString(1, cuestionario.getNombre());
                insert.setInt(2, cuestionario.getIdOferta());

                int filasAfectadas = insert.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido insertar el cuestionario", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(insert);
        }
        return exito;
    }
    
    public Cuestionario getCuestionario(int id){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Cuestionario cuestionario = null;        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Cuestionarios WHERE Id=?");
            select.setInt(1,id);
            resultado = select.executeQuery();
            while(resultado.next()){
                cuestionario = new Cuestionario(resultado.getInt("ID"),resultado.getString("Nombre"),resultado.getInt("Oferta"));
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el cuestionario", ex);
            cuestionario=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return cuestionario;
    }
    
    public Cuestionario getCuestionarioPorOferta(int idOferta){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Cuestionario cuestionario = null;        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Cuestionarios WHERE Oferta=?");
            select.setInt(1,idOferta);
            resultado = select.executeQuery();
            while(resultado.next()){
                cuestionario = new Cuestionario(resultado.getInt("ID"),resultado.getString("Nombre"),idOferta);
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el cuestionario", ex);
            cuestionario=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return cuestionario;
              
    }
    
    public boolean addPregunta(Pregunta pregunta){
        Connection conexion = null;
        PreparedStatement insert = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                insert = conexion.prepareStatement("INSERT INTO " + nombreBase + ".Preguntas(Enunciado,Cuestionario) VALUES (?,?)");
                insert.setString(1, pregunta.getEnunciado());
                insert.setInt(2,pregunta.getIdCuestionario());

                int filasAfectadas = insert.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido insertar la pregunta", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(insert);
        }
        return exito;
    }
    
    public Pregunta getPregunta(int id){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Pregunta pregunta = null;        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Preguntas WHERE Id=?");
            select.setInt(1,id);
            resultado = select.executeQuery();
            while(resultado.next()){
                pregunta = new Pregunta(resultado.getInt("ID"),resultado.getString("Enunciado"),resultado.getInt("Cuestionario"));
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la pregunta", ex);
            pregunta=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return pregunta;
    }
    
    public ArrayList<Pregunta> getPreguntasPorCuestionario(int idCuestionario){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Preguntas WHERE Cuestionario=? ORDER BY ID");
            select.setInt(1,idCuestionario);
            resultado = select.executeQuery();
            while(resultado.next()){
                listaPreguntas.add(new Pregunta(resultado.getInt("ID"),resultado.getString("Enunciado"),resultado.getInt("Cuestionario")));
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la lista de preguntas", ex);
            listaPreguntas=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaPreguntas;
              
    }
    
    public boolean modificarPregunta(Pregunta pregunta){
        Connection conexion = null;
        PreparedStatement update = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                update = conexion.prepareStatement("UPDATE " + nombreBase + ".Preguntas SET Enunciado=? WHERE Id=?");
                update.setString(1, pregunta.getEnunciado());
                update.setInt(2,pregunta.getId());
                
                int filasAfectadas = update.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido modificar la pregunta", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(update);
        }
        return exito;
    }
    
    public boolean borrarPregunta(int id){
        Connection conexion = null;
        PreparedStatement delete = null;
        boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            delete = conexion.prepareStatement("DELETE FROM " + nombreBase + ".Preguntas WHERE Id=?");
            delete.setInt(1,id);
            if((delete.executeUpdate()) == 1){
                exito = true;
            }
        } catch (SQLException ex) {    
            logger.log(Level.SEVERE, "No se ha podido eliminar la pregunta", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(delete);
        }
        return exito;
    }
    
    public boolean addRespuesta(Respuesta respuesta){
        Connection conexion = null;
        PreparedStatement insert = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                insert = conexion.prepareStatement("INSERT INTO " + nombreBase + ".Respuestas(Usuario,Pregunta,Contenido) VALUES (?,?,?)");
                insert.setString(1, respuesta.getEmail());
                insert.setInt(2, respuesta.getIdPregunta());
                insert.setString(3, respuesta.getContenido());

                int filasAfectadas = insert.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido insertar la respuesta", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(insert);
        }
        return exito;
    }
   
    public Respuesta getRespuesta(String email, int idPregunta){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Respuesta respuesta = null;        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Respuestas WHERE Usuario=? AND Pregunta=?");
            select.setString(1,email);
            select.setInt(2, idPregunta);
            resultado = select.executeQuery();
            while(resultado.next()){
                respuesta = new Respuesta(resultado.getString("Usuario"),resultado.getInt("Pregunta"),resultado.getString("Contenido"));
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la respuesta", ex);
            respuesta=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return respuesta;
    }
    
    public boolean borrarRespuesta(String email, int idPregunta){
        Connection conexion = null;
        PreparedStatement delete = null;
        boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            delete = conexion.prepareStatement("DELETE FROM " + nombreBase + ".Respuestas WHERE Usuario=? AND Pregunta=?");
            delete.setString(1,email);
            delete.setInt(2,idPregunta);
            if((delete.executeUpdate()) == 1){
                exito = true;
            }
        } catch (SQLException ex) {    
            logger.log(Level.SEVERE, "No se ha podido eliminar la respuesta", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(delete);
        }
        return exito;
    }
    
    public boolean addComentario(Comentario comentario){
        Connection conexion = null;
        PreparedStatement insert = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                insert = conexion.prepareStatement("INSERT INTO " + nombreBase + ".Comentarios(Usuario,Autor,Texto,Fecha) VALUES (?,?,?,?)");
                insert.setString(1, comentario.getUsuario());
                insert.setString(2, comentario.getAutor());
                insert.setString(3, comentario.getTexto());
                insert.setObject(4,comentario.getFecha());

                int filasAfectadas = insert.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido insertar el comentario", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(insert);
        }
        return exito;
    }
    
    public Comentario getComentario(int id){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        Comentario comentario = null;        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Comentarios WHERE Id=?");
            select.setInt(1,id);
            resultado = select.executeQuery();
            while(resultado.next()){
                comentario = new Comentario(resultado.getInt("ID"),resultado.getString("Usuario"),resultado.getString("Texto"),resultado.getString("Autor"),resultado.getTimestamp("Fecha"));
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener el comentario", ex);
            comentario=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return comentario;
    }
    
    public boolean modificarComentario(Comentario comentario){
        Connection conexion = null;
        PreparedStatement update = null;
        Boolean exito = false;
             
        try {   
                conexion = ds.getConnection();
                update = conexion.prepareStatement("UPDATE " + nombreBase + ".Comentarios SET Texto=? WHERE Id=?");
                update.setString(1, comentario.getTexto());
                update.setInt(2,comentario.getId());
                
                int filasAfectadas = update.executeUpdate();
                if (filasAfectadas == 1) {
                    exito = true;
                }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido modificar el comentario", ex);
        }
         finally {
            cerrarConexion(conexion);
            cerrarStatement(update);
        }
        return exito;
    }
    
    public boolean borrarComentario(int idComentario){
        Connection conexion = null;
        PreparedStatement delete = null;
        boolean exito = false;
        
        try{
            conexion = ds.getConnection();
            delete = conexion.prepareStatement("DELETE FROM " + nombreBase + ".Comentarios WHERE ID=?");
            delete.setInt(1,idComentario);
            if((delete.executeUpdate()) == 1){
                exito = true;
            }
        } catch (SQLException ex) {    
            logger.log(Level.SEVERE, "No se ha podido eliminar el comentario", ex);
        } finally {
            cerrarConexion(conexion);
            cerrarStatement(delete);
        }
        return exito;
    }
    
    public ArrayList<Comentario> getComentariosPorUsuario(String email){
        Connection conexion = null;
        PreparedStatement select = null;
        ResultSet resultado = null;
        ArrayList<Comentario> listaComentarios = new ArrayList<Comentario>();        
        try {      
            
            conexion = ds.getConnection();
            
            select = conexion.prepareStatement("SELECT * FROM " + nombreBase + ".Comentarios WHERE Usuario=? ORDER BY Fecha DESC");
            select.setString(1,email);
            resultado = select.executeQuery();
            while(resultado.next()){
                listaComentarios.add(new Comentario(resultado.getInt("ID"),resultado.getString("Usuario"),resultado.getString("Texto"),resultado.getString("Autor"),resultado.getTimestamp("Fecha")));
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido obtener la lista de comentarios", ex);
            listaComentarios=null;
        }
        finally{
            cerrarConexion(conexion);
            cerrarStatement(select);
            cerrarResultSet(resultado);
        }
        return listaComentarios;
    }
    
    public boolean cerrarConexion(Connection conexion){
        boolean exito = false;
        if(conexion != null){
            try {
                conexion.close();
                exito = true;
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "No se ha podido cerrar la conexión con la base de datos", ex);
            }
        }
        return exito;
    }
    
    public boolean cerrarStatement(PreparedStatement statement){
        boolean exito = false;
        if(statement != null){
            try {
                statement.close();
                exito = true;
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "No se ha podido cerrar la declaración", ex);
            }
        }
        return exito;
    }
    
    public boolean cerrarResultSet(ResultSet resultSet){
        boolean exito = false;
        if(resultSet != null){
            try {
                resultSet.close();
                exito = true;
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "No se ha podido cerrar un resultado", ex);
            }
        }
        return exito;
    }
}
