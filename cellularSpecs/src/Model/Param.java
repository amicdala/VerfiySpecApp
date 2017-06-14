package Model;

import java.io.Serializable;

public class Param implements Serializable{
	private int index;
	private static int counter=0; 
	private String paramName; 
	private String paramVal;
	private String type;
		
	public Param(String paramName, String paramVal, String type) {
		super();
		this.index=counter++;
		this.paramName = paramName;
		this.paramVal = paramVal; 
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = Character.toUpperCase(paramName.charAt(0))+paramName.substring(1);;
	}
	public String getParamVal() {
		return paramVal;
	}
	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 
	public String [] getValues(){										/*how to get values for list type*/
		if (ElementType.getEmptyNotEmptyType().equals(this.type)){
			return ElementType.getEmptyNotEmptyType().split("/");
		}
		else if(ElementType.getOnOffType().equals(this.type)){
			return ElementType.getOnOffType().split("/");
			}
		
		else{ 
			return ElementType.getStandartBtnType().split("/");
			}
	
	}

}
