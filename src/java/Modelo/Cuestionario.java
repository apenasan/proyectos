/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Antonio Pena Santiso
 */
public class Cuestionario implements Serializable {
    private int id;
    private String nombre;
    private ArrayList<Pregunta> listaPreguntas;
    private int idOferta;

    public Cuestionario() {
    }

    public Cuestionario(int id, String nombre,int idOferta) {
        this.id = id;
        this.nombre = nombre;
        this.listaPreguntas = new ArrayList<Pregunta>();
        this.idOferta = idOferta;
    }
   
    public Cuestionario(int id, String nombre,int idOferta, ArrayList<Pregunta> listaPreguntas) {
        this.id = id;
        this.nombre = nombre;
        this.idOferta = idOferta;
        this.listaPreguntas = listaPreguntas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    public void setListaPreguntas(ArrayList<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }
    
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
    
    
    
}
