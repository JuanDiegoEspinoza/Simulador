/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;
import java.util.LinkedList;
/**
 *
 * @author JuanDiego
 */
public class Proceso {
    private int id;
    private int estado;
    private int memoria;
    private String codigo;
    private int tiempo;
    private LinkedList listaRecursos = new LinkedList();



    public Proceso(int id, int estado, int memoria, String codigo, int tiempo, LinkedList listaRecursos){
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
    public int getEstado(){
        return this.estado;
    }

    public void setEstado(int estado){
        //Estado == 1 -> Ready
        //Estado == 0 -> Block
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