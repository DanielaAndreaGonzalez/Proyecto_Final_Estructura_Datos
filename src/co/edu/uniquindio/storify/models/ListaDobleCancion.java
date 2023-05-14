package co.edu.uniquindio.storify.models;

import java.io.Serializable;
import java.util.ArrayList;



public class ListaDobleCancion implements Serializable{
	private static final long serialVersionUID = 1L;
	private NodoCancion inicio;
	private NodoCancion fin;
	private int tamanio;
	private ArrayList<Cancion> listaCancion = new ArrayList<Cancion>();
	
	
	public ListaDobleCancion() {
		inicio = null;
		fin = null;
	}

	public void insertarInicio(Cancion dato)
	{
		NodoCancion nuevo = new NodoCancion(dato);
		if(inicio==null)
		{
			inicio = nuevo;
			fin = nuevo;	
		}
		else
		{
			inicio.setAnterior(nuevo);
			nuevo.setSiguiente(inicio);
			inicio=nuevo;
		}
		tamanio++;
		listaCancion.add(nuevo.getDato());
	}
	
	public void insertarFinal(Cancion dato)
	{
		NodoCancion nuevo = new NodoCancion(dato);
		if(inicio==null)
		{
			inicio = nuevo;
			fin = nuevo;	
		}
		else
		{
			fin.setSiguiente(nuevo);
			nuevo.setAnterior(fin);
			fin=nuevo;
		}
		listaCancion.add(nuevo.getDato());
	}
	
	public void recorrerAdelante()
	{
		NodoCancion actual = inicio;
		while(actual != null)
		{
			System.out.print(""+ "->"+actual.getDato() + "<-");
			actual = actual.getSiguiente();		
		}
	}
	
	public void recorrerRegreso() {
		NodoCancion actual = fin;

		do {
			System.out.print("->" + actual.getDato() + "<-");
			actual = actual.getAnterior();
		} while (actual != null);

	}
	
	public Cancion buscarDato(Cancion dato)
	{
		NodoCancion actual = inicio;
		Cancion encontrado = null;
		while(actual!=null)
		{
			if(actual.getDato() == dato)
			{
				encontrado = dato;
			}
			actual = actual.getSiguiente();
		}
		return encontrado;
	}
	
	
	
	

	/**
	 * @return the tamanio
	 */
	public int getTamanio() {
		return tamanio;
	}


	/**
	 * @param tamanio the tamanio to set
	 */
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}


	/**
	 * @return the listaCancion
	 */
	public ArrayList<Cancion> getListaCancion() {
		return listaCancion;
	}


	/**
	 * @param listaCancion the listaCancion to set
	 */
	public void setListaCancion(ArrayList<Cancion> listaCancion) {
		this.listaCancion = listaCancion;
	}

	@Override
	public String toString() {
		return "ListaDobleCancion [inicio=" + inicio + ", fin=" + fin + ", tamanio=" + tamanio + "]";
	}
	
	
	
	
}
