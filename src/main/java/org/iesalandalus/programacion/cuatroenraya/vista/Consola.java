package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.*;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	// CONSTRUCTOR VACÍO
	private Consola() {
	}

	// MÉTODOS
	public static String leerNombre() {
		String nombre;
		do {
			System.out.print("Introduce el nombre del jugador: ");
			nombre=Entrada.cadena();
		} while (nombre.equals("") || nombre == null);
		return nombre;
	}

	public static Ficha elegirColorFichas() {
		Ficha ficha;
		int opcion = 2;
		do {
			System.out.print("Elige el color de tus fichas (0-Azul, 1-Verde): ");
			opcion = Entrada.entero();
		} while (opcion == 2);

		if (opcion == 0) {
			ficha = Ficha.AZUL;
		}
		ficha = Ficha.VERDE;

		return ficha;
	}

	public static Jugador leerJugador() {

		System.out.println("Introduce los datos del PRIMER jugador ");
		String nombre = leerNombre();
		Ficha ficha = elegirColorFichas();

		Jugador jugador1 = new Jugador(nombre, ficha);
		return jugador1;
	}

	public static Jugador leerJugador(Ficha ficha) {

		System.out.println("Introduce los datos del SEGUNDO jugador ");
		String nombre = leerNombre();

		Jugador jugador2 = new Jugador(nombre, ficha);
		return jugador2;
	}

	public static int leerColumna(Jugador jugador) {

		int columna = 7;
		do {
			System.out.printf("%s, introduce la columna en la que deseas introducir la ficha (0-6): ",
					jugador.getNombre());
			columna = Entrada.entero();
		} while (columna < 0 || columna > 6);

		return columna;
	}

}
