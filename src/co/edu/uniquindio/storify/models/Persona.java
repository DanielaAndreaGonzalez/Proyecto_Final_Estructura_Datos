/**
 * 
 */
package co.edu.uniquindio.storify.models;

import java.io.Serializable;

/**
 * @author GonzalezHDanielaA
 *
 */
public class Persona extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombre;
	private String edad;
	
	
	public Persona() {}
	
	
	
	
	public Persona(String cedula, String nombre, String edad) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.edad = edad;
	}




	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
	
	
	
}
