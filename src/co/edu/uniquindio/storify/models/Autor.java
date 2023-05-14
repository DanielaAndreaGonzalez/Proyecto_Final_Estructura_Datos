package co.edu.uniquindio.storify.models;

import java.io.Serializable;

public class Autor extends Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nombreAutor;
	private String nacionalidad;
	private String tipoArtista;
	
	private ListaDobleCancion listaCanciones = new ListaDobleCancion();
	
	public Autor() {}
	
	public Autor(String codigo, String nombreAutor, String nacionalidad, String tipoArtista) {
		super();
		this.codigo = codigo;
		this.nombreAutor = nombreAutor;
		this.nacionalidad = nacionalidad;
		this.tipoArtista = tipoArtista;
	}	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombreAutor
	 */
	public String getNombreAutor() {
		return nombreAutor;
	}
	/**
	 * @param nombreAutor the nombreAutor to set
	 */
	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}
	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	/**
	 * @param nacionalidad the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * @return the tipoArtista
	 */
	public String getTipoArtista() {
		return tipoArtista;
	}
	/**
	 * @param tipoArtista the tipoArtista to set
	 */
	public void setTipoArtista(String tipoArtista) {
		this.tipoArtista = tipoArtista;
	}
	/**
	 * @return the listaCanciones
	 */
	public ListaDobleCancion getListaCanciones() {
		return listaCanciones;
	}

	/**
	 * @param listaCanciones the listaCanciones to set
	 */
	public void setListaCanciones(ListaDobleCancion listacanciones) {
		this.listaCanciones = listacanciones;
	}

	@Override
	public String toString() {
		return codigo + "-"+nombreAutor;
	}
}
