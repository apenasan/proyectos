package Modelo;

import java.io.Serializable;

/**
 *
 * @author Antonio Pena Santiso
 */
public class Usuario implements Serializable {
    private String nombre;
    private String apellidos;
    private String contrasena;
    private char tipo;
    private String email;
    private String telefono;
    private String cv;

    public Usuario() {
    }
        
    public Usuario(String nombre, String apellidos, String email,String telefono, String contrasena, 
                    char tipo, String cv) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.email = email;
        this.telefono = telefono;
        this.cv = cv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
        
    public String getTipoString(){
        if (this.tipo=='a'){
            return("Administrador");
        } else if (this.tipo=='e'){
            return("Empleado");
        } else{
            return("Candidato");
        }
    }

    
    
    
   
}
