package eg.edu.alexu.csd.oop.db.cs03;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainDB {

	public static void main(String[] args) {
	    System.out.println("\" DataBase Managment System \"");
		System.out.println("-----------------------------");
		System.out.println("-> Enter a query :");
		Scanner sca = new Scanner(System.in);
		String query = sca.nextLine();
		IDatabase controller =new IDatabase();
		while(!(query.equals("exit"))) {	
			if(query.toLowerCase().contains("create")||query.toLowerCase().contains("drop")) {
				try {
					controller.executeStructureQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}       
			}else if (query.toLowerCase().contains("select")) {
				try {
					controller.executeQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (query.toLowerCase().contains("insert")||query.toLowerCase().contains("update")
					||query.toLowerCase().contains("delete")) {
				try {
					controller.executeUpdateQuery(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("invalid query");
			}
			System.out.println("\n-> Enter another query :");
	        sca = new Scanner(System.in);
	        query = sca.nextLine();
	          }

	}

}
