/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;
import infra.Inicio;
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

    public ArrayList<Proceso> listaProceso;
    private int uso;
    
    public RAM(int tamano){
        listaProceso = new ArrayList<Proceso>();
        this.uso=tamano;
    }
    
    public void setUso(int uso){
        this.uso +=uso;
    }

    public int getUso(){
        return this.uso;
    }
    
    
    /*Metodo utilizado para ingresar procesos a la memoria RAM
    Retorno: 1  ---> Se completo exitosamente
    Retorno: 0  --->
    Retorno: -1 ---> 
    */
    
    public int agregarProceso(Proceso proceso){
        /*
        Se valida que exista memoria disponible para agregar procesos a RAM
        y que no hayan procesos en HDD que puedan sufrir Starvation
        */
        if ((getUso() - proceso.getMemoria()>=0) && (Inicio.cpu.hdd.listaHDD.isEmpty())){
            proceso.setEstado(1);
            //Se agrega el proceso a la lista en RAM
            listaProceso.add(proceso);
            //Se disminuye la cantidad de memoria disponible
            setUso(-proceso.getMemoria());
            infra.Inicio.pantalla.append("\t\tEl proceso ha sido agregado a RAM PID: "+proceso.getId()+"\n\n\n");
            Inicio.actualizaInterfaz();
            
            return 1;
        }
        //En caso de que la lista de RAM no se encuentre vacia
        else if (getUso() - proceso.getMemoria()>=0 && Inicio.cpu.hdd.listaHDD.isEmpty()==false){
            //Se obtiene el primer proceso en HDD
            Proceso enHDD = Inicio.cpu.hdd.listaHDD.get(0);
            //Se valida que exista espacio en RAM para el proceso
            if (getUso()-enHDD.getMemoria() >=0){
                enHDD.setEstado(1);
                listaProceso.add(enHDD);
                //Elimino de la lista de HDD el proceso que se saco anteriormente
                Inicio.cpu.hdd.sacarProceso(enHDD.getId());
                //Se disminuye la cantidad de memoria disponible en RAM
                setUso(-enHDD.getMemoria());
                infra.Inicio.pantalla.append("\t\tEl proceso ha sido agregado a RAM PID: "+enHDD.getId()+"\n\n\n");
                Inicio.actualizaInterfaz();
                //Se intenta ingresar a RAM el proceso nuevamente
                agregarProceso(proceso);
                return 1;
            }
        }
        else{
          return -1;
        }
        return 0;
    }

    
    /*
    Metodo utilizado para sacar de la lista los procesos que ya fueron
    ejecutados
    */
    public Proceso sacarProceso(int id){
        int largo = listaProceso.size()-1;
        for(int i =0; i<=largo;i++){
            Proceso proceso = listaProceso.get(i);
            if (proceso.getId()==id){
                listaProceso.remove(proceso);
                //Se reestablece la cantidad de memoria disponible
                setUso(proceso.getMemoria());
                System.out.println("Saco:"+proceso.getId());
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

    //Metodo utilizado para sacar de RAM los procesos bloqueados
    public Proceso sacarBloqueado(){
        int largoLista = listaProceso.size();
        for (int i=0;i<largoLista;i++){
            Proceso qwerty = listaProceso.get(i);
            if (qwerty.getEstado()==0){
                listaProceso.remove(qwerty);
                //Se reeetablece la cantidad de memoria disponible en RAM
                setUso(qwerty.getMemoria());
                return qwerty;
            }
        }
        return null;
    }

}
