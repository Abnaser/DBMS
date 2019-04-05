package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

public class FactoryCommandModify {

	private String order;
	private String databaseName;
	private String tableName;
	private ArrayList<IHolder> arrayOfinsert;
	private Object[][] data;
	private String col_name;
	private String sign;
	private String value;

	public FactoryCommandModify (String order, String databaseName, String tableName, ArrayList<IHolder> arrayOfinsert, Object[][] dataString, String col_name, String sign,
			String value) {
		this.order = order;
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.arrayOfinsert = arrayOfinsert;
		this.data = dataString;
		this.col_name = col_name;
		this.sign = sign;
		this.value = value;
	}


	public int modifyChooser() {
		switch (this.order) {
		case "insert" : return (int) new InsertTable(this.databaseName, this.tableName, this.arrayOfinsert, this.data).execute();

		case "update" : return (int) new Update_table(this.databaseName, this.tableName, this.data, this.col_name, this.sign, this.value, this.arrayOfinsert).execute();

		case "delete" : return (int) new Delete_table(this.databaseName, this.tableName, this.data, this.col_name, this.sign, this.value).execute();

		default : return 0;
		}
	}

}
