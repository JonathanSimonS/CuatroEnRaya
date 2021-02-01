package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jugador {

	// ATRIBUTOS
	private String nombre;
	private Ficha colorFichas;

	// CONSTRUCTOR CON PARÁMETROS
	public Jugador(String nombre, Ficha ficha) {
		setNombre(nombre);
		setColorFichas(ficha);
	}

	// MÉTODOS GET Y SET
	public Ficha getColorFichas() {
		return colorFichas;
	}

	private void setColorFichas(Ficha colorFichas) {
		if (colorFichas == null) {
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		}
		this.colorFichas = colorFichas;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {

		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (nombre.equals("") || nombre.equals("   ")) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		}
		
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.format("%2$s (%1$s)", colorFichas, nombre);
	}

}
