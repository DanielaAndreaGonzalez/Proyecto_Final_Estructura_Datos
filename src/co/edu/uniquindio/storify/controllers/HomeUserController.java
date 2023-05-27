package co.edu.uniquindio.storify.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import co.edu.uniquindio.storify.Main;
import co.edu.uniquindio.storify.dto.TableMusicaDto;
import co.edu.uniquindio.storify.models.Cancion;
import co.edu.uniquindio.storify.models.Persona;
import co.edu.uniquindio.storify.models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeUserController {
	Main aplication;
	
	Usuario usuarioLogueado;
	CrudRegistroViewcontroller crudRegistroViewcontroller;
	ModelFactoryController modelFactoryController;
	ObservableList<TableMusicaDto> listaInformacionAnunciosData = FXCollections.observableArrayList();
	ObservableList<TableMusicaDto> listaInformacionCancionFavoriData = FXCollections.observableArrayList();
	List<TableMusicaDto> listacancionesEncontradas = new ArrayList<>();
	List<TableMusicaDto> listacancionesFavoritasEncontradas = new ArrayList<>();
	
	@FXML
    private Button idbtnBuscar;
	
	@FXML
    private TableView<TableMusicaDto> tablaListaMusica;
	
	@FXML
    private TableColumn<TableMusicaDto, String> columnaArtista;

    @FXML
    private TableColumn<TableMusicaDto, String> columnaCancion;     
    @FXML
    private TableColumn<TableMusicaDto, String> codigoCancion;
    
    @FXML
    private TableView<TableMusicaDto> tablaListaMusicaFavorita;
    
    @FXML
    private TableColumn<TableMusicaDto, String> columnaCancionFavorita;

    @FXML
    private TableColumn<TableMusicaDto, String> columnaArtistaFavorito;
    
    @FXML
    private TabPane tblListaSolicitudAnuncios;

    @FXML
    private TextField txtNombreArtista;

    @FXML
    void onActionBuscar(ActionEvent event) {
    	System.out.println("Buscando...");    	
    	String busqueda = txtNombreArtista.getText();
    	List<TableMusicaDto> listacancionesEncontradas = crudRegistroViewcontroller.buscarCancionUsuario(busqueda);
    	this.listacancionesEncontradas.addAll(listacancionesEncontradas);  
    	llenarTablaMusica();    	
    }     

	@FXML
   	void initialize()
   	{
		this.columnaArtista.setCellValueFactory(new PropertyValueFactory<>("nombreArtista"));
		this.columnaCancion.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
		this.codigoCancion.setCellValueFactory(new PropertyValueFactory<>("codigoCancion"));
		//this.columnaCancion.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
		
		this.columnaArtistaFavorito.setCellValueFactory(new PropertyValueFactory<>("codigoCancion"));
		this.columnaCancionFavorita.setCellValueFactory(new PropertyValueFactory<>("nombreCancion"));
		
		
       	modelFactoryController = ModelFactoryController.getInstance();
       	crudRegistroViewcontroller = new CrudRegistroViewcontroller(modelFactoryController);
       	iniciarTablaMusica();       	
       	
       	tablaListaMusica.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    		
    		
       		int returnValue = 0;
       		//TODO
       		//buscar si ya esta en la lista de favoritos no mostrar este mensaje
       		returnValue = JOptionPane.showConfirmDialog(null, "¿Desea añadir a favoritos?", "Añadir a favoritos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       		if (returnValue == JOptionPane.YES_OPTION) {
       			System.out.println(newSelection.getNombreCancion() + " " + newSelection.getCodigoCancion());
       			TableMusicaDto tableMusicaDto = this.crudRegistroViewcontroller.agregarCacionFavoritos(newSelection, this.usuarioLogueado);
       		}
       		returnValue = JOptionPane.showConfirmDialog(null, "¿Desea reproducir?", "Reproducir en el navegador", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       		if (returnValue == JOptionPane.YES_OPTION) {
       			System.out.println(newSelection.getNombreCancion() + " " + newSelection.getCodigoCancion());       		
       			this.crudRegistroViewcontroller.reproducirCancion(newSelection);
       		}    		
    	});
       	
   	}

	private void iniciarTablaMusica() {		
		for (int i =0; i<this.crudRegistroViewcontroller.getTienda().getListaAutores().size(); i++) {
			for (Cancion cancionObj : this.crudRegistroViewcontroller.getTienda().getListaAutores().get(i).getListaCanciones().getListaCancion()) {
				TableMusicaDto tableMusicaDto = new TableMusicaDto();
				tableMusicaDto.setCodigoCancion("" + cancionObj.getCodigo());
				tableMusicaDto.setNombreCancion(cancionObj.getNombre());
				tableMusicaDto.setNombreArtista(this.crudRegistroViewcontroller.getTienda().getListaAutores().get(i).getNombreAutor());
				this.listacancionesEncontradas.add(tableMusicaDto);
			}
		}
		llenarTablaMusica();
		
	}
	
	private void iniciarTablaMusicaFavorita() {
		
		ArrayList<Map<String, Persona>> usuariosFavoritas =  this.crudRegistroViewcontroller.getTienda().getListaUsers();
		Map<String,Persona> usuarioAux = null;	
		ArrayList<Cancion> cancionesFavoritasUsuario = new ArrayList<Cancion>();
		for (int i = 0; i < usuariosFavoritas.size(); i++) 
		{
			usuarioAux = usuariosFavoritas.get(i);					
			if (usuarioAux != null &&  usuarioAux.get(usuarioLogueado.getUsuario()) != null) {
				//se agrega la cancion a la lista circular del usuario logueado como cancion favorita
				cancionesFavoritasUsuario = usuariosFavoritas.get(i).get(usuarioLogueado.getUsuario()).getListaCancionesFavoritas().getListaCancion();
				for(int j =0; j <cancionesFavoritasUsuario.size(); j++) {
					TableMusicaDto tableMusicaDto = new TableMusicaDto();
					tableMusicaDto.setCodigoCancion("" + cancionesFavoritasUsuario.get(i).getCodigo());
					tableMusicaDto.setNombreCancion (cancionesFavoritasUsuario.get(i).getNombre());
					//tableMusicaDto.setNombreArtista(cancionesFavoritasUsuario.get(i).getCodigo());
					listacancionesFavoritasEncontradas.add(tableMusicaDto);
				}
				
			}
			usuarioAux = null;
		}	
	}

	public Main getAplicacion() {
		return aplication;
	}
	public void setAplicacion(Main aplicacion, Usuario usuarioLogueado) {
		this.aplication = aplicacion;
		this.usuarioLogueado = usuarioLogueado;
		//llenarComboProductos();
		//llenarInformacionAnunciosTabla();	
		iniciarTablaMusicaFavorita();
       	llenarTablaMusicaFavorita();
	}
	
	private void llenarTablaMusica() {
		 listaInformacionAnunciosData.addAll(listacancionesEncontradas);
		 tablaListaMusica.setItems(listaInformacionAnunciosData);
	}
	 
	private void llenarTablaMusicaFavorita() {
		listaInformacionCancionFavoriData.addAll(listacancionesFavoritasEncontradas);
		tablaListaMusicaFavorita.setItems(listaInformacionCancionFavoriData);
	}	 
}
