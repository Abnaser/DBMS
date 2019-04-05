package eg.edu.alexu.csd.oop.db.cs03;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Database;

public class IDatabase implements Database {

	private String databaseN = null;

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		File dir = new File (databaseName);
	
		if (dir.exists() && dropIfExists) {
			try {

				this.databaseN = databaseName;
				executeStructureQuery("DROP DATABASE" + " " + databaseName);
				if (executeStructureQuery("CREATE DATABASE" + " " + databaseName)) {
					return dir.getAbsolutePath();
				} else {
					return null;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else if (dir.exists() && !dropIfExists) {

			return dir.getAbsolutePath();

		} else if (!dir.exists()) {
			this.databaseN = databaseName;
			try {
				if (executeStructureQuery("CREATE DATABASE" + " " + databaseName)) {
					return dir.getAbsolutePath();
				} else {
					return null;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		parsingCreate_Drop parse = new parsingCreate_Drop(query);

		if (parse.validity()) {
			if ((parse.getWhichOrder() + " " + parse.getDatabaseOrTable()).equals("create database")) {
				this.databaseN = parse.getName();
			}
			FactoryCommand f = new FactoryCommand(parse.getWhichOrder() + " " + parse.getDatabaseOrTable(), this.databaseN, parse.getName(), parse.getTableDetails());

            return f.commandChooser();
		} else {
			throw new SQLException ("Invalid Query");
		}


	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
     parsingSelect parse = new parsingSelect(query);

     if (parse.validity()) {
    	 CheckerSelect checker = new CheckerSelect(this.databaseN, parse.getTableName(), parse.getWhichOrder(),
				 parse.getTableDetails(), new Dtdreader(this.databaseN,
						parse.getTableName()).read(),parse.getConditionColumn());
    	 if (checker.tableIfExist()) {
    		 if (checker.colsIfExist()) {
    			 FactoryCommandSelect fSelect = new FactoryCommandSelect(parse.getWhichOrder(), new Read_table(this.databaseN, parse.getTableName()).read_table(), parse.getConditionColumn(), parse.getConditionSign(),
			parse.getConditionValue(), new Dtdreader(this.databaseN, parse.getTableName()).read(),parse.getTableDetails());
    			 return fSelect.selecter();
    		 } else {
    			 throw new SQLException ("Invalid SelectedCols");
    		 }
    	 } else {
    		 throw new SQLException ("Invalid Table");
    	 }

     } else {
    	 throw new SQLException ("Invalid Query");
     }

		/*if(parse.validity()) {

			if(parse.conditonExist()) {
				checkerSelect checker = new checkerSelect(this.databaseN, parse.getTableName(),
						 parse.getTableDetails(), new Dtdreader(this.databaseN,
								parse.getTableName()).read(),parse.getConditionColumn());
				if(checker.colOfConditionIfExist()) {
					if (checker.tableIfExist()) {
						if(parse.getWhichOrder()=="select") {
						if (checker.colsIfExist()) {




						} else {
							throw new SQLException ("Invalid SelectedCols");
						}
						}
					} else {
						throw new SQLException ("Invalid Table");
					}
				}
				else {
					throw new SQLException ("Invalid Columnof the condition");
				}
			}else {
				checkerSelect checker = new checkerSelect(this.databaseN, parse.getTableName(),
						 parse.getTableDetails(), new Dtdreader(this.databaseN,
								parse.getTableName()).read());

					if (checker.tableIfExist()) {
						if(parse.getWhichOrder()=="select") {
						if (checker.colsIfExist()) {




						} else {
							throw new SQLException ("Invalid SelectedCols");
						}
						}
					} else {
						throw new SQLException ("Invalid Table");
					}

			}

		}else {
			throw new SQLException ("Invalid Query");
		}*/
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		parsingInsert_Update_Delete parse = new parsingInsert_Update_Delete(query);


		if (parse.validity()) {
			CheckerExist checker = new CheckerExist(this.databaseN, parse.getTableName(), parse.getWhichOrder(), parse.getTableDetails(), new Dtdreader(this.databaseN, parse.getTableName()).read());
			if (checker.tableIfExist()) {
				if (checker.colsIfExist()) {
					FactoryCommandModify fModify = new FactoryCommandModify(parse.getWhichOrder(), this.databaseN, parse.getTableName(), parse.getTableDetails(), new Read_table(this.databaseN, parse.getTableName()).read_table(), parse.getConditionColumn(), parse.getConditionSign(), parse.getConditionValue());
                    return fModify.modifyChooser();
				} else {
					throw new SQLException ("Invalid SelectedCols");
				}
			} else {
				throw new SQLException ("Invalid Table");
			}
		} else {
			throw new SQLException ("Invalid Query");
		}
	}

}
