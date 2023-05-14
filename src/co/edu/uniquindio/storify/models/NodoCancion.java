/**
 * 
 */
package co.edu.uniquindio.storify.models;

import java.io.Serializable;

/**
 * @author GonzalezHDanielaA
 *
 */
public class NodoCancion implements Serializable{
	private static final long serialVersionUID = 1L;
	private NodoCancion siguiente;
	private NodoCancion anterior;
	private Cancion datoCancion;
	
	public NodoCancion(Cancion datoCancion) {
		this.siguiente = null;
		this.anterior = null;
		this.datoCancion = datoCancion;
	}
	
	public NodoCancion getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoCancion siguiente) {
		this.siguiente = siguiente;
	}
	public NodoCancion getAnterior() {
		return anterior;
	}
	public void setAnterior(NodoCancion anterior) {
		this.anterior = anterior;
	}
	public Cancion getDato() {
		return datoCancion;
	}
	public void setDato(Cancion datoCancion) {
		this.datoCancion = datoCancion;
	}

	@Override
	public String toString() {
		return "NodoCancion [siguiente=" + siguiente + ", anterior=" + anterior + ", datoCancion=" + datoCancion + "]";
	}
	
	
}
