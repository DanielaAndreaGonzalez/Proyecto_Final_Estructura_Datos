package co.edu.uniquindio.storify.models;

import java.io.Serializable;

/**
 * 
 * @author GonzalezHDanielaA
 *
 */
public class Cancion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nombre;
	private String nombreAlbum;
	private String caratula;
	private String anio;
	private String duracion;
	private String genero;
	private String url;
	
	
	public Cancion() {}
	
	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @param nombreAlbum
	 * @param caratula
	 * @param anio
	 * @param duracion
	 * @param genero
	 * @param url
	 */
	public Cancion(int codigo, String nombre, String nombreAlbum, String caratula, String anio, String duracion,
			String genero, String url) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nombreAlbum = nombreAlbum;
		this.caratula = caratula;
		this.anio = anio;
		this.duracion = duracion;
		this.genero = genero;
		this.url = url;
	}

/**
 * 
 * @return
 */
	public int getCodigo() {
		return codigo;
	}

/**
 * 
 * @param codigo
 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

/**
 * 
 * @return
 */
	public String getNombre() {
		return nombre;
	}

/**
 * 
 * @param nombre
 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

/**
 * 
 * @return
 */
	public String getNombreAlbum() {
		return nombreAlbum;
	}

/**
 * 
 * @param nombreAlbum
 */
	public void setNombreAlbum(String nombreAlbum) {
		this.nombreAlbum = nombreAlbum;
	}

/**
 * 
 * @return
 */
	public String getCaratula() {
		return caratula;
	}

/**
 * 
 * @param caratula
 */
	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

/**
 * 
 * @return
 */
	public String getAnio() {
		return anio;
	}

/**
 * 
 * @param anio
 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

/**
 * 
 * @return
 */
	public String getDuracion() {
		return duracion;
	}

/**
 * 
 * @param duracion
 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

/**
 * 
 * @return
 */
	public String getGenero() {
		return genero;
	}

/**
 * 
 * @param genero
 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

/**
 * 
 * @return
 */
	public String getUrl() {
		return url;
	}

/**
 * 
 * @param url
 */
	public void setUrl(String url) {
		this.url = url;
	}

@Override
public String toString() {
	return "Cancion [codigo=" + codigo + ", nombre=" + nombre + ", nombreAlbum=" + nombreAlbum + "]";
}
	
	
	
	



}
