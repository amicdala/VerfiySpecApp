package Controller;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Model.Screen;
import Model.WorkSpace;
import ToolGUI.*;

public class ElementController implements ActionListener,MouseListener,MouseMotionListener {
	private AddScreenGUI addScreenGUI;
	private   MainScreenGui mainScreenGui ;
	private  int x,y;
	private String elementName;

	public ElementController()
	{
		this.x=0;
		this.y=0;	
		this.mainScreenGui=MainScreenGui.getInstance();
	}
public ElementController(MainScreenGui mainScreenGui,ScreenGUI screenGui)
{
	

}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public void actionPerformed(ActionEvent arg0) {
	WorkSpace.getLog().debug("choose which operation we will do in AddScreenControll");
	switch(arg0.getActionCommand())
	{
	case "Delete screen":
		
	}

	//System.out.println(arg0.getActionCommand().toString()); Button name
	System.out.println(elementName+""+arg0.getActionCommand());
	switch(elementName+" "+arg0.getActionCommand())
	{
	case ("List Save"):
		elementName=null;
	System.out.println(elementName+""+arg0.getActionCommand());
	break;
	}
}
@Override
public void mouseDragged(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseMoved(MouseEvent arg0) {
}
@Override
public void mouseClicked(MouseEvent arg0) {
	if(arg0.getButton()== MouseEvent.BUTTON3)
	{
		System.out.println("abc");

					 
   	System.out.println("def");
	}
	// TODO Auto-generated method stub
	
	
}
@Override
public void mouseEntered(MouseEvent arg0) {
}
@Override
public void mouseExited(MouseEvent arg0) {
}
@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
