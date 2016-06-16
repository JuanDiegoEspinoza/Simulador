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
import infra.Inicio;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanDiego
 */
public class HDD {
    private int uso;
    public ArrayList<Proceso> listaHDD;
    private int porcentaje;

    public HDD(int tamano){
        listaHDD=new ArrayList<Proceso>();
        this.uso=tamano;
        this.porcentaje=100;
    }
    
    /*
    SETs y GETs de las variables en el constructor
    */

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
    
    /*
    Fin de SETs y GETs
    */

    //Devuelve la posicion de la lista donde se ingreso el proceso, para poder paginarlo
    public int agregarProceso(Proceso proceso){
        //Se valida que haya campo en la memoria
        if (getUso()-proceso.getMemoria()>=0){
            //Se disminuye la cantidad de memoria disponible
            setUso(-proceso.getMemoria());
            infra.Inicio.pantalla.append("\tProceso ha sido paginado PID: "+proceso.getId()+"\n\n\n");
            proceso.setPosicion(listaHDD.size()-1);
            Inicio.actualizaInterfaz();
            return 1;
        }
        Inicio.actualizaInterfaz();
        return -1;
    }

    //Metodo utilizado para eliminar procesos del HDD
    public void sacarProceso(int id){
        int largo = listaHDD.size();
        for(int i =0; i<largo;i++){
            Proceso proceso = listaHDD.get(i);
            if (proceso.getId()==id){
                listaHDD.remove(proceso);
                //Se reestablece la cantidad de memoria disponible
                setUso(proceso.getMemoria());
                return;
            }
        }
        System.out.println("\tProceso no encontrado...");
    }
}
