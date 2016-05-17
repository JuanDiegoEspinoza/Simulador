package simulador;

import simulador.Proceso;
import simulador.RAM;
import simulador.HDD;
import Estructuras.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import simulador.Hilo;

public class CPU{
    private Queue colaBlock;
    private Queue colaReady;
    public  static RAM ram;
    private HDD hdd;
    private Map<Integer, Integer> paginacion;
    public static Queue terminados;
    public Thread thread;

    public CPU(){
        colaBlock = new Queue();
        colaReady = new Queue();
        ram = new RAM(4096);
        hdd = new HDD(524288);
        paginacion = new HashMap<Integer, Integer>();
        terminados= new Queue();
  }
    public void verMap(){
        System.out.println(paginacion.isEmpty());
        System.out.println(paginacion.values());
        System.out.println(paginacion.entrySet());
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
                valor2 = hdd.agregarProceso(proceso);
              
                if (valor2==-1){
                    infra.Inicio.pantalla.append("\tEl HDD no cuenta con suficientes recursos \n");
                    System.out.println("El HDD no cuenta con suficientes recursos");
                }
      
                else{
                    paginacion.put(proceso.getId(), valor2);
                    if (proceso.getEstado()==1){
                        colaReady.enqueue(proceso);
                        
                    }
                    else if (proceso.getEstado()==0){
                        colaBlock.enqueue(proceso);
                    }
                }
            }
        }
      verMap();
      
    }
    
    
 
}
