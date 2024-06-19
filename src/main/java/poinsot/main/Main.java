package poinsot.main;

import poinsot.framework.Start;

public class Main {
    public static void main(String[] args) {
        var start = new Start("/configuracion.properties");
        start.init();
    }
}