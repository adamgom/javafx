package controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import data.BeanResultData;
import data.ResultData;
import gui.GuiScreens;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ResultController {
	@FXML private Pane resultPane;
	@FXML private Button returnToMenuButton, newButton, cancelButton, addButton, removeButton, exitButton;
	@FXML private DatePicker datePicker;
	@FXML private TextField textField;
	@FXML private ListView<BeanResultData> listView, removeView;
	@FXML private Label labelAdd, labelRemove;
	
	private MainController mainController;
	private ResultData resultData;
	private int selectedIndex;
	private long selectedDate;
	
	public ResultController(MainController mainController, ResultData resultData) {
		this.mainController = mainController;
		this.resultData = resultData;
	}
	
	@FXML
	private void initialize() {
//		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				System.out.println("click");
//			}
//		};
		listView.itemsProperty().bind(resultData.listProperty);
		removeView.itemsProperty().bind(resultData.listProperty);
		initialSettings();
		EventHandler<ActionEvent> returnToMenuAction = e -> {setDisable();this.mainController.setScreen(GuiScreens.MENU.getPane());};  
		EventHandler<ActionEvent> selectDate = e -> selectDate();
		EventHandler<KeyEvent> selectItemToRemoveKey = e -> selectIndex();
		EventHandler<MouseEvent> selectItemToRemoveMouse = e -> selectIndex();

		this.returnToMenuButton.addEventHandler(ActionEvent.ACTION, returnToMenuAction);
		this.addButton.addEventHandler(ActionEvent.ACTION, event -> addNewData());
		this.datePicker.addEventHandler(ActionEvent.ACTION, selectDate);
		this.newButton.addEventHandler(ActionEvent.ACTION, event -> newData());
		this.cancelButton.addEventHandler(ActionEvent.ACTION, event -> cancelNewData());
		this.removeButton.addEventHandler(ActionEvent.ACTION, event -> removeData());
		this.removeView.addEventHandler(MouseEvent.MOUSE_CLICKED, selectItemToRemoveMouse);
		this.removeView.addEventHandler(KeyEvent.ANY, selectItemToRemoveKey);
		this.exitButton.addEventHandler(ActionEvent.ACTION, event -> Platform.exit());
		this.datePicker.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> selectDate());
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
		this.labelRemove.setText("Usuni�to: " + resultData.getResultListItem(selectedIndex));
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
		this.labelAdd.setText("Inicjacja rezultat�w");
		this.labelRemove.setText("Inicjacja rezultat�w");
	}
	
	private long curentTimeInDay() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.HOUR) *1000*60*60 + cld.get(Calendar.MINUTE) *1000*60 + cld.get(Calendar.SECOND) *1000;
	}
}