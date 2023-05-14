package co.edu.uniquindio.storify.controllers;

import co.edu.uniquindio.storify.Main;
import co.edu.uniquindio.storify.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class HomeUserController {
	Main aplication;
	
	Usuario usuarioLogueado;
	
	@FXML
    private Button idbtnBuscar;

    @FXML
    private TabPane tblListaSolicitudAnuncios;

    @FXML
    private TextField txtNombreArtista;

    @FXML
    void onActionBuscar(ActionEvent event) {
    	System.out.println("Buscando...");
    }
	    
	public Main getAplicacion() {
		return aplication;
	}
	public void setAplicacion(Main aplicacion, Usuario usuarioLogueado) {
		this.aplication = aplicacion;
		this.usuarioLogueado = usuarioLogueado;
		//llenarComboProductos();
		//llenarInformacionAnunciosTabla();	
	}
}
