package co.edu.uniquindio.storify.models;

import java.io.Serializable;

public class Nodo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Cancion valor;
	
	private Nodo siguiente;
	
	public void Nodo()
	{
		this.valor=null;
		this.siguiente=null;
	}

	

	public Cancion getValor() {
		return valor;
	}



	public void setValor(Cancion valor) {
		this.valor = valor;
	}



	public Nodo getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo siguiente) {
		this.siguiente = siguiente;
	}

}
