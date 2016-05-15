/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;
import java.util.LinkedList;
import java.util.ArrayList;
import simulador.Proceso;
import Estructuras.Array;

/**
 *
 * @author JuanDiego
 */
public class HDD {
    private int uso;
    private ArrayList<Proceso> listaHDD;
    private Array lista;
    private int porcentaje;

    public HDD(int tamano){
        listaHDD=new ArrayList<Proceso>();
        this.uso=tamano;
        this.porcentaje=100;
    }

    public void setUso(int uso){
      this.uso += uso;
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
    
    //Devuelve la posicion de la lista donde se ingreso el proceso, para poder paginarlo
    public int agregarProceso(Proceso proceso){
        if (getUso()-proceso.getMemoria()>0){
            listaHDD.add(proceso);
            setUso(-proceso.getMemoria());
            System.out.println("Proceso ha sido paginado");
            return listaHDD.size()-1;
        }
        System.out.println("Proceso no ha sido paginado");
        return -1;
    }


    public void sacarProceso(int id, int estado){
        int largo = listaHDD.size();
        for(int i =0; i<largo;i++){
            Proceso proceso = listaHDD.get(i);
            if (proceso.getId()==id){
                listaHDD.remove(id);
                setUso(proceso.getMemoria());
                return;
            }
        }
        System.out.println("Proceso no encontrado...");
    }

    public void setLista(ArrayList<Proceso> lista){
        this.listaHDD = lista;
    }

    public Proceso sacarBloqueado(){
      int largoLista = listaHDD.size();
      for (int i=0;i<largoLista;i++){
        if (listaHDD.get(i).getEstado()==1){
          setUso(listaHDD.get(i).getMemoria());
          return listaHDD.get(i);
        }
      }
      return null;
    }

}