package data;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {
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
		return this.stringList.get(position);
	}
	
	public int getSize() {
		return this.stringList.size();
	}
	
	public boolean isNoData() {
		if (this.stringList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
