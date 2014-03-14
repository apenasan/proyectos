package Modelo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Antonio Pena Santiso
 */
public class Interesado implements Serializable {
    private String nombre;
    private String apellidos;
    private String email;
    private String estado;
    private int idOferta;
    private String puesto;
    private Date fecha;

    public Interesado() {
    }
      
    public Interesado(String nombre, String apellidos, String email, String estado,int idOferta, String puesto, Date fecha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.estado = estado;
        this.idOferta = idOferta;
        this.puesto = puesto;
        this.fecha = fecha;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public String getPuesto() {
        return puesto;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public String getStringFecha() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String strFecha = format.format(fecha);
        return strFecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
       
}
