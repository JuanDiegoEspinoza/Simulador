/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import Estructuras.Queue;
import infra.Inicio;
import static infra.Inicio.cpu;
import static infra.Inicio.ram;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static simulador.CPU.ram;
/**
 *
 * @author JuanDiego
 */
public class Dispatcher  extends CPU implements Runnable{
    

    public void MISS(){
        ArrayList<Proceso> listaHDD = infra.Inicio.cpu.hdd.listaHDD;
        int largo = listaHDD.size();
        ArrayList<Proceso> listaRAM = infra.Inicio.cpu.ram.listaProceso;
        int largoRAM  = listaRAM.size();
        //MISS
        if(largo!=0){
            for(int i=0;i<largo-1;i++){
             
                Proceso proceso = listaHDD.get(i);
                if (proceso.getEstado()==0){
                    proceso.setTiempo(10);
                    infra.Inicio.cpu.agregarProceso(proceso);
                    infra.Inicio.cpu.paginacion.remove(proceso.getId());
                }
            }
        }      
    }
    
    public void dispatch() {
        while(true){
         System.out.println("");
            if(ram.getLista().isEmpty()==false){
                int e=ram.getLista().size();
                for(int i=0;i<e-1;i++){
                    Proceso p = ram.getLista().get(i);
                    if(p.getEstado()==1){
                        try {
                            infra.Inicio.pantalla.append("ME cago en la puta HDD: "+infra.Inicio.cpu.hdd.listaHDD.toString());
                            infra.Inicio.pantalla.append("ME cago en la puta RAM: "+infra.Inicio.cpu.ram.listaProceso.toString());
                            infra.Inicio.pantalla.append("\tPROCESO SERA INICIADO. PID: "+p.getId()+"\n");
                            Thread.sleep(p.getTiempo()*1000);
                            
                            Proceso proc = ram.sacarProceso(p.getId());
                            infra.Inicio.cpu.terminados.enqueue(proc);
                            MISS();
                            infra.Inicio.pantalla.append("<<< PROCESO TERMINADO. PID: "+ p.getId()+">>>\n");
                            
                            //Define las "listas" donde se visualizaran los pids en interfaz 
                            infra.Inicio.ram.setModel(cpu.getListaItemsRam());
                            infra.Inicio.hdd.setModel(cpu.getListaItemsHdd());
                
                            //Muestra la lista de los pids en interfaz
                            infra.Inicio.JListRam.addElement(cpu.ram);
                            infra.Inicio.JListHDD.addElement(cpu.hdd);
                            //infra.Inicio.pantalla.append("ME cago en la puta HDD: "+infra.Inicio.cpu.hdd.listaHDD.toString());
                            //infra.Inicio.pantalla.append("ME cago en la puta RAM: "+infra.Inicio.cpu.ram.listaProceso.toString());
                            
                            //MISS();
                            
                        } catch (InterruptedException ex) {
                            System.out.println("ERROR");
                          //  Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    }                  

    @Override
    public void run() {
        dispatch();
        throw new UnsupportedOperationException("Imposible de realizar"); //To change body of generated methods, choose Tools | Templates.
    }
}
