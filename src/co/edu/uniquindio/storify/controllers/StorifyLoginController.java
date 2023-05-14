package co.edu.uniquindio.storify.controllers;


import java.io.IOException;

import javax.swing.JOptionPane;

import co.edu.uniquindio.storify.Main;
import co.edu.uniquindio.storify.models.Usuario;
import co.edu.uniquindio.storify.persistence.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class StorifyLoginController {
		Main aplication;
		
		CrudRegistroViewcontroller crudRegistroViewcontroller;
		
		ModelFactoryController modelFactoryController;
		
		@FXML
	    private Button idIngresarLogin;

	    @FXML
	    private Button idRegistrasPersona;

	    @FXML
	    private TabPane tblListaSolicitudAnuncios;

	    @FXML
	    private TextField txtcedula;

	    @FXML
	    private PasswordField txtconfirmarContrasena;

	    @FXML
	    private PasswordField txtcontrasena;

	    @FXML
	    private PasswordField txtcontrasenaautenticarse;

	    @FXML
	    private TextField txtedad;

	    @FXML
	    private TextField txtnombre;

	    @FXML
	    private TextField txtusuario;

	    @FXML
	    private TextField txtusuarioautenticarse;
	    
	    
	    @FXML
		void initialize()
		{
	    	modelFactoryController = ModelFactoryController.getInstance();
	    	crudRegistroViewcontroller = new CrudRegistroViewcontroller(modelFactoryController);
		}
	    
	    
	    
	    @FXML 
	    void ingresar(ActionEvent event) {
			 ingresarLogin();
	    }
	    
	    @FXML
	    void registrarPerson(ActionEvent event) {
			 registerPerson();	 
	    }
	    
	    public Main getAplicacion() {
			return aplication;
		}
	    
	    public void setAplicacion(Main aplicacion) {
			this.aplication = aplicacion;
		}
	    
	    private void ingresarLogin() {
	    	//capturar usuario y contrase�a
			 String usuarioIngresado =  txtusuarioautenticarse.getText();
			 String contrase�aIngresada =  txtcontrasenaautenticarse.getText();
			 			 
			 if(usuarioIngresado.equals("admin") && contrase�aIngresada.equals("admin")) {
				 Usuario usuarioLogin =  new Usuario();
				 getAplicacion().mostrarVentanaAdministrador(usuarioLogin);
			 }else {
				 Usuario usuarioLogin = this.crudRegistroViewcontroller.login(usuarioIngresado, contrase�aIngresada);
				 if(usuarioLogin == null) {
					JOptionPane.showMessageDialog(null, "Usuario no existe");
				 }else {
					 getAplicacion().mostrarVentanaUser(usuarioLogin);
				 }
				 //Usuario usuarioLogin = Persistencia.obtenerUsuario(usuarioIngresado, contrase�aIngresada);					 
			 }			
	    }
	    
	    private void registerPerson()
		{
	    	 //1. Capturar los datos
			 String cedula = txtcedula.getText(); 
			 String nombre = txtnombre.getText();
			 String edad = txtedad.getText();
			 String usuario = txtusuario.getText();
			 String contrasenia = txtcontrasena.getText();
			 
			 if(datosValidos(cedula, nombre, edad, usuario, contrasenia)) {
				 Usuario usuario2 = null;
				 usuario2 = crudRegistroViewcontroller.registerUsuario(cedula, nombre, edad, usuario, contrasenia);
				 
				 if(usuario2 != null)
				 {
					 showMessage("Notificaci�n registro", "Usuario creado", "El usuario se ha creado con �xito", AlertType.INFORMATION);
					 clearFields();
				 }else {
					 showMessage("Notificaci�n registro", "Usuario no creado", "El usuario no se ha creado", AlertType.ERROR);
				 }
			 }
			 else {
				 showMessage("Notificaci�n registro", "Usuario no creado", "Los datos ingresados son inv�lidos", AlertType.ERROR);
			 }
		}
	    
	    
	    private boolean datosValidos(String cedula,String nombre,String edad,String usuario,String contrasenia)
	    {
	    	String mensaje = "";
	    	
	    	if(cedula == null || cedula.equals(""))
				 mensaje += "La cedula es inv�lida \n";
			 
			 if(nombre == null || nombre.equals(""))
				 mensaje += "El nombre es invalido \n";
			 
			 if(edad ==null || edad.equals(" "))
				 mensaje += "La edad es invalidad \n";
			 
			 if(usuario == null || usuario.equals(""))
				 mensaje += "El usuario es invalido \n";
			 
			 if(contrasenia == null || contrasenia.equals(""))
				 mensaje += "La contrasena es invalida \n";
			 
			 if(mensaje.equals(""))
			 {
				 return true;
			 }else
			 {
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
	    	txtcedula.setText("");
			 txtnombre.setText("");
			 txtedad.setText("");
			 //txtrol.setValue(null);
			 txtusuario.setText("");
			 txtcontrasena.setText("");
	    }

}
