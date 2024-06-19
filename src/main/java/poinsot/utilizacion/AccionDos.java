package poinsot.utilizacion;

import poinsot.framework.Accion;

public class AccionDos implements Accion {
	public AccionDos(){

	}
	@Override
	public void ejecutar() {
		System.out.println("Ejecutando AccionDos...");
	}
	@Override
	public String nombreItemMenu() {
		return "Accion 2";
	}
	@Override
	public String descripcionItemMenu() {
		return "Esto trae las primeras diez personas de la BD...";
	}
}
