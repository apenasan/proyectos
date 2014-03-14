/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Antonio Pena Santiso
 */
public class Respuesta implements Serializable {
    private String email;
    private int idPregunta;
    private String contenido;

    public Respuesta() {
    }

    public Respuesta(String email, int idPregunta, String contenido) {
        this.email = email;
        this.idPregunta = idPregunta;
        this.contenido = contenido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
       
}
