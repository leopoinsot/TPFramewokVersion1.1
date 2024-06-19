package poinsot.framework;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MenuItems {
	private List<Accion> acciones = new ArrayList<>();

	public MenuItems(String path) {
		cargarAcciones(path);
	}

	private void cargarAcciones(String configFilePath) { //Utilizamos reflection
		Configuracion configuracion = new Configuracion(configFilePath);
		List<String> clasesAcciones = configuracion.obtenerClases();
		for (String clase : clasesAcciones) {
			try {
				Class<?> clazz = Class.forName(clase);
				Accion accion = (Accion) clazz.getDeclaredConstructor().newInstance();
				acciones.add(accion);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
					 InvocationTargetException e) {
				throw new RuntimeException("Error al cargar la clase: " + clase, e);
			}
		}
	}
	public void iniciar() {
		try {
			// Crear terminal y pantalla una sola vez
			DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
			Screen screen = terminalFactory.createScreen();
			screen.startScreen();

			// Crear GUI y ventana principal
			WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
			BasicWindow mainWindow = new BasicWindow("Menú de Opciones");

			// Crear panel con layout y agregar componentes
			Panel mainPanel = new Panel(new GridLayout(1));
			mainPanel.addComponent(new Label("Bienvenido, estas son sus opciones:"));

			// Itera sobre la lista de acciones y para cada acción, crea un botón con el nombre de la acción.
			acciones.forEach(accion -> mainPanel.addComponent(
							new Button(accion.nombreItemMenu(),
							() -> ejecutarAccion(accion, textGUI))));

			// Agregar botón de salida
			mainPanel.addComponent(new Button("Salir", () -> System.exit(0)));

			// Configurar y mostrar ventana principal
			mainWindow.setComponent(mainPanel);
			textGUI.addWindowAndWait(mainWindow);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ejecutarAccion(Accion accion, WindowBasedTextGUI textGUI) {
		try {
			accion.ejecutar(); //Ejecuta la accion (Mustra en consola)
			BasicWindow popupWindow = new BasicWindow("Acción Ejecutada");
			Panel popupPanel = new Panel(new GridLayout(1));

			// Agregar mensajes y botón de aceptar al panel emergente
			popupPanel.addComponent(new Label("Se ejecutó la acción: " + accion.nombreItemMenu()));
			popupPanel.addComponent(new Button("Aceptar", popupWindow::close));

			// Configurar y mostrar ventana emergente
			popupWindow.setComponent(popupPanel);
			textGUI.addWindowAndWait(popupWindow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
