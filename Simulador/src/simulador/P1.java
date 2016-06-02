package simulador;


import infra.Inicio;
import simulador.Proceso;

public class P1 extends Proceso{

	public P1(int id, int estado, int memoria, int tiempo, int context, int cantidadEjecuciones) {
		
		super(id, estado, memoria, tiempo, context, cantidadEjecuciones);

	}

	//se corre siempre
    public void execute(){
  				//toString()
        int context = getContext() * 2;

		//cambia el valor del contexto
        Inicio.result.append("Resultado del PID "+getId()+">>>"+Integer.toString(context)+"\n");
	setContext(context);

		//toString()

	}



}