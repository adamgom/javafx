package data;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.Settings;

public class BeanResultData {
	StringProperty text;
	LongProperty data;
	
	public BeanResultData(String text, long data) {
		this.text = new SimpleStringProperty(text);
		this.data = new SimpleLongProperty(data);
	}
	
	@Override
	public String toString() {
		return this.text.get() + " ; " + Settings.formatter.format(this.data.get());
	}
	
	public StringProperty getText() {
		return text;
	}
	
	public LongProperty getData() {
		return data;
	}
}