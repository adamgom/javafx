package Managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import data.DataStorage;
import main.Config;

public class DataStorageManager {
	private DataStorage dataStorage;
	
	public DataStorageManager(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}
	
	public void dataToFile() {
		try {
			FileOutputStream fileOS = new FileOutputStream(Config.FilePath + Config.FileName);
			ObjectOutputStream outputStream = new ObjectOutputStream(fileOS);
			outputStream.writeObject(dataStorage);
			outputStream.close();
			fileOS.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void dataFromFile() {
		try {
			FileInputStream fileIS = new FileInputStream(Config.FilePath + Config.FileName);
			ObjectInputStream inputStream = new ObjectInputStream(fileIS);
			DataStorage tempdataStorage = (DataStorage) inputStream.readObject();
			this.dataStorage = tempdataStorage;
			System.out.println(dataStorage);
			System.out.println("DSM dFF -> " + dataStorage.getSize());
			inputStream.close();
			fileIS.close();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
