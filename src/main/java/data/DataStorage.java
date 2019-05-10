package data;

import java.util.ArrayList;
import java.util.List;

import main.Engine;

public class DataStorage {
	public List<String> stringList;
	
	public DataStorage() {
		this.stringList = new ArrayList<>();
	}
	
	public void addData(String data) {
		this.stringList.add(data);
	}
	
	public void removeData(int position) {
		this.stringList.remove(position);
	}
	
	public void changeData(String data, int position) {
		this.stringList.remove(position);
		this.stringList.add(position, data);
	}
	
	public String getData(int position) {
		if (isNoData()) {
			return null;
		}
		return stringList.get(position);
	}
	
	public int getSize() {
		return this.stringList.size();
	}
	
	public boolean isNoData() {
		return stringList.isEmpty();
	}
	
	public String dataToFile() {
		String export = "";
		for (int i = 0 ; i < stringList.size() ; i++ ) {
			export += stringList.get(i); 
			if (i != stringList.size()-1) export += "\r\n";
		}
		return export;
	}
	
	public void dataFromFile() {
		String dataFromFile = Engine.getInstance().getFileManager().readFile();
		stringList.clear();
		int counterBegin = 0;
		int counterEnd = 0;
		while (true) {
			counterEnd = dataFromFile.indexOf(";", counterBegin);
			if (counterBegin >= counterEnd) break;
			stringList.add(dataFromFile.substring(counterBegin, counterEnd));
			counterBegin = counterEnd + 1;
		}
		
	}
}
