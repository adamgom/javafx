package gui.controllers;

import gui.GuiScreens;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BaseController {

	@FXML private StackPane stackPane;
	
	@FXML
	public void initialize() throws Exception {
		setScreen(GuiScreens.MENU.getPane());
	}
	
	public void setScreen (Pane pane) {
		this.stackPane.getChildren().clear();
		this.stackPane.getChildren().add(pane);
	}
}
