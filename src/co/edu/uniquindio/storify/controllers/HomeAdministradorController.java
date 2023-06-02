package co.edu.uniquindio.storify.controllers;

import co.edu.uniquindio.storify.Main;
import co.edu.uniquindio.storify.models.Autor;
import co.edu.uniquindio.storify.models.Cancion;
import co.edu.uniquindio.storify.models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
public class HomeAdministradorController {
	Main aplication;
	
	CrudRegistroViewcontroller crudRegistroViewcontroller;
	ModelFactoryController modelFactoryController;
	
	Usuario usuarioLogueado;
	
	
	ObservableList<Autor> autor = FXCollections.observableArrayList();
	
	@FXML
    private Button idAgregarCancion;

    @FXML
    private Button idbtnCrearArtista;

    @FXML
    private TabPane tblListaSolicitudAnuncios;

    @FXML
    private TextField txtA�oCancion;

    @FXML
    private TextField txtCaratulaCancion;

    @FXML
    private TextField txtDuracionCancion;

    @FXML
    private TextField txtGeneroCancion;

    @FXML
    private TextField txtNacionalidadArtista;

    @FXML
    private TextField txtNombreArtista;

    @FXML
    private TextField txtNombreCancion;

    @FXML
    private TextField txtNombreDelAlbum;
    
    @FXML
    private TextField txtCodigoArtista;
    
    @FXML
    private TextField txtTipoArtista;

    @FXML
    private TextField txtUrlCancion;
    
    
    @FXML
    private ComboBox<Autor> comboNombreAutor;
    
    @FXML
    private TextField txtGeneroConMasCanciones;
    
    @FXML
    private TextField txtArtistaMasPopular;
    
    @FXML
	void initialize()
	{
    	modelFactoryController = ModelFactoryController.getInstance();
    	crudRegistroViewcontroller = new CrudRegistroViewcontroller(modelFactoryController);
    	
    	consultarGeneroMasCanciones();
    	consultarArtistaPopular();
	}
    

    private void consultarArtistaPopular() {		
		String reultado = crudRegistroViewcontroller.consultarArtistaPopular();
		txtArtistaMasPopular.setText(reultado);
	}


	private void consultarGeneroMasCanciones() {
		String resultado = crudRegistroViewcontroller.consultarGeneroMasCanciones();
		txtGeneroConMasCanciones.setText(resultado);
	}


	@FXML
    void onActionAgregarCancion(ActionEvent event) {
    		agregarCancion();
    }


	@FXML
    void onActionCrearArtista(ActionEvent event) {
    	crearArtista();
    }
	
	 private void agregarCancion() {
			
	    	Autor artista = this.comboNombreAutor.getValue();
			String nombreCancion = this.txtNombreCancion.getText();
			String nombreAlbum = this.txtNombreDelAlbum.getText();
			String caratula = this.txtCaratulaCancion.getText();
			String anio = this.txtA�oCancion.getText();
			String duracion = this.txtDuracionCancion.getText();
			String genero = this.txtGeneroCancion.getText();
			String url = this.txtUrlCancion.getText();
			
			Cancion cancion = new Cancion();
			
			cancion =  crudRegistroViewcontroller.registerCancion(artista, nombreCancion, nombreAlbum, caratula, anio, duracion, genero, url);
			
			if(cancion!=null)
			{
				showMessage("Notificaci�n registro", "Autor creado", "El autor se ha creado con �xito", AlertType.INFORMATION);
			}else {
				 showMessage("Notificaci�n registro", "Usuario no creado", "El usuario no se ha creado", AlertType.ERROR);
			}
			
			consultarGeneroMasCanciones();
	 }
    
    private void crearArtista() {
    	
		String codigoArtista = txtCodigoArtista.getText();
		String nombreArtista = txtNombreArtista.getText();
		String nacionalidadArtista = txtNacionalidadArtista.getText();
		String tipoArtista = txtTipoArtista.getText();
		
		
		if(datosValidos(codigoArtista, nombreArtista, nacionalidadArtista, tipoArtista)) {
		
			Autor autor = null;
			
			autor = crudRegistroViewcontroller.registerAutor(codigoArtista, nombreArtista, nacionalidadArtista, tipoArtista);
			
			if(autor!=null)
			{
				showMessage("Notificaci�n registro", "Autor creado", "El autor se ha creado con �xito", AlertType.INFORMATION);
				clearFields();
			}else {
				 showMessage("Notificaci�n registro", "Usuario no creado", "El usuario no se ha creado", AlertType.ERROR);
			}
		}else {
			showMessage("Notificaci�n registro", "Usuario no creado", "Los datos ingresados son inv�lidos", AlertType.ERROR);
		}
		llenarComboAutores();
		
	}

	public Main getAplicacion() {
		return aplication;
	}
	public void setAplicacion(Main aplicacion, Usuario usuarioLogueado) {
		this.aplication = aplicacion;
		this.usuarioLogueado = usuarioLogueado;
		//llenarComboProductos();
		//llenarInformacionAnunciosTabla();	
		llenarComboAutores();
	}
	
	
	private boolean datosValidos(String codigo,String nombre,String nacionalidad,String tipoArtista)
	{
		String mensaje = "";
		
		if(codigo == null || codigo.equals(""))
			mensaje +="C�digo no es v�lido ";
		
		if(nombre == null || nombre.equals(""))
			mensaje += "Nombre no es v�lido";
		
		if(nacionalidad == null || nacionalidad.equals(""))
			mensaje += "La nacionalidad no es v�lida";
		
		if(tipoArtista == null || tipoArtista.equals(""))
			mensaje += "El tipo de artista no es v�lida";
		
		if(mensaje.equals(""))
		{
			return true;
		}
		else {
			showMessage("Notificaci�n registro ", "Datos inv�lidos", mensaje, AlertType.WARNING);
			 return false;
		}
	}
	
	private void showMessage(String titulo, String header, String contenido, AlertType alertType)
	 {
		 Alert alert = new Alert(alertType);
		 alert.setTitle(titulo);
		 alert.setHeaderText(header);
		 alert.setContentText(contenido);
		 alert.showAndWait();	 
	 }


	private void clearFields()
	{
		this.txtCodigoArtista.setText("");
		this.txtNombreArtista.setText("");
		this.txtNacionalidadArtista.setText("");
		this.txtTipoArtista.setText("");
	}

	
	public void llenarComboAutores()
	{
		if(modelFactoryController.getTienda().getListaAutores() != null && modelFactoryController.getTienda().getListaAutores().size() >0 ) 
		{	
			autor.addAll(modelFactoryController.getTienda().getListaAutores());
			this.comboNombreAutor.setItems(autor);		
		}	
	}
		
	
	
	
	
	
	
	
}
