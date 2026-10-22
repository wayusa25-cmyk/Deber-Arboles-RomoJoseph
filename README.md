# Deber Arboles

Aplicación Java para representar el catálogo de la Biblioteca FISEI mediante un árbol general n-ario y un ABB por estante.

## Ejecución

Se recomienda abrir el programa desde Visual Studio Code y ejectuar el main dentro de /app

## Opciones del menú principal

Al ejecutar `app.Main`, el programa muestra un menú interactivo para trabajar con la biblioteca y sus catálogos de libros.

### 1. Cargar estructura inicial

Crea nuevamente la biblioteca de prueba definida en el enunciado:

- Biblioteca FISEI (`BIB`)
- Áreas: Software (`SW`), Tecnologías de la Información (`TI`) y Telecomunicaciones (`TEL`)
- Estantes: `SW-E1`, `SW-E2` y `TI-E1`
- Los 13 libros iniciales insertados en los ABB de cada estante.

Esta opción reinicia los cambios realizados durante la ejecución.

### 2. Mostrar jerarquía

Muestra la estructura de la biblioteca como árbol general n-ario. Permite visualizar la relación entre biblioteca, áreas y estantes.

Ejemplo:

```text
BIB Biblioteca FISEI [BIBLIOTECA]
|-- SW Software [AREA]
|   |-- SW-E1 Estante 1 de Software [ESTANTE]
|   \-- SW-E2 Estante 2 de Software [ESTANTE]
|-- TI Tecnologías de la Información [AREA]
|   \-- TI-E1 Estante 1 de TI [ESTANTE]
\-- TEL Telecomunicaciones [AREA]

Las clases están organizadas en `enums`, `modelo`, `negocio` y `app`. Las operaciones estructurales se implementan con recursividad y no se usan colecciones ordenadas ni métodos de ordenamiento.
```
## Captura de PruebasBiblioteca

<img width="423" height="372" alt="image" src="https://github.com/user-attachments/assets/f6c186c4-9f16-44a6-aa59-95b8a532b159" />
