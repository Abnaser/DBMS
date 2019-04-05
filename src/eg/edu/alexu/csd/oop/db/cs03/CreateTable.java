package eg.edu.alexu.csd.oop.db.cs03;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Command;

public class CreateTable implements Command {

	private String tableName;
	private String databaseName;
	private ArrayList<IHolder> cols;
	private File dir;

	public CreateTable (String tableName, String databaseName, ArrayList<IHolder> cols) {
		this.tableName = tableName;
		this.databaseName = databaseName;
		this.cols = cols;
	}

	@Override
	public Object execute() {
		dir = new File(databaseName);
		if (dir.exists()) {
			File file1 = new File (databaseName +  System.getProperty("file.separator") + tableName + ".xml");
			File file2 = new File (databaseName + System.getProperty("file.separator") + tableName + ".DTD");
			if (!file1.exists() && !file2.exists()) {
				try {
					file1.createNewFile();
					file2.createNewFile();
					WriteDTD(file2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	private void WriteDTD (File file) {
		BufferedWriter bw = null;

		String myContent = "<!ELEMENT " + this.tableName + " (row)*>";
		String col = null;
		String s = null;
		for (int i = 0; i < this.cols.size(); i ++) {
			if (i == 0) {
				col = this.cols.get(i).getFieldNames();
			} else {

				col = col + "," + this.cols.get(i).getFieldNames();
			}
		}

		myContent = myContent + "\r\n" + "<!ELEMENT row (" + col + ")>";

		for (int i = 0; i < this.cols.size(); i ++) {
			s = "<!ELEMENT " + this.cols.get(i).getFieldNames() + " (#PCDATA)>";
			myContent = myContent + "\r\n" + s;
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  bw = new BufferedWriter(fw);
		  try {
			bw.write(myContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   try{
		      if(bw!=null)
			 bw.close();
		      fw.close();
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedWriter"+ex);
		    }

	}


}
