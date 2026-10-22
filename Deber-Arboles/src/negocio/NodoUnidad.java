package negocio;

import java.util.ArrayList;
import java.util.List;
import enums.TipoUnidad;
import modelo.UnidadBiblioteca;

public class NodoUnidad {
    private final UnidadBiblioteca dato;
    private final List<NodoUnidad> hijos = new ArrayList<>();
    private final CatalogoABB catalogo;
    public NodoUnidad(UnidadBiblioteca dato) { this.dato = dato; this.catalogo = dato.getTipo() == TipoUnidad.ESTANTE ? new CatalogoABB() : null; }
    public UnidadBiblioteca getDato() { return dato; }
    public List<NodoUnidad> getHijos() { return hijos; }
    public CatalogoABB getCatalogo() { return catalogo; }
}
