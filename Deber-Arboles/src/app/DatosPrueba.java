package app;

import enums.TipoUnidad;
import modelo.Libro;
import modelo.UnidadBiblioteca;
import negocio.ArbolBiblioteca;
import negocio.NodoUnidad;

public final class DatosPrueba {
    private DatosPrueba() {}
    public static ArbolBiblioteca crearBiblioteca() {
        ArbolBiblioteca a = new ArbolBiblioteca(new UnidadBiblioteca("BIB", "Biblioteca FISEI", TipoUnidad.BIBLIOTECA));
        a.agregar("BIB", new UnidadBiblioteca("SW", "Software", TipoUnidad.AREA));
        a.agregar("BIB", new UnidadBiblioteca("TI", "Tecnologías de la Información", TipoUnidad.AREA));
        a.agregar("BIB", new UnidadBiblioteca("TEL", "Telecomunicaciones", TipoUnidad.AREA));
        a.agregar("SW", new UnidadBiblioteca("SW-E1", "Estante 1 de Software", TipoUnidad.ESTANTE));
        a.agregar("SW", new UnidadBiblioteca("SW-E2", "Estante 2 de Software", TipoUnidad.ESTANTE));
        a.agregar("TI", new UnidadBiblioteca("TI-E1", "Estante 1 de TI", TipoUnidad.ESTANTE));
        int[] e1={60,35,80,20,45,70,90,40,75}; int[] e2={15,10,25};
        insertar(a.buscar("SW-E1"), e1); insertar(a.buscar("SW-E2"), e2); insertar(a.buscar("TI-E1"), new int[]{50});
        return a;
    }
    private static void insertar(NodoUnidad n, int[] codigos) { for (int c : codigos) n.getCatalogo().insertar(new Libro(c,"Libro "+c,"Autor",2020)); }
}
