package main;

import data.DataStorage;
import data.OptionSettings;
import data.ResultData;
import fileManager.FileManager;
import gui.GuiScreens;
import gui.controllers.AppController;
import gui.controllers.BaseController;
import gui.controllers.MenuController;
import gui.controllers.OptionController;
import gui.controllers.ResultController;

public class Engine {
	private static Engine instance;
	private FileManager fileManager;
//	private OptionSettings optionSettings;
	private DataStorage dataStorage;
	private ResultData resultData;
	
//	private BaseController baseController;
//	private MenuController menuController;
//	private AppController appController;
//	private OptionController optionController;
//	private ResultController resultController;
	
	private Engine() {
		fileManager = new FileManager(Config.PATH);
		dataStorage = new DataStorage();
		resultData = new ResultData();
//		dataStorage.dataFromFile();
//		optionSettings = new OptionSettings();
	}
	
	public static Engine getInstance() {
		if (instance == null) {
			return instance = new Engine();
		} else {
			return instance;	
		}
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public ResultData getResultData() {
		return resultData;
	}
	
	public DataStorage getDataStorage() {
		return dataStorage;
	}
}
