package org.iesalandalus.programacion.cuatroenraya;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class MainApp {

	public static void main(String[] args) {
		
		Ficha ficha;
		Jugador jugador1 = Consola.leerJugador();

		if (jugador1.getColorFichas() == Ficha.AZUL) {
			ficha = Ficha.VERDE;
		} else
			ficha = Ficha.AZUL;

		Jugador jugador2 = Consola.leerJugador(ficha);
		CuatroEnRaya cuatroEnRaya = new CuatroEnRaya(jugador1, jugador2);
		cuatroEnRaya.jugar();

	}
}