package simulador;
public class P3 extends Proceso{

	public P3(int id, int estado, int memoria, int tiempo, int context, int cantidadEjecuciones) {
		

		super(id, estado, memoria, tiempo, context, cantidadEjecuciones);
		setEstado(3);


	}

	//se corre segun la condicion
	public void execute(){

		int context = (int)Math.pow(getContext(),2) ;

			//cambia el valor 
			setContext(context);

			reducirEjecucion();


			// si ya no necesita ejecutar maas
			//se pone en estado exit para q sea sacado de la cola
			if (getCantidadEjecuciones() == 0){

				setEstado(0);
			}
		
	}



}