package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;

import eg.edu.alexu.csd.oop.db.Command;

public class CreateDatabase implements Command {

	private String databaseName;
	private File dir;

	public CreateDatabase (String databaseName) {
		this.databaseName = databaseName;


	}

	@Override
	public Object execute() {

		dir = new File (this.databaseName);
		
		dir.mkdirs();
		return true;
	}
}
