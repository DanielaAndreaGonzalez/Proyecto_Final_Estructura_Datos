package co.edu.uniquindio.storify.models;


public class Usuario {
	
	
	private String usuario;
	private String contrasenia;	
	private ListaCircularSimple listaCancionesFavoritas;

	/**
	 * @return the listaCancionesFavoritas
	 */
	public ListaCircularSimple getListaCancionesFavoritas() {
		return listaCancionesFavoritas;
	}
	/**
	 * @param listaCancionesFavoritas the listaCancionesFavoritas to set
	 */
	public void setListaCancionesFavoritas(ListaCircularSimple listaCancionesFavoritas) {
		this.listaCancionesFavoritas = listaCancionesFavoritas;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
	
	
	
	

}
