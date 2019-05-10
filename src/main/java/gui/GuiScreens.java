package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public enum GuiScreens {
	MENU("MenuScreen"),
	APP("AppScreen"),
	OPTION("OptionScreen"),
	RESULT("ResultScreen");

	private FXMLLoader screenLoader;
	private Pane pane;
	
	private GuiScreens(String guiFXMLResource) {
		this.screenLoader = new FXMLLoader(this.getClass().getResource("/FXML/" + guiFXMLResource + ".fxml"));
	}
	
	public FXMLLoader getScreenLoader() {
		return screenLoader;
	};
	
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
	public Pane getPane() {
		return pane;
	}
	
//	public void initPane() {
//		try {
//			this.pane = this.screenLoader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
