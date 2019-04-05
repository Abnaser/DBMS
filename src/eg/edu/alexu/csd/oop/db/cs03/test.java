package eg.edu.alexu.csd.oop.db.cs03;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Command;
import eg.edu.alexu.csd.oop.db.Database;


public class test {

	public static void main(String[] args) throws SQLException {

		ArrayList<IHolder> arraylist = new ArrayList<>();
		ArrayList<IHolder> arraylist1 = new ArrayList<>();

		arraylist.add(new IHolder("name", "VARCHAR"));
		arraylist.add(new IHolder("phone", "VARCHAR"));
		arraylist.add(new IHolder("id", "INT"));

		arraylist1.add(new IHolder("name", "'ahmed'"));
		arraylist1.add(new IHolder("phone", "'0111'"));
		arraylist1.add(new IHolder("id", "4"));

		Command c = new CreateDatabase( "PoliceStation");
		Command c1 = new DropDatabase( "sample" +  System.getProperty("file.separator") + "testdb" );
		Command c2 = new CreateTable("NewTable", "PoliceStation", arraylist);
		Command c3 = new DropTable("NewTable", "PoliceStation");
		
		
		
		Object[][] o = new Object[4][3];
		
		o[0][0] = "'v1'";
		o[0][1] = "4";
		o[0][2] = "'v3'";
		
		o[1][0] = "'v1'";
		o[1][1] = "4";
		o[1][2] = "'v3'";
		
		o[2][0] = "'v2'";
		o[2][1] = "5";
		o[2][2] = "'v4'";
		
		o[3][0] = "'v5'";
		o[3][1] = "6";
		o[3][2] = "'v6'";
		
		ArrayList<String> namesOfCol = new ArrayList<>();

		namesOfCol.add("col1");
		namesOfCol.add("col2");
		namesOfCol.add("col3");
		
		ArrayList<String> selectedCols = new ArrayList<>();
		
		selectedCols.add("col1");
		
		Command c5 = new SelectTableConditional(o, namesOfCol, selectedCols,"col2", "<", "5", namesOfCol);
		
		Object[][] o1 = (Object[][]) c5.execute();
		
	//	System.out.println(o1[0][0]);
		
		
		for (int i = 0; i < o1.length; i ++) {
			for (int j = 0; j <o1[0].length; j++) {
				System.out.println(o1[i][j]);
			}
		}
		
		//CommandUpdate c4 = new InsertTable("PoliceStation", "NewTable", arraylist1);

		//c1.execute();
		
		//Database db = new IDatabase();
		
		//db.executeStructureQuery("CREATE   TABLE   table_name1(column_name1 varchar , column_name2    int,  column_name3 varchar)       ");
		
			
		
	//	db.executeStructureQuery("CREATE   TABLE   table_name1(column_name1 varchar , column_name2    int,  column_name3 varchar)       ");

	}
}
