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
public abstract class Proceso {
    private int id;
    private int estado;
    private int memoria;
    private int codigo;
    private int tiempo;
    private LinkedList listaRecursos = new LinkedList();
    private int ja =0;
    private int context;
    private int cantidadEjecuciones;
    private int posicion;


    public Proceso(int id, int estado, int memoria, int tiempo, int context, int cantidadEjecuciones){
        this.id= id;
        this.estado= estado;
        this.memoria=memoria;
        this.tiempo= tiempo;
        this.context= context;

        this.listaRecursos= listaRecursos;
    }

    public int getId(){
        return this.id;
    }

    public void setPosicion(int pos){
      this.posicion = pos;
    }

    public int getPosicion(){
      return this.posicion;
    }

    public void setContext(int context){
        this.context = context;
    }

    public void reducirEjecucion(){
        this.cantidadEjecuciones = this.cantidadEjecuciones--;
    }

    public int getContext(){
        return this.context;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getCantidadEjecuciones(){
	return this.cantidadEjecuciones;
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

    public int getCodigo(){
        return this.codigo;
    }

    public void setCodigo(int codigo){
        this.codigo= codigo;
    }
    public int getTiempo(){
        return this.tiempo;
    }
    public void setTiempo(int tiempo){
        this.tiempo= tiempo;
    }

    //se implementa en cada proceso
    public abstract void execute();
}
