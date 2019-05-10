package gui.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import data.BeanResultData;
import data.ResultData;
import gui.GuiScreens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import main.Engine;

public class ResultController {
	@FXML private Pane resultPane;
	@FXML private Button returnToMenuButton, newButton, cancelButton, addButton, removeButton, exit;
	@FXML private DatePicker datePicker;
	@FXML private TextField textField;
	@FXML private ListView<BeanResultData> listView, removeView;
	@FXML private Label labelAdd, labelRemove;
	
	private ResultData resultData;
	private BaseController mainController;
	private int selectedIndex;
	private long selectedDate;
	
	public ResultController(BaseController mainController) {
		this.mainController = mainController;
//		this.resultData = Engine.getInstance().getResultData();
	}
	
	@FXML
	private void initialize() {
		listView.itemsProperty().bind(resultData.listProperty);
		removeView.itemsProperty().bind(resultData.listProperty);
		initialSettings();

		this.returnToMenuButton.addEventHandler(ActionEvent.ACTION, e -> {setDisable();this.mainController.setScreen(GuiScreens.MENU.getPane());});
		this.addButton.addEventHandler(ActionEvent.ACTION, e -> addNewData());
		this.datePicker.addEventHandler(ActionEvent.ACTION, e -> selectDate());
		this.newButton.addEventHandler(ActionEvent.ACTION, e -> newData());
		this.cancelButton.addEventHandler(ActionEvent.ACTION, e -> cancelNewData());
		this.removeButton.addEventHandler(ActionEvent.ACTION, e -> removeData());
		this.removeView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectIndex());
		this.removeView.addEventHandler(KeyEvent.ANY, e -> selectIndex());
		this.datePicker.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectDate());
		this.exit.addEventHandler(ActionEvent.ACTION, e -> System.exit(0));
	}
	
	private void selectIndex() {
		this.selectedIndex = removeView.getSelectionModel().getSelectedIndex();
	}
	
	private void selectDate() {
		this.selectedDate = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
	}
	
	private void cancelNewData() {
		setDisable();
		this.textField.clear();
	}
	
	private void removeData() {
		if (this.selectedIndex < 0) {
			this.labelRemove.setText("brak danych lub brak zaznaczenia");
			return;
		}
		this.labelRemove.setText("Usuniêto: " + resultData.getResultListItem(selectedIndex));
		resultData.removeFromResultList(selectedIndex);
		this.selectedIndex = -1;
	}
	
	private void addNewData() {
		this.resultData.addToResultList(new BeanResultData(textField.getText(), selectedDate + curentTimeInDay()));
		this.labelAdd.setText("Dodano: " + resultData.getResultListItem(-1)); 
		setDisable();
	}
	
	private void newData() {
		setActive();
		this.datePicker.setValue(LocalDate.now());
		selectDate();
	}
	
	private void setActive() {
		this.labelAdd.setText("czekam na nowe dane");
		this.textField.clear();
		this.addButton.setDisable(false);
		this.cancelButton.setDisable(false);
		this.textField.setDisable(false);
		this.newButton.setDisable(true);
		this.datePicker.setDisable(false);
	}

	private void setDisable() {
		this.addButton.setDisable(true);
		this.cancelButton.setDisable(true);
		this.textField.setDisable(true);
		this.newButton.setDisable(false);
		this.datePicker.setDisable(true);
	}
	
	private void initialSettings() {
		this.selectedIndex = -1;
		setDisable();
		this.labelAdd.setText("Inicjacja rezultatów");
		this.labelRemove.setText("Inicjacja rezultatów");
	}
	
	private long curentTimeInDay() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.HOUR) *1000*60*60 + cld.get(Calendar.MINUTE) *1000*60 + cld.get(Calendar.SECOND) *1000;
	}
}
