package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListElementType implements Serializable, Element {
private ParamList parameter ; 
private final  String type="List"; 

//private List <MyCondition> conditions= new ArrayList<>(); 

public ListElementType() {}

public String [] getValues() {
	
	
	return this.parameter.getValues();
}

public void setValues(String [] values) {
	this.parameter.setValues(values);
}

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

@Override
public void setParam(Param param) {
	// TODO Auto-generated method stub
	 this.parameter=(ParamList) param;
}
public void setParamName(String name) {
	// TODO Auto-generated method stub
	this.parameter.setParamName(name);
}
/*
 * now get values only in Param Class 
 */

}


