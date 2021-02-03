package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Tablero {

	// CONSTANTES
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

	// ATRIBUTO
	private Casilla[][] casillas;

	// CONSTRUCTOR
	public Tablero() {
		casillas = new Casilla[FILAS][COLUMNAS];
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}

	// MÉTODOS
	private boolean columnaVacia(int columna) {
		return !casillas[0][columna].estaOcupada();
	}

	public boolean estaVacio() {
		boolean vacio = true;
		for (int columna = 0; columna < COLUMNAS && vacio; columna++) {
			vacio &= columnaVacia(columna);
		}
		return vacio;
	}

	private boolean columnaLlena(int columna) {
		return casillas[FILAS - 1][columna].estaOcupada();
	}

	public boolean estaLleno() {
		boolean lleno = true;
		for (int i = 0; i < COLUMNAS && lleno; i++) {
			lleno &= columnaLlena(i);
		}
		return lleno;
	}

	private void comprobarFicha(Ficha ficha) {
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
	}

	private void comprobarColumna(int e) {
		if (e < 0 || e > COLUMNAS - 1) {
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}
	}

	private int getPrimeraFilaVacia(int columna) {
		int primeraFilaVacia = 0;
		for (int i = 0; i < FILAS; i++) {
			if (casillas[i][columna].estaOcupada()) {
				primeraFilaVacia++;
			}
		}
		return primeraFilaVacia;
	}

	private boolean objetivoAlcanzado(int fichas) {
		if (fichas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
		}
		return false;
	}

	private boolean comprobarHorizontal(int fila, Ficha ficha) {
		int contador = 0;
		for (int i = 0; i < COLUMNAS; i++) {
			if (casillas[fila][i].getFicha() == ficha) {
				contador++;
				if (objetivoAlcanzado(contador))
					return true;
			} else {
				contador = 0;
			}
		}
		return false;
	}

	private boolean comprobarVertical(int columna, Ficha ficha) {
		int contador = 0;

		for (int i = 0; i < FILAS; i++) {
			if (casillas[i][columna].getFicha() == ficha) {
				contador++;
				if (objetivoAlcanzado(contador))
					return true;
			} else {
				contador = 0;
			}
		}
		return false;
	}

	private int menor(int num1, int num2) {
		return Math.min(num1, num2);
	}

	private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
		// Abajo izquierda hasta arriba derecha
		int desplazamientoInicial = menor(fila, columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna - desplazamientoInicial;
		int contador = 0;

		for (int i = filaInicial; i < FILAS; i++) {
			for (int j = columnaInicial; j < COLUMNAS; j++) {
				if (casillas[i][j].getFicha() == ficha) {
					contador++;
					if (objetivoAlcanzado(contador))
						return true;
				} else {
					contador = 0;
				}
			}
		}
		return false;
	}

	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
		// Abajo derecha hasta arriba izquierda

		int desplazamientoInicial = menor(fila, ((COLUMNAS - 1) - columna));
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna + desplazamientoInicial;
		int fichasConsecutivas = 0;

		for (int i = filaInicial; i < FILAS - 1; i++) {
			for (int j = columnaInicial; j < 0; j--) {

				if (casillas[fila][i].getFicha() == ficha) {
					fichasConsecutivas++;
					if (objetivoAlcanzado(fichasConsecutivas))
						return true;
				} else {
					fichasConsecutivas = 0;
				}

			}
		}
		return objetivoAlcanzado(fichasConsecutivas);
	}

	private boolean comprobarTirada(int fila, int columna) {
		Ficha ficha = casillas[fila][columna].getFicha();

		return comprobarHorizontal(fila, ficha) || comprobarVertical(columna, ficha)
				|| comprobarDiagonalNE(fila, columna, ficha) || comprobarDiagonalNO(fila, columna, ficha);
	}

	public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {

		comprobarFicha(ficha);
		comprobarColumna(columna);

		if (columnaLlena(columna))
			throw new OperationNotSupportedException("ERROR: Columna llena.");

		if (comprobarTirada(getPrimeraFilaVacia(columna), columna))
			return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder salida = new StringBuilder("|");
		for (int i = FILAS - 1; i >= 0; i--) {
			for (int j = 0; j < COLUMNAS; j++) {
				salida.append(casillas[i][j]);
			}
			salida.append(i == 0 ? "|\n" : "|\n|");
		}

		
		
		String barraHorizontal = String.format(String.format(" %%0%dd%n", COLUMNAS), 0).replace('0', '-');
		salida.append(barraHorizontal);
		return salida.toString();
	}

}
