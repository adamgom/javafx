package gui.controllers;

import gui.GuiScreens;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import main.Engine;

public class OptionController {
	@FXML private CheckBox checkBox;
	@FXML private Button okButton;
	@FXML private DatePicker datePicker;
	@FXML private TextField textField;
	@FXML private ProgressBar progressBar;
	@FXML private Label label;
	@FXML private Button returnToMenuButton;

	private BaseController mainController;
	private StringProperty textFieldProperty;
	private double progressBarProgress;
	
	public OptionController(BaseController mainController) {
		this.mainController = mainController;
//		this.checkBox.setSelected(true);
		this.datePicker = null;
		this.progressBarProgress = 0.0;
		this.textFieldProperty = new SimpleStringProperty("init value");
				//(this, "textFieldProperty", "init value");
	}
	
	@FXML
	public void initialize () {
		this.checkBox.setSelected(true);
		EventHandler<ActionEvent> write = e -> okButtonMethod();
		this.returnToMenuButton.addEventHandler(ActionEvent.ACTION, event -> mainController.setScreen(GuiScreens.MENU.getPane()));
		this.okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click());
		this.okButton.addEventHandler(ActionEvent.ACTION, write);
		this.textField.textProperty().bindBidirectional(textFieldProperty);
//		this.label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> click());
//		this.textField.textProperty().addListener((Observable, oldValue, newValue) -> this.label.textProperty().bind(textFieldProperty)); 
	}

	private void click() {
		String newLine = ", ";
		if (checkBox.isSelected()) {
			newLine = "\r\n";
		}
		
		this.progressBar.setProgress(progressBarProgress += 0.02);
		Engine.getInstance().getFileManager().writeFile(newLine + textField.getText(), checkBox.isSelected());
	}
	
	public void okButtonMethod() {
		this.label.textProperty().unbind();
		this.label.textProperty().set("OK!");
	}
}
