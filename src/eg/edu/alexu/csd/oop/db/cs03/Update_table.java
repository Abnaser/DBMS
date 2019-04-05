package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Command;

public class Update_table implements Command {
	private String databaseName;
	private String tableName;
	private String col_name;
	private String sign;
	private ArrayList<IHolder> arrayOfinsert = new ArrayList<>();
	private String value;
	private Object[][] data;
	private Dtdreader dtdreader;
	private ArrayList<String> col_names;
	private int col_num;
	private int update_count = 0;
	private Save_table save_table;

	public Update_table(String databaseName, String tableName, Object[][] data, String col_name, String sign,
			String value, ArrayList<IHolder> arrayOfinsert) {
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.data = data;
		this.arrayOfinsert = arrayOfinsert;
		this.col_name = col_name;
		this.sign = sign;
		this.value = value;
	}

	@Override
	public Object execute() {
		dtdreader = new Dtdreader(databaseName, tableName);
		col_names = dtdreader.read();
		if(data == null) {
			return 0;
		}
		if (!col_name.equals(" ")) {

			for (int i = 0; i < col_names.size(); i++) {
				if (col_names.get(i).equals(col_name)) {
					col_num = i;
				}
			}
			System.out.println(col_names.get(2));

			if (value.charAt(0) == '\'') {

				for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < data[0].length; j++) {
						if (j == col_num) {
							switch (sign) {
							case "=":
								if (value.equals((String) data[i][j])) {
									update_count++;
									for (int l = 0; l < arrayOfinsert.size(); l++) {
										for (int k = 0; k < data[0].length; k++) {
											if (arrayOfinsert.get(l).getFieldNames().equals(col_names.get(k))) {
												data[i][k] = arrayOfinsert.get(k).getDataTypes();
											}
										}

									}
								}
								break;
							case ">":
								if (((String) data[i][j]).compareTo(value) > 0) {
									update_count++;

									for (int l = 0; l < arrayOfinsert.size(); l++) {
										for (int k = 0; k < data[0].length; k++) {
											if (arrayOfinsert.get(l).getFieldNames().equals(col_names.get(k))) {
												data[i][k] = arrayOfinsert.get(k).getDataTypes();
											}
										}

									}
								}
								break;
							case "<":
								if (((String) data[i][j]).compareTo(value) < 0) {
									update_count++;

									for (int l = 0; l < arrayOfinsert.size(); l++) {
										for (int k = 0; k < data[0].length; k++) {
											if (arrayOfinsert.get(l).getFieldNames().equals(col_names.get(k))) {
												data[i][k] = arrayOfinsert.get(k).getDataTypes();
											}
										}

									}
									break;
								}

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
									update_count++;

									for (int l = 0; l < arrayOfinsert.size(); l++) {
										for (int k = 0; k < data[0].length; k++) {
											if (arrayOfinsert.get(l).getFieldNames().equals(col_names.get(k))) {
												data[i][k] = arrayOfinsert.get(k).getDataTypes();
											}
										}

									}
								}
								break;
							case ">":
								if (Integer.parseInt(data[i][j].toString()) > Integer.parseInt(value)) {
									update_count++;

									for (int l = 0; l < arrayOfinsert.size(); l++) {
										for (int k = 0; k < data[0].length; k++) {
											if (arrayOfinsert.get(l).getFieldNames().equals(col_names.get(k))) {
												data[i][k] = arrayOfinsert.get(k).getDataTypes();
											}
										}

									}
								}
								break;
							case "=":
								if (Integer.parseInt(data[i][j].toString()) == Integer.parseInt(value)) {
									update_count++;

									for (int l = 0; l < arrayOfinsert.size(); l++) {
										for (int k = 0; k < data[0].length; k++) {
											if (arrayOfinsert.get(l).getFieldNames().equals(col_names.get(k))) {

												data[i][k] = arrayOfinsert.get(k).getDataTypes();
											}
										}

									}
								}
								break;

							}
						}
					}
				}
			}

		} else {
			for (int i = 0; i < data.length; i++) {
				update_count++;
				for (int j = 0; j < arrayOfinsert.size(); j++) {
					
					for (int k = 0; k < data[0].length; k++) {
						if (arrayOfinsert.get(j).getFieldNames().equals(col_names.get(k))) {
							data[i][k] = arrayOfinsert.get(j).getDataTypes();
						}
					}

				}
			}
		}
		save_table = new Save_table(databaseName, tableName, data);
		save_table.save();
		return update_count;

	}

}
