package app;

import enums.TipoRecorrido;
import enums.TipoUnidad;
import java.util.Scanner;
import modelo.Libro;
import modelo.UnidadBiblioteca;
import negocio.ArbolBiblioteca;
import negocio.NodoUnidad;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); ArbolBiblioteca biblioteca = DatosPrueba.crearBiblioteca();
        int op; do {
            System.out.println("===========================\n1 Cargar Predeterminado  \n2 Mostrar Jerarquía  \n3 Metodos de las Unidades  \n4 Mostrar Métricas  \n5 Metodos de los Libros  \n6 Recorridos con su Traza  \n7 Ejecutar los Retos  \n8 Ejecutar las Pruebas  \n0 Salir  \n===========================");
            op=leer(sc,"Opción: ");
            try {
                switch(op) {
                    case 1 -> biblioteca=DatosPrueba.crearBiblioteca();
                    case 2 -> System.out.print(biblioteca.mostrarJerarquia());
                    case 3 -> gestionarUnidades(sc, biblioteca);
                    case 4 -> mostrarMetricas(sc, biblioteca);
                    case 5 -> gestionarLibros(sc, biblioteca);
                    case 6 -> recorrido(sc,biblioteca);
                    case 7 -> ejecutarRetos(sc, biblioteca);
                    case 8 -> new PruebasBiblioteca().ejecutar();
                    case 0 -> System.out.println("Fin");
                    default -> System.out.println("Opción inválida");
                }
            } catch (RuntimeException e) { System.out.println("Error: "+e.getMessage()); }
        } while(op!=0); sc.close();
    }
    private static void gestionarUnidades(Scanner sc, ArbolBiblioteca a) {
        System.out.println("1 Buscar unidad  2 Agregar unidad  0 Regresar");
        int op=leer(sc,"Opción: ");
        if (op==1) { System.out.println("Resultado: "+a.buscar(texto(sc,"Código: "))); }
        else if (op==2) {
            String padre=texto(sc,"Código del padre: ");
            String codigo=texto(sc,"Código nuevo (mayúsculas): ");
            String nombre=texto(sc,"Nombre: ");
            TipoUnidad tipo=TipoUnidad.valueOf(texto(sc,"Tipo (AREA/ESTANTE): ").toUpperCase());
            System.out.println(a.agregar(padre,new UnidadBiblioteca(codigo,nombre,tipo)) ? "Unidad agregada" : "No se pudo agregar la unidad");
        }
    }
    private static void mostrarMetricas(Scanner sc, ArbolBiblioteca a) {
        System.out.println("Altura="+a.altura()+", hojas="+a.contarHojas()+", libros="+a.contarLibrosTotal());
        String codigo=texto(sc,"Código para consultar nivel (vacío para omitir): ");
        if (!codigo.isEmpty()) System.out.println("Nivel="+a.nivel(codigo));
    }
    private static void gestionarLibros(Scanner sc, ArbolBiblioteca a) {
        String codigoEstante=texto(sc,"Código del estante: ");
        NodoUnidad n=a.buscar(codigoEstante);
        if(n==null||n.getCatalogo()==null){System.out.println("Estante no encontrado");return;}
        System.out.println("1 Insertar  2 Buscar  3 Modificar  4 Eliminar  5 Contar  0 Regresar");
        int op=leer(sc,"Opción: "); int codigo;
        switch(op) {
            case 1 -> { codigo=leer(sc,"Código: "); String titulo=texto(sc,"Título: "); String autor=texto(sc,"Autor: "); int anio=leer(sc,"Año: "); System.out.println(n.getCatalogo().insertar(new Libro(codigo,titulo,autor,anio)) ? "Libro insertado" : "Código repetido"); }
            case 2 -> { codigo=leer(sc,"Código: "); System.out.println("Resultado: "+n.getCatalogo().buscar(codigo)); }
            case 3 -> { codigo=leer(sc,"Código: "); String titulo=texto(sc,"Nuevo título: "); String autor=texto(sc,"Nuevo autor: "); int anio=leer(sc,"Nuevo año: "); System.out.println(n.getCatalogo().modificar(codigo,titulo,autor,anio) ? "Libro modificado" : "Libro no encontrado"); }
            case 4 -> { codigo=leer(sc,"Código: "); System.out.println(n.getCatalogo().eliminar(codigo) ? "Libro eliminado" : "Libro no encontrado"); }
            case 5 -> System.out.println("Total de libros en el estante: "+n.getCatalogo().contar());
            default -> { }
        }
    }
    private static void recorrido(Scanner sc, ArbolBiblioteca a) {
        NodoUnidad n=a.buscar(texto(sc,"Código del estante: "));
        if(n==null||n.getCatalogo()==null){System.out.println("Estante no encontrado");return;}
        TipoRecorrido tipo=TipoRecorrido.valueOf(texto(sc,"Tipo (PREORDEN/INORDEN/POSORDEN): ").toUpperCase());
        System.out.println(n.getCatalogo().trazaRecorrido(tipo));
    }
    private static void ejecutarRetos(Scanner sc, ArbolBiblioteca a) {
        NodoUnidad n=a.buscar(texto(sc,"Código del estante: "));
        if(n==null||n.getCatalogo()==null){System.out.println("Estante no encontrado");return;}
        System.out.println("1 Camino de búsqueda  2 Contar en rango  3 Validar ABB  4 Reconstruir desde preorden");
        int op=leer(sc,"Opción: ");
        switch(op) {
            case 1 -> System.out.println("Camino: "+n.getCatalogo().caminoBusqueda(leer(sc,"Código: ")));
            case 2 -> System.out.println("Cantidad: "+n.getCatalogo().contarEnRango(leer(sc,"Límite inferior: "),leer(sc,"Límite superior: ")));
            case 3 -> System.out.println("¿Es ABB válido?: "+n.getCatalogo().esABBValido());
            case 4 -> System.out.println("Extra desdePreorden disponible mediante CatalogoABB.desdePreorden(int[])");
            default -> System.out.println("Opción inválida");
        }
    }
    private static String texto(Scanner sc,String p){System.out.print(p);return sc.nextLine().trim();}
    private static int leer(Scanner sc,String p){System.out.print(p); String x=sc.nextLine(); return Integer.parseInt(x.trim());}
}
