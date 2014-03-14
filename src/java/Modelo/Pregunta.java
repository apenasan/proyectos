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
public class Pregunta implements Serializable {
    private int id;
    private String enunciado;
    private String respuesta;
    private int idCuestionario;

    public Pregunta() {
    }
   
    public Pregunta(int id, String enunciado,int idCuestionario) {
        this.id = id;
        this.enunciado = enunciado;
        this.idCuestionario = idCuestionario;
    }

    public Pregunta(int id, String enunciado, String respuesta, int idCuestionario) {
        this.id = id;
        this.enunciado = enunciado;
        this.respuesta = respuesta;
        this.idCuestionario = idCuestionario;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }
       
        
}
