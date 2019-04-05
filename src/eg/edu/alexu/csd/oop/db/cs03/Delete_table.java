package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Command;

public class Delete_table implements Command {

	private String databaseName;
	private String tableName;
	private String col_name;
	private String sign;
	private String value;
	private Object[][] data;
	private Dtdreader dtdreader;
	private ArrayList<String> col_names;
	private int col_num;
	private int count_delete = 0;
	private int count_new = 0;
	private Save_table save_table;
	private boolean[][] flag;

	public Delete_table(String databaseName, String tableName, Object[][] data, String col_name, String sign,
			String value) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.data = data;
		this.col_name = col_name;
		this.sign = sign;
		this.value = value;
	}

	@Override
	public Object execute() {
		dtdreader = new Dtdreader(databaseName, tableName);
		col_names = dtdreader.read();
		flag = new boolean[data.length][data[0].length];
		if(col_name.equals(" ")) {
			File dir = new File (databaseName +  System.getProperty("file.separator") + tableName + ".xml");
			
			dir.delete();
			dir.mkdirs();
			return data.length;
		}

		for (int i = 0; i < col_names.size(); i++) {
			if (col_names.get(i).equals(col_name)) {
				col_num = i;
			}
		}
		if (value.charAt(0) == '\'') {

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					if (j == col_num) {
						switch (sign) {
						case "=":
							if (value.equals((String) data[i][j])) {
								count_delete++;
								for (int k = 0; k < data[0].length; k++) {
									flag[i][k] = true;
								}
							}
							break;
						case "<":
							if (((String) data[i][j]).compareTo(value) < 0) {
								count_delete++;
								for (int k = 0; k < data[0].length; k++) {
									flag[i][k] = true;
								}
							}
							break;
						case ">":
							if (((String) data[i][j]).compareTo(value) > 0) {
								count_delete++;
								for (int k = 0; k < data[0].length; k++) {
									flag[i][k] = true;
								}
							}
							break;
						}

					}

				}
			}

		} else if (value.charAt(0) != '\'') {
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					if (j == col_num) {
						switch (sign) {
						case "<":
							if (Integer.parseInt(data[i][j].toString()) < Integer.parseInt(value)) {
								count_delete++;
								for (int k = 0; k < data[0].length; k++) {
									flag[i][k] = true;
								}
							}
							break;
						case ">":
							if (Integer.parseInt(data[i][j].toString()) > Integer.parseInt(value)) {
								count_delete++;
								for (int k = 0; k < data[0].length; k++) {
									flag[i][k] = true;
								}
							}
							break;
						case "=":
							if (Integer.parseInt(data[i][j].toString()) == Integer.parseInt(value)) {
								count_delete++;
								for (int k = 0; k < data[0].length; k++) {
									flag[i][k] = true;
								}
							}
							break;

						}
					}
				}
			}
		}
		Object[][] new_data = new Object[data.length - count_delete][data[0].length];
		for (int i = 0; i < data.length; i++) {
			if (flag[i][0] == false) {
				for (int j = 0; j < data[0].length; j++) {
					new_data[count_new][j] = data[i][j];

				}
				count_new++;
			}
		}
		save_table = new Save_table(databaseName, tableName, new_data);
		save_table.save();
		return count_delete;
	}

}
