/**
 * La clase Movimiento representa un movimiento del solitario en cruz, mantenido éste como
 * la celda origen, la celda que se salta y la celda destino.
 *
 * @author Algoritmica Y Complejidad (Plan 2014)
 * @version 1.0, (c)2015
 */

public class Movimiento {
    private final Posicion origen;
    private final Posicion salto;
    private final Posicion destino;

    /**
     * Construye e inicializa un objeto Movimiento con los valores origen, salto y destino indicados como parámetros
     *
     * @param origen  objeto Posicion que identifica la celda origen del salto
     * @param salto   objeto Posicion que identifica la celda que se salta
     * @param destino objeto Posicion que identica la celda destino del salto
     */
    public Movimiento(Posicion origen, Posicion salto, Posicion destino) {
        this.origen = origen;
        this.salto = salto;
        this.destino = destino;
    }

    /**
     * Devuelve el la posicion origen de este Movimiento
     *
     * @return la Posicion origen asociada a este objeto Movimiento
     */
    public Posicion getOrigen() {
        return origen;
    }

    /**
     * Devuelve la posicion que se salta en este Movimiento
     *
     * @return la Posicion que se salta asociada a este objeto Movimiento
     */
    public Posicion getSalto() {
        return salto;
    }

    /**
     * Devuelve el la posicion destino de este Movimiento
     *
     * @return la Posicion destino asociada a este objeto Movimiento
     */
    public Posicion getDestino() {
        return destino;
    }

    /**
     * Devuelve una representación de este objeto Movimiento en formato cadena de caracteres.
     *
     * @return representación en formato cadena de caracteres de este Movimiento
     */
    public String toString() {
        return "{canica en celda: " + origen +
                ", salto hueco: " + salto +
                ", hasta celda destino: " + destino + "}\n";
    }
}
