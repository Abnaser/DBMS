package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parsingInsert_Update_Delete {

	private String query ;
	private String order ;
	private String tableName;
	private String conditionColumn;
	private String conditionSign;
	private String conditionValue;
	private ArrayList<IHolder> tableDetails;
	private ArrayList<String> value ;
	private ArrayList<String> value1 ;
	//private ArrayList<String> fieldNames;
	//private ArrayList<String> valuesArgs;
	public parsingInsert_Update_Delete(String query) {
		this.query = query;
		tableName = " ";
		conditionColumn =" ";
		conditionSign = " ";
		conditionValue =" ";
		tableDetails = new ArrayList<>();
		value = new ArrayList<>();
		value1 = new ArrayList<>();
		//fieldNames = new ArrayList<>();
		//valuesArgs = new ArrayList<>();
	}
	
	public boolean validity () {
		if(check1()||check2()||check3()) {
			return true;
		}else {
		  return false;
		} 
	}
	
	private boolean check1()	{
		String pattern = "^\\s*(?i)(DELETE)\\s*(FROM)\\s*+(\\w*+)\\s*+((?i)(WHERE)+\\s*+"
				+ "(\\w*+)\\s*+((?:[<|>|=]))\\s*+(\\d|(')(\\w*+)('))\\s*)*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "delete";
	    	  tableName = m.group(3);            
	    	  if(m.group(4)!=null) {
		  		     conditionColumn = m.group(6);
		  		     conditionSign = m.group(7);
		  		     conditionValue = m.group(8);
		    	  }
	       
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
	
	private boolean check2()	{
		 String pattern = "^\\s*+(?i)(UPDATE)+\\s*+(\\w*+)\\s*+(?i)(SET)+\\s*+((\\w*+)\\s*+(=)+\\s*+"
			   		+ "(\\d|(')+(\\w*+)('))+\\s*+(,)\\s*)*+(\\w*+)\\s*+(=)+\\s*+(\\d|(')+(\\w*+)('))+\\s*+"
			   		+ "((?i)(WHERE)+\\s*+(\\w*+)\\s*+((?:[<|>|=]))+\\s*+(\\d|(')(\\w*+)('))\\s*)*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "update";
	    	  tableName=m.group(2);
	    	  String[] attr = this.query.trim().split("(?i)SET");
	    	  String[] attr3 = attr[1].trim().split("(?i)WHERE");
	    	  String[] attr1 = attr3[0].trim().split(","); 
	    	  String withoutS1 = null ;
	    	  String withoutS2 = null ;
	    	  for (int i = 0; i < attr1.length; i++) {
	    		  String[] attr2 = attr1[i].trim().split("=");
	    		  withoutS1=attr2[0].replaceAll(" ", "");
	    		  withoutS2=attr2[1].replaceAll(" ", "");
				tableDetails.add(new IHolder(withoutS1.toLowerCase(), withoutS2.toLowerCase() ));
				  
	      }
	    	  if(m.group(19)!=null) {
		  		     conditionColumn = m.group(20);
		  		     conditionSign = m.group(21);
		  		     conditionValue = m.group(22);
		    	  }
	       
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
	
	private boolean check3()	{
		String pattern = "^\\s*+(?i)(INSERT)\\s*+(INTO)+(\\s*)+(\\w*+)\\s*((\\()+\\s*+((\\w*+)\\s*(,)\\s*)*+(\\w*+)\\s*(\\))\\s*)*+"
		  		+ "(?i)(VALUES)\\s*+(\\()\\s*+((\\d|(')+"
		  		+ "(\\w*+)('))+\\s*(,)\\s*)*+(\\d|((')+(\\w*+)(')))+\\s*+(\\))\\s*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order = "insert";
	    	  tableName = m.group(4);
	    	  if(m.group(5)!=null) {
	    		  String[] attr = this.query.trim().split("\\(");
		    	  String[] attr3 = attr[1].trim().split("\\)");
		    	  String[] attr1 = attr3[0].trim().split(",");
		    	  String[] attr4 = attr3[0].trim().split(",");
		    	  String withoutS1 = null ;
		    	  for (int i = 0; i < attr1.length; i++) { 
		    		  withoutS1=attr1[i].replaceAll(" ", "");
					  value.add(withoutS1);
		    	  }
		    	  attr = this.query.trim().split("(?i)VALUES");
		    	  attr4 = attr[1].trim().split("\\(");
		    	  attr3 = attr4[1].trim().split("\\)");
		    	  attr1 = attr3[0].trim().split(",");
		    	  for (int i = 0; i < attr1.length; i++) { 
		    		  withoutS1=attr1[i].replaceAll(" ", "");
		    		  value1.add(withoutS1);
		    	  }

		          for(int i=0 ; i<attr1.length; i++) {
		        	  tableDetails.add(new IHolder(value.get(i).toLowerCase() , value1.get(i).toLowerCase() ));
		          }  
	    	  }
	    	  else {
	    		  String[] attr = this.query.trim().split("\\(");
		    	  String[] attr3 = attr[1].trim().split("\\)");
		    	  String[] attr1 = attr3[0].trim().split(",");
		    	  String[] attr4 = attr3[0].trim().split(",");
		    	  String withoutS1 = null ;
		    	  for (int i = 0; i < attr1.length; i++) {
					  value.add(null);
		    	  }
		    	  attr = this.query.trim().split("(?i)VALUES");
		    	  attr4 = attr[1].trim().split("\\(");
		    	  attr3 = attr4[1].trim().split("\\)");
		    	  attr1 = attr3[0].trim().split(",");
		    	  for (int i = 0; i < attr1.length; i++) { 
		    		  withoutS1=attr1[i].replaceAll(" ", "");
		    		  value1.add(withoutS1);
		    	  }

		    	  
		          for(int i=0 ; i<attr1.length; i++) {
		        	  tableDetails.add(new IHolder(value.get(i) , value1.get(i).toLowerCase() ));
		          }  
	    		  
	    	  }
	    	  
	          
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
	
	
	public String getWhichOrder() {
		return order;                 
	}
	
	
	public String getTableName() {
		return tableName.toLowerCase();
	}
	
	public String getConditionColumn() {
		return conditionColumn.toLowerCase();
	}
	
	public String getConditionSign() {
		return conditionSign;
	}
	
	public String getConditionValue() {
		return conditionValue.toLowerCase() ;
	}
	
	public ArrayList<IHolder> getTableDetails(){
		return tableDetails;
	}
	
}
