package poinsot.utilizacion;

import poinsot.framework.Accion;

public class AccionUno implements Accion {

	public AccionUno() {
	}

	@Override
	public void ejecutar() {
		System.out.println("Ejecutando AccionUno...");
	}

	@Override
	public String nombreItemMenu() {
		return "Accion 1";

	}

	@Override
	public String descripcionItemMenu() {
		return "Esto es para traer los twitts de Maradona...";
	}
}
