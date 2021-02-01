package org.iesalandalus.programacion.cuatroenraya;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class CuatroEnRaya {

	// ATRIBUTO Y CONSTANTE
	private static final int NUMERO_JUGADORES = 2;
	private Jugador[] jugadores;
	private Tablero tablero;

	// MÉTODOS
	public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
		if (jugador1 == null) {
			throw new NullPointerException("ERROR: Jugador 1 no puede ser nulo.");
		}
		if (jugador2 == null) {
			throw new NullPointerException("ERROR: Jugador 2 no puede ser nulo.");
		}

		jugadores = new Jugador[NUMERO_JUGADORES];
		jugadores[0] = jugador1; // Inicializar
		jugadores[1] = jugador2;
		tablero = new Tablero();
	}

	private boolean tirar(Jugador jugador) {
		boolean gana = false;
		boolean valida = false;
		do {
			try {
				gana = tablero.introducirFicha(Consola.leerColumna(jugador), jugador.getColorFichas());
				System.out.printf("%n%s%n", tablero);
				valida = true;
			} catch (OperationNotSupportedException e) {
				System.out.println(e.getMessage());
				valida = false;
			}
		} while (!valida);
		return gana;
	}

	public void jugar() {
		int turno = 0;
		boolean hayGanador = false;
		Jugador jugando = jugadores[turno];
		while (!tablero.estaLleno() && !hayGanador) {
			jugando = jugadores[turno++ % NUMERO_JUGADORES]; // Alterno entre el jugador 0 y el 1
			hayGanador = tirar(jugando);
		}
		if (hayGanador) {
			System.out.printf("ENHORABUENA, %s has ganado.", jugando.toString());
		} else {
			System.out.println("El tablero está lleno. La partida acabó en EMPATE.");
		}
	}

}
