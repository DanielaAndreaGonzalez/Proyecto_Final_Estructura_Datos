package co.edu.uniquindio.storify;
	
import java.io.IOException;

import co.edu.uniquindio.storify.controllers.HomeAdministradorController;
import co.edu.uniquindio.storify.controllers.HomeUserController;
import co.edu.uniquindio.storify.controllers.StorifyLoginController;
import co.edu.uniquindio.storify.models.Usuario;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tienda Musical UQ");
		mostrarVentanaPrincipal();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void mostrarVentanaPrincipal(){
		try {
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("views/StorifyLogin.fxml"));
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/StorifyLogin.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			StorifyLoginController storifyController = loader.getController();
			storifyController.setAplicacion(this);
			Scene scene = new Scene(root,880,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarVentanaAdministrador(Usuario usuariosLogueado) {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/HomeAdministrador.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			//AnunciantesViewController anunciantesViewController = loader.getController();
			HomeAdministradorController homeAdministradorController = loader.getController();
			
			homeAdministradorController.setAplicacion(this,usuariosLogueado);
			Scene scene = new Scene(rootLayout, 900,650);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}	
	
	public void mostrarVentanaUser(Usuario usuariosLogueado) {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/HomeUser.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			//AnunciantesViewController anunciantesViewController = loader.getController();
			HomeUserController homeUserController = loader.getController();
			
			homeUserController.setAplicacion(this,usuariosLogueado);
			Scene scene = new Scene(rootLayout, 900,650);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
}
