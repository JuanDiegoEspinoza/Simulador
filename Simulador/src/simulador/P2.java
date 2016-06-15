package simulador;

import infra.Inicio;

public class P2 extends Proceso{

	public P2(int id, int estado, int memoria, int tiempo, int context, int cantidadEjecuciones) {
		

		super(id, estado, memoria, tiempo, context, cantidadEjecuciones,2);

	}

	//se corre segun la condicion
	public void execute(){

			int context = getContext() + 10;

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
        return 2;
    }



}