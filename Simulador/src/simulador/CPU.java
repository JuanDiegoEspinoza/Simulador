package simulador;

import simulador.Proceso;
import simulador.RAM;
import simulador.HDD;
import Estructuras.Queue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;

public class CPU{
    private Queue colaBlock;
    private Queue colaReady;
    private RAM ram;
    private HDD hdd;
    private Map<Integer, Integer> paginacion;

    public CPU(){
        colaBlock = new Queue();
        colaReady = new Queue();
        ram = new RAM(512);
        hdd = new HDD(2006565655);
        paginacion = new HashMap<Integer, Integer>();
  }
    public void verMap(){
        System.out.println(paginacion.isEmpty());
        System.out.println(paginacion.values());
        System.out.println(paginacion.entrySet());
    }

  public void agregarProceso(Proceso proceso){
      int valor2 = 0;
      int valor = ram.agregarProceso(proceso);
      if (valor==-1){
          System.out.println("RAM no cuenta con suficientes recursos");
          valor2 = hdd.agregarProceso(proceso);
          if (valor2==-1){
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
  public static void main(String[] args) {
      LinkedList jf = new LinkedList();
      LinkedList f = new LinkedList();
      //Proceso(int id, int estado, int memoria, String codigo, int tiempo, LinkedList listaRecursos){
      Proceso proceso = new Proceso(1,1,1,"j",1,jf);
      Proceso proceso2 = new Proceso(2,1,654684681,"d",1,f);
      Proceso proceso3 = new Proceso(3,0,1,"d",1,f);
      Proceso proceso4 = new Proceso(4,1,55005445,"d",1,f);
      //Proceso proceso5 = new Proceso(5,1,1220,"d",1,f);
      CPU cpu = new CPU();
      cpu.agregarProceso(proceso);
      cpu.agregarProceso(proceso2);
      cpu.agregarProceso(proceso3);
      cpu.agregarProceso(proceso4);
      //cpu.agregarProceso(proceso5);
      cpu.verMap();
    
  }

}