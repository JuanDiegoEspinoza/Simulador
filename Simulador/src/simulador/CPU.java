package simulador;

import simulador.Proceso;
import simulador.RAM;
import simulador.HDD;
import Estructuras.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import simulador.Hilo;

public class CPU{
    private Queue colaBlock;
    private Queue colaReady;
    public  static RAM ram;
    public HDD hdd;
    public Map<Integer, Integer> paginacion;
    public static Queue terminados;
    public Thread thread;

    public CPU(){
        colaBlock = new Queue();
        colaReady = new Queue();
        ram = new RAM(1000);
        hdd = new HDD(2000);
        paginacion = new HashMap<Integer, Integer>();
        terminados= new Queue();
  }
    public void verMap(){
        System.out.println(paginacion.isEmpty());
        System.out.println(paginacion.values());
        System.out.println(paginacion.entrySet());
    }
    
    
   /* public void crearNuevoProceso(int id){
        if (ram.getUso())       
    }*/
       
    public DefaultListModel getListaItemsRam(){
        DefaultListModel jaja = new DefaultListModel();
        int i = ram.listaProceso.size();
        for (int e=0;e<i;e++){
            jaja.addElement(ram.listaProceso.get(e).getId());
        }
        return jaja;
    }
    
    public DefaultListModel getListaItemsHdd(){
        DefaultListModel jaja = new DefaultListModel();
        int i = hdd.listaHDD.size();
        for (int e=0;e<i;e++){
            jaja.addElement(hdd.listaHDD.get(e).getId());
        }
        return jaja;
    }
    
    public void agregarProceso(Proceso proceso){
        int valor2 = 0;
        int valor = ram.agregarProceso(proceso);
        //Si no hay espacio en la ram entonces
        if (valor==-1){
            Proceso x= ram.sacarBloqueado();    //Saco los procesos bloqueados de la ram
        
            if (x!=null){   //Si si hay procesos bloquados entonces
                hdd.agregarProceso(x); //Pagino el proceso
                agregarProceso(proceso); //E intento de nuevo meterlo a la RAM
            }
            else{
                infra.Inicio.pantalla.append("\tRAM no cuenta con suficientes recursos \n");
                //proceso.setEstado(0);
                valor2 = hdd.agregarProceso(proceso);
              
                if (valor2==-1){
                    infra.Inicio.pantalla.append("\tEl HDD no cuenta con suficientes recursos \n");
                    System.out.println("El HDD no cuenta con suficientes recursos");
                }
      
                else{
                    paginacion.put(proceso.getId(), valor2);
                    //if (proceso.getEstado()==1){
                        hdd.listaHDD.add(proceso);
                        
                    }
                    //else if (proceso.getEstado()==0){
                      //  colaBlock.enqueue(proceso);
                    //}
                }
            }
              verMap();
        }
        
        

      
    }
    
  
