package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;

public class testing_syntax {

	public static void main(String[] args) {
		String line ="SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5";
		String pattern = "^\\s*(?i)(SELECT)\\s*+((\\w*+)\\s*+(,)+\\s*)*+(\\w*+)\\s*+(?i)(FROM)\\s*+(\\w*+)\\s*+"
    	 		+ "((?i)(WHERE)+\\s*+(\\w*+)\\s*+((?:[<|>|=]))\\s*+(\\d)+\\s*)*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	    	  
	    	  String[] attr = line.trim().split("(?i)SELECT");
	    	  String[] attr3 = attr[1].trim().split("(?i)FROM");
	    	  String[] attr1 = attr3[0].trim().split(",");
	    	  String withoutS1 = null ;
	    	  for (int i = 0; i < attr1.length; i++) { 
	    		  withoutS1=attr1[i].replaceAll(" ", "");
	    	  }
	    	  System.out.println(m.group(1));
	    	  System.out.println(m.group(2));
	    	  System.out.println(m.group(3));
	    	  System.out.println(m.group(4));
	    	  System.out.println(m.group(5));
	    	  System.out.println(m.group(6));
	    	  System.out.println(m.group(7));
	    	  System.out.println(m.group(8));
	    	  System.out.println(m.group(9));
	    	  
	    	  
	    	  if(m.group(8)!=null) {
                  //System.out.println(m.group(9));
	  		      m.group(10);
	  		       m.group(11);
	    	  }
 }
	}

   void create_database() {
	      String line = "Create dataBASE jk_f8j" ;
	      String pattern = "^(?i)(CREATE)+(\\s)+(?i)(DATABASE)+(\\s)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
	      }
   }

   void drop_database() {
	      String line = "drop dataBASE jk_f8j" ;
	      String pattern = "^(?i)(DROP)+(\\s)+(?i)(DATABASE)+(\\s)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
	      }
   }
   void drop_table() {
	   String line = "drop TABLE jk_f8j" ;
	      String pattern = "^(?i)(DROP)+(\\s)+(?i)(table)+(\\s)+(\\w*+)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
	      }
   }

   void create_table() {
	   String linem = "Create TABLE table_name1(column_name1 varchar, "
	   		      + "column_name2 int, column_name3 varchar)" ;
	      String patternm = "^(?i)(CREATE)+(\\s)+(?i)(table)+(\\s)+(\\w*+)(\\()+"
	      		+ "((\\w*+)\\s+(int|varchar)+(,)\\s)*+"
	      		+ "((\\w*+)\\s+(int|varchar))+(\\))$";
	      // Create a Pattern object
	      Pattern rm = Pattern.compile(patternm);
	      // Now create matcher object.
	      Matcher mm = rm.matcher(linem);
	      if (mm.find()) {
	      System.out.println(mm.group(1));
	      }
	      String line = "Create TABLE table_name1(column_name1 varchar)" ;
	      String pattern = "^(?i)\\s*(CREATE)\\s(TABLE)\\s(\\w+)\\s*+"
	      		+ "(\\()\\s*(\\w+\\s+(varchar|int)\\s*(,)\\s*)*(\\w+\\s+(varchar|int)\\s*)"
	      		+ "(\\))\\s*(;)?\\s*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	    	  ArrayList<String> fieldNames = new ArrayList< String>(),dataTypes = new ArrayList< String>();
	    	  String[] spliter;
	    	  String helper ;
			  String[] columns = line.split(",");
	          helper = null;
	          spliter = new String[columns.length];
	          for (int i = 0; i < columns.length; i++) {
	        	  String[] attr = columns[i].trim().split("\\s+");
	              if(i==0&&i!=columns.length-1) {
	            	  helper = attr[2].trim();
	            	  spliter=helper.trim().split("\\(");
	            	  fieldNames.add(spliter[1].trim()) ;
		              dataTypes.add(attr[3].trim());
	              }
	              else if(i==columns.length-1&&i!=0) {
	            	  helper = attr[1].trim();
	            	  spliter=helper.trim().split("\\)");
	            	  dataTypes.add(spliter[0].trim());
	            	  fieldNames.add(attr[0].trim());
	              }
	              else if(i==0 && i==columns.length-1 ) {
	            	  helper = attr[2].trim();
	            	  spliter=helper.trim().split("\\(");
	            	  fieldNames.add(spliter[1].trim());
	            	  helper = attr[3].trim();
	            	  spliter=helper.trim().split("\\)");
	            	  dataTypes.add(spliter[0].trim());

	              }
	              else {
	                  fieldNames.add(attr[0].trim());
	                  dataTypes.add(attr[1].trim());
	              }
	          }
	      System.out.println(dataTypes.get(0));
	      }
   }

   void select() {
	   String line = "SELECT * FROM table_name1 WHERE coluMN_NAME2 > 4" ;
	   String pattern = "^(?i)(SELECT)\\s+(\\*)\\s+(FROM)\\s+(\\w*+)\\s+(WHERE)+\\s+"
	   		           + "(\\w*+)\\s+(?:[<|>|=])\\s+(\\d)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(0));

	      }
   }

   void delete() {
	   String line = "DELETE From table_name1  WHERE coLUmn_NAME2=4" ;
		  String pattern = "^(?i)(DELETE FROM)+(\\s)+(\\w*+)\\s*+(WHERE)+\\s+(\\w*+)(?:[<|>|=])(\\d)$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
   }

   }

   void insert() {
	      String line = "INSERT INTO table_name1(column_NAME1, COLUMN_name3, "
	   		        + "column_name2) VALUES ('value1', 'value3', 4) " ;
		  String pattern = "^(?i)(INSERT INTO)+(\\s)+(\\w*+)(\\()+((\\w*+)(,)\\s)*+(\\w*+)(\\))\\s+"
		  		+ "(?i)(VALUES)\\s+(\\()+((\\d|(')+"
		  		+ "(\\w*+)('))+(,)\\s)*+(\\d|((')+(\\w*+)(')))+(\\))$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	      System.out.println(m.group(1));
   }

}


   void update() {
	   String line = "UPDATE table_name1 SET column_name1='11111111' WHERE coLUmn_NAME2=8" ;
	   String pattern = "^\\s*+(?i)(UPDATE)+\\s*+(\\w*+)\\s*+(?i)(SET)+\\s*+((\\w*+)\\s*+(=)+\\s*+"
	   		+ "(\\d|(')+(\\w*+)('))+\\s*+(,)\\s*)*+(\\w*+)\\s*+(=)+\\s*+(\\d|(')+(\\w*+)('))+\\s*+"
	   		+ "(?i)(WHERE)+\\s*+(\\w*+)\\s*+((?:[<|>|=]))+\\s*+(\\d|(')(\\w*+)('))\\s*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(line);
	      if (m.find()) {
	    	 /* ArrayList<String> fieldNames = new ArrayList<>();
	    	  ArrayList<String> valuesArgs = new ArrayList<>();
	    	  ArrayList<String> fieldNames = new ArrayList<>();
	    	  ArrayList<String> valuesArgs = new ArrayList<>();
	    	  order = "update";
	    	  String[] attr = this.query.trim().split("(?i)SET");
	    	  String[] attr3 = attr[1].trim().split("(?i)WHERE");
	    	  String[] attr1 = attr3[0].trim().split(","); 
	    	  String withoutS1 = null ;
	    	  String withoutS2 = null ;
	    	  for (int i = 0; i < attr1.length; i++) {
	    		  String[] attr2 = attr1[i].trim().split("=");
	    		  withoutS1=attr2[0].replaceAll(" ", "");
	    		  withoutS2=attr2[1].replaceAll(" ", "");*/
   }
}}