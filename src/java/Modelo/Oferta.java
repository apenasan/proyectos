package Modelo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Antonio Pena Santiso
 */
public class Oferta implements Serializable {
    private int id;
    private String puesto;
    private String descripcion;
    private String pais;
    private String ubicacion;
    private Date fecha;
    private String estudios;
    private String experiencia;
    private String requisitos;
    private String tipo;
    private String sueldo;
    private String duracion;
    private String destinatarios;

    public Oferta() {
    }

    public Oferta(int id, String puesto, String descripcion, String pais, String ubicacion, Date fecha, String estudios, String experiencia, String requisitos, String tipo, String sueldo, String duracion, String destinatarios) {
        this.id = id;
        this.puesto = puesto;
        this.descripcion = descripcion;
        this.pais = pais;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.estudios = estudios;
        this.experiencia = experiencia;
        this.requisitos = requisitos;
        this.tipo = tipo;
        this.sueldo = sueldo;
        this.duracion = duracion;
        this.destinatarios = destinatarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }
    
    
    
    

}
