package main;

import Managers.DataStorageManager;
import data.DataStorage;
import data.ResultData;
import gui.controllers.AppController;
import gui.controllers.BaseController;
import gui.controllers.MenuController;
import gui.controllers.OptionController;
import gui.controllers.ResultController;

public class Engine {
	private static Engine instance;
	private DataStorageManager dataStorageManager;
//	private OptionSettings optionSettings;
	private DataStorage dataStorage;
	private ResultData resultData;
	
	private BaseController baseController;
	private MenuController menuController;
	private AppController appController;
	private OptionController optionController;
	private ResultController resultController;
	
	private Engine() {
		dataStorage = null;
		dataStorage = new DataStorage();
		System.out.println(dataStorage);
		resultData = new ResultData();
		dataStorageManager = new DataStorageManager(dataStorage);
		dataStorageManager.dataFromFile();
		this.baseController = new BaseController();
		this.menuController = new MenuController();
		this.appController = new AppController(dataStorage);
		this.optionController = new OptionController();
		this.resultController = new ResultController();
	}

	public static Engine getInstance() {
		if (instance == null) {
			return instance = new Engine();
		} else {
			return instance;	
		}
	}

	public void appScreenOpen() {
		appController.initialText();
	}
	
	public BaseController getBaseController() {
		return baseController;
	}

	public void setBaseController(BaseController baseController) {
		this.baseController = baseController;
	}

	public MenuController getMenuController() {
		return menuController;
	}

	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}

	public AppController getAppController() {
		return appController;
	}

	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	public OptionController getOptionController() {
		return optionController;
	}

	public void setOptionController(OptionController optionController) {
		this.optionController = optionController;
	}

	public ResultController getResultController() {
		return resultController;
	}

	public void setResultController(ResultController resultController) {
		this.resultController = resultController;
	}

//	public FileManager getFileManager() {
//		return fileManager;
//	}
	
	public ResultData getResultData() {
		return resultData;
	}
	
	public DataStorage getDataStorage() {
		return dataStorage;
	}
	
	public DataStorageManager getDataStorageManager() {
		return dataStorageManager;
	}
}
