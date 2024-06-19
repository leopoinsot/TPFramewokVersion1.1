package poinsot.utilizacion;

import poinsot.framework.Accion;

public class AccionTres implements Accion {
	public AccionTres(){

	}
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando AccionTres...");
	}
	@Override
	public String nombreItemMenu() {
		return "Accion 3";
	}
	@Override
	public String descripcionItemMenu() {
		return "se va al otro mundo con goku...";
	}
}
