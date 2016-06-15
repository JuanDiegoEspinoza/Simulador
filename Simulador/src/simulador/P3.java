package simulador;

import infra.Inicio;
import java.awt.Color;

public class P3 extends Proceso{

	public P3(int id, int estado, int memoria, int tiempo, int context, int cantidadEjecuciones) {
		

		super(id, estado, memoria, tiempo, context, cantidadEjecuciones,3);
		setEstado(3);


	}

	//se corre segun la condicion
	public void execute(){
            Inicio.semaforo.setBackground(Color.red);
                

		int context = (int)Math.pow(getContext(),2) ;

			//cambia el valor 
                         Inicio.result.append("Resultado del PID "+getId()+">>>"+Integer.toString(context)+"\n");
                
			setContext(context);

			reducirEjecucion();


			// si ya no necesita ejecutar maas
			//se pone en estado exit para q sea sacado de la cola
			if (getCantidadEjecuciones() == 0){

				setEstado(0);
			}
		
	}
           public int tipo(){
        return 3;
    }



}