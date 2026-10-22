package negocio;

import enums.TipoUnidad;
import modelo.UnidadBiblioteca;

public class ArbolBiblioteca {
    private NodoUnidad raiz;
    public ArbolBiblioteca() { }
    public ArbolBiblioteca(UnidadBiblioteca raiz) { if (raiz.getTipo() != TipoUnidad.BIBLIOTECA) throw new IllegalArgumentException("La raíz debe ser BIBLIOTECA"); this.raiz = new NodoUnidad(raiz); }
    public NodoUnidad getRaiz() { return raiz; }
    public boolean agregar(String codigoPadre, UnidadBiblioteca nueva) {
        if (raiz == null || nueva == null || buscar(nueva.getCodigo()) != null) return false;
        NodoUnidad padre = buscar(codigoPadre);
        if (padre == null || !padre.getDato().getTipo().puedeTenerHijo(nueva.getTipo())) return false;
        padre.getHijos().add(new NodoUnidad(nueva)); return true;
    }
    public NodoUnidad buscar(String codigo) { return buscarRec(raiz, codigo); }
    private NodoUnidad buscarRec(NodoUnidad n, String codigo) {
        if (n == null || codigo == null) return null;
        if (n.getDato().getCodigo().equalsIgnoreCase(codigo)) return n;
        return buscarLista(n, codigo, 0);
    }
    private NodoUnidad buscarLista(NodoUnidad n, String codigo, int i) {
        if (i >= n.getHijos().size()) return null;
        NodoUnidad encontrado = buscarRec(n.getHijos().get(i), codigo);
        return encontrado != null ? encontrado : buscarLista(n, codigo, i + 1);
    }
    public int altura() { return alturaRec(raiz); }
    private int alturaRec(NodoUnidad n) { if (n == null || n.getHijos().isEmpty()) return n == null ? -1 : 0; return 1 + alturaHijos(n, 0); }
    private int alturaHijos(NodoUnidad n, int i) { return i == n.getHijos().size() ? -1 : Math.max(alturaRec(n.getHijos().get(i)), alturaHijos(n, i + 1)); }
    public int contarHojas() { return contarHojasRec(raiz); }
    private int contarHojasRec(NodoUnidad n) { if (n == null) return 0; return n.getHijos().isEmpty() ? 1 : contarHijos(n, 0); }
    private int contarHijos(NodoUnidad n, int i) { return i == n.getHijos().size() ? 0 : contarHojasRec(n.getHijos().get(i)) + contarHijos(n, i + 1); }
    public int nivel(String codigo) { return nivelRec(raiz, codigo, 0); }
    private int nivelRec(NodoUnidad n, String codigo, int nivel) { if (n == null) return -1; if (n.getDato().getCodigo().equalsIgnoreCase(codigo)) return nivel; return nivelHijos(n, codigo, nivel, 0); }
    private int nivelHijos(NodoUnidad n, String codigo, int nivel, int i) {
        if (i == n.getHijos().size()) return -1;
        int nivelEncontrado = nivelRec(n.getHijos().get(i), codigo, nivel + 1);
        return nivelEncontrado >= 0 ? nivelEncontrado : nivelHijos(n, codigo, nivel, i + 1);
    }
    public String mostrarJerarquia() {
        StringBuilder sb = new StringBuilder();
        if (raiz != null) {
            sb.append(raiz.getDato()).append('\n');
            jerarquiaHijos(raiz, "", 0, sb);
        }
        return sb.toString();
    }

    private void jerarquiaHijos(NodoUnidad padre, String prefijo, int indice, StringBuilder sb) {
        if (indice >= padre.getHijos().size()) return;
        NodoUnidad hijo = padre.getHijos().get(indice);
        boolean ultimo = indice == padre.getHijos().size() - 1;
        sb.append(prefijo).append(ultimo ? "\\-- " : "|-- ").append(hijo.getDato()).append('\n');
        if (!hijo.getHijos().isEmpty()) {
            jerarquiaHijos(hijo, prefijo + (ultimo ? "    " : "|   "), 0, sb);
        }
        jerarquiaHijos(padre, prefijo, indice + 1, sb);
    }
    public int contarLibrosTotal() { return librosRec(raiz); }
    private int librosRec(NodoUnidad n) { if (n == null) return 0; return (n.getCatalogo() == null ? 0 : n.getCatalogo().contar()) + librosHijos(n, 0); }
    private int librosHijos(NodoUnidad n, int i) { return i == n.getHijos().size() ? 0 : librosRec(n.getHijos().get(i)) + librosHijos(n, i + 1); }
}
