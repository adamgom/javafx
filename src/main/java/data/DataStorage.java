package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import main.Engine;

public class DataStorage implements Serializable {
	private static final long serialVersionUID = 12345L;
	private List<String> stringList;
	
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
}
