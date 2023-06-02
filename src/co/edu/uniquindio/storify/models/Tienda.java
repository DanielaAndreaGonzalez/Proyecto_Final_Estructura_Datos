package co.edu.uniquindio.storify.models;

import java.awt.Desktop;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import co.edu.uniquindio.storify.dto.TableMusicaDto;
import co.edu.uniquindio.storify.exceptions.RegistroException;
import co.edu.uniquindio.storify.persistence.ArchivoUtil;


public class Tienda implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	ArrayList<Map<String, Persona>> listaUsers = new ArrayList<>();
	ArrayList<Autor> listaAutores = new ArrayList<>();	
	ArrayList<Map<String, Integer>> listaAutorFavorito = new ArrayList<>();
	
	/**
	 * @return the listaAutorFavorito
	 */
	public ArrayList<Map<String, Integer>> getListaAutorFavorito() {
		return listaAutorFavorito;
	}

	/**
	 * @param listaAutorFavorito the listaAutorFavorito to set
	 */
	public void setListaAutorFavorito(ArrayList<Map<String, Integer>> listaAutorFavorito) {
		this.listaAutorFavorito = listaAutorFavorito;
	}

	public ArrayList<Map<String, Persona>> getListaUsers() {
		return listaUsers;
	}

	public void setListaUsers(ArrayList<Map<String, Persona>> listaUsers) {
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
		Map<String, Persona> mapUser = new HashMap<String, Persona>();
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
			userNuevo.setListaCancionesFavoritas(new ListaCircularSimple());
			
			//getListaUsers().add(userNuevo);
			mapUser.put(userNuevo.getUsuario(), userNuevo);
			getListaUsers().add(mapUser);
			
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
		
		//cancionNueva.setCodigo(codigo);
		cancionNueva.setCodigo(generarCodigoAleatorioUnico());
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
	
	
	
	
	
	
	private int generarCodigoAleatorioUnico() {
		int numero ;
		List<Integer> cancionList = new ArrayList<Integer>();		
		for(int i = 0; i <getListaAutores().size(); i++)
		{					
			for (Cancion cancion : getListaAutores().get(i).getListaCanciones().getListaCancion()) {
				cancionList.add(cancion.getCodigo());
			}
		}
		numero = Collections.max(cancionList) + 1;
		return numero;
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
	
	public String consultarArtistaPopular() {
		//se debe de consultar en la lista de las canciones favoritas del cliente y hacer una suma por cada artista
		int mayorEntero=0;
		String nombreArtista = "";
		for(Map<String,Integer> favoritas: listaAutorFavorito)
		{
			for(int mayor: favoritas.values())
			{
				if(mayor>mayorEntero)
				{
					mayorEntero = mayor;
					nombreArtista = favoritas.keySet().toString();
				}
			}
		}
		return nombreArtista;
	}
	
	
	
	

	public List<TableMusicaDto> buscarCancionUsuario(String busqueda) {
		List<TableMusicaDto> listaDeCancionesCoincidencia = new ArrayList<>();
		
		for (int i =0; i<getListaAutores().size(); i++) {
			for (Cancion cancionObj : getListaAutores().get(i).getListaCanciones().getListaCancion()) {
				if(cancionObj.getNombre().contains(busqueda)) {
					TableMusicaDto tableMusicaDto = new TableMusicaDto();
					tableMusicaDto.setCodigoCancion("" + cancionObj.getCodigo());
					tableMusicaDto.setNombreCancion(cancionObj.getNombre());
					tableMusicaDto.setNombreArtista(getListaAutores().get(i).getNombreAutor());
					listaDeCancionesCoincidencia.add(tableMusicaDto);
				}
			}
		}
		return listaDeCancionesCoincidencia;
	}

	public TableMusicaDto agregarCacionFavoritos(TableMusicaDto newSelectionCancion,Usuario usuarioLogueado) {
		//buscar cancion por codigo y nombre 
		Cancion cancionEncontrada = buscarCancionPorCodigoNombre(Integer.parseInt(newSelectionCancion.getCodigoCancion()), newSelectionCancion.getNombreCancion());
		//obtener ese objeto cancion 
		ArrayList<Map<String, Persona>> usuarios =  getListaUsers();
		Map<String,Persona> usuarioAux = null;		
		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
		{
			usuarioAux = usuarios.get(indiceUsuario);					
			if (usuarioAux != null &&  usuarioAux.get(usuarioLogueado.getUsuario()) != null) {
				//se agrega la cancion a la lista circular del usuario logueado como cancion favorita
				getListaUsers().get(indiceUsuario).get(usuarioLogueado.getUsuario()).getListaCancionesFavoritas().agregarAlinicio(cancionEncontrada);
			}
			usuarioAux = null;
		}		
		agregarAutorFavorito(newSelectionCancion.getNombreArtista());
		return newSelectionCancion;
	}

	public void agregarAutorFavorito(String nombreAutor)
	{
		if(verificarExisteArtistaFavorito(nombreAutor))
		{
			int posicion= obtenerPosicionArtistaFavorito(nombreAutor);
			int valor=0;
			valor = listaAutorFavorito.get(posicion).get(nombreAutor) + 1;	
			listaAutorFavorito.get(posicion).put(nombreAutor, valor);
		}
		else
		{
			Map<String,Integer> autorFavorito = new HashMap<>();
			autorFavorito.put(nombreAutor, 1);
			listaAutorFavorito.add(autorFavorito);
		}
		
	}
	
	public int obtenerPosicionArtistaFavorito(String nombreAutor)
	{
		int posicion =0;
		for(Map<String, Integer> autorFavorito:listaAutorFavorito)
		{
			if(autorFavorito.get(nombreAutor)!=null)
			{
				break;
			}
			posicion+=1;
		}
		return posicion;
	}
	public boolean verificarExisteArtistaFavorito(String nombreAutor)
	{
		
		for(Map<String, Integer> autorFavorito:listaAutorFavorito)
		{
			if(autorFavorito.get(nombreAutor)!=null)
			{
				return true;
			}
		}
		return false;
	}
	
	public void reproducirCancion(TableMusicaDto newSelection) {
		
		//buscar Cancion por codigo y nombre
		Cancion cancionEncontrada = buscarCancionPorCodigoNombre(Integer.parseInt(newSelection.getCodigoCancion()), newSelection.getNombreCancion());
		try {
			if (cancionEncontrada != null) {
				Desktop.getDesktop().browse(new URI(cancionEncontrada.getUrl()));				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Cancion buscarCancionPorCodigoNombre(int codigoCancion, String nombreCancion) {
		Cancion cancionEncontrada =  null;
		for (int i =0; i<getListaAutores().size(); i++) {
			for (Cancion cancionObj : getListaAutores().get(i).getListaCanciones().getListaCancion()) {
				if(cancionObj.getCodigo() == codigoCancion && cancionObj.getNombre().equals(nombreCancion)) {					
					cancionEncontrada = cancionObj;
				}
			}
		}
		return cancionEncontrada;
	}

		
	
	public void conteoCancionesFavoritas() {
		
		int conteoShakira =2;
		int conteoFonseca =1;
		ArrayList<Integer> conteos = new ArrayList<>();
		
		
		
		
		
		
		
		
		for(int i=0; i<getListaUsers().size(); i++) {
			for(int j=0; j< getListaUsers().get(i).get(i).getListaCancionesFavoritas().getListaCancion().size();j++)
				  {
					 System.out.println("Entró");
					   if(getListaUsers().get(i).get(i).getListaCancionesFavoritas().getListaCancion().get(i).getCodigo()== 1 ||
							   getListaUsers().get(i).get(i).getListaCancionesFavoritas().getListaCancion().get(i).getCodigo()==2)
					   {
						   conteoShakira++;
						   System.out.println("Entró");
						   
					   }
					   if(getListaUsers().get(i).get(i).getListaCancionesFavoritas().getListaCancion().get(i).getCodigo()==10)
					   {
						   conteoFonseca++;
					   }
				   	
			}
		}
		conteos.add(conteoShakira);
		conteos.add(conteoFonseca);
		JOptionPane.showMessageDialog(null,"Canciones de Shakira"+ conteoShakira);
		JOptionPane.showMessageDialog(null,"Canciones de Fonseca"+ conteoFonseca);	
	}
	
	
	
		
}
