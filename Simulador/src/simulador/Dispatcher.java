/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import java.util.logging.Level;
import java.util.logging.Logger;
import static simulador.CPU.ram;

/**
 *
 * @author JuanDiego
 */
public class Dispatcher implements Runnable{
    

    @Override
    public void run() {
        while(true){
            if(ram.getLista().isEmpty()){
                int e=ram.getLista().size();
                for(int i=0;i<e;i++){
                    if(ram.getLista().get(i).getEstado()==1){
                        infra.Inicio.pantalla.append("\tPROCESO SERA INICIADO: "+"\n");
                        try {
                            // Hilo qwerty = new Hilo(ram.getLista().get(i));
                            //qwerty.iniciar();
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    }
}
