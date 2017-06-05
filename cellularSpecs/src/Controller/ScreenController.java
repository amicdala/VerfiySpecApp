package Controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Model.Element;
import Model.ElementType;
import Model.Param;
import Model.Screen;
import Model.WorkSpace;
import ToolGUI.AddConditonGui;
import ToolGUI.ScreenGUI;

public class ScreenController {
	
public static Element getElementByName(String elementName){
	
	ArrayList<Element> params = new ArrayList<Element>(); 
	Screen s ;
	
	Iterator<Entry<String, Screen>> it = WorkSpace.getInstance().getScreensMap().entrySet().iterator();
	while(it.hasNext()){
		Map.Entry pair =(Map.Entry) it.next(); 
		s= (Screen)pair.getValue();
		if (null != s.getElementByName(elementName)){
			return s.getElementByName(elementName); 
		}
	}
	return null ; 
		
	}
	@SuppressWarnings("rawtypes")
	public static String[] getparams(){//get all the parameters arraylist
		ArrayList<String> params = new ArrayList<String>();
		Iterator<Entry<String, Param>> it = WorkSpace.getInstance().getParamsMap().entrySet().iterator();
		Param p;
		while(it.hasNext()){
			Map.Entry pair =(Map.Entry) it.next(); 
			 p =(Param)pair.getValue();
			params.add(p.getParamName());	
		}
		if(params.size()>0)
		{
		String[] stockArr = new String[params.size()];
		stockArr = params.toArray(stockArr);	
		return stockArr;
		}
		return null;
	
	}
	public static String [] getdefaultValues(String paramName){//get all the the parameter values
		Param p;
		p=WorkSpace.getInstance().getParamsByName(paramName);
		return(p.getValues());	
	}
	@SuppressWarnings("rawtypes")
	public static String [] getParams(String type, String screenName){
		ArrayList<String> params = new ArrayList<String>();
		params.add("");
		params.add("New..");

		Iterator<Entry<String, Param>> it = WorkSpace.getInstance().getParamsMap().entrySet().iterator();
		Screen s= WorkSpace.getInstance().getScreenByName(screenName);
		Param p;
		while(it.hasNext()){
			Map.Entry pair =(Map.Entry) it.next(); 
			 p =(Param)pair.getValue();
			if(p.getType().equals(type) && s.getElementByName(p.getParamName()) == null)
			{		
			params.add(p.getParamName());	
			}
		}
		String[] stockArr = new String[params.size()];
		stockArr = params.toArray(stockArr);	
	return stockArr;
	}
	public static String [] getParams(String type, String screenName,String paramSelected){
		ArrayList<String> params = new ArrayList<String>();
		params.add("");
		params.add("New..");
		params.add(paramSelected);

		Iterator<Entry<String, Param>> it = WorkSpace.getInstance().getParamsMap().entrySet().iterator();
		Screen s= WorkSpace.getInstance().getScreenByName(screenName);
		Param p;
		while(it.hasNext()){
			Map.Entry pair =(Map.Entry) it.next(); 
			 p =(Param)pair.getValue();
			if(p.getType().equals(type) && s.getElementByName(p.getParamName()) == null)
			{		
			params.add(p.getParamName());	
			}
		}
		String[] stockArr = new String[params.size()];
		stockArr = params.toArray(stockArr);	
	return stockArr;
	}

	public static String getAllScreenName(){
		
		Screen s = new Screen(); 
		Iterator<Entry<String, Screen>> it = WorkSpace.getInstance().getScreensMap().entrySet().iterator();
		Map.Entry pair =(Map.Entry) it.next(); 
		s= (Screen)pair.getValue();	
		String sAll = new String(s.getScreenName());

		while(it.hasNext()){
		    pair =(Map.Entry) it.next(); 
			s= (Screen)pair.getValue();	
			sAll+=","+s.getScreenName();
			
		}
		
		return sAll; 
	}
	public static String[] getScreenNameNames(){
	Screen s1 ;
	int i=0;
	String[]  st=new String[WorkSpace.getInstance().getScreensMap().size()] ;
	Iterator<Entry<String, Screen>> it1 = WorkSpace.getInstance().getScreensMap().entrySet().iterator();
	while(it1.hasNext()){
		Map.Entry pair =(Map.Entry) it1.next(); 
		s1= (Screen)pair.getValue();	
		st[i]=s1.getScreenName();
		 i++; 	
	} 
	return st;
	}
}
