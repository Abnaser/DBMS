package eg.edu.alexu.csd.oop.db.cs03;

public class IHolder {
	
	private String fieldNames;
	private String dataTypes;
	
	public IHolder (String fieldNames, String dataTypes) {
		// TODO Auto-generated constructor stub
		this.fieldNames = fieldNames;
		this.dataTypes = dataTypes;
	}
	 
	public String getFieldNames() {
		return this.fieldNames;
	}
	
	public String getDataTypes() {
		return dataTypes;
	}

}
