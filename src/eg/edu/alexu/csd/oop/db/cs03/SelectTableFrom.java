package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Command;

public class SelectTableFrom implements Command {

	private Object[][] detailsOfTable;
	private ArrayList<String> namesOfCols;
	private ArrayList<Object> col = new ArrayList<>();
	private Object[][] selectedTable;
	private String target;
	private String sign;
	private String value;
	private int count = 0;
	private int c = 0;
	private boolean[][] checker;
	private ArrayList<String> cols_name;
	
	public SelectTableFrom(Object[][] detailsOfTable, ArrayList<String> namesOfCols, String target, String sign, String value, ArrayList<String> cols_name) {
		this.detailsOfTable = detailsOfTable;
		this.namesOfCols = namesOfCols;
		this.target = target;
		this.sign = sign;
		this.value = value;
		this.cols_name = cols_name;
	}
	
	@Override
	public Object execute() {
		checker = new boolean[this.detailsOfTable.length][this.detailsOfTable[0].length];

		switch (sign) {
		case "=":
			for (int i = 0; i < this.namesOfCols.size(); i++) {
				if (this.target.equals(this.namesOfCols.get(i))) {
					count = 0;
					for (int x = 0; x < this.detailsOfTable.length; x++) {
						if (this.detailsOfTable[x][i].equals(value)) {
							count++;
						}
					}
				}
			}

			break;

		case "<":
			if (this.value.charAt(0) == '\'') {
				for (int i = 0; i < this.namesOfCols.size(); i++) {
					if (this.target.equals(this.namesOfCols.get(i))) {
						count = 0;
						for (int x = 0; x < this.detailsOfTable.length; x++) {
							if (this.detailsOfTable[x][i].toString().compareTo(value) < 0) {
								count++;
							}
						}
					}
				}
			} else {
				for (int i = 0; i < this.namesOfCols.size(); i++) {
					if (this.target.equals(this.namesOfCols.get(i))) {
						count = 0;
						for (int x = 0; x < this.detailsOfTable.length; x++) {
							if (Integer.parseInt((String) this.detailsOfTable[x][i]) < Integer.parseInt(value)) {
								count++;
							}
						}
					}
				}
			}
			break;

		case ">":

			if (this.value.charAt(0) == '\'') {
				for (int i = 0; i < this.namesOfCols.size(); i++) {
					if (this.target.equals(this.namesOfCols.get(i))) {
						count = 0;
						for (int x = 0; x < this.detailsOfTable.length; x++) {
							if (this.detailsOfTable[x][i].toString().compareTo(value) > 0) {
								count++;
							}
						}
					}
				}
			} else {
				for (int i = 0; i < this.namesOfCols.size(); i++) {
					if (this.target.equals(this.namesOfCols.get(i))) {
						count = 0;
						for (int x = 0; x < this.detailsOfTable.length; x++) {

							if (detailsOfTable[x][i].toString().charAt(0) != '\'') {
								if (Integer.parseInt((String) this.detailsOfTable[x][i]) > Integer.parseInt(value)) {
									count++;
								}
							}

						}
					}
				}
			}

			break;
		}
		
		selectedTable = new Object[count][this.namesOfCols.size()];
		
		switch (sign) {
		case "=":
			for (int i = 0; i < this.namesOfCols.size(); i++) {
				if (this.target.equals(this.namesOfCols.get(i))) {
					for (int x = 0; x < this.detailsOfTable.length; x++) {
						if (this.detailsOfTable[x][i].equals(value)) {
							for (int l = 0; l < namesOfCols.size(); l++) {
										checker[x][l] = true;
							}
						}
					}
				}
			}
			break;

		case "<":

			if (this.value.charAt(0) == '\'') {
				for (int i = 0; i < this.namesOfCols.size(); i++) {
					if (this.target.equals(this.namesOfCols.get(i))) {
						for (int x = 0; x < this.detailsOfTable.length; x++) {
							if (this.detailsOfTable[x][i].toString().compareTo(value) < 0) {
								for (int l = 0; l < namesOfCols.size(); l++) {
									checker[x][l] = true;
						}
							}
						}
					}
				}
			} else {
				for (int i = 0; i < this.namesOfCols.size(); i++) {
					if (this.target.equals(this.namesOfCols.get(i))) {
						for (int x = 0; x < this.detailsOfTable.length; x++) {
							if (Integer.parseInt((String) this.detailsOfTable[x][i]) < Integer.parseInt(value)) {
								for (int l = 0; l < namesOfCols.size(); l++) {
									checker[x][l] = true;
						}
							}
						}
					}
				}
			}
			break;

		case ">":

			if (this.value.charAt(0) == '\'') {
				for (int i = 0; i < this.namesOfCols.size(); i++) {
					if (this.target.equals(this.namesOfCols.get(i))) {
						for (int x = 0; x < this.detailsOfTable.length; x++) {
							if (this.detailsOfTable[x][i].toString().compareTo(value) > 0) {
								for (int l = 0; l < namesOfCols.size(); l++) {
									checker[x][l] = true;
						}
							}
						}
					}
				}
			} else {
				for (int i = 0; i < this.namesOfCols.size(); i++) {
					if (this.target.equals(this.namesOfCols.get(i))) {
						for (int x = 0; x < this.detailsOfTable.length; x++) {
							if (Integer.parseInt((String) this.detailsOfTable[x][i]) > Integer.parseInt(value)) {
								for (int l = 0; l < namesOfCols.size(); l++) {
									checker[x][l] = true;
						}
							}
						}
					}
				}
			}
			break;
		}
		
		int x = 0, y = 0;
		int flag = -1;

		for (int i = 0; i < checker.length; i++) {
			for (int j = 0; j < checker[0].length; j++) {
				if (checker[i][j] == true) {

					selectedTable[x][y] = detailsOfTable[i][j];
					y++;

				}
				if (j == checker[0].length - 1 && y != 0) {
					x++;
				}
			}
			y = 0;
		}
		
		for (int i = 0; i <this.selectedTable.length; i++) {
			for (int j = 0; j < this.selectedTable[0].length; j++) {
				if (this.selectedTable[i][j].toString().charAt(0) == '\'') {
					continue;
				} else {
					this.selectedTable[i][j] = Integer.parseInt((String) this.selectedTable[i][j]);
				}
			}
		}

		return selectedTable;
		
	}

}
