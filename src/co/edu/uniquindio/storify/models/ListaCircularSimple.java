/**
 * 
 */
package co.edu.uniquindio.storify.models;

import java.util.ArrayList;

/**
 * @author GonzalezHDanielaA
 *
 */
public class ListaCircularSimple {
	
	
	private Nodo inicio;
	private Nodo ultimo;
	private int tamanio;
	private ArrayList<Cancion> listaCancion = new ArrayList<Cancion>();
	
	
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

	public void ListaCircular()
	{
		inicio = null;
		ultimo = null;
		tamanio=0;
	}
	
	/**
	 * Consulta si la lista es vacia 
	 * @return true si el primer nodo (inicio), no apunta a otro
	 */
	public boolean esVacia()
	{
		return inicio ==null;
	}
	
	/**
	 * Consulta cuántos elementos (nodos) tiene la lista. 
	 * @return numero entero entre [0,n] donde n es el numero de elementos que
	 * contena la lista
	 */
	public int getTamanio()
	{
		return this.tamanio;
	}
	
	public void agregarAlFinal(Cancion bus)
	{
		//Define un nuevo nodo
		Nodo nuevo = new Nodo();
		//Agrega el valor al nodo
		nuevo.setValor(bus);
		//Consulta si la lista esta vacía
		if(esVacia())
		{
			//Inicializa la lista agregando como inicio al nuevo nodo
			inicio = nuevo;
			//De igual forma el ultimo nodo sera el nuevo.
			ultimo = nuevo;
			//Y el puntero del ultimo debe apuntar al primero
			ultimo.setSiguiente(inicio);
			//Caso contrario el nodo se agrega al final de la lista
		}
		else {
			//Apuntamos con el ultimo nodo de la lista al nuevo.
			ultimo.setSiguiente(nuevo);
			//Apuntamos con el nuevo nodo al inicio de la lista.
			nuevo.setSiguiente(inicio);
			//El nuevo nodo es el ultimo se actualiza  la variable ultimo
			ultimo = nuevo;
		}
		//Incrementa el contador de tamaño de la lista
		tamanio++;
	}
	 
	public void agregarAlinicio(Cancion bus)
	{
		//Define un nuevo nodo
		Nodo nuevo = new Nodo();
		
		//Agrega el valor al nodo 
		nuevo.setValor(bus);
		
		//consulta si la lista esta vacia
		if(esVacia())
		{
			//Inicializa la lista agregando como inicio al nuevo nodo .
			inicio = nuevo;
			//El ultimo nodo sera el nuevo
			ultimo= nuevo;
			// y el puntero del ultimo debe apuntar al primero 
			ultimo.setSiguiente(inicio);
		//Caso contrario va aagregando los nodos al inicio de la lista
		}else {
			//Une el nuevo nodo con la lista existente 
			nuevo.setSiguiente(inicio);
			//Renombra al nuevo nodo como el inicio de la lista
			inicio = nuevo;
			//El puntero del ultimo debe apuntar al primero
			ultimo.setSiguiente(inicio);
		}
		listaCancion.add(nuevo.getValor());
		//Incrementa el contador del tamanio de la lista
		tamanio++;
	
	}
	
	public void listar(){
        // Verifica si la lista contiene elementoa.
        if (!esVacia()) {
            // Crea una copia de la lista.
            Nodo aux = inicio;
            // Posicion de los elementos de la lista.
            int i = 0;
            System.out.print("-> ");
            // Recorre la lista hasta llegar nuevamente al incio de la lista.
            do{
            	for(int j=0; j < 5;j++)
            	{
            		 // Imprime en pantalla el valor del nodo.
                    //System.out.println(i + ".[ Código: " + aux.getValor().getCodigo() +" Placa: "+aux.getValor().getCodigo()+" Ruta: "+aux.getValor().getRuta()+ " ]" + " ->  ");
                    System.out.println("");
                    // Avanza al siguiente nodo.
                    aux = aux.getSiguiente();
                    // Incrementa el contador de la posión.
                    i++;  
            	}
               
            }while(aux != inicio);
        }
    }
	

}
