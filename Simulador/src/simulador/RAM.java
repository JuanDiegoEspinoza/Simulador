/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;
import simulador.Proceso;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.*;

/**
 *
 * @author JuanDiego
 */
public class RAM {

    private int tamano;
    private ArrayList<Proceso> listaProceso;
    private int porcentaje;
    private int uso;


    public RAM(int tamano){
        listaProceso = new ArrayList<Proceso>();
        this.uso=tamano;
        this.porcentaje=100;
    }

    
    public void setUso(int uso){
        this.uso +=uso;
    }
    
    public int getUso(){
        return this.uso;
    }

    public int getPorcentaje(){
        return this.porcentaje;
    }

    public void setPorcentaje(int porcentaje){
        this.porcentaje+=porcentaje;
    }

    public Proceso BuscarProceso(int id){
        int largoLista = this.listaProceso.size();
        for(int i=0;i<largoLista;i++){
            Proceso proceso = listaProceso.get(i);
            if(proceso.getId()==id){
                return proceso;
            }
        }
        System.out.println("Proceso no encontrado...");
        return null;
    }

    public int agregarProceso(Proceso proceso){
        if (getUso() - proceso.getMemoria()>0){
            listaProceso.add(proceso);
            setUso(-proceso.getMemoria());
            infra.Inicio.pantalla.append(">>\tEl proceso ha sido agregado a RAM PID: "+proceso.getId()+"\n");
            System.out.println("El proceso ha sido agregado a RAM...PID: "+proceso.getId()+"\n");
            return 1;
        }
        else{
            infra.Inicio.pantalla.append("El proceso no ha sido agregado a RAM...PID: "+proceso.getId()+"\n");
          System.out.println("El proceso no sido agregado a RAM...PID: "+proceso.getId()+"\n");
          return -1;
        }

    }
    
    public Proceso verificarBloqueados(){
        for (int i=0;i<listaProceso.size();i++){
            if(listaProceso.get(i).getEstado()==0){
                sacarProceso(listaProceso.get(i).getId());
                return listaProceso.get(i);
            } 
            return null; //-1 indica que no hay bloqueados
        }
         return null;
    }

    public Proceso sacarProceso(int id){
        int largo = listaProceso.size();
        for(int i =0; i<largo;i++){
            Proceso proceso = listaProceso.get(i);
            if (proceso.getId()==id){
                listaProceso.remove(id);
                setUso(proceso.getMemoria());
                System.out.println("YEAH");
                return null;
            }
        }
        System.out.println("Proceso no encontrado...");
        return null;
    }

    public ArrayList<Proceso> getLista(){
        return listaProceso;
    }

    public void setLista(ArrayList<Proceso> lista){
        this.listaProceso = lista;
    }

    public Proceso sacarBloqueado(){
        int largoLista = listaProceso.size();
        for (int i=0;i<largoLista;i++){
            Proceso qwerty = listaProceso.get(i);
            if (qwerty.getEstado()==0){
                listaProceso.remove(qwerty);
                setUso(qwerty.getMemoria());
                return qwerty;
            }
        }
        return null;
    }

}