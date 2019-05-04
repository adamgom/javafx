package gui;

import controllers.AppController;
import controllers.BaseController;
import controllers.MenuController;
import controllers.OptionController;
import controllers.ResultController;
import data.DataStorage;
import data.OptionSettings;
import data.ResultData;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Gui {
	Stage primaryStage;
	StackPane stackPane;
	Scene scene;
	BaseController mainController;
	OptionSettings optionSettings;
	
	DataStorage dataStorage;
	ResultData resultData;
	
	public Gui(Stage primaryStage) throws Exception {
		this.dataStorage = new DataStorage();
		this.optionSettings = new OptionSettings();
		this.resultData = new ResultData();
		
		for (int i = 0 ; i < 2 ; i++) {
			this.dataStorage.addData("Adam" + i);
			this.dataStorage.addData("Ania" + i);
			this.dataStorage.addData("Piotr" + i);
			this.dataStorage.addData("Agata" + i);
		}
		
		this.primaryStage = primaryStage;
		this.mainController = new BaseController();
		GuiScreens.BASE.getScreenLoader().setController(mainController);
		
		GuiScreens.MENU.getScreenLoader().setController(new MenuController(this.mainController));
		GuiScreens.APP.getScreenLoader().setController(new AppController(this.mainController, this.dataStorage));
		GuiScreens.OPTION.getScreenLoader().setController(new OptionController(this.mainController, this.optionSettings));
		GuiScreens.RESULT.getScreenLoader().setController(new ResultController(this.mainController, this.resultData));
		
		startPrimaryStage();
	}
	
	private void startPrimaryStage() throws Exception {
		this.stackPane = GuiScreens.BASE.getScreenLoader().load();
		this.scene = new Scene(stackPane, 600, 550);
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Przyk³adowy projekt JavaFX");
		primaryStage.show();
	}
}
