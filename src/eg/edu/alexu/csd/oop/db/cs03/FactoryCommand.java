package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;

public class FactoryCommand {

	private String command;
	private String databaseName;
	private String tableName;
	private ArrayList<IHolder> cols = new ArrayList<>();


	public FactoryCommand(String command, String databaseName, String tableName, ArrayList<IHolder> cols) {
		this.command = command;
		this.databaseName = databaseName;
		this.tableName = tableName;
		this.cols = cols;
	}

	public boolean commandChooser () {
		if (this.command.equals("create database")) {

			return (boolean) new CreateDatabase(this.databaseName).execute();

		} else if (this.command.equals("drop database")) {

			return (boolean) new DropDatabase(this.databaseName).execute();

		} else if (this.command.equals("create table")) {

			return (boolean) new CreateTable(this.tableName, this.databaseName, this.cols).execute();

		} else if (this.command.equals("drop table")) {

			return (boolean) new DropTable(this.tableName, this.databaseName).execute();

		} else {

			return false;

		}
	}


}
