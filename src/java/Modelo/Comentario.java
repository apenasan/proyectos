/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Antonio Pena Santiso
 */
public class Comentario implements Serializable{
    private int id;
    private String usuario;
    private String texto;
    private String autor;
    private Timestamp fecha;

    public Comentario() {
    }

    public Comentario(int id, String usuario, String texto, String autor, Timestamp fecha) {
        this.id = id;
        this.usuario = usuario;
        this.texto = texto;
        this.autor = autor;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    public String getStringFecha() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss 'del' dd/MM/yyyy");
        String strFecha = format.format(fecha);
        return strFecha;
    }
        
}
