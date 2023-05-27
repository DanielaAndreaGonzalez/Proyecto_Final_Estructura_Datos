/**
 * 
 */
package co.edu.uniquindio.storify.controllers;

import java.util.List;

import co.edu.uniquindio.storify.dto.TableMusicaDto;
import co.edu.uniquindio.storify.models.Autor;
import co.edu.uniquindio.storify.models.Cancion;
import co.edu.uniquindio.storify.models.Persona;
import co.edu.uniquindio.storify.models.Tienda;
import co.edu.uniquindio.storify.models.Usuario;

/**
 * @author GonzalezHDanielaA
 *
 */
public class CrudRegistroViewcontroller {
	ModelFactoryController modelFactoryController;
	Tienda tienda;
	
	public CrudRegistroViewcontroller(ModelFactoryController modelFactoryController)
	{
		this.modelFactoryController = modelFactoryController;
		tienda =  modelFactoryController.getTienda();
	}		

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Persona registerUsuario(String cedula,String nombre,String edad, String usuario,String contrasenia)
	{
		return this.modelFactoryController.registerUsuario(cedula, nombre, edad, usuario, contrasenia);	
	}
	
		
	public Autor registerAutor(String codigo, String nombre, String nacionalidad,String tipoArtista) {
		return this.modelFactoryController.registerAutor(codigo,nombre,nacionalidad,tipoArtista);
	}

	public Usuario login(String usuarioIngresado, String contraseñaIngresada) {
		return this.modelFactoryController.login(usuarioIngresado,contraseñaIngresada);
	}
	
	public Cancion registerCancion(Autor autor,
			String nombreCancion,String nombreAlbum,String caratula,String anio,
			String duracion,String genero,String url)
	{
		return this.modelFactoryController.registerCancion(autor, nombreCancion, nombreAlbum, caratula, anio, duracion, genero, url);
	}

	public String consultarArtistaPopular() {
		return this.modelFactoryController.consultarArtistaPopular();
	}

	public String consultarGeneroMasCanciones() {
		return this.modelFactoryController.consultarGeneroMasCanciones();
	}

	public List<TableMusicaDto> buscarCancionUsuario(String busqueda) {
		return this.modelFactoryController.buscarCancionUsuario(busqueda);
	}

	public TableMusicaDto agregarCacionFavoritos(TableMusicaDto newSelectionCancion,Usuario usuarioLogueado) {
		return this.modelFactoryController.agregarCacionFavoritos(newSelectionCancion,usuarioLogueado);
	}

	public void reproducirCancion(TableMusicaDto newSelection) {
		this.modelFactoryController.reproducirCancion(newSelection);
	}
	
	
}
