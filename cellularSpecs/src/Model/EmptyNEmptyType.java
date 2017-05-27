package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmptyNEmptyType implements Serializable, Element {
private Param parameter; 
private String elementName;
private final String type="Empty/NotEmpty"; 
private List <Action> actions= new ArrayList<>(); 
private List <MyCondition> conditions= new ArrayList<>(); 

public EmptyNEmptyType() {}

public String getType() {
	return type;
}

public String getParamName() {
	return parameter.getParamName();
}
/**
 * @param paramName the paramName to set
 */


@Override
public String getParamVal() {
	// TODO Auto-generated method stub
	return this.parameter.getParamVal(); 
}

@Override
public void loadElement() {
	// TODO Auto-generated method stub	
}

@Override
public String getStringPromela() {
	// TODO Auto-generated method stub
	return "";
}


public void setParam(Param param) {
	// TODO Auto-generated method stub
	 this.parameter=(Param) param;
}

@Override
public void setElementName(String name) {
	// TODO Auto-generated method stub
	this.elementName=name;
}
/*
 * now get values only in Param Class 
 */


}


