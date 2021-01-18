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
	}

	// MÉTODOS
	private boolean columnaVacia(int columna) {
		if (casillas[0][columna] == null) {
			return true;
		}
		return false;
	}

	public boolean estaVacio() {
		for (int i = 0; i < COLUMNAS - 1; i++) {
			if ((columnaVacia(i) == false)) {
				return false;
			}
		}
		return true;
	}

	private boolean columnaLlena(int columna) {
		if (casillas[5][columna] != null) {
			return true;
		}
		return false;
	}

	public boolean estaLleno() {
		int lleno = 0;
		for (int i = 0; i < COLUMNAS - 1; i++) {
			if ((columnaLlena(i) == true)) {
				lleno += i;
			}
		}
		if (lleno >= COLUMNAS)
			return true;
		return false;
	}

	private void comprobarFicha(Ficha ficha) {
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}
	}

	private void comprobarColumna(int e) {
		if (e < COLUMNAS - 1 || e > COLUMNAS - 1) {
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}
	}

	private int getPrimeraFilaVacia(int columna) {
		int primeraFilaVacia = 0;
		for (int i = 0; i < FILAS - 1; i++) {
			if (casillas[i][0] != null) {
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

		int fichasAzules = 0;
		int fichasVerdes = 0;

		for (int i = 0; i < COLUMNAS - 1; i++) {
			if ((casillas[fila][i] != null)) {
				if (Ficha.AZUL == ficha) {
					fichasAzules++;
					fichasVerdes = 0;
				}
				if (Ficha.VERDE == ficha) {
					fichasVerdes++;
					fichasAzules = 0;
				}
			}
		}
		if (fichasAzules >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS
				|| fichasVerdes == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
		}
		return false;
	}

	private boolean comprobarVertical(int columna, Ficha ficha) {
		int fichasAzules = 0;
		int fichasVerdes = 0;

		for (int i = 0; i < FILAS - 1; i++) {
			if ((casillas[i][columna] != null)) {
				if (Ficha.AZUL == ficha) {
					fichasAzules++;
					fichasVerdes = 0;
				}
				if (Ficha.VERDE == ficha) {
					fichasVerdes++;
					fichasAzules = 0;
				}
			}
		}
		if (fichasAzules >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS
				|| fichasVerdes == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
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

		int fichasAzules = 0;
		int fichasVerdes = 0;

		for (int i = filaInicial; i < FILAS - 1; i++) {
			for (int j = columnaInicial; j < COLUMNAS - 1; j++) {
				if ((casillas[i][j] != null)) {
					if (Ficha.AZUL == ficha) {
						fichasAzules++;
						fichasVerdes = 0;
					}
					if (Ficha.VERDE == ficha) {
						fichasVerdes++;
						fichasAzules = 0;
					}
				}
			}

		}

		if (fichasAzules >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS
				|| fichasVerdes == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
		}
		return false;

	}

	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
		// Abajo derecha hasta arriba izquierda
		int desplazamientoInicial = menor(fila, ((COLUMNAS - 1) - columna));
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna + desplazamientoInicial;

		int fichasAzules = 0;
		int fichasVerdes = 0;

		for (int i = filaInicial; i < FILAS - 1; i++) {
			for (int j = columnaInicial; j < 0; j--) {
				if ((casillas[i][j] != null)) {
					if (Ficha.AZUL == ficha) {
						fichasAzules++;
						fichasVerdes = 0;
					}
					if (Ficha.VERDE == ficha) {
						fichasVerdes++;
						fichasAzules = 0;
					}
				}
			}

		}

		if (fichasAzules >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS
				|| fichasVerdes == FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
		}
		return false;
	}

	private boolean comprobarTirada(int fila, int columna) {// Debe aceptar la ultima ficha. No sé
		Ficha ficha;
		if (comprobarHorizontal(fila, Ficha.AZUL) || // Falta poner la última ficha, no la azul
				comprobarVertical(columna, Ficha.AZUL) || comprobarDiagonalNE(fila, columna, Ficha.AZUL)
				|| comprobarDiagonalNO(fila, columna, Ficha.AZUL))
			return true;
		return false;
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
		StringBuilder builder = new StringBuilder();

		builder.append("|       |\n");
		builder.append("|       |\n"); // FALTA AÑADIR FICHAS
		builder.append("|       |\n"); // No sé como usar el StringBuilder para añadir una variable, ni qué variable
										// añadir
		builder.append("|       |\n");
		builder.append("|       |\n");
		builder.append("|       |\n");
		builder.append(" -------\n");

		return builder.toString();

	}

}
