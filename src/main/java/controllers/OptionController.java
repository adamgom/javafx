package controllers;

import data.OptionSettings;
import gui.GuiScreens;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class OptionController {
	@FXML private CheckBox approvedCheckBox;
	@FXML private Button okButton;
	@FXML private DatePicker datePicker;
	@FXML private TextField textField;
	@FXML private ProgressBar progressBar;
	@FXML private Label label;
	@FXML private Button returnToMenuButton;

	private BaseController mainController;
	private OptionSettings optionSettings;
	
	public OptionController(BaseController mainController, OptionSettings optionSettings) {
		this.mainController = mainController;
		this.optionSettings = optionSettings;
	}
	
	@FXML
	public void initialize () {
		EventHandler<ActionEvent> write = e -> okButtonMethod();
		this.returnToMenuButton.addEventHandler(ActionEvent.ACTION, event -> mainController.setScreen(GuiScreens.MENU.getPane()));
		this.approvedCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click());
		this.okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click());
		this.okButton.addEventHandler(ActionEvent.ACTION, write);
		this.datePicker.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click());
		this.progressBar.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click());
		this.textField.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click());
		this.textField.textProperty().bindBidirectional(optionSettings.getTextField());;
		this.label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click());
		this.textField.textProperty().addListener((Observable, oldValue, newValue) -> this.label.textProperty().bind(optionSettings.getTextField())); 
	}
	
	private void click() {
		optionSettings.progress();
		this.progressBar.setProgress(optionSettings.getProgressBarProgress());
	}
	
	public void okButtonMethod() {
		this.label.textProperty().unbind();
		this.label.textProperty().set("OK!");
	}
}
