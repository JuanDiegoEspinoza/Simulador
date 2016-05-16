/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;
import simulador.Proceso;

/**
 *
 * @author JuanDiegoY FELIPE
 */
public class Hilo {

    private Proceso proceso;
    Thread thread;

    public Hilo(Proceso proceso){
        thread= new Thread();
    }
    public void iniciar(){
        try{
            thread.sleep(proceso.getTiempo()*1000);
            System.out.println("TERMINA PROCESO");
            Proceso x = CPU.ram.sacarProceso(proceso.getId());
            if (x!=null){
                CPU.terminados.enqueue(x);
            }
        }
        catch (java.lang.InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
}
