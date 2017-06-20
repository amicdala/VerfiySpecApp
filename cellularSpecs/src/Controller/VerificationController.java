package Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;

import our.Utils.Promela;
import Model.MyAction;
import Model.ChangeScreen;
import Model.Element;
import Model.ElementType;
import Model.EmptyNEmptyType;
import Model.OnOffType;
import Model.Param;
import Model.RequirementList;
import Model.Screen;
import Model.WorkSpace;
import Model.screenInterface;

public class VerificationController implements ItemListener {
	private FormulaTranslate formulaTranslate = new FormulaTranslate(); 
	HashMap<String,Param> ParamList ;
	WorkSpace w  ;

	public VerificationController(){
		w  =WorkSpace.getInstance();

		formulaTranslate = new FormulaTranslate();
		ParamList = new HashMap<String,Param>(w.getParamsMap());
		initialize();

	}

	public  String translateToPROMELA(){
		WorkSpace.getLog().debug("translateToPROMELA");

		return ""
				+"#define ON  1 \n"
				+"#define OFF 0 \n"
				+"#define Empty 2 \n"
				+"#define NotEmpty 3 \n"
						+ "mytype={"+ScreenController.getAllScreenName()
						+",\n"+w.getAllChangeStates()+"}\n"
						+defineParamsPromela()
//						+getLTLReq()
						+"\nactive proctype vm(){\n"
						+" do\n"
						+ getPG()
						 +"od\n}";
	}
private void initialize(){
	WorkSpace.getLog().debug("initialize");
	w.setAllChangeScreen();
	FormulaTranslate.setChangeStates(w.getAllChangeStates().split(","));
	FormulaTranslate.setScreenStates(ScreenController.getAllScreenName().split(","));
	
	
}


	//	private static String getLTLReq() 
//	{
////		String st="";
////		Router.getInstance().getRequirementList().getReqlist().get(0);
////		for(int i=0;i<Router.getInstance().getRequirementList().getReqlist().size();i++)
////		{
////			if(Router.getInstance().getRequirementList().getReqlist().get(i).isSelected())
////			st+=Router.getInstance().getRequirementList().getReqlist().get(i).getFormula()+"\n";
////		}
////		return "";
//	}
	private  String getPG() {
		String sAll = new String("");Screen s = new Screen(); Element e ; 
		ChangeScreen changeScreen;
		Iterator<Entry<String, Screen>> it = w.getScreensMap().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair =(Map.Entry) it.next(); 
			s= (Screen)pair.getValue();	
			WorkSpace.getLog().debug("screen is "+s.getScreenName());
			Iterator<Entry<String, Element>> it2 = w
					.getScreenByName(s.getScreenName()).getElementsMap().entrySet().iterator();
			while(it2.hasNext()){
				Map.Entry pair2 =(Map.Entry) it2.next(); 
				e= (Element)pair2.getValue();
				WorkSpace.getLog().debug("--element is "+e.getELementName()+"with param "+e.getParamName());
				WorkSpace.getLog().debug("---type "+e.getType());
				if (e.getType().equals(ElementType.getStandartBtnType())){
					s.getTransPromela().add(e.getStringPromela());
					WorkSpace.getLog().debug("---get promela standrt button "+e.getType());

				}
				else 
					if (e.getType().equals(ElementType.getOnOffType())){
						
				WorkSpace.getLog().debug("---start ON OFF condtion action");

						
				/*
						 ::(aaaa==on)->atomic(aaaa=off;action[13]=1;state=changemainScreenaaaa);
				 */
				WorkSpace.getLog().debug("---start OFF->ON condtion action ");
					setTransONOFF(s,w.getParamsByName(e.getParamName()),"ON");
					
					WorkSpace.getLog().debug("---start ON->OFF condtion action ");
					setTransONOFF(s,w.getParamsByName(e.getParamName()),"OFF");

										
				}else if (e.getType().equals(ElementType.getEmptyNotEmptyType())){
					
					WorkSpace.getLog().debug("---Empty type start action to Empty->notEmpty ");
					changeScreen = new ChangeScreen(s.getScreenName()+e.getParamName()); 
					s.getTransPromela().add("("+e.getParamName()+"=="
							+((EmptyNEmptyType)e).getParameter().getValues()[0]+")->atomic("+e.getParamName()+"="
							+((EmptyNEmptyType)e).getParameter().getValues()[1]+";"
							+"action["+((EmptyNEmptyType)e).getParameter().getIndex()+"]=1;"
							+"state="+changeScreen.getScreenName()+");");
					
					changeScreen.addTransPromela("action["+((EmptyNEmptyType)e).getParameter().getIndex()+"]==1",
							"action["+((EmptyNEmptyType)e).getParameter().getIndex()+"]=0",
							s.getScreenName());
					
					w.addChangeScreen(changeScreen);
					
					}
				
				}
			WorkSpace.getLog().debug("---get string promela from screen object ");
			sAll+=s.getStringPromela()+"\n";
			sAll+= "/*"
					+ "\n*/////////////////////////////////////// End of changeParamScreens for screen "+s.getScreenName()+"////////////////////////////////////////////////\n*/\n\n";
			RemoveStructPromela();
		}
		WorkSpace.getLog().debug("---get string promela from change screen  ");
		sAll+=w.getBlockChangeScreen();

		return sAll;
	}
