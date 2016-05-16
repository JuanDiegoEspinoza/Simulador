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
        ram = new RAM(4096);
        hdd = new HDD(524288);
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
          Proceso idproc= ram.sacarBloqueado();
          System.out.println("Verificando bloqueados");
          if (idproc!=null){
              System.out.println("Verificando bloqueados dentro del if");
              hdd.agregarProceso(idproc);
              agregarProceso(proceso);
          }
          else{
              System.out.println("RAM no cuenta con suficientes recursos");
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
