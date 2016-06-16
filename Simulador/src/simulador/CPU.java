package simulador;

import simulador.Proceso;
import simulador.RAM;
import simulador.HDD;
import Estructuras.Queue;
import infra.Inicio;
import static infra.Inicio.cpu;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.text.Document;
import simulador.Hilo;

public class CPU{
    private Queue colaBlock;
    private Queue colaReady;
    public  static  RAM ram;
    public HDD hdd;
    public Map<Integer, Integer> paginacion;
    public static Queue terminados;
    public Thread thread;
    public int ejecucion;
    public int contador;

    public CPU(){
        colaBlock = new Queue();
        colaReady = new Queue();
        ram = new RAM(1000);
        hdd = new HDD(2000000);
        paginacion = new HashMap<Integer, Integer>();
        terminados= new Queue();
        ejecucion = 0;
        contador=0;
  }

    //Metodo utilizado para visualizar los datos existentes en el archivo de paginación
    public void verMap(){
        System.out.println(paginacion.isEmpty());
        System.out.println(paginacion.values());
        System.out.println(paginacion.entrySet());
    }

    //Metodo utilizado para obtener los procesos en RAM
    public DefaultListModel getListaItemsRam(){
        DefaultListModel memoriaRam = new DefaultListModel();
        for (int e=0;e<ram.listaProceso.size();e++){
            memoriaRam.addElement(ram.listaProceso.get(e).getId());
        }
        return memoriaRam;
    }

    //Metodo utilizado para obtener los procesos almacenados en HDD
    public DefaultListModel getListaItemsHdd(){
        DefaultListModel discoduro = new DefaultListModel();
        for (int e=0;e<hdd.listaHDD.size();e++){
            discoduro.addElement(hdd.listaHDD.get(e).getId());
        }
        return discoduro;
    }

    //Metodo utilizado para obtener los datos de los procesos paginados
    public DefaultListModel getListaItemsPag(){
        DefaultListModel paginacion = new DefaultListModel();
        int tamanoDisco = hdd.listaHDD.size();
        for(int e=0;e<tamanoDisco;e++){
            int key = hdd.listaHDD.get(e).getId();
            int value = infra.Inicio.cpu.paginacion.get(key);
            String valorPantalla = "PID:"+ key+"---> Dir: "+value;
            paginacion.addElement(valorPantalla);
        }
        return paginacion;
    }

    //Metodo utilizado para ingresar procesos y ser ejecutados posteriormente
    public void agregarProceso(Proceso proceso){
        int valor2 = 0;
        int valor = ram.agregarProceso(proceso);

        //En caso de que no exista espacio en RAM
        if (valor==-1){
            //Elimino de la RAM el primer proceso bloqueado que encuentre
            Proceso procesoBloqueado= ram.sacarBloqueado();

            //En caso de que SI haya proceso bloqueado
            if (procesoBloqueado!=null){
                //Se realiza la paginacion del proceso bloqueado en RAM, se pasa a HDD
                hdd.agregarProceso(procesoBloqueado);
                //Ahora con espacio libre en RAM, se intenta ingresar de nuevo
                agregarProceso(proceso);
            }
            //En caso de que no hayan procesos bloqueados
            else{
                //Se muestra mensaje en pantalla de que no hay memoria
                infra.Inicio.pantalla.append("\tRAM no cuenta con suficientes recursos \n");
                //Se pagina el proceso ingresado
                valor2 = hdd.agregarProceso(proceso);

                //En caso de que no exista espacio en HDD
                if (valor2==-1){
                    //Se muestra mensaje en pantalla
                    infra.Inicio.pantalla.append("\tEl HDD no cuenta con suficientes recursos \n");
                }
                else{
                    //Se ingresan los datos del proceso ingresado
                    //...en un "archivo" de paginacion
                    paginacion.put(proceso.getId(), proceso.getPosicion());
                    hdd.listaHDD.add(proceso);
                    //Se actualiza los valores en la interfaz
                    Inicio.actualizaInterfaz();
                    }
                }
            }
        }

    /*
    Metodo utilizado para encontrar procesos en HDD que pueden...
        ser ejecutados en RAM
    */

    public void MISS(){
        //Obtengo la lista de los procesos en HDD
        ArrayList<Proceso> listaHDD = infra.Inicio.cpu.hdd.listaHDD;
        int largo = listaHDD.size();
        ArrayList<Proceso> listaRAM = infra.Inicio.cpu.ram.listaProceso;

        //En caso de que la lista no este vacia
        if(listaHDD.isEmpty()==false){
            //Se realiza la busqueda de los procesos en HDD
            for(int i=0;i<largo;i++){
                Proceso proceso = listaHDD.get(i);
                /*
                En caso de que el proceso paginado pueda ser ingresado
                a RAM, debido a la cantidad de memoria disponible
                */
                if(Inicio.cpu.ram.getUso()-proceso.getMemoria()>=0){
                    //Se le reasigna el tiempo al proceso debido al MISS
                    proceso.setTiempo(10);
                    proceso.setEstado(1);
                    Inicio.pantalla.append("MISS PID:"+proceso.getId()+"\n");
                    infra.Inicio.cpu.ram.agregarProceso(proceso);
                    infra.Inicio.cpu.hdd.sacarProceso(proceso.getId());
                    infra.Inicio.cpu.paginacion.remove(proceso.getId());
                  }

            }
        }
    }
    /*Este metodo es utilizado para gestionar la ejecucion de los procesos
    listos en RAM
    */
    public void despachador(){
       try{

           //En caso de que la lista no este vacia
           if(ram.getLista().isEmpty()==false){
                int e = ram.getLista().size();
                for(int i=0;i<e;i++){
                    //Se obtiene el primer proceso en RAM
                    Proceso procesoP = ram.getLista().get(i);
                    //Se evalua si el proceso esta listo para ser ejecutado
                    if(procesoP.getEstado()==1){
                        try {
                            //Se ejecuta el proceso
                            infra.Inicio.pantalla.append("\tPROCESO SERA INICIADO. PID: "+procesoP.getId()+"\n");
                            int largooo = infra.Inicio.cpu.ram.listaProceso.size();
                            Thread.sleep(procesoP.getTiempo()*1000);
                            procesoP.execute();
                            if(procesoP.getTipo()==2){
                                Proceso proc = ram.sacarProceso(procesoP.getId());
                                infra.Inicio.cpu.agregarProceso(procesoP);
                                MISS();
                                Inicio.actualizaInterfaz();
                                infra.Inicio.pantalla.append("<<< PROCESO TERMINADO. PID: "+ procesoP.getId()+ "  El proceso continuara..."+">>>\n");
                                if(procesoP.getTipo()==3){
                                    Inicio.semaforo.setBackground(Color.green);
                                }
                             }
                             else{
                                Proceso proc = ram.sacarProceso(procesoP.getId());
                                infra.Inicio.cpu.terminados.enqueue(proc);
                                infra.Inicio.terminados.append(Integer.toString(procesoP.getId())+"\n");
                                MISS();

                                Inicio.actualizaInterfaz();
                                infra.Inicio.pantalla.append("<<< PROCESO TERMINADO. PID: "+ procesoP.getId()+">>>\n");
                                if(procesoP.getTipo()==3){
                                    Inicio.semaforo.setBackground(Color.green);
                                }
                             }
                              despachador();
                        } catch (InterruptedException ex) {
                            System.out.println("ERROR");
                          //  Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            else{
                cpu.ejecucion=0;
            }
        }
        catch(Exception e){
            despachador();
        }
    }
}
