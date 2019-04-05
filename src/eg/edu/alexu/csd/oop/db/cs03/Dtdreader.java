package eg.edu.alexu.csd.oop.db.cs03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dtdreader {
	private String databaseName;
	private String tableName;

	public Dtdreader(String dbname, String tname) {
		this.databaseName = dbname;
		this.tableName = tname;
	}

	public ArrayList<String> read() {
		try {
			File file = new File(databaseName + System.getProperty("file.separator") + tableName + ".DTD");
			BufferedReader br;
			int count = 0;
			ArrayList<String> col_names = new ArrayList<String>();
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				if (count > 1) {

					String pattern = "^<!ELEMENT+\\s+(\\w*+)\\s+(\\()+(#PCDATA)+(\\))+(\\>)$";
					// Create a Pattern object
					Pattern r = Pattern.compile(pattern);
					// Now create matcher object.
					Matcher m = r.matcher(st);
					if (m.find()) {
						col_names.add(m.group(1));
					}
				}
				count++;

			}
			br.close();
			return col_names;

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}
}
