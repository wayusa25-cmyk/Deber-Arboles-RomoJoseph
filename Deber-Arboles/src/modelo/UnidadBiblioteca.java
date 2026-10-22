package modelo;

import enums.TipoUnidad;

public class UnidadBiblioteca {
    private final String codigo;
    private final String nombre;
    private final TipoUnidad tipo;

    public UnidadBiblioteca(String codigo, String nombre, TipoUnidad tipo) {
        if (codigo == null || codigo.trim().isEmpty() || !codigo.equals(codigo.toUpperCase())) throw new IllegalArgumentException("El código debe estar en mayúsculas");
        if (nombre == null || nombre.trim().isEmpty() || tipo == null) throw new IllegalArgumentException("Datos de unidad inválidos");
        this.codigo = codigo; this.nombre = nombre; this.tipo = tipo;
    }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public TipoUnidad getTipo() { return tipo; }
    @Override public String toString() { return codigo + " " + nombre + " [" + tipo + "]"; }
}
