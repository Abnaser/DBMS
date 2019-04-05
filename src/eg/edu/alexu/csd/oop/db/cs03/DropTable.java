package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;

import eg.edu.alexu.csd.oop.db.Command;

public class DropTable implements Command {

	private String tableName;
	private String databaseName;
	private File dir;

	public DropTable(String tableName, String databaseName) {
		this.tableName = tableName;
		this.databaseName = databaseName;
	}

	@Override
	public Object execute() {
		dir = new File (databaseName);

		if (dir.exists()) {
			File file1 = new File (databaseName + System.getProperty("file.separator") + tableName + ".xml");
			File file2 = new File (databaseName + System.getProperty("file.separator") + tableName + ".DTD");

			if (file1.exists() && file2.exists()) {
				file1.delete();
				file2.delete();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
