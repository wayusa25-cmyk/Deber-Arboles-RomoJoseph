package modelo;

public class Libro {
    private final int codigo;
    private String titulo;
    private String autor;
    private int anio;

    public Libro(int codigo, String titulo, String autor, int anio) {
        if (codigo <= 0) throw new IllegalArgumentException("El código debe ser positivo");
        validarTexto(titulo, "título");
        validarTexto(autor, "autor");
        if (anio < 1900 || anio > 2026) throw new IllegalArgumentException("El año debe estar entre 1900 y 2026");
        this.codigo = codigo; this.titulo = titulo; this.autor = autor; this.anio = anio;
    }

    private static void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) throw new IllegalArgumentException("El " + campo + " no puede estar vacío");
    }
    public int getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }
    public void actualizar(String titulo, String autor, int anio) {
        validarTexto(titulo, "título"); validarTexto(autor, "autor");
        if (anio < 1900 || anio > 2026) throw new IllegalArgumentException("El año debe estar entre 1900 y 2026");
        this.titulo = titulo; this.autor = autor; this.anio = anio;
    }
    @Override public String toString() { return codigo + " - " + titulo + " (" + autor + ", " + anio + ")"; }
}
