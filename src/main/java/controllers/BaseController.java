package controllers;

import gui.GuiScreens;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BaseController {

	@FXML private StackPane mainStackPane;
	
	@FXML
	public void initialize() throws Exception {
		GuiScreens.MENU.setPane(GuiScreens.MENU.getScreenLoader().load());
		GuiScreens.APP.setPane(GuiScreens.APP.getScreenLoader().load());
		GuiScreens.OPTION.setPane(GuiScreens.OPTION.getScreenLoader().load());
		GuiScreens.RESULT.setPane(GuiScreens.RESULT.getScreenLoader().load());
		setScreen(GuiScreens.MENU.getPane());
	}
	
	public void setScreen (Pane pane) {
		this.mainStackPane.getChildren().clear();
		this.mainStackPane.getChildren().add(pane);
	}
}
