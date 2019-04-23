package com.example.carles.navigationdraw;

public enum TipusLloc {
    OTROS("Otros", 11),
    RESTAURANTE("Restaurante", 1),
    BAR("Bar", 2),
    COPAS("Copas", 3),
    ESPECTACULO("Espectáculo", 4),
    HOTEL("Hotel", 5),
    COMPRAS("Compras", 6),
    EDUCACION("Educación", 7),
    DEPORTE("Deporte", 8),
    NATURALEZA("Naturaleza", 9),
    GASOLINERA("Gasolinera", 10);
    private final String texto;
    private final int recurso;
    TipusLloc(String texto, int recurso) {
        this.texto = texto;
        this.recurso = recurso;
    }
    public String getTexto() { return texto; }
    public int getRecurso() { return recurso; }
}
