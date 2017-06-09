package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Controller.FormulaTranslate;
import ToolGUI.ScreenGUI;

public class Screen implements Serializable{
	private String  screenName;
	private int  cordinateX;
	private int cordinateY;
	private int height;
	private int width;
	private String description;
	private ArrayList<String> transPromela= new ArrayList<String>();
	private  HashMap <String,ChangeScreen> ChangeScreen = new HashMap <String,ChangeScreen>();
	private HashMap <String,Element> elementsMap;
	/**
	 * @return the screenName
	 */
	public Screen()
	{
		this.elementsMap= new HashMap<String,Element>();
	}
	public Screen(String screenName,int cordinateX,int cordinateY,int height,int width,
			String description)
	{
	this.elementsMap= new HashMap<String,Element>();
	this.screenName=screenName;
	this.cordinateX=cordinateX;
	this.cordinateY=cordinateY;
	this.height=height;
	this.width=width;
	this. description= description;
	}
	
	public Screen(String screenName,int cordinateX,int cordinateY,String description)
	{
	this.elementsMap= new HashMap<String,Element>();
	this.screenName=screenName;
	this.cordinateX=cordinateX;
	this.cordinateY=cordinateY;
	this. description= description;
	}
	
	
	/*
	 *debug/////////////////
	 */
	
	public Screen(String s) {
		this.screenName=s;
		this.elementsMap= new HashMap<String,Element>();

	}
	 /*
	*debug////////////////////
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * @return the cordinateX
	 */
	public int getCordinateX() {
		return cordinateX;
	}
	/**
	 * @param cordinateX the cordinateX to set
	 */
	public void setCordinateX(int cordinateX) {
		this.cordinateX = cordinateX;
	}
	/**
	 * @return the cordinateY
	 */
	public int getCordinateY() {
		return cordinateY;
	}
	/**
	 * @param cordinateY the cordinateY to set
	 */
	public void setCordinateY(int cordinateY) {
		this.cordinateY = cordinateY;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addElement(Element s){
		this.elementsMap.put(s.getELementName(),s);	
	}
	public void remoceElement(String eLementName) {
		this.elementsMap.remove(eLementName);
		
	}
	public Element getElementByName(String elementName){
		return this.elementsMap.get(elementName);
	}
	public HashMap<String, Element> getElementsMap() {
		return elementsMap;
	}
	
	
	public HashMap<String, ChangeScreen> getChangeScreenMap() {
		return ChangeScreen;
	}
	public void addChangeScreen(ChangeScreen changeScreen) {
		ChangeScreen.put(changeScreen.getScreenName(), changeScreen);
	}
	public void addTransPromela(String cond,String action,String toState) {
		this.transPromela.add("("+cond+")->atomic("+action+";state="+toState+")");
	}
	public ArrayList<String> getTransPromela() {
		return transPromela;
	}
	public  String getStringPromela(){
		String startScreen=new String("	::(state=="+this.getScreenName()+")->\n"+"	  if");
		String out= new String ("");
		transPromela.add("");
		transPromela.add("");

		for(String i : transPromela)
		{
			out +="\n"+"		 ::"+i;
		}
		

		return 	startScreen+out
				+"\n"+"	  fi"; 
	}
	public  String getChangeStates(){
		Element e ;
		Map.Entry pair;
		String states;
		Iterator it ;
		it = this.elementsMap.entrySet().iterator();
		if(it.hasNext()){
		pair =(Map.Entry) it.next(); 
		e= (Element)pair.getValue();
		 states=new String("Change"+this.getScreenName()+e.getParamName());
		}else {
			 states=new String("");
		}
		while(it.hasNext()){
			pair =(Map.Entry) it.next(); 
			e= (Element)pair.getValue();
			if (!(e.getType().equals(ElementType.getStandartBtnType()))){
			states+=",Change"+this.getScreenName()+e.getParamName();
			//FormulaTranslate.addtoChangeStates("Change"+this.getScreenName()+e.getParamName());
			}
		}
		it = this.ChangeScreen.entrySet().iterator();
		ChangeScreen changeScreen ;
		while(it.hasNext()){
			pair =(Map.Entry) it.next(); 
			changeScreen= (ChangeScreen)pair.getValue();
			states+=","+changeScreen.getScreenName();
			//FormulaTranslate.addtoChangeStates("Change"+this.getScreenName()+e.getParamName());
			}		
		return states;
	}



}
