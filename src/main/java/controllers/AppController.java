package controllers;

import data.DataStorage;
import gui.GuiScreens;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class AppController {
	@FXML private Button returnToMenuButton;
	@FXML private Button saveButton;
	@FXML private Button printButton;
	@FXML private Button removeButton;
	@FXML private Label label;
	@FXML private Slider slider;
	@FXML private TextArea textArea;
	@FXML private Label auxLabel1;
	@FXML private Label auxLabel2;
	private MainController mainController;
	private DataStorage dataStorage;
	private int currentSliderValue;
	private String newDataInfo;
	
	public AppController(MainController mainController, DataStorage dataStorage) {
		this.newDataInfo = "Wprowadz now¹ dan¹";
		this.mainController = mainController;
		this.dataStorage = dataStorage;
	}
	
	@FXML
	public void initialize () {
		sliderProperties();
		areaText(initialText());
		this.auxLabel1.textProperty().bind(Bindings.format("%.3f", slider.valueProperty()));
		this.auxLabel2.setText(((Integer)this.currentSliderValue).toString());
		
		EventHandler<ActionEvent> printData = e -> this.label.setText(this.textArea.getText());
		EventHandler<ActionEvent> returnToMenuEvent = e -> mainController.setScreen(GuiScreens.MENU.getPane());
		EventHandler<MouseEvent> activeSlider = e -> activeSlider();
		EventHandler<ActionEvent> saveData = e -> saveData();
		EventHandler<ActionEvent> removeData = e -> removeData();
		
		this.slider.addEventHandler(MouseEvent.MOUSE_DRAGGED, activeSlider);
		this.slider.addEventHandler(MouseEvent.MOUSE_RELEASED, activeSlider);
		this.saveButton.addEventHandler(ActionEvent.ACTION, saveData);
		this.printButton.addEventHandler(ActionEvent.ACTION, printData);
		this.removeButton.addEventHandler(ActionEvent.ACTION, removeData);
		this.returnToMenuButton.addEventHandler(ActionEvent.ACTION, returnToMenuEvent);
	}
	
	private void activeSlider() {
		int temporarySliderValue = sliderValueRoundedInt();
		if (isLastSliderPosition()) {
			areaText(this.newDataInfo);
		} else if (temporarySliderValue != this.currentSliderValue) {
			areaText(dataStorage.getData(sliderValueRoundedInt()));
		}
		this.currentSliderValue = sliderValueRoundedInt();
		this.auxLabel2.setText(((Integer)this.currentSliderValue).toString());
	}

	private void saveData() {
		if (noneNewData()) {
			labelText("brak nowej danej");
		} else if (isNoData()) {
			this.dataStorage.addData(this.textArea.getText());
			labelText("Pierwsza dana zosta³a dodana");
		} else if (isLastSliderPosition()) {
			this.dataStorage.addData(this.textArea.getText());
			labelText("Dane zosta³y dodane");
		} else if (isNotChanded()) {
			labelText("Dane bez zmian");
		} else {
			this.dataStorage.changeData(this.textArea.getText(), this.currentSliderValue);
			labelText("Dane zosta³y zmienione");
		}
		resizeSlider();
	}
	
	private void removeData() {
		if (isNoData()) {
			this.textArea.setText(this.newDataInfo);
			labelText(this.newDataInfo);
		} else if (isLastSliderPosition()) {
			labelText("Brak danych do usuniêcia");
		} else if (isNotChanded()) {
			this.dataStorage.removeData(currentSliderValue);
			this.currentSliderValue = sliderValueRoundedInt();
			if (isNoData() || isLastSliderPosition()) {
				areaText(this.newDataInfo);
			} else {
				areaText(this.dataStorage.getData(this.currentSliderValue));
			}
			labelText("Dane zosta³y usuniête");
		} else {
			labelText("B³êdne dane do usuniêcia");
		}
		resizeSlider();
	}

	private void labelText(String text) {
		this.label.setText(text);
	}
	private void areaText(String text) {
		this.textArea.setText(text);
	}
	private String initialText() {
		if (isNoData() || isLastSliderPosition()) {
			return this.newDataInfo;
		} else {
			return this.dataStorage.getData(this.currentSliderValue);
		}
	}
	private int sliderValueRoundedInt() {
		return ((Long)Math.round(this.slider.valueProperty().getValue())).intValue()-1;
	}
	private boolean noneNewData() {
		if (this.textArea.getText().equals(this.newDataInfo)) {
			return true;
		} else {
			return false;
		}
	}
	private boolean isNotChanded() {
		if (this.dataStorage.getData(this.currentSliderValue).equals(textArea.getText())) {
			return true;
		} else {
			return false;
		}
	}
	private boolean isLastSliderPosition() {
		if (this.sliderValueRoundedInt() == this.dataStorage.getSize()) {
			return true;
		} else {
			return false;
		}
	}
	private boolean isNoData() {
		if (this.dataStorage.isNoData()) {
			return true;
		} else {
			return false;
		}
	}
	private void resizeSlider() {
		if (isNoData()) {
			this.slider.setMin(0);
			this.slider.setMax(0);
		} else {
			this.slider.setMin(1);
			this.slider.setMax(this.dataStorage.getSize()+1);
		}
	}
	private void sliderProperties() {
		this.slider.setValue(1);
		this.currentSliderValue = sliderValueRoundedInt();
//		this.slider.setBlockIncrement(4);
		this.slider.setMajorTickUnit(1);
		this.slider.setMinorTickCount(0);
		this.slider.setSnapToTicks(true);
		this.slider.setShowTickLabels(true);
		this.slider.setMin(1);
		resizeSlider();
	}
}
