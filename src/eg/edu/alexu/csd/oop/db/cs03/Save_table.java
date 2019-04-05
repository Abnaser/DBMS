package eg.edu.alexu.csd.oop.db.cs03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Save_table {
	private String databaseName;
	private String tableName;
	private Object [][] data;

	public Save_table(String dbname, String tname,Object [][] data) {
		this.databaseName = dbname;
		this.tableName = tname;
		this.data = data;
	}
	

	public void save () {
		
		File file = new File(databaseName +  System.getProperty("file.separator") + tableName + ".DTD");
		File dir = new File (databaseName +  System.getProperty("file.separator") + tableName + ".xml");
		
		dir.delete();
		try {
			dir.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		BufferedReader br;
		int count = 0;
		ArrayList<String> col_names = new ArrayList<String>();

		try {
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
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// root element
			Element rootElement = doc.createElement("table");
			doc.appendChild(rootElement);
			// where j is number of columns

			
			for (int j = 0; j < data.length; j++) {
				Element row = doc.createElement("row");
				rootElement.appendChild(row);
				for(int i = 0 ;i < data[0].length;i++) {
					row.appendChild(Add_column(col_names.get(i),(String) data[j][i], doc));

				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(databaseName +  System.getProperty("file.separator") + tableName + ".xml"));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		


		
	}
	
	public static Element Add_column(String col_name, String col_data, Document doc) {
		Element col = doc.createElement(col_name);
		col.appendChild(doc.createTextNode(col_data));

		return col;
	}
}
