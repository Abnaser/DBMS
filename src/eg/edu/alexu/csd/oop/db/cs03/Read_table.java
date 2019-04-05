package eg.edu.alexu.csd.oop.db.cs03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Read_table {

	private String databaseName;
	private String tableName;
	private Dtdreader dtdreader;
	private ArrayList<String> col_names;

	public Read_table(String dbname, String tname) {
		this.databaseName = dbname;
		this.tableName = tname;
	}

	public Object[][] read_table() {
				
		dtdreader = new Dtdreader(databaseName,tableName);
		col_names = dtdreader.read();
		try {
			File inputFile = new File(databaseName + System.getProperty("file.separator") + tableName + ".xml");
			BufferedReader br = new BufferedReader(new FileReader(databaseName + System.getProperty("file.separator") + tableName + ".xml"));     
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			if (br.readLine() == null) {
				br.close();
				return null;
			} else {
				Document doc = dBuilder.parse(inputFile);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("row");
				Object data[][] = new Object[nList.getLength()][col_names.size()];

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						for (int i = 0; i < col_names.size(); i++) {
							data[temp][i] = eElement.getElementsByTagName(col_names.get(i)).item(0).getTextContent();

						}
					}
				}
				br.close();
				return data;

			}
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

}
