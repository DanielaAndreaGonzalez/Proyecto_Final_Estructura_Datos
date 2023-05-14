module co.edu.uniquindio.storify {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.logging;
	requires java.desktop;
	
	opens co.edu.uniquindio.storify to javafx.graphics, javafx.fxml;
	opens co.edu.uniquindio.storify.controllers;
	opens co.edu.uniquindio.storify.models;	

}
