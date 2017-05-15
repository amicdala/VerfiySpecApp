package Model;

import java.io.Serializable;
import java.util.*;

import our.Utils.Logger;

public class WorkSpace implements Serializable { 

private  String  workSpaceName;
private static WorkSpace instance =null ; 
private static Logger logger;
/*
 * number of screens on workspace 
 */public static int numScreen;

private   boolean IsPressed;
private HashMap <String,Screen> screensMap ; 
private HashMap <String,Param> ParamsMap ; 

public boolean IsClicked;

private  WorkSpace()
{
		this.IsPressed=false;
		screensMap=new HashMap<String,Screen>();
		ParamsMap=new HashMap<String,Param>();
		this.IsClicked=false;
		numScreen=1; 
}

public  static WorkSpace getInstance()
{
	if(null==instance ){
	instance=new WorkSpace();
	logger=new Logger(true);
	}
	return instance;
}


public static void setInstance(WorkSpace instance) {
	WorkSpace.instance = instance;
}
public static Logger getLog() {
	return logger;
}
public void addScreen(String screenName , Screen s){
	this.screensMap.put(screenName,s);	
}
public void updateScreen(String screenName , Screen s){
	this.screensMap.remove(screenName);
	this.screensMap.put(screenName,s);	
}
public Screen getScreenByName(String screenName){
	return this.screensMap.get(screenName);
}
public boolean isIsPressed() {
	return IsPressed;
}
/**
 * @return the workSpaceName
 */
public String getWorkSpaceName() {
	return workSpaceName;
}

/**
 * @param workSpaceName the workSpaceName to set
 */
public void setWorkSpaceName(String workSpaceName) {
	this.workSpaceName = workSpaceName;
}

/**
 * @return the isPressed
 */
public boolean getIsPressed() {
	return IsPressed;
}

public HashMap<String,Screen> getScreensMap() {
	return screensMap;
}


/**
 * @param isPressed the isPressed to set
 */
public boolean getisIsClicked() {
	return IsClicked;
}

public void setIsClicked(boolean isClicked) {
	IsClicked = isClicked;
}
public void setIsPressed(boolean isPressed) {
	IsPressed = isPressed;
}

public HashMap<String, Param> getParamsMap() {
	return ParamsMap;
}
public void addParameterToHash(String ParameterName , Param p){
	this.ParamsMap.put(ParameterName,p);	
}
public void updatParameterInHash(String ParameterName , Param p){
	this.ParamsMap.remove(ParameterName);
	this.ParamsMap.put(ParameterName,p);	
}
}
