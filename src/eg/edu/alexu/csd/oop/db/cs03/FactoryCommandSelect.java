package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

public class FactoryCommandSelect {
	private String order;
	private Object[][] detailsOfTable;
	private ArrayList<String> namesOfCols;
	private ArrayList<String> selectedCols;
	private String col_name;
	private String sign;
	private String value;

	public FactoryCommandSelect (String order,Object[][] detailsOfTable, String col_name, String sign,
			String value,ArrayList<String> namesOfCols ,ArrayList<String> selectedCols) {
		this.order = order;
		this.detailsOfTable = detailsOfTable;
		this.namesOfCols=namesOfCols;
		this.selectedCols = selectedCols;
		this.col_name = col_name;
		this.sign = sign;
		this.value = value;
	}


	public Object[][] selecter() {
		switch (this.order) {
		
		case "select" : 
			if(col_name.equals(" ")) {
					return (Object[][]) new SelectTable(this.detailsOfTable,this.namesOfCols, this.selectedCols).execute();
			}else {
				   return (Object[][]) new SelectTableConditional(this.detailsOfTable,this.namesOfCols,this.selectedCols,this.col_name,this.sign,this.value,this.namesOfCols).execute();

			}
		
		case "select*from" :
			if(col_name.equals(" ")) {
				return detailsOfTable;
			}else {
				return (Object[][]) new SelectTableFrom(this.detailsOfTable, this.namesOfCols, this.col_name, this.sign, this.value, this.namesOfCols).execute();

			}

		default : return null ;
		}
	}

}
