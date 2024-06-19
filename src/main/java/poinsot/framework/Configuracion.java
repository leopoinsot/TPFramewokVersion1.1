package poinsot.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Configuracion {
	private Properties propiedades = new Properties();

	public Configuracion(String rutaArchivo) {
		try (InputStream input = getClass().getResourceAsStream(rutaArchivo);) {
			if (input == null) {
				throw new RuntimeException("No se encuentra el archivo de configuración: " + rutaArchivo);
			}
			propiedades.load(input);
		} catch (IOException ex) {
			throw new RuntimeException("Error:"+ex);
		}
	}
	public List<String> obtenerClases() {
		List<String> clases = new ArrayList<>();
		String clasesConfig = propiedades.getProperty("acciones");
		if (clasesConfig != null && !clasesConfig.isEmpty()) {
			String[] clasesArray = clasesConfig.split(";");
			for (String clase : clasesArray) {
				clase = clase.trim();
				if (!clase.isEmpty()) {
					clases.add(clase);
				}
			}
		}
		return clases;
	}
}
