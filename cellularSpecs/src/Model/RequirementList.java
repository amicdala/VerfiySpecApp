package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequirementList  implements Serializable{
	
	private List <Requirement> reqlist = new ArrayList<Requirement>();

 public void addRequirement(Requirement r){
	 this.reqlist.add(r); 
 }
 
 public void removeRequirement(Requirement r){
	 this.reqlist.remove(r); 
 }

public List<Requirement> getReqlist() {
	return reqlist;
}
}