//	private void setParamAction(OnOffType parent, OnOffType son ){
//		
//		
//	}
//	private void parentAction(ChangeScreen parent,OnOffType e){
//		
//		if (e.getActions().size() != 0){
//			
//			for (Action i:e.getActions()){
//				setParamAction(e,w.g)
//				
//			}
//			
//		}
//		
//		
//	}
	
	private void setTransONOFF(screenInterface s ,Param e,String SwitchTo) {
		ChangeScreen changeScreen;
		// TODO Auto-generated method stub
				
		changeScreen = w.getChangeScreenByname(e.getParamName()+SwitchTo); 
		s.addTransPromela(e.getParamName()+"=="+(e).getValues()[1]
			,Promela.getActionString(e.getParamName(), e.getValues()[0])
			+Promela.getActionString(e.getIndex(),1)
			+Promela.getActionSonsString(e,1,SwitchTo)
			,changeScreen.getScreenName()+");");

		changeScreen.addTransPromela(Promela.getActionCondString(e.getIndex(), 1)
				+Promela.getActionCondSonsString(e, 0,SwitchTo)
				,Promela.getActionString(e.getIndex(),0), s.getScreenName());
	w.addChangeScreen(changeScreen);
	if (e.getActions("ON").size() ==0) return;
	else {
		for (MyAction i:e.getActions("ON")){
			
			if(i.getParamVal().equals("ON")){
				setTransSonONOFF(changeScreen,e,w.getParamsByName(i.getParamName()),"ON");
				
			}else {
				setTransSonONOFF(changeScreen,e,w.getParamsByName(i.getParamName()),"OFF");
				
			}
			

		}
	}
		
	}
/*	int fromVal=0,toVal=1;
		if(val.equals("ON")){
			fromVal=1;toVal=0;
		}
 * 
 * p.getParamName()+"=="+(p).getValues()[fromVal]
			,Promela.getActionString(p.getParamName(), p.getValues()[toVal])
 * 
 */
	private void setTransSonONOFF(ChangeScreen parent ,Param pParent, Param pson,
			String val) {
		// TODO Auto-generated method stub
		int toVal=1;
		if(val.equals("ON")){
			toVal=0;
		}
		
		ChangeScreen changeScreen;
		changeScreen = w.getChangeScreenByname(pson.getParamName()+val); 
		parent.addTransPromela(Promela.getActionCondString(pson.getIndex(), 1)
			,Promela.getActionString(pson.getParamName(), pson.getValues()[toVal])
			+Promela.getActionString(pson.getIndex(),1)
			+Promela.getActionSonsString(pson,1,val)
			,changeScreen.getScreenName()+");");
		w.addChangeScreen(parent);

		changeScreen.addTransPromela(Promela.getActionCondString(pParent.getIndex(), 1)
				+Promela.getActionCondString(pson.getIndex(), 1)
				+Promela.getActionCondSonsString(pson, 0,val)
				,Promela.getActionString(pson.getIndex(),0), parent.getScreenName());
		w.addChangeScreen(changeScreen);
		
	}

	private void RemoveStructPromela(){
		Screen s = new Screen(); 
		
		Iterator<Entry<String, Screen>> it = WorkSpace.getInstance().getScreensMap().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair =(Map.Entry) it.next(); 
			s= (Screen)pair.getValue();
			s.getTransPromela().clear();
			ParamList.clear();
			WorkSpace.getInstance().addScreen(s);
			
		}
			
		
	}
	private  String defineParamsPromela(){
		String out = new String("\n/*define flag for action */\nbyte action["+WorkSpace.getInstance().getParamsMap().size()+"];\n\n"
				+ "/*define params and default value*/\n");
		Iterator<Entry<String, Param>> it = WorkSpace.getInstance().getParamsMap().entrySet().iterator();
		Param p;
		while(it.hasNext()){
			Map.Entry pair =(Map.Entry) it.next(); 
			 p =(Param)pair.getValue();
			 out+="byte "+p.getParamName()+"="+p.getParamVal()+";\n";
		}
		return out;
	}
	


	public  void selectReqNum(boolean flag,int reqNum) {
		RequirementList.getReqlist().get(reqNum).setSelected(flag);;
	
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox s=(JCheckBox)(e.getSource());
		WorkSpace.getLog().debug(s.getActionCommand());
		switch(s.getActionCommand())
		{
		case "req1":
			WorkSpace.getLog().debug("req1 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,0);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(0).getReq());
		break;
		
		case "req2":
			WorkSpace.getLog().debug("req2 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,1);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(1).getReq());
		break;
		
		case "req3":
			WorkSpace.getLog().debug("req3 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,2);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(2).getReq());
		break;
		
		case "req4":
			WorkSpace.getLog().debug("req4 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,3);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(3).getReq());
		break;
		
		case "req5":
			WorkSpace.getLog().debug("req5 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,4);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(4).getReq());
		break;
		
		case "req6":
			WorkSpace.getLog().debug("req6 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,5);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(5).getReq());
		break;
		
		case "req7":
			WorkSpace.getLog().debug("req7 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,6);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(6).getReq());
		break;
		
		case "req8":
			WorkSpace.getLog().debug("req8 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,7);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(7).getReq());
		break;
		case "req9":
			WorkSpace.getLog().debug("req9 is selected");
			this.selectReqNum(e.getStateChange() == ItemEvent.SELECTED,8);
			WorkSpace.getLog().debug(RequirementList.getReqlist().get(8).getReq());
		break;
		}

}
}
