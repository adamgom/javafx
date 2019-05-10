package data;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OptionSettings {
	private boolean approvedCheckBox;
	private Date datePicker;
	private StringProperty textField;
	private double progressBarProgress;
	
	public OptionSettings() {
		this.approvedCheckBox = true; 
		this.datePicker = null;
		this.progressBarProgress = 0.0;
		this.textField = new SimpleStringProperty(this, "textFieldProperty", "init value");
	}

//	public void progress() {
//		this.progressBarProgress += 0.02;
//	}
	
//	public boolean isApprovedCheckBox() {
//		return approvedCheckBox;
//	}
//	
//	public void setApprovedCheckBox(boolean approvedCheckBox) {
//		this.approvedCheckBox = approvedCheckBox;
//	}
	
//	public Date getDatePicker() {
//		return datePicker;
//	}
//	
//	public void setDatePicker(Date datePicker) {
//		this.datePicker = datePicker;
//	}
//	
//	public StringProperty getTextField() {
//		return textField;
//	}
//	
//	public double getProgressBarProgress() {
//		return progressBarProgress;
//	}
}
