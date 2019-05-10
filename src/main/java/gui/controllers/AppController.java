package gui.controllers;

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
import main.Engine;

public class AppController {
	@FXML private Button returnToMenuButton, addButton, printButton, removeButton, saveButton;
	@FXML private Slider slider;
	@FXML private TextArea textArea;
	@FXML private Label label, auxLabel1, auxLabel2;

	private BaseController mainController;
	private DataStorage dataStorage;
	private int currentSliderValue;
	private String newDataInfo;
	
	public AppController(BaseController mainController) {
		this.newDataInfo = "Wprowadz now¹ dan¹";
		this.mainController = mainController;
//		this.dataStorage = Engine.getInstance().getDataStorage();
	}
	
	@FXML
	public void initialize () {
		auxLabel1.textProperty().bind(Bindings.format("%.3f", slider.valueProperty()));
//		auxLabel2.setText(((Integer)this.currentSliderValue).toString());
		slider.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> activeSlider());
		slider.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> activeSlider());
		addButton.addEventHandler(ActionEvent.ACTION, e -> addData());
		printButton.addEventHandler(ActionEvent.ACTION, e -> this.label.setText(this.textArea.getText()));
		removeButton.addEventHandler(ActionEvent.ACTION, e -> removeData());
		returnToMenuButton.addEventHandler(ActionEvent.ACTION, e -> mainController.setScreen(GuiScreens.MENU.getPane()));
//		saveButton.addEventHandler(ActionEvent.ACTION, e -> Engine.getInstance().getFileManager().writeFile(dataStorage.dataToFile(), false));
//		textArea.setText(dataStorage.getData(0));
		sliderProperties();
	}

	private void activeSlider() {
		int temporarySliderValue = sliderValueRoundedInt();
//		if (isLastSliderPosition()) {
//			textArea.setText(newDataInfo);
//		} else if (temporarySliderValue != currentSliderValue) {
//			textArea.setText(dataStorage.getData(sliderValueRoundedInt()));
//		}
//		currentSliderValue = sliderValueRoundedInt();
		if (currentSliderValue != temporarySliderValue) {
			System.out.println(dataStorage.getData(temporarySliderValue));
			textArea.setText(dataStorage.getData(temporarySliderValue));
			currentSliderValue = temporarySliderValue;
		}
		
		auxLabel2.setText(((Integer)currentSliderValue).toString());
		resizeSlider();
	}

	private int sliderValueRoundedInt() {
		return Math.max(((Long)Math.round(slider.valueProperty().getValue())).intValue()-1,0);
	}
	
	private void resizeSlider() {
		if (dataStorage.isNoData()) {
			slider.setMin(0);
			slider.setMax(0);
		} else {
			slider.setMin(1);
			slider.setMax(dataStorage.getSize()+1);
		}
	}
	
	private void sliderProperties() {
		slider.setValue(1);
		currentSliderValue = sliderValueRoundedInt();
//		this.slider.setBlockIncrement(4);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setSnapToTicks(true);
		slider.setShowTickLabels(true);
		slider.setMin(1);
//		resizeSlider();
	}
	
	private void addData() {
//		if (noneNewData()) {
//			label.setText("brak nowej danej");
//		} else if (dataStorage.isNoData()) {
//			dataStorage.addData(textArea.getText());
//			label.setText("Pierwsza dana zosta³a dodana");
//		} else if (isLastSliderPosition()) {
//			dataStorage.addData(textArea.getText());
//			label.setText("Dane zosta³y dodane");
//		} else if (isNotChanded()) {
//			label.setText("Dane bez zmian");
//		} else {
//			dataStorage.changeData(textArea.getText(), currentSliderValue);
//			label.setText("Dane zosta³y zmienione");
//		}
//		resizeSlider();
	}
	
	private void removeData() {
		if (dataStorage.isNoData()) {
			textArea.setText(newDataInfo);
			label.setText(newDataInfo);
		} else if (isLastSliderPosition()) {
			label.setText("Brak danych do usuniêcia");
		} else if (isNotChanded()) {
			dataStorage.removeData(currentSliderValue);
			currentSliderValue = sliderValueRoundedInt();
			if (dataStorage.isNoData() || isLastSliderPosition()) {
				textArea.setText(newDataInfo);
			} else {
				textArea.setText(dataStorage.getData(currentSliderValue));
			}
			label.setText("Dane zosta³y usuniête");
		} else {
			label.setText("B³êdne dane do usuniêcia");
		}
		resizeSlider();
	}

	private String initialText() {
		if (dataStorage.isNoData() || isLastSliderPosition()) {
			return newDataInfo;
		} else {
			return dataStorage.getData(currentSliderValue);
		}
	}
	
	
	private boolean noneNewData() {
		return textArea.getText().equals(newDataInfo);
	}

	private boolean isNotChanded() {
		return dataStorage.getData(currentSliderValue).equals(textArea.getText());
	}

	private boolean isLastSliderPosition() {
		return sliderValueRoundedInt() == dataStorage.getSize();
	}
	

}
