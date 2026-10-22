package negocio;

import enums.TipoRecorrido;
import java.util.ArrayList;
import java.util.List;
import modelo.Libro;

public class CatalogoABB {
    private NodoLibro raiz;
    public NodoLibro getRaiz() { return raiz; }
    public void setRaiz(NodoLibro raiz) { this.raiz = raiz; }
    public boolean insertar(Libro libro) { if (libro == null) return false; if (raiz == null) { raiz = new NodoLibro(libro); return true; } return insertarRec(raiz, libro); }
    private boolean insertarRec(NodoLibro n, Libro l) { if (l.getCodigo() == n.getDato().getCodigo())
     return false; if (l.getCodigo() < n.getDato().getCodigo()) { if (n.getIzquierdo() == null) n.setIzquierdo(new NodoLibro(l));
     else return insertarRec(n.getIzquierdo(), l); } else { if (n.getDerecho() == null) n.setDerecho(new NodoLibro(l)); else return insertarRec(n.getDerecho(), l); } return true; }
    public Libro buscar(int codigo) { NodoLibro n = buscarNodo(raiz, codigo); return n == null ? null : n.getDato(); }
    private NodoLibro buscarNodo(NodoLibro n, int c) { if (n == null || n.getDato().getCodigo() == c) return n; return buscarNodo(c < n.getDato().getCodigo() ? n.getIzquierdo() : n.getDerecho(), c); }
    public int contar() { return contarRec(raiz); }
    private int contarRec(NodoLibro n) { return n == null ? 0 : 1 + contarRec(n.getIzquierdo()) + contarRec(n.getDerecho()); }
    public List<Libro> recorrer(TipoRecorrido tipo) { List<Libro> r = new ArrayList<>(); recorrerRec(raiz, tipo, r); return r; }
    private void recorrerRec(NodoLibro n, TipoRecorrido t, List<Libro> r) { if (n == null) return; if (t == TipoRecorrido.PREORDEN) r.add(n.getDato()); recorrerRec(n.getIzquierdo(), t, r); if (t == TipoRecorrido.INORDEN) r.add(n.getDato()); recorrerRec(n.getDerecho(), t, r); if (t == TipoRecorrido.POSORDEN) r.add(n.getDato()); }
    public String trazaRecorrido(TipoRecorrido tipo) { StringBuilder s = new StringBuilder(); 
        List<Integer> sec = new ArrayList<>(); traza(raiz, tipo, 0, s, sec); 
        s.append("Secuencia ").append(tipo).append(": "); agregarSecuencia(sec, 0, s); 
        return s.toString(); }
    private void agregarSecuencia(List<Integer> secuencia, int indice, StringBuilder salida) { if (indice >= secuencia.size()) return; if (indice > 0) salida.append(' '); salida.append(secuencia.get(indice)); agregarSecuencia(secuencia, indice + 1, salida); }
    private void traza(NodoLibro n, TipoRecorrido t, int nivel, StringBuilder s, List<Integer> sec) 
    { s.append("  ".repeat(nivel)).append(n == null ? "null -> regresa\n" : "entra " + n.getDato().getCodigo() + "\n"); 
    if (n == null) return; if (t == TipoRecorrido.PREORDEN) { s.append("  ".repeat(nivel)).append(
    "VISITA ").append(n.getDato().getCodigo()).append('\n'); sec.add(n.getDato().getCodigo()); } traza(n.getIzquierdo(),
     t, nivel+1, s, sec); if (t == TipoRecorrido.INORDEN) { s.append("  ".repeat(nivel)).append("VISITA ").append(n.getDato(
     ).getCodigo()).append('\n'); sec.add(n.getDato().getCodigo()); } traza(n.getDerecho(), t, nivel+1, s, sec); if (t == 
    TipoRecorrido.POSORDEN) { s.append("  ".repeat(nivel)).append("VISITA ").append(n.getDato().getCodigo()).append('\n'); 
    sec.add(n.getDato().getCodigo()); } }
    public boolean modificar(int codigo, String titulo, String autor, int anio) { Libro l = buscar(codigo); if (l == null) return false; l.actualizar(titulo, autor, anio); return true; }
    public boolean eliminar(int codigo) { if (buscar(codigo) == null) return false; raiz = eliminarRec(raiz, codigo); return true; }
    private NodoLibro eliminarRec(NodoLibro n, int c) { if (n == null) return null; if (c < n.getDato().getCodigo())
     n.setIzquierdo(eliminarRec(n.getIzquierdo(), c)); else if (c > n.getDato().getCodigo()) n.setDerecho(eliminarRec(n.getDerecho(), c));
     else {if (n.getIzquierdo() == null)return n.getDerecho();if (n.getDerecho()==null)return n.getIzquierdo(); NodoLibro sucesor = minimo(n.getDerecho());
     n.setDato(sucesor.getDato()); n.setDerecho(eliminarRec(n.getDerecho(), sucesor.getDato().getCodigo())); } return n; }
    private NodoLibro minimo(NodoLibro n) { return n.getIzquierdo() == null ? n : minimo(n.getIzquierdo()); }
    public int contarEnRango(int a, int b) { return a > b ? 0 : rango(raiz, a, b); }
    private int rango(NodoLibro n, int a, int b) { if (n == null) return 0; if (n.getDato().getCodigo() < a) return rango(n.getDerecho(), a, b); if (n.getDato().getCodigo() > b) return rango(n.getIzquierdo(), a, b); return 1 + rango(n.getIzquierdo(), a, b) + rango(n.getDerecho(), a, b); }
    public List<Integer> caminoBusqueda(int codigo) { List<Integer> r = new ArrayList<>(); camino(raiz, codigo, r); return r; }
    private boolean camino(NodoLibro n, int c, List<Integer> r) { if (n == null) return false; r.add(n.getDato().getCodigo()); return n.getDato().getCodigo() == c || camino(c < n.getDato().getCodigo() ? n.getIzquierdo() : n.getDerecho(), c, r); }
    public boolean esABBValido() { return valido(raiz, Long.MIN_VALUE, Long.MAX_VALUE); }
    private boolean valido(NodoLibro n, long min, long max) { return n == null || (n.getDato().getCodigo() > min && n.getDato().getCodigo() < max && valido(n.getIzquierdo(), min, n.getDato().getCodigo()) && valido(n.getDerecho(), n.getDato().getCodigo(), max)); }
    public static CatalogoABB desdePreorden(int[] preorden) { CatalogoABB c = new CatalogoABB(); c.raiz = construir(preorden, new int[]{0}, Long.MIN_VALUE, Long.MAX_VALUE); return c; }
    private static NodoLibro construir(int[] a, int[] i, long min, long max) { if (i[0] >= a.length || a[i[0]] <= min || a[i[0]] >= max) return null; int v=a[i[0]++]; NodoLibro n=new NodoLibro(new Libro(v,"Libro "+v,"Autor",2020)); n.setIzquierdo(construir(a,i,min,v)); n.setDerecho(construir(a,i,v,max)); return n; }
}
