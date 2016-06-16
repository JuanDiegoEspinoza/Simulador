/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import Estructuras.Queue;
import infra.Inicio;
import static infra.Inicio.cpu;
import static infra.Inicio.pantalla;
import static infra.Inicio.ram;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//import static simulador.CPU.ram;
/**
 *
 * @author JuanDiego
 */
public class Dispatcher  extends CPU implements Runnable{


    

    public void dispatch() {

        while(true){
            System.out.println("");
            if(Inicio.cpu.jd==1){
                System.out.println("DEBERIA INICIAR");
                Inicio.cpu.despachador();
                Inicio.cpu.jd=3;
            }
            
            }
         
        }

    

    @Override
    public void run() {
        dispatch();
        throw new UnsupportedOperationException("Imposible de realizar"); //To change body of generated methods, choose Tools | Templates.
    }

}
