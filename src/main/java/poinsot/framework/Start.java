package poinsot.framework;

public class Start {
	private MenuItems menuItems;
	public Start(String path){
		this.menuItems = new MenuItems(path);
	}

	public void init(){
		menuItems.iniciar();
	}
}
