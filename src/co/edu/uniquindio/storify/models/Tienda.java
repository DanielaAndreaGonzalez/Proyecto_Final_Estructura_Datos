package co.edu.uniquindio.storify.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import co.edu.uniquindio.storify.exceptions.RegistroException;
import co.edu.uniquindio.storify.persistence.ArchivoUtil;
import co.edu.uniquindio.storify.persistence.Persistencia;


public class Tienda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<Persona> listaUsers = new ArrayList<>();
	ArrayList<Autor> listaAutores = new ArrayList<>();
	ArrayList<Cancion> listaCanciones = new ArrayList<>();
	
	

	
	
	/**
	 * @return the listaCanciones
	 */
	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	/**
	 * @param listaCanciones the listaCanciones to set
	 */
	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}

	public ArrayList<Persona> getListaUsers() {
		return listaUsers;
	}

	public void setListaUsers(ArrayList<Persona> listaUsers) {
		this.listaUsers = listaUsers;
	}

	/**
	 * @return the listaAutores
	 */
	public ArrayList<Autor> getListaAutores() {
		return listaAutores;
	}

	/**
	 * @param listaAutores the listaAutores to set
	 */
	public void setListaAutores(ArrayList<Autor> listaAutores) {
		this.listaAutores = listaAutores;
	}

	public Persona registerPerson(String cedula,String nombre,String edad,String usuario,String contrasenia)throws RegistroException,IOException
	{
		Persona userNuevo = null;
		//boolean userExist = verifyPersonExists(cedula);
		boolean userExist = false;
		String mensaje = "";
		if(userExist == true)
		{
			throw new RegistroException("El usuario con cédula "+cedula+"ya existe");
		}
		else
		{
			userNuevo = new Persona();
			userNuevo.setCedula(cedula);
			userNuevo.setNombre(nombre);
			userNuevo.setUsuario(usuario);
			userNuevo.setEdad(edad);
			userNuevo.setContrasenia(contrasenia);
			getListaUsers().add(userNuevo);
			
			mensaje = "Se guardó el usuario con cédula "+userNuevo.getCedula()+
					  "nombre: "+userNuevo.getNombre()+",edad: "+userNuevo.getEdad()+
					  ",Usuario: "+userNuevo.getUsuario()+",contrasenia "+userNuevo.getContrasenia();
			
			
			//Persistencia.guardarRegistroLog(mensaje, 1, "Información registrar usuario");
			//Persistencia.guardarUsuarios(listaUsers);
		}
		return userNuevo;
	}
	
	public Autor registerAutor(String codigo, String nombre, String nacionalidad, String tipoArtista)throws RegistroException,IOException
	{
		boolean autorExist = false;
		Autor autorNuevo = null;
		String mensaje = "";
		if(autorExist == true)
		{
			throw new RegistroException("El artista ya existe");
		}else {
			autorNuevo = new Autor();
			 
			autorNuevo.setCodigo(codigo);
			autorNuevo.setNombreAutor(nombre);
			autorNuevo.setNacionalidad(nacionalidad);
			autorNuevo.setTipoArtista(tipoArtista);
			getListaAutores().add(autorNuevo);
			mensaje += "Se guardó el autor" +autorNuevo.getCodigo()+
					"Nombre: "+autorNuevo.getNombreAutor()+
					"Nacionalidad "+autorNuevo.getNacionalidad()+
					"Tipo Artista "+ autorNuevo.getTipoArtista();
			//Persistencia.guardarRegistroLog(mensaje, 1, "Registrar autor");
		}
		return autorNuevo;
		
	}
	
	public Cancion registerCancion(Autor autor,
			String nombreCancion,String nombreAlbum,String caratula,String anio,
			String duracion,String genero,String url)
	{
		
		Cancion cancionNueva = new Cancion();
		
		
		cancionNueva.setNombre(nombreCancion);
		cancionNueva.setNombreAlbum(nombreAlbum);
		cancionNueva.setCaratula(caratula);
		cancionNueva.setAnio(anio);
		cancionNueva.setDuracion(duracion);
		cancionNueva.setGenero(genero);
		cancionNueva.setUrl(url);
		int indexAnunciante = 0;
		for(int i = 0; i <getListaAutores().size(); i++)
		{		
			if(getListaAutores().get(i).getNombreAutor().equals(autor.getNombreAutor()))
			{
				indexAnunciante = i;
				break;				
			}
			
		}	
		getListaAutores().get(indexAnunciante).getListaCanciones().insertarInicio(cancionNueva);
		return cancionNueva;
	}
	
	
	
	
	
	
	public boolean verifyPersonExists(String cedula) {
		if(ArchivoUtil.searchPerson(cedula)) 
			return true;
		else
			return false;
	}

	public String consultarGeneroMasCanciones() {
		int acuRock = 0;
		int acuPop = 0;		
		int acuPunk = 0;
		int acuReggaeton = 0;
		int acuElectronica = 0;
		
		String generoMayor = "";
		int mayor=0;
		
		for (int i =0; i<getListaAutores().size(); i++) {
			for (Cancion cancionObj : getListaAutores().get(i).getListaCanciones().getListaCancion()) {
				switch (cancionObj.getGenero()) {
				case "rock":
					acuRock++;
					break;
				case "pop":
					acuPop++;
					break;
				case "punk":
					acuPunk++;
					break;
				case "reggaeton":
					acuReggaeton++;
					break;
				case "electronica":
					acuElectronica++;
					break;
				default:					
					break;
				}
				
				if(acuRock > mayor) {
					mayor = acuRock;
					generoMayor = "rock";
				}
				
				if(acuPop > mayor) {
					mayor = acuPop;
					generoMayor = "pop";
				}
				
				if(acuPunk>mayor) {
					mayor = acuPunk;
					generoMayor = "punk";
				}
				
				if(acuReggaeton>mayor) {
					mayor = acuReggaeton;
					generoMayor = "reggaeton";
				}
				if(acuElectronica>mayor) {
					mayor = acuElectronica;
					generoMayor = "electronica";
				}
			}
		}

		return generoMayor;
	}
	
}
