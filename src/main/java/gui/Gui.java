package gui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.Engine;

public class Gui {
	private Stage primaryStage;
	private StackPane stackPane;
	private Scene scene;
//	private BaseController baseController;
//	private MenuController menuController;
//	private AppController appController;
//	private OptionController optionController;
//	private ResultController resultController;
//	private FXMLLoader baseLoader;
	
	public Gui(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
//		this.baseController = new BaseController();
//		this.menuController = new MenuController(baseController);
//		this.appController = new AppController(baseController);
//		this.optionController = new OptionController(baseController);
//		this.resultController = new ResultController(baseController);

//		this.baseLoader = new FXMLLoader(this.getClass().getResource("/FXML/BaseScreen.fxml"));

		GuiScreens.BASE.getScreenLoader().setController(Engine.getInstance().getBaseController());
		GuiScreens.MENU.getScreenLoader().setController(Engine.getInstance().getMenuController());
		GuiScreens.APP.getScreenLoader().setController(Engine.getInstance().getAppController());
		GuiScreens.OPTION.getScreenLoader().setController(Engine.getInstance().getOptionController());
		GuiScreens.RESULT.getScreenLoader().setController(Engine.getInstance().getResultController());
//		this.baseLoader.setController(baseController);
//		this.stackPane = baseLoader.load();
		
//		GuiScreens.MENU.getScreenLoader().setController(this.menuController);
//		GuiScreens.APP.getScreenLoader().setController(this.appController);
//		GuiScreens.OPTION.getScreenLoader().setController(this.optionController);
//		GuiScreens.RESULT.getScreenLoader().setController(this.resultController);

		GuiScreens.MENU.setPane(GuiScreens.MENU.getScreenLoader().load());
		GuiScreens.APP.setPane(GuiScreens.APP.getScreenLoader().load());
		GuiScreens.OPTION.setPane(GuiScreens.OPTION.getScreenLoader().load());
		GuiScreens.RESULT.setPane(GuiScreens.RESULT.getScreenLoader().load());
		
//		GuiScreens.MENU.initPane();
//		GuiScreens.APP.initPane();
//		GuiScreens.OPTION.initPane();
//		GuiScreens.RESULT.initPane();
		
		startPrimaryStage();
	}

	private void startPrimaryStage() throws Exception {
		this.stackPane = GuiScreens.BASE.getScreenLoader().load();
		this.scene = new Scene(stackPane, 600, 550);
		this.scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Przyk³adowy projekt JavaFX");
		this.primaryStage.show();
	}
}
