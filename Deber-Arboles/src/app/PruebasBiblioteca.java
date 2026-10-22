package app;

import enums.TipoRecorrido;
import enums.TipoUnidad;
import modelo.Libro;
import modelo.UnidadBiblioteca;
import negocio.ArbolBiblioteca;
import negocio.CatalogoABB;

public class PruebasBiblioteca {
    private int ok, total;
    private void prueba(String nombre, boolean resultado) { total++; if (resultado) ok++; System.out.println((resultado ? "OK   " : "FAIL ") + nombre); }
    public void ejecutar() {
        ArbolBiblioteca a = DatosPrueba.crearBiblioteca(); CatalogoABB c = a.buscar("SW-E1").getCatalogo();
        ArbolBiblioteca soloRaiz = new ArbolBiblioteca(new UnidadBiblioteca("BIB","Biblioteca",TipoUnidad.BIBLIOTECA));
        prueba("raíz: altura 0 y una hoja", soloRaiz.altura()==0 && soloRaiz.contarHojas()==1);
        prueba("biblioteca: altura 2, 4 hojas y 13 libros", a.altura()==2 && a.contarHojas()==4 && a.contarLibrosTotal()==13);
        prueba("niveles", a.nivel("BIB")==0 && a.nivel("sw")==1 && a.nivel("SW-E2")==2 && a.nivel("XYZ")==-1);
        prueba("restricción de tipos", !a.agregar("BIB",new UnidadBiblioteca("MAL","Mal",TipoUnidad.ESTANTE)));
        prueba("agregar unidad válida y sin duplicados", a.agregar("TEL",new UnidadBiblioteca("TEL-E1","Estante 1 de Telecomunicaciones",TipoUnidad.ESTANTE)) && !a.agregar("TEL",new UnidadBiblioteca("TEL-E1","Duplicado",TipoUnidad.ESTANTE)));
        prueba("preorden", codigos(c,TipoRecorrido.PREORDEN).equals("60 35 20 45 40 80 70 75 90"));
        prueba("inorden", codigos(c,TipoRecorrido.INORDEN).equals("20 35 40 45 60 70 75 80 90"));
        prueba("posorden", codigos(c,TipoRecorrido.POSORDEN).equals("20 40 45 35 75 70 90 80 60"));
        prueba("repetido", !c.insertar(new Libro(60,"Repetido","Autor",2020)));
        prueba("modificar libro", c.modificar(45,"Algoritmos","Ada Lovelace",2021) && c.buscar(45).getTitulo().equals("Algoritmos") && c.buscar(45).getAnio()==2021);
        prueba("retos", c.caminoBusqueda(42).toString().equals("[60, 35, 45, 40]") && c.contarEnRango(40,75)==5);
        c.insertar(new Libro(42,"Libro 42","Autor",2020)); c.eliminar(20); c.eliminar(70); c.eliminar(60);
        prueba("eliminaciones", codigos(c,TipoRecorrido.PREORDEN).equals("75 35 45 40 42 80 90"));
        prueba("inexistente", c.buscar(999)==null && !c.modificar(999,"x","y",2020) && !c.eliminar(999));
        boolean invalido=false; try { new Libro(0,"","",1800); } catch (IllegalArgumentException e) { invalido=true; } prueba("validación", invalido);
        CatalogoABB mal=DatosPrueba.crearBiblioteca().buscar("SW-E1").getCatalogo(); mal.getRaiz().getIzquierdo().setDerecho(new negocio.NodoLibro(new Libro(1000,"Mal","Autor",2020))); prueba("validación ABB", !mal.esABBValido());
        CatalogoABB reconstruido=CatalogoABB.desdePreorden(new int[]{40,20,10,30,60,50,70});
        prueba("reconstrucción desde preorden", codigos(reconstruido,TipoRecorrido.INORDEN).equals("10 20 30 40 50 60 70") && reconstruido.esABBValido());
        System.out.println(ok + "/" + total + " pruebas OK");
    }
    private String codigos(CatalogoABB c, TipoRecorrido t) { StringBuilder s=new StringBuilder(); for (Libro l:c.recorrer(t)) { if(s.length()>0)s.append(' '); s.append(l.getCodigo()); } return s.toString(); }
    public static void main(String[] args) { new PruebasBiblioteca().ejecutar(); }
}
