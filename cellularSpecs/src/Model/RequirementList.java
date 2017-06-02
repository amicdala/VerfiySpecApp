package Model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class RequirementList {
	
	private static List <Requirement> reqlist = new ArrayList<Requirement>();

 public void addRequirement(Requirement r){
	 reqlist.add(r); 
 }
 
 public void removeRequirement(Requirement r){
	reqlist.remove(r); 
 }

public static List<Requirement> getReqlist() {
	return reqlist;
}
public  void loadreqs() {
	   JSONParser parser = new JSONParser();
     try {
         Object obj = parser.parse(new FileReader("Config.txt"));
         JSONObject jsonObject = (JSONObject) obj;

         JSONArray Reqiuriements = (JSONArray) jsonObject.get("Reqiuriement_List");

         Iterator<JSONObject> iterator = Reqiuriements.iterator();
         while (iterator.hasNext()) {
         	JSONObject j=iterator.next();
         	Requirement r = new Requirement((String)j.get("ID"),(String)j.get("name")
         			,(String)j.get("formula"));
         	addRequirement(r);
			WorkSpace.getLog().debug((String)j.get("name"));
         }

     } catch (Exception e) {
         e.printStackTrace();
     }
  
 }


}