package controllers;

import gui.GuiScreens;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MenuController {

	MainController mainController;
	FXMLLoader menuScreenLoader;
	
	@FXML private Pane menuPane;
	@FXML private Button appButton;
	@FXML private Button resultButton;
	@FXML private Button optionButton;
	@FXML private Button quitButton;

	public MenuController(MainController mainController) {
		this.mainController = mainController;
	}
	
	@FXML
	public void initialize () {
		EventHandler<ActionEvent> openAppEvent = e -> mainController.setScreen(GuiScreens.APP.getPane());
		EventHandler<ActionEvent> openOprionEvent = e -> mainController.setScreen(GuiScreens.OPTION.getPane());
		EventHandler<ActionEvent> openResultEvent = e -> mainController.setScreen(GuiScreens.RESULT.getPane());
		EventHandler<ActionEvent> exitEvent = e -> Platform.exit();
		EventHandler<MouseEvent> mouseEnterEvent = e -> mouseEnter();
		EventHandler<MouseEvent> mouseExitEvent = e -> mouseExit();
		
		this.appButton.addEventHandler(ActionEvent.ACTION, openAppEvent);
		this.resultButton.addEventHandler(ActionEvent.ACTION, openResultEvent);
		this.optionButton.addEventHandler(ActionEvent.ACTION, openOprionEvent);
		this.quitButton.addEventHandler(ActionEvent.ACTION, exitEvent);
		this.quitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnterEvent);
		this.quitButton.addEventHandler(MouseEvent.MOUSE_EXITED, mouseExitEvent);
		
		this.mainController.setScreen(menuPane);
	}
	
	private void mouseEnter() {
		quitButton.setText("WYJŒCIE");
		quitButton.setStyle("-fx-background-color: #ff0000");
	}
	
	private void mouseExit() {
		quitButton.setText("Wyjœcie");
		quitButton.setStyle("");
	}
}
