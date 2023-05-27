package co.edu.uniquindio.storify.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import co.edu.uniquindio.storify.dto.TableMusicaDto;
import co.edu.uniquindio.storify.exceptions.RegistroException;
import co.edu.uniquindio.storify.models.Autor;
import co.edu.uniquindio.storify.models.Cancion;
import co.edu.uniquindio.storify.models.Persona;
import co.edu.uniquindio.storify.models.Tienda;
import co.edu.uniquindio.storify.models.Usuario;
import co.edu.uniquindio.storify.persistence.Persistencia;

public class ModelFactoryController {

	Tienda tienda ;	

	//***********************************Singleton***********************************************
	//Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder{
		//El constructor de Singleton puede ser llamado desde aquí al ser protected 
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}
	//Metodo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance()
	{
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController()
	{
		tienda = new Tienda();
		cargarResourceXML();
		System.out.println("Se cargan datos ..");
	}
	
	private void guardarResourceXML()
	{
		Persistencia.guardarResourcetiendaXML(tienda);
	}
	
	private void cargarResourceXML() {
		tienda = Persistencia.cargarRecursoSubastaQuindioXML();
	}
	
	public Persona registerUsuario(String cedula,String nombre,String edad, String usuario,String contrasenia)
	{
		Persona persona =null;
		try {	
			persona = getTienda().registerPerson(cedula, nombre, edad, usuario, contrasenia);
			guardarResourceXML();
		} catch (RegistroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return persona;	
	}
	
	
	public Autor registerAutor(String codigo,String nombre, String nacionalidad,String tipoArtista)
	{
		Autor autor =null;
		try {
			autor = getTienda().registerAutor(codigo, nombre, nacionalidad, tipoArtista);
			guardarResourceXML();
		} catch (RegistroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autor;
	}
	
	
	public Cancion registerCancion(Autor autor,
			String nombreCancion,String nombreAlbum,String caratula,String anio,
			String duracion,String genero,String url)
	{
		Cancion cancion = null;
		
		cancion = getTienda().registerCancion(autor, nombreCancion, nombreAlbum, caratula, anio, duracion, genero, url);
		guardarResourceXML();
		
		return cancion;
	}
	
	
	/**
	 * @return the tienda
	 */
	public Tienda getTienda() {
		return tienda;
	}

	/**
	 * @param tienda the tienda to set
	 */
	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Usuario login(String usuarioIngresado, String contraseñaIngresada) {
		ArrayList<Map<String, Persona>> usuarios =  tienda.getListaUsers();
		Map<String,Persona> usuarioAux = null;
		Usuario usuarioEncontrado = null;
		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
		{
			usuarioAux = usuarios.get(indiceUsuario);					
			if (usuarioAux != null &&  usuarioAux.get(usuarioIngresado) != null
					&& usuarioAux.get(usuarioIngresado).getUsuario().equalsIgnoreCase(usuarioIngresado)
					&& usuarioAux.get(usuarioIngresado).getContrasenia().equalsIgnoreCase(contraseñaIngresada)) {
				usuarioEncontrado = usuarioAux.get(usuarioIngresado);
				return usuarioEncontrado;
			}
			usuarioAux = null;
		}
		return usuarioEncontrado;
	}

	public String consultarArtistaPopular() {
		//se debe de consultar en la lista de las canciones favoritas del cliente y hacer una suma por cada artista
		return "N/N";
	}

	public String consultarGeneroMasCanciones() {		
		return getTienda().consultarGeneroMasCanciones();
	}

	public List<TableMusicaDto> buscarCancionUsuario(String busqueda) {
		return getTienda().buscarCancionUsuario( busqueda);
	}

	public TableMusicaDto agregarCacionFavoritos(TableMusicaDto newSelectionCancion,Usuario usuarioLogueado) {
		TableMusicaDto registrado = getTienda().agregarCacionFavoritos(newSelectionCancion,usuarioLogueado);
		guardarResourceXML();
		return registrado;
	}

	public void reproducirCancion(TableMusicaDto newSelection) {
		getTienda().reproducirCancion(newSelection);
	}
}
