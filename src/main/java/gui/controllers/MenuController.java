package gui.controllers;

import gui.GuiScreens;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import main.Engine;

public class MenuController {
	FXMLLoader menuScreenLoader;
	
	@FXML private Pane menuPane;
	@FXML private Button appButton;
	@FXML private Button resultButton;
	@FXML private Button optionButton;
	@FXML private Button quitButton;

	public MenuController() {
	}
	
	@FXML
	public void initialize () {
		this.appButton.addEventHandler(ActionEvent.ACTION, e -> openApp());
		this.resultButton.addEventHandler(ActionEvent.ACTION, e ->  Engine.getInstance().getBaseController().setScreen(GuiScreens.RESULT.getPane()));
		this.optionButton.addEventHandler(ActionEvent.ACTION, e -> Engine.getInstance().getBaseController().setScreen(GuiScreens.OPTION.getPane()));
		this.quitButton.addEventHandler(ActionEvent.ACTION, e -> Platform.exit());
	}
	
	private void openApp() {
		Engine.getInstance().getDataStorageManager().dataFromFile();
		Engine.getInstance().getBaseController().setScreen(GuiScreens.APP.getPane());
		Engine.getInstance().appScreenOpen();
	}
}
