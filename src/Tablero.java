import java.util.ArrayList;
import java.util.Arrays;

/**
 * La clase Tablero representa el tablero de juego del solitario en cruz, así como la funcionalidad
 * necesaria para manejarlo.
 *
 * @author Algoritmica Y Complejidad (Plan 2014)
 * @version 1.0, (c)2015
 */

public class Tablero {

    /**
     * Valor a usar en una celda del tablero si tiene una canica
     */
    private static final char CANICA = 'X';

    /**
     * Valor a usar en una celda del tablero si tiene un hueco (sin canica)
     */
    private static final char HUECO = 'O';

    /**
     * Valor a usar en una celda del tablero si la celda no forma parte del tablero con el que se juega
     */
    private static final char SIN_USO = '-';

    /**
     * Numero de filas del tablero
     */
    private static final int ALTO = 7;

    /**
     * Numero de columnas del tablero
     */
    private static final int ANCHO = 7;

    /**
     * Desplazamiento en filas para los movimientos de una canica en el tablero
     */
    private static final int[] DX = {0, 0, 1, -1};

    /**
     * Desplazamiento en columnas para los movimientos de una canica en el tablero
     */
    private static final int[] DY = {1, -1, 0, 0};

    /**
     * Tablero con los valores de la situación inicial del juego (tablero en cruz, con 32 canicas
     * y la celda central vacía)
     */
    private final char[][] TABLERO_INCIAL = {
            {SIN_USO, SIN_USO, CANICA, CANICA, CANICA, SIN_USO, SIN_USO},
            {SIN_USO, SIN_USO, CANICA, CANICA, CANICA, SIN_USO, SIN_USO},
            {CANICA, CANICA, CANICA, CANICA, CANICA, CANICA, CANICA},
            {CANICA, CANICA, CANICA, HUECO, CANICA, CANICA, CANICA},
            {CANICA, CANICA, CANICA, CANICA, CANICA, CANICA, CANICA},
            {SIN_USO, SIN_USO, CANICA, CANICA, CANICA, SIN_USO, SIN_USO},
            {SIN_USO, SIN_USO, CANICA, CANICA, CANICA, SIN_USO, SIN_USO}
    };

    /**
     * Tablero con los valores de la situación final objetivo del juego (tablero en cruz, con 1 única canica
     * situada en la celda central del tablero)
     */
    private final char[][] TABLERO_SOLUCION = {
            {SIN_USO, SIN_USO, HUECO, HUECO, HUECO, SIN_USO, SIN_USO},
            {SIN_USO, SIN_USO, HUECO, HUECO, HUECO, SIN_USO, SIN_USO},
            {HUECO, HUECO, HUECO, HUECO, HUECO, HUECO, HUECO},
            {HUECO, HUECO, HUECO, CANICA, HUECO, HUECO, HUECO},
            {HUECO, HUECO, HUECO, HUECO, HUECO, HUECO, HUECO},
            {SIN_USO, SIN_USO, HUECO, HUECO, HUECO, SIN_USO, SIN_USO},
            {SIN_USO, SIN_USO, HUECO, HUECO, HUECO, SIN_USO, SIN_USO}
    };

    /**
     * Tablero a usar para mantener el estado del juego.
     */
    private char[][] tablero;

    /**
     * Construye e inicializa un objeto Tablero con los valores de la situación inicial del juego
     */
    public Tablero() {
        tablero = TABLERO_INCIAL;
    }

    /**
     * Determina si el Movimiento que se pasa como parámetro es un movimiento válido en el tablero actual
     *
     * @param movimiento Movimiento a evaluar
     * @return true si la celdas del Movimiento están dentro de los limites del tablero y se cumplen las
     * condiciones de salto válido del solitario (la celda origen tiene una canica, la celda que se salta
     * tiene una canica y la celda destino tiene un hueco); false en caso contrario
     */
    public boolean esMovimientoValido(Movimiento movimiento) {
        int filaOrigen = movimiento.getOrigen().getFila();
        int columnaOrigen = movimiento.getOrigen().getColumna();

        int filaSalto = movimiento.getSalto().getFila();
        int columnaSalto = movimiento.getSalto().getColumna();

        int filaDestino = movimiento.getDestino().getFila();
        int columnaDestino = movimiento.getDestino().getColumna();

        return filaOrigen >= 0 && filaOrigen <= 6 && columnaOrigen >= 0 && columnaOrigen <= 6
                && filaSalto >= 0 && filaSalto <= 6 && columnaSalto >= 0 && columnaSalto <= 6
                && filaDestino >= 0 && filaDestino <= 6 && columnaDestino >= 0 && columnaDestino <= 6
                && !(tablero[columnaOrigen][filaOrigen] == SIN_USO || tablero[columnaOrigen][filaOrigen] == HUECO)
                && !(tablero[columnaSalto][filaSalto] == SIN_USO || tablero[columnaSalto][filaSalto] == HUECO)
                && !(tablero[columnaDestino][filaDestino] == SIN_USO || tablero[columnaDestino][filaDestino] == CANICA);
    }

