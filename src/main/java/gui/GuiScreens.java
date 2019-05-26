package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public enum GuiScreens {
	BASE("Base"),
	MENU("Menu"),
	APP("App"),
	OPTION("Option"),
	RESULT("Result");

	private FXMLLoader screenLoader;
	private Pane pane;
//	private String name;
//	private Class<?> controller;
	
	private GuiScreens(String name) {
//		this.name = name + "Controller.class";
		this.screenLoader = new FXMLLoader(this.getClass().getResource("/FXML/" + name + "Screen.fxml"));
//		File rootDir = new File(ClassLoader.getSystemResource("gui/controllers").getPath());
//		for (String item : rootDir.list()) {
//			if (item.equals(this.name)) {
//				try {
//					this.controller = (Class<?>) Class.forName(this.name).newInstance();
////					Constructor constructor = controller.getConstructor();
////					this.controller = (Class<?>) constructor.newInstance();
//				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		this.screenLoader.setController(controller);
//		try {
//			this.screenLoader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

//	public Class<?> getController() {
//		return (Class<?>) controller;
//	}
	
	public FXMLLoader getScreenLoader() {
		return screenLoader;
	};
	
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
	public Pane getPane() {
		return pane;
	}
	
//	public <T> T getController(String name) {
//		return (T) (name + "Controller") controller;
//	}
	
//	public void initPane() {
//		try {
//			this.pane = this.screenLoader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
