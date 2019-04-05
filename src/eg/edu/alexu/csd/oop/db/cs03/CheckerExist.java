package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;
import java.util.ArrayList;

public class CheckerExist {
	
	private String databaseName;
	private String tableName;
	private String order;
	private ArrayList<IHolder> selectedCols;
	private ArrayList<String> namesOfCols; 
	private ArrayList<Boolean> status = new ArrayList<>();
	
	public CheckerExist(String databaseName, String tableName,  String order, ArrayList<IHolder> selectedCols, ArrayList<String> namesOfCols) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.selectedCols = selectedCols;
		this.namesOfCols = namesOfCols;
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
		switch (this.order) {
		case "update" : for (int i = 0; i < this.selectedCols.size(); i++) {
			for (int j = 0; j < this.namesOfCols.size(); j++) {
				if (this.selectedCols.get(i).getFieldNames().equals(this.namesOfCols.get(j))) {
					status.add(true);
				}
			}
		}
		if (status.size() == this.selectedCols.size()) {
			return true;
		} else {
			return false;
		}
			
		case "insert" : if (this.selectedCols.get(0).getFieldNames() == null && this.selectedCols.get(0).getDataTypes() != null) {
			return true;
		} else {
			for (int i = 0; i < this.selectedCols.size(); i++) {
				for (int j = 0; j < this.namesOfCols.size(); j++) {
					if (this.selectedCols.get(i).getFieldNames().equals(this.namesOfCols.get(j))) {
						status.add(true);
					}
				}
			}
			if (status.size() == this.namesOfCols.size()) {
				return true;
			} else {
				return false;
			}
		}
			
		
		default : return true;
		
		}
	}
	
}
