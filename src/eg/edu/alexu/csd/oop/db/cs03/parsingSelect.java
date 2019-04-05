 package eg.edu.alexu.csd.oop.db.cs03;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parsingSelect {
	 
	private String query ;
	private String order ;
	private String tableName;
	private String conditionColumn;
	private String conditionSign;
	private String conditionValue;
	private int conditonExist;
	private ArrayList<String> col ;
	
	public parsingSelect(String query) {
		this.query = query;
		tableName = " ";
		conditionColumn = " ";
		conditionSign = " ";
		conditionValue = " ";
		conditonExist=0;
		col = new ArrayList<>();
	}
	
	public boolean validity () {
		if(check1()||check2()) {
			return true;
		}else {
		  return false;
		} 
	}
	
    private boolean check1()	{
    	 String pattern = "^\\s*(?i)(SELECT)\\s*+((\\w*+)\\s*+(,)+\\s*)*+(\\w*+)\\s*+(?i)(FROM)\\s*+(\\w*+)\\s*+"
    	 		+ "((?i)(WHERE)+\\s*+(\\w*+)\\s*+((?:[<|>|=]))\\s*+(\\d)+\\s*)*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order="select";
	    	  tableName = m.group(7);
	    	  String[] attr = this.query.trim().split("(?i)SELECT");
	    	  String[] attr3 = attr[1].trim().split("(?i)FROM");
	    	  String[] attr1 = attr3[0].trim().split(",");
	    	  String withoutS1 = null ;
	    	  for (int i = 0; i < attr1.length; i++) { 
	    		  withoutS1=attr1[i].replaceAll(" ", "");
				  col.add(withoutS1.toLowerCase());
	    	  }
	    	  if(m.group(8)!=null) {
	    		 conditonExist=1;
	  		     conditionColumn = m.group(10);
	  		     conditionSign = m.group(11);
	  		     conditionValue = m.group(12);
	    	  }
	          return true;
	      }	
	      else {
	    	  return false;
	      }	
	}
  
    private boolean check2()	{
    	  String pattern = "^\\s*(?i)(SELECT)\\s*+(\\*)\\s*+(?i)(FROM)\\s*+(\\w*+)"
    	  		+ "\\s*((?i)(WHERE)+\\s*+(\\w*+)\\s*+((?:[<|>|=]))\\s*+(\\d)+\\s*)*$";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(this.query);
	      if (m.find()) {
	    	  order="select*from";
	    	  tableName = m.group(4);
	    	  if(m.group(6)!=null) {
		    		 conditonExist=1;
		  		     conditionColumn = m.group(7);
		  		     conditionSign = m.group(8);
		  		     conditionValue = m.group(9);
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
	
	public boolean conditonExist() {
		if(conditonExist==0) {
			return false;
		}
		else {
		    return true;
		} 
	}
	
	public String getConditionColumn() {
		return conditionColumn.toLowerCase();
	}
	
	public String getConditionSign() {
		return conditionSign;
	}
	
	public String getConditionValue() {
		return conditionValue.toLowerCase();
	}
	
	public ArrayList<String> getTableDetails() {
		return col;
	}
	
	
}
