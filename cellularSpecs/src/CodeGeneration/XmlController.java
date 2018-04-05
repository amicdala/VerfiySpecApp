package CodeGeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Model.*;

public class XmlController {
	
	String code;
	
	public void GenerateScreen (File file, Screen s, String rootScreen) {
		
		// creating xml code of screen
		code = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"+
			   "<android.support.constraint.ConstraintLayout\n"+
			   "xmlns:android=\"http://schemas.android.com/apk/res/android\"\n"+
			   "	xmlns:app=\"http://schemas.android.com/apk/res-auto\"\n"+
			   "  	xmlns:tools=\"http://schemas.android.com/tools\"\n"+
			   "  	android:layout_width=\"match_parent\"\n"+
			   "  	android:layout_height=\"match_parent\"\n";
		if (s.getScreenName() != rootScreen)
			code += "  	tools:context=\"com.example."+getApplicationName(WorkSpace.getInstance().getWorkSpaceName())+"."+s.getScreenName()+"\"> \n";
		else
			code += "  	tools:context=\"com.example."+getApplicationName(WorkSpace.getInstance().getWorkSpaceName())+".MainActivity\"> \n";
		code += "  </android.support.constraint.ConstraintLayout>\n";
		
		Iterator<Entry<String, Element>> it = WorkSpace.getInstance().getScreenByName(s.getScreenName()).getElementsMap().entrySet().iterator();	// iterator for elements in screen
		while(it.hasNext()){	// going through all the elements in the screen
			Map.Entry pair2 =(Map.Entry) it.next(); 
			Element e = (Element)pair2.getValue();		// current element. generate for all types of elements
			if (e.getType() == ElementType.getStandartBtnType()) 
				GenerateButton((StandartButtonType) e);
			if (e.getType() == ElementType.getOnOffType()) 
				GenerateOnOff((OnOffType) e);
			if (e.getType() == ElementType.getEmptyNotEmptyType())
					GenerateEmptyNotEmpty((EmptyNEmptyType) e);
			if (e.getType() == ElementType.getListType()) 
				GenerateList((ListElementType) e);	
		}
		
		try {		// writing all code to java screen file
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw, true);
			pw.println(code);
			pw.close();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
			WorkSpace.getLog().debug("Failed handling files in XmlController.GenerateScreen");
		}
	}
	
	private void GenerateButton (StandartButtonType s) {}
	
	private void GenerateOnOff (OnOffType s) {}
	
	private void GenerateEmptyNotEmpty (EmptyNEmptyType s) {}
	
	private void GenerateList (ListElementType s) {}
	
	private String getApplicationName(String workSpaceName){
		return workSpaceName.toLowerCase();
	}

}
