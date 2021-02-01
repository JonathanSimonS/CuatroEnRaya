package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {

	// ATRIBUTO
	private Ficha ficha;

	// CONSTRUCTOR
	public Casilla() {
		ficha = null;
	}

	// METODOS GET Y SET
	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException, NullPointerException {
		if (ficha == null)
			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
		if (estaOcupada())
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		this.ficha = ficha;
	}

	public boolean estaOcupada() {
		if (ficha == null) {
			return false;
		}
		return true;
	}

	// MÉTODO TOSTRING
	@Override
	public String toString() {
		return String.format("%s", estaOcupada() ? ficha.name().charAt(0) : " ");
		//Si está ocupada devuelve el caracter de la posición 0 de ficha, si no, un espacio
	}
}
