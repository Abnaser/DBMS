package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;
import java.util.ArrayList;

public class CheckerSelect { 
	private String databaseName;
	private String tableName;
	private ArrayList<String> selectedCols;
	private ArrayList<String> namesOfCols; 
	private ArrayList<Boolean> status = new ArrayList<>();
	private String colCondition;
	private String order;
	
	
	public CheckerSelect(String databaseName, String tableName, String order,  ArrayList<String> selectedCols,
			ArrayList<String> namesOfCols,String colCondition) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.selectedCols = selectedCols;
		this.namesOfCols = namesOfCols;
		this.colCondition = colCondition;
		this.order = order;
	}

	public boolean tableIfExist() {
		File checkerXML = new File (this.databaseName + System.getProperty("file.separator") + this.tableName + ".xml");
		File checkerDTD = new File (this.databaseName + System.getProperty("file.separator") + this.tableName + ".DTD");
		
		if (checkerXML.exists() && checkerDTD.exists()) {
			return true;
		} else {
			return false;
		}
	}
		
	public boolean colsIfExist() {
		
		
		switch (order) {
		case "select" : if (this.colCondition.equals(" ")) {
			return true;
		} else {
			for (int i = 0; i < this.selectedCols.size(); i++) {
				for (int j = 0; j < this.namesOfCols.size(); j++) {
					if (this.selectedCols.get(i).equals(this.namesOfCols.get(j))) {
						status.add(true);
					}
				}
			}
			if (status.size() == this.selectedCols.size()) {
				return true;
			} else {
				return false;
			}
		}
		
		case "select*from" : if (this.colCondition.equals(" ")) {
			return true;
		} else {
				for (int j = 0; j < this.namesOfCols.size(); j++) {
					if (this.colCondition.equals(this.namesOfCols.get(j))) {
						return true;
					}
					if (j == this.namesOfCols.size() - 1) {
						return false;
					}
				}
		}
		default : return false;
		}
		}
}