    /**
     * Anota en el tablero un movimiento (salto) de una canica
     *
     * @param movimiento Movimiento que identifica las celdas origen, salto y destino
     */
    public void anotarMovimiento(Movimiento movimiento) {
        tablero[movimiento.getOrigen().getColumna()][movimiento.getOrigen().getFila()] = HUECO;
        tablero[movimiento.getSalto().getColumna()][movimiento.getSalto().getFila()] = HUECO;
        tablero[movimiento.getDestino().getColumna()][movimiento.getDestino().getFila()] = CANICA;
    }

    /**
     * Desanota en el tablero un movimiento (salto) de una canica
     *
     * @param movimiento Movimiento que identifica las celdas origen, salto y destino
     */
    public void desanotarMovimiento(Movimiento movimiento) {
        tablero[movimiento.getOrigen().getColumna()][movimiento.getOrigen().getFila()] = CANICA;
        tablero[movimiento.getSalto().getColumna()][movimiento.getSalto().getFila()] = CANICA;
        tablero[movimiento.getDestino().getColumna()][movimiento.getDestino().getFila()] = HUECO;
    }

    /**
     * Genera una lista de Movimientos formada por todos los movimientos potenciales (sin comprobar si son válidos)
     * a partir del estado actual del tablero (movimientos para un nivel concreto de backtracking). Cada una de las celdas del
     * tablero generara 4 movimientos potenciales (salto al sur, salto al norte, salto al este y salto al oeste)
     *
     * @return lista que contiene todos los Movimientos potenciales a partir del estado actual del tablero
     */
    public ArrayList<Movimiento> obtenerMovimientos() {
        ArrayList<Movimiento> movsPotenciales = new ArrayList<>();

        for(int i = 0; i < ALTO; i++)
            for(int j = 0; j < ANCHO; j++)
                for(int k = 0; k < 4; k++)
                    movsPotenciales.add(new Movimiento(new Posicion(i, j), new Posicion(i + DX[k], j + DY[k]),
                        new Posicion(i + 2 * DX[k], j + 2 * DY[k])));

        return movsPotenciales;
    }

    /**
     * Determina si el tablero actual es solucion del solitario en cruz
     *
     * @return devuelve si el tablero es solución
     */
    public boolean esSolucion() {
        return Arrays.deepEquals(tablero, TABLERO_SOLUCION);
    }

    /**
     * Imprime por consola el contenido del tablero. Este método está completamente implementado
     */
    public void imprimirTablero() {
        System.out.print("    -");
        for (int x = 0; x < ANCHO; x++) {
            System.out.print("--");
        }
        System.out.println("--");
        System.out.print("    | ");
        for (int x = 0; x < ANCHO; x++) {
            System.out.print("" + x + " ");
        }
        System.out.println("|");
        System.out.print("-----");
        for (int x = 0; x < ANCHO; x++) {
            System.out.print("--");
        }
        System.out.println("--");
        for (int x = 0; x < ALTO; x++) {
            System.out.print("| " + x + " | ");
            for (int y = 0; y < ANCHO; y++) {
                if (tablero[x][y] == SIN_USO) System.out.print("  "); // casilla fuera de tablero
                else if (tablero[x][y] == CANICA) System.out.print("* "); // casilla con canica
                else System.out.print(tablero[x][y] + " "); // casilla vacia (hueco)
            }
            System.out.println("|");
        }
        System.out.print("-----");
        for (int x = 0; x < ANCHO; x++) {
            System.out.print("--");
        }
        System.out.println("--");
        System.out.println();
    }
}

