package fileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	private File file;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private FileReader fileReader;
	private FileWriter fileWriter;
	
	public FileManager(String path) {
		file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String readFile() {
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		bufferedReader = new BufferedReader(fileReader);
		try {
			String tempLine = "";
			while ((tempLine = bufferedReader.readLine()) != null) {
				line += tempLine + ";";
	        }     
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public void writeFile(String text, boolean append) {
		try {
			fileWriter = new FileWriter(file, append);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufferedWriter = new BufferedWriter(fileWriter);
		
		try {
			bufferedWriter.write(text);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}




