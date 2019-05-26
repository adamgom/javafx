package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResultData {
	public ObservableList<BeanResultData> resultList;
	public ListProperty<BeanResultData> listProperty;
	
	public ResultData() {
		this.listProperty = new SimpleListProperty<>();
		List<BeanResultData> baseResultList = new ArrayList<>();

		for (int i = 0 ; i < 4 ; i++) {
			baseResultList.add(new BeanResultData("Info: " + i, new Date().getTime()));
		}
		this.resultList = FXCollections.observableArrayList(baseResultList);
		listProperty.set(this.resultList);
	}
	
	public BeanResultData getResultListItem(int position) {
		if (position == -1) return resultList.get(resultList.size()-1);
		return resultList.get(position);	
	}
	
	public void addToResultList(String text, long date) {
		this.resultList.add(new BeanResultData(text, date));
	}
	public void addToResultList(BeanResultData beanResultData) {
		this.resultList.add(beanResultData);
	}
	public void removeFromResultList(int position) {
			this.resultList.remove(position);
	}
}
