package org.iesalandalus.programacion.cuatroenraya;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class CuatroEnRaya {

	// ATRIBUTO Y CONSTANTE
	private static final int NUMERO_JUGADORES = 2;
	private Jugador[] jugadores; // Declaro referencia al array (nula)
	private Tablero tablero;

	// MÉTODOS
	public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
		if (jugador1 == null || jugador2 == null)
			throw new NullPointerException("ERROR: Jugador nulo.");

		jugadores = new Jugador[NUMERO_JUGADORES];

		jugadores[0] = jugador1; // Inicializar
		jugadores[1] = jugador2;

		tablero = new Tablero();
	}

	private boolean tirar(Jugador jugador) throws OperationNotSupportedException {

		boolean gana = false;
		try {
			do {
				int columna = Consola.leerColumna(jugador); // Nos devuelve la columna que eliga el jugador
				gana = tablero.introducirFicha(columna, jugador.getColorFichas()); // Devuelve si la jugada gana
				tablero.toString();
				if (gana == true) {
					gana = true;
				}
				gana = false;
			} while (gana = false);
		} catch (Exception e) {
			e.getMessage();
		}
		return gana;
	}

	public void jugar() throws OperationNotSupportedException {
		boolean resultado = false;
		do {
			for (Jugador jugador : jugadores) { // Se ejecutará 2 veces, y se evalúa el while
				resultado = tirar(jugador);
			}
			
		} while (!tablero.estaLleno() || !resultado);
		if (tablero.estaLleno()) {
			System.out.println("El tablero está lleno. La partida acabó en EMPATE.");
		}
		System.out.printf("ENHORABUENA, %s HAS GANADO.", jugadores.toString());

	}

}
