/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra;

import java.util.*;

/**
 *
 * @author felipe
 */
public class Proceso {
    int id;
    String estado;
    int memoria;
    String codigo;
    int tiempo;
    LinkedList listaRecursos = new LinkedList();
    
    
    
    public Proceso(int id, String estado, int memoria, String codigo, int tiempo, LinkedList listaRecursos){
        this.id= id;
        this.estado= estado;
        this.memoria=memoria;
        this.codigo= codigo;
        this.tiempo= tiempo;
        this.listaRecursos= listaRecursos;
    }
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getEstado(){
        return this.estado;
    }
    public void setEstado(String estado){
        this.estado= estado;
    }
    public int getMemoria(){
        return this.memoria;
    }
    public void setMemoria(int memoria){
        this.memoria= memoria;
    }
    public String getCodigo(){
        return this.codigo;
    }
    public void setCodigo(String codigo){
        this.codigo= codigo;
    }
    public int getTiempo(){
        return this.tiempo;
    }
    public void setTiempo(int tiempo){
        this.tiempo= tiempo;
    }
}
