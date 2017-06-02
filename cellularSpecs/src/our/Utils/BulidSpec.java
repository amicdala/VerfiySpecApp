package our.Utils;

import Model.Action;
import Model.Element;
import Model.ElementActionInterface;
import Model.ElementType;
import Model.EmptyNEmptyType;
import Model.OnOffType;
import Model.Param;
import Model.Screen;
import Model.StandartButtonType;
import Model.WorkSpace;
import ToolGUI.EmptyNotEmptyGUI;

public class BulidSpec {
	
	static WorkSpace wk;

	public static void build(){
		WorkSpace.setInstance(null); 
		wk=WorkSpace.getInstance();
		wk.setWorkSpaceName("example ");
		addParmsOnOff();
		addScreens();
		addelements();
		WorkSpace.setInstance(wk); 
	}
	

	private static void addScreens(){
	
		wk.addScreen("loginScreen", new Screen("loginScreen", 38, 102, "login for app"));
		wk.addScreen("mainScreen", new Screen("mainScreen", 210, 102, "login for app"));
		wk.addScreen("createNewEvent", new Screen("createNewEvent", 380, 102, " The user creates new event. Upon creating the event the user becomes the moderator of the event."));
		
	}
	private  static void addelements(){
		StandartButtonType s; 
		/* 
		 * data for  log in screen 
		 */
		
		// login button 
		s = new StandartButtonType();
		s.setElementName("log in");
		s.setTransition("loginScreen","mainScreen");
		wk.getScreenByName("loginScreen").addElement(s);
		addElemenEmpty("loginScreen",new String[] {"user","pass"});
		addConditions("loginScreen","log in", new String [] {"user","pass"}); 

		
		/*
		 *data for screen 2		
		 */
		addElementONOFF("mainScreen",new String[] {"wifi","bluetooth","airplane_mode"});
		addAction("mainScreen","airplane_mode");
		/*
		 * add data to create event
		 */
		s = new StandartButtonType();
		s.setElementName("save");
		s.setTransition("createNewEvent","mainScreen");
		wk.getScreenByName("createNewEvent").addElement(s);
		addElemenEmpty("createNewEvent",new String[] {"title", "description", "date", "time","more details",});
	addConditions("createNewEvent","save", new String [] {"title", "description", "date", "time"}); 

	}
	
	
		private static void addElemenEmpty(String screenName, String [] fields){
			EmptyNEmptyType e; 
			Param p; 
			for (int i=0 ; i< fields.length; i++){
				e = new EmptyNEmptyType();
				e.setElementName(fields[i]);
				p = new Param(fields[i],"Empty", ElementType.getEmptyNotEmptyType());
				e.setParam(p);
				wk.addParameterToHash(p);
				wk.getScreenByName(screenName).addElement(e);
			}
			
			
		}
		private static void addElementONOFF(String screenName, String [] fields){
			OnOffType e; 
			Param p; 
			for (int i=0 ; i< fields.length; i++){
				e = new OnOffType();
				e.setElementName(fields[i]);
				p = new Param(fields[i],"off", ElementType.getOnOffType());
				e.setParam(p);
				wk.addParameterToHash(p);
				wk.getScreenByName(screenName).addElement(e);
			}
			
			
		}
		private static void addConditions(String screenName,String button, String [] fields) {
			/*
			 * for log in screen 
			 */
			// TODO Auto-generated method stub
			StandartButtonType s;
			EmptyNEmptyType e1;
			s=(StandartButtonType) wk.getScreenByName(screenName).getElementByName(button);
			for (int i=0 ; i<fields.length;i++){
				e1=(EmptyNEmptyType) wk.getScreenByName(screenName).getElementByName(fields[i]);
				s.addCondition(e1.getParamName(),"==","NotEmpty");
			}
			//update element 
			wk.getScreenByName(screenName).addElement(s);
			
		/*
		 * ==== for create new event 
		 */
		}
		private static void addAction(String screenName,String elementName){
			
			OnOffType e = (OnOffType) wk.getScreenByName(screenName).getElementByName(elementName);
			Action action =new Action("wifi=OFF");
			action.addCond("airplane_mode==ON");
			e.addAction(action);
			
			action =new Action("bluetooth=OFF");
			action.addCond("airplane_mode==ON");
			e.addAction(action);
			
			//update 
			wk.getScreenByName(screenName).addElement(e);

		}
		private static void addParmsOnOff(){
			for (int i=0; i<10;i++){
			Param p = new Param("onOff_"+i, "off", ElementType.getOnOffType());
				wk.addParameterToHash(p);

				
			}
		}
	}
