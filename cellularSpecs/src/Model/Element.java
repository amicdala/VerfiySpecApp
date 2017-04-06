package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Element implements Serializable{
private String elementName;
private String type;
private String paramName;
//private List <Action> actions= new ArrayList<>(); 
//private List <MyCondition> conditions= new ArrayList<>(); 

public Element() {}
/**
 * @return the elementName
 */
public String getElementName() {
	return elementName;
}
/**
 * @param elementName the elementName to set
 */
public void setElementName(String elementName) {
	this.elementName = elementName;
}
/**
 * @return the type
 */
public String getType() {
	return type;
}
/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}
/**
 * @return the paramName
 */
public String getParamName() {
	return paramName;
}
/**
 * @param paramName the paramName to set
 */
public void setParamName(String paramName) {
	this.paramName = paramName;
}

}