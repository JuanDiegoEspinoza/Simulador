/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import infra.Inicio;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import static simulador.CPU.ram;

/**
 *
 * @author JuanDiego
 */
public class Dispatcher  extends CPU implements Runnable{
    

    public void dispatch() {
        while(true){
         System.out.println("");
            if(ram.getLista().isEmpty()==false){
                int e=ram.getLista().size();
                for(int i=0;i<e-1;i++){
                    Proceso p = ram.getLista().get(i);
                    if(p.getEstado()==1){
                        try {
                            // Hilo qwerty = new Hilo(ram.getLista().get(i));
                            //qwerty.iniciar();
                            infra.Inicio.pantalla.append("\tPROCESO SERA INICIADO. PID: "+p.getId()+"\n");
                            Thread.sleep(p.getTiempo()*1000);
                            //ram.getLista().get(i).setEstado(0);
                            
                            Proceso proc = ram.sacarProceso(p.getId());
                            infra.Inicio.cpu.terminados.enqueue(proc);
                            infra.Inicio.pantalla.append("<<< PROCESO TERMINADO. PID: "+ p.getId()+">>\n");
                            
                        } catch (InterruptedException ex) {
                            System.out.println("ERROOOORERERER");
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
