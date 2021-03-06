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
    public int jd;

    public CPU(){
        colaBlock = new Queue();
        colaReady = new Queue();
        ram = new RAM(1000);
        hdd = new HDD(2000000);
        paginacion = new HashMap<Integer, Integer>();
        terminados= new Queue();
        ejecucion = 0;
        jd=0;
  }
    public void verMap(){
        System.out.println(paginacion.isEmpty());
        System.out.println(paginacion.values());
        System.out.println(paginacion.entrySet());
    }

    public DefaultListModel getListaItemsRam(){
        DefaultListModel memoriaRam = new DefaultListModel();
        for (int e=0;e<ram.listaProceso.size();e++){
            memoriaRam.addElement(ram.listaProceso.get(e).getId());
        }
        return memoriaRam;
    }

    public DefaultListModel getListaItemsHdd(){
        DefaultListModel discoduro = new DefaultListModel();
        for (int e=0;e<hdd.listaHDD.size();e++){
            discoduro.addElement(hdd.listaHDD.get(e).getId());
        }
        return discoduro;
    }
    
    public DefaultListModel getListaItemsPag(){
        DefaultListModel paginacion = new DefaultListModel();
        int tamanoDisco = hdd.listaHDD.size();
        for(int e=0;e<tamanoDisco;e++){
            int key = hdd.listaHDD.get(e).getId();
            int value = infra.Inicio.cpu.paginacion.get(key);
            value+=1;
            String valorPantalla = "PID:"+ key+"---> Dir: "+value;
            paginacion.addElement(valorPantalla);
        }
        return paginacion;
    }
    
    public String getPaginacion(){
        int i = paginacion.size();
        int x = 0;
        if (i!=0){
            String valores = "PID:\t\tPosición en HDD";
            while(x<i){
                valores+=hdd.listaHDD.get(x).getId()+"\t\t";
                valores+=paginacion.get(hdd.listaHDD.get(x));
                valores+="\n\n";
            }   
            return valores;
        }
        return "";
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
          
                }

                else{
                    paginacion.put(proceso.getId(), proceso.getPosicion());
                    //if (proceso.getEstado()==1){
                        hdd.listaHDD.add(proceso);
                        Inicio.actualizaInterfaz();

                    }

                }
            }
              //verMap();
        }
    public void MISS(){
        ArrayList<Proceso> listaHDD = infra.Inicio.cpu.hdd.listaHDD;
        int largo = listaHDD.size();
        ArrayList<Proceso> listaRAM = infra.Inicio.cpu.ram.listaProceso;
        //MISS
        if(listaHDD.isEmpty()==false){
            
            for(int i=0;i<largo;i++){
                Proceso proceso = listaHDD.get(i);
                
                  if(Inicio.cpu.ram.getUso()-proceso.getMemoria()>=0){
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
    public void despachador(){
        System.out.println("zsdhjykyl;");
       try{
    
           if(ram.getLista().isEmpty()==false){
                int e = ram.getLista().size();
                for(int i=0;i<e;i++){
                    Proceso p = ram.getLista().get(i);
                    if(p.getEstado()==1){
                        try {
                            
                            infra.Inicio.pantalla.append("\tPROCESO SERA INICIADO. PID: "+p.getId()+"\n");
                            

                            int largooo = infra.Inicio.cpu.ram.listaProceso.size();
                        
                            Thread.sleep(p.getTiempo()*1000);
                            p.execute();
                             if(p.getTipo()==2){
                               //  infra.Inicio.cpu.agregarProceso(p);
                                
                               Proceso proc = ram.sacarProceso(p.getId());
                               // infra.Inicio.cpu.terminados.enqueue(proc);
                              // infra.Inicio.terminados.append(Integer.toString(p.getId())+"\n");
                                 infra.Inicio.cpu.agregarProceso(p);
                                MISS();

                                Inicio.actualizaInterfaz();
                                infra.Inicio.pantalla.append("<<< PROCESO TERMINADO. PID: "+ p.getId()+ "  El proceso continuara..."+">>>\n");
                                if(p.getTipo()==3){
                                    Inicio.semaforo.setBackground(Color.green);
                                }

                        
                             }
                             else{
                                Proceso proc = ram.sacarProceso(p.getId());
                                infra.Inicio.cpu.terminados.enqueue(proc);
                                infra.Inicio.terminados.append(Integer.toString(p.getId())+"\n");
                                MISS();

                                Inicio.actualizaInterfaz();
                                infra.Inicio.pantalla.append("<<< PROCESO TERMINADO. PID: "+ p.getId()+">>>\n");
                                if(p.getTipo()==3){
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
          
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    }
