package ToolGUI;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;



import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import Controller.ElementController;
import Controller.ScreenController;
import Model.ElementType;
import Model.MyAction;
import Model.WorkSpace;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;
import javax.swing.JMenuBar;

import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmptyNotEmptyGUI extends JFrame implements ActionListener {
	protected JTextField txtUndefined;
	protected JTextField elementName;
	protected  int  x=0,y=0,hight=143,width=30;
	protected static JButton btnSave;
	private JComboBox ComboparameterNames;
	private String defaultValue;;
	private JRadioButton rdbtnOff,rdbtnOn ;
	private  int colNumber=3;
	private Object[][] data = {};
	String ScreenName; 
	private JTextField ParameterName;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextArea exception;
	private ArrayList<String> On_To_Off_Condition;
	private ArrayList<String>  Off_To_On_Condition;
	private ArrayList<String> On_To_Off_Action;
	private ArrayList<String>  Off_To_On_Action;
	private TextArea textAreaOnToOff;
	private TextArea textAreaOffToON;
	private  AddConditonGui addconditonGui;
	private AddActionGUI addactionGui;
	private String switchTo="";
	private EditReomveConditionGUI edRmCon;
	private TextArea ActionAreaOnToOff ;
	private TextArea ActionAreaOffToON;
	private ActionManagment actions;
	private conditionManagment condition ;
	private EmptyNotEmptyGUI thisref = this;
	public EmptyNotEmptyGUI(String ScreenName,String eName)
	{
		On_To_Off_Condition=new ArrayList<String>();
		Off_To_On_Condition=new ArrayList<String>();
		On_To_Off_Action=new ArrayList<String>();
		Off_To_On_Action=new ArrayList<String>();
		this.ScreenName=ScreenName; 
		setTitle("ON-OFF");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		 lblNewLabel_2 = new JLabel("New label");
		 lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		JLabel lblOnoff = new JLabel(ScreenName+"- Empty/NotEmpty");
		lblOnoff.setFont(new Font("Arial", Font.BOLD, 22));
		lblOnoff.setBounds(30, 18, 361, 36);
		getContentPane().add(lblOnoff);
		
		JLabel lblName = new JLabel("Element name:");
		lblName.setBounds(30, 68, 78, 14);
		getContentPane().add(lblName);
		
		JLabel lblDefaulval = new JLabel("DefaultVal:");
		lblDefaulval.setBounds(30, 120, 64, 14);
		getContentPane().add(lblDefaulval);
		
		elementName = new JTextField("");
		elementName.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				lblNewLabel_2.setVisible(false);
			}
		});
		elementName.setBounds(118, 65, 152, 20);
		getContentPane().add(elementName);
		elementName.setColumns(10);

		ComboparameterNames = new JComboBox();
		ComboparameterNames.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if((arg0.getItem().toString().equals("New..")))
				{
					ParameterName.setVisible(true);
					ParameterName.setText("parameter name");
				}
				else
				{
					ParameterName.setVisible(false);
					lblNewLabel_3.setVisible(false);
					ParameterName.setText(arg0.getItem().toString());
				}
			}
		});
		ComboparameterNames.setBounds(118, 89, 152, 22);
		getContentPane().add(ComboparameterNames);
	
		
		 btnSave = new JButton("Save");
		 
		 btnSave.setActionCommand("_save_EmptyNEmpty");
			
		btnSave.setBounds(118, 512, 112, 23);
		getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		 	//	edRmCon=new EditReomveConditionGUI();
		 		edRmCon.setVisible(true);
			}
		});
		btnCancel.setBounds(280, 512, 116, 23);
		getContentPane().add(btnCancel);
		rdbtnOff = new JRadioButton(ElementType.getEmpty());
		rdbtnOff.setSelected(true);
		setDefaultValue(rdbtnOff.getText());
		 rdbtnOn = new JRadioButton(ElementType.getNotEmpty());
		rdbtnOn.setSelected(false);
		rdbtnOn.setBounds(118, 113, 72, 28);
		getContentPane().add(rdbtnOn);
		rdbtnOff.setBounds(192, 113, 78, 28);
		getContentPane().add(rdbtnOff);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOn);
		group.add(rdbtnOff);
		
		rdbtnOn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnOn.isSelected())
					setDefaultValue(rdbtnOn.getText());
				}
		});

		rdbtnOff.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			if(rdbtnOff.isSelected())
				setDefaultValue(rdbtnOff.getText());
			}
		});
		ImageIcon imageForOne = new ImageIcon(getClass().getResource("../add.png"));
		
		JLabel lblNewLabel_1 = new JLabel("Parameter name");
		lblNewLabel_1.setBounds(29, 93, 79, 14);
		getContentPane().add(lblNewLabel_1);
		setSize(477, 613);
			
			ParameterName = new JTextField();
			ParameterName.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					lblNewLabel_3.setVisible(false);
				}
			});
			
			getContentPane().add(ParameterName);
			ParameterName.setForeground(Color.GRAY);
			ParameterName.setText("parameter name");
			ParameterName.setVisible(true);
			ParameterName.setBounds(280, 90, 152, 20);
			ParameterName.setToolTipText("add new Parameter");
			

			 lblNewLabel_2.setVisible(false);
			lblNewLabel_2.setForeground(Color.RED);
			lblNewLabel_2.setBounds(280, 68, 184, 14);
			getContentPane().add(lblNewLabel_2);
			
			 
			 lblNewLabel_3.setVisible(false);
			lblNewLabel_3.setForeground(Color.RED);
			lblNewLabel_3.setBounds(281, 113, 194, 14);
			getContentPane().add(lblNewLabel_3);
			String[] doc_columnNames = { "Empty->NotEmpty","NotEmpty->Empty"};
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBackground(Color.RED);
				separator_1.setForeground(Color.RED);
				separator_1.setOrientation(SwingConstants.VERTICAL);
				separator_1.setBounds(50, 58, 445, 2);
				getContentPane().add(separator_1);
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBounds(64, 155, 343, 364);
				getContentPane().add(tabbedPane);
				
				JPanel panel_1 = new JPanel();
				panel_1.setVisible(false);
				tabbedPane.addTab("Reqular Transation", null, panel_1, null);
				panel_1.setLayout(null);
				
				JLabel lblOnoff_1 = new JLabel("Empty->NotEmpty");
				lblOnoff_1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
				lblOnoff_1.setBounds(80, 11, 161, 14);
				panel_1.add(lblOnoff_1);
				
				JLabel lblConditions = new JLabel("Conditions for transiton");
				lblConditions.setBounds(30, 36, 190, 14);
				panel_1.add(lblConditions);
				
				JLabel lblActrions = new JLabel("Parameters will be changed");
				lblActrions.setBounds(30, 164, 190, 14);
				panel_1.add(lblActrions);
				
				 textAreaOnToOff = new TextArea("",5,100,TextArea.SCROLLBARS_NONE);
				 textAreaOnToOff.setEditable(false);
				 textAreaOnToOff.addMouseListener(new MouseAdapter() {
				 	@Override
				 	public void mouseClicked(MouseEvent arg0) {
				 		
						condition= new conditionManagment(getParameterName(),On_To_Off_Condition,ElementType.getEmpty());
						condition.getFrame().setVisible(true);
						condition.setListener(thisref);
						WorkSpace.getLog().debug("I do add cond for OFF ");

				 	}
				 });
				 textAreaOnToOff.setBounds(30, 54, 190, 50);
				 panel_1.add(textAreaOnToOff);
				 
				  ActionAreaOnToOff = new TextArea("", 5, 100, TextArea.SCROLLBARS_NONE);
				  ActionAreaOnToOff.setEditable(false);
				  ActionAreaOnToOff.setBounds(29, 184, 190, 95);
				  ActionAreaOnToOff.addMouseListener(new MouseAdapter() {
				   	@Override
				   	public void mouseClicked(MouseEvent arg0) {
				   		
				   		System.out.println("edit textarea");
				  	    actions = new ActionManagment(getParameterName(),On_To_Off_Action,ElementType.getEmpty());
				  		actions.getFrame().setVisible(true);
				  		actions.setListener(thisref);
				   	}
				   });
				  panel_1.add(ActionAreaOnToOff);
				  
				  
				  		
				  		
				  		JSeparator separator = new JSeparator();
				  		separator.setVisible(false);
				  		separator.setBounds(267, 21, 2, 222);
				  		panel_1.add(separator);
				  		separator.setOrientation(SwingConstants.VERTICAL);
				  		separator.setBackground(Color.DARK_GRAY);
				  		
				  						ActionAreaOffToON = new TextArea("", 5, 100, TextArea.SCROLLBARS_NONE);
				  						ActionAreaOffToON.setVisible(false);
				  						ActionAreaOffToON.setEditable(false);
				  						ActionAreaOffToON.setBounds(313, 184, 190, 95);
				  						ActionAreaOffToON.addMouseListener(new MouseAdapter() {
				  						 	@Override
				  						 	public void mouseClicked(MouseEvent arg0) {
				  						 		
				  						 		System.out.println("edit textarea");
				  							    actions = new ActionManagment(getParameterName(),Off_To_On_Action,ElementType.getEmpty());
				  								actions.getFrame().setVisible(true);
				  								actions.setListener(thisref);
				  						 	}
				  						 });
				  						panel_1.add(ActionAreaOffToON);
				  						
				  						
				  						 textAreaOffToON = new TextArea("", 5, 100, TextArea.SCROLLBARS_NONE);
				  						 textAreaOffToON.setVisible(false);
				  						 textAreaOffToON.setEditable(false);
				  						 textAreaOffToON.setBounds(314, 54, 190, 50);
				  						 textAreaOffToON.addMouseListener(new MouseAdapter() {
				  						  	@Override
				  						  	public void mouseClicked(MouseEvent arg0) {
				  						  		
				  						 		condition= new conditionManagment(getParameterName(),Off_To_On_Condition,ElementType.getEmpty());
				  						 		condition.getFrame().setVisible(true);
				  						 		condition.setListener(thisref);
				  						 		WorkSpace.getLog().debug("I do add cond for ON ");

				  						  	}
				  						  });
				  						 panel_1.add(textAreaOffToON);
				  						 
				  						 JLabel lblActions = new JLabel("Parameters will be changed");
				  						 lblActions.setVisible(false);
				  						 lblActions.setBounds(313, 164, 190, 14);
				  						 panel_1.add(lblActions);
				  						 
				  						 JLabel lblConditionsForTransiton = new JLabel("Conditions for transiton");
				  						 lblConditionsForTransiton.setVisible(false);
				  						 lblConditionsForTransiton.setBounds(314, 36, 190, 14);
				  						 panel_1.add(lblConditionsForTransiton);
				  						 
				  						 Button button_1 = new Button("Add /Edit Conditions");
				  						 button_1.setActionCommand("_add_condition_ON_To_Off");
				  						 button_1.addActionListener(this);
				  						 button_1.setBounds(30, 102, 190, 22);
				  						 panel_1.add(button_1);
				  						 
				  						 Button button_3 = new Button("Add /Edit Conditions");
				  						 button_3.setVisible(false);
				  						 button_3.setActionCommand("_add_condition_Off_To_On");
				  						 button_3.addActionListener(this);
				  						 button_3.setBounds(314, 100, 190, 22);
				  						 panel_1.add(button_3);
				  						 
				  						 Button button_2 = new Button("Add /edit Actions");
				  						 button_2.setVisible(false);
				  						 button_2.setBackground(Color.LIGHT_GRAY);
				  						 button_2.setBounds(313, 282, 192, 22);
				  						 panel_1.add(button_2);
				  						 button_2.setActionCommand("_add_action_Off_To_On");
				  						 button_2.addActionListener(this);
				  						 
				  						 Button button = new Button("Add /edit Actions");
				  						 button.setBounds(29, 282, 190, 22);
				  						 panel_1.add(button);
				  						 button.setActionCommand("_add_action_ON_To_Off");
				  						 
				  						 JLabel lblOffon = new JLabel("OFF->ON");
				  						 lblOffon.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
				  						 lblOffon.setBounds(364, 11, 90, 14);
				  						 panel_1.add(lblOffon);
				  						 
				  						 JPanel panel = new JPanel();
				  						 panel.setLayout(null);
				  						 tabbedPane.addTab("Exception", null, panel, null);
				  						 
				  						 JLabel lblExceptionForParamter = new JLabel("Exception for paramter (optional)");
				  						 lblExceptionForParamter.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
				  						 lblExceptionForParamter.setBounds(21, 11, 284, 14);
				  						 panel.add(lblExceptionForParamter);
				  						 
				  						 JLabel lblWriteExceptionFor = new JLabel("Write exception for this param");
				  						 lblWriteExceptionFor.setBounds(20, 268, 190, 14);
				  						 panel.add(lblWriteExceptionFor);
				  						 
				  						 JScrollPane scrollPane = new JScrollPane();
				  						 scrollPane.setBounds(10, 65, 313, 202);
				  						 panel.add(scrollPane);
				  						 
				  						 exception = new JTextArea();
				  						 scrollPane.setViewportView(exception);
				  						 button.addActionListener(this);
			ParameterName.addFocusListener(new FocusListener() {
		
				public void focusGained(FocusEvent arg0) {
					ParameterName.setForeground(Color.black);
					ParameterName.setText("");
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					
				}
			});
			
			if (ElementController.elementIsExist(ScreenName,eName ))
			{
				WorkSpace.getLog().debug(" filling data to this gui");
				loadData(ElementController.getDataOfElement(ScreenName,eName),eName) ; 
			}
		
	}
	private void loadData(	ArrayList <String> dataOfelement,String eName) {
		elementName.setText(dataOfelement.get(0));
		setParameterName(ScreenController.getParams(ElementType.getEmptyNotEmptyType(), ScreenName,dataOfelement.get(1),dataOfelement.get(0)));
		ComboparameterNames.setSelectedItem(dataOfelement.get(1));
		setOnOff(dataOfelement.get(2));
		//addToTable(ElementController.getActrion(getScreenName(),eName));
		WorkSpace.getLog().debug("I do setActionArrayList");
		setException(dataOfelement.get(3));
		setActionArrayList(WorkSpace.getInstance().getParamsByName(dataOfelement.get(1))
				.getActions(ElementType.getEmpty()),ElementType.getEmpty());
		setActionArrayList(WorkSpace.getInstance().getParamsByName(dataOfelement.get(1))
				.getActions(ElementType.getNotEmpty()),ElementType.getNotEmpty());
		
		setActionArea(On_To_Off_Action,ElementType.getNotEmpty());
		setActionArea(Off_To_On_Action,ElementType.getEmpty());

		
		
		btnSave.setActionCommand("_edit_EmptyNEmpty");	
		
	}
	protected JLabel CreateLabel(String string, int x2, int y2, int hight2, int width2) {
	       JLabel lblNewLabel = new JLabel(string);
	        lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
	        lblNewLabel.setBounds(x2, y2, hight2, width2);
	      lblNewLabel.setText(" "+elementName.getText().toString()+"");
	      return lblNewLabel;
		// TODO Auto-generated method stub
	}
	
	public JTextArea getException() {
		return exception;
	}
	public void setException(String t) {
		 this.exception.setText(t);;
	}
	
	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}
	public void setLblNewLabel_3(String hint) {
		this.lblNewLabel_3.setText(hint);
		this.lblNewLabel_3.setVisible(true);;
	}
	public String getElementName() {
		return elementName.getText().toString();
	}
	
	public String getScreenName() {
		return ScreenName;
	}
	public String getDefaultValue() {
		return this.defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public void setParameterName(String []  parameterNames) {		
        DefaultComboBoxModel cbm = new DefaultComboBoxModel(parameterNames);
        ComboparameterNames.setModel(cbm);
	}
	public void setOnOff(String defultval) {
	if(defultval.equals("OFF"))
	{
		rdbtnOff.setSelected(true);
		setDefaultValue(rdbtnOff.getText());
	}
	else
		rdbtnOn.setSelected(true);
	setDefaultValue(rdbtnOn.getText());
	}

	public void setEmptyListener(ActionListener OnOfTypeListener ){       
		btnSave.addActionListener(OnOfTypeListener);
	}
	public void setParamChangeListener(ItemListener OnOfTypeListener)
	{
	ComboparameterNames.addItemListener(OnOfTypeListener);
	}
	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}
	public void setLblNewLabel_2(String hint) {
		lblNewLabel_2.setVisible(true);
		this.lblNewLabel_2.setText(hint);;
	}
	public void setParameterName(String parameterName) {
		JTextField tx=new JTextField();
		tx.setText(parameterName);
		ParameterName = tx;
	}
	public String getParameterName() {
		return ParameterName.getText().toString();
	}

	public String getComboParameterName() {
		return ComboparameterNames.getSelectedItem().toString();
	}
	public ArrayList<String> get_Off_To_On_Condition() {
		return Off_To_On_Condition;
	}

	public ArrayList<String> get_On_To_Off_Actions() {
		return On_To_Off_Action;
	}
	public ArrayList<String> get_Off_To_ON_Actions() {
		return Off_To_On_Action;
	}
	public ArrayList<String> get_On_To_Off_Conditions()
	{
		return On_To_Off_Condition;
	}
	


	public void addTotextAreaOnToOf(String textArea) {
		this.textAreaOnToOff.setText(textArea);
	}
	public void addTotextAreaOffToOn(String textArea) {
		this.textAreaOffToON.setText(textArea);
	}
	public void setActionAreaOnToOff(String textArea) {
		this.ActionAreaOnToOff.setText(textArea);
	}
	public void setActionAreaOffToOn(String textArea) {
		this.ActionAreaOffToON.setText(textArea);
	}
	

	public void setTextArea(ArrayList<String> ActionArray, String switchTo) {
		String data=new String(); 
		this.textAreaOnToOff.setText("");
		this.textAreaOffToON.setText("");
		if(switchTo.equals(ElementType.getNotEmpty()))
		{
			if(null ==ActionArray){
				this.textAreaOnToOff.setText("cond not defined yet!");

			}
			if (ActionArray.size()>0){
				data=ActionArray.get(0);
			}
			for(int i=1;i<ActionArray.size();i++)
			{
				data += " && "+ActionArray.get(i);	
		
			}
			addTotextAreaOnToOf(data);
		}
		else
		{
			if(null ==ActionArray){
			this.textAreaOffToON.setText("cond not defined yet!");
			}
			if (ActionArray.size()>0){
				data=ActionArray.get(0);
			}
			for(int i=1;i<ActionArray.size();i++)
			{
				data += " && "+ActionArray.get(i);	
		
			}
			addTotextAreaOffToOn(data);
		}
	}
	public void setActionArea(ArrayList<String> ActionArray, String switchTo) {
		String data =new String(""); 
		this.ActionAreaOffToON.setText("");
		this.ActionAreaOnToOff.setText("");
		if(switchTo.equals(ElementType.getNotEmpty()))
		{
			if(null ==ActionArray){
				this.ActionAreaOnToOff.setText("Actions not defined yet!");

			}
			for(String i:ActionArray)
			{
				data += i+"\n";	
		
			}
			setActionAreaOnToOff(data);
		}
		else
		{
			if(null ==ActionArray){
			this.ActionAreaOffToON.setText("Actions not defined yet!");
			}
			for(String i:ActionArray)
			{
				data += i+"\n";	
		
			}
			setActionAreaOffToOn(data);
		}
	}
	
	private void setaddconditonGui(String st)
	{
		switchTo=st;
		addconditonGui=new AddConditonGui();
		addconditonGui.setSwitchlbl("switch to"+switchTo);
		addconditonGui.setVisible(true);
		addconditonGui.setAddAconditionListener(this);
		addconditonGui.setAddAconditionListener(null);
	}
	private void setaddActionGui(String st)
	{
		switchTo=st;
		addactionGui=new AddActionGUI();
		addactionGui.setSwitchlbl("switch to"+switchTo);
		addactionGui.setVisible(true);

	}
	private void addConditionToTextArea(String switchTo) {
		String st=addconditonGui.getParameterNameCombo()+addconditonGui.getLabel_equal()+addconditonGui.getParameterValueCombo();
		if(switchTo.equals(ElementType.getNotEmpty()))
		{
			On_To_Off_Condition.add(st);
			setTextArea(On_To_Off_Condition,switchTo);
			
		}
		else
		{
			Off_To_On_Condition.add(st);
			setTextArea(Off_To_On_Condition,switchTo);
		}
		addconditonGui.setVisible(false);	
	}
	private void setActionArrayList(ArrayList<MyAction> actions,String switchTo) {
		if(switchTo.equals(ElementType.getNotEmpty()))
		{
			for (MyAction i : actions ){
				On_To_Off_Action.add(i.getActionString());			
			}
		}
		else
		{
			for (MyAction i : actions ){
				Off_To_On_Action.add(i.getActionString());			
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand())
		{
	
		case ("_add_condition_ON_To_Off"):
			//getParameterName()
			condition= new conditionManagment(getParameterName(),On_To_Off_Condition,ElementType.getNotEmpty());
			condition.getFrame().setVisible(true);
			condition.setListener(thisref);
			WorkSpace.getLog().debug("I do add cond for OFF ");

			break;
			
		case ("_add_condition_Off_To_On"):
			condition= new conditionManagment(getParameterName(),Off_To_On_Condition,ElementType.getEmpty());
			condition.getFrame().setVisible(true);
			condition.setListener(thisref);
			WorkSpace.getLog().debug("I do add cond for ON ");

			break;
		case ("_save_cond_NotEmpty"):
			Off_To_On_Condition=condition.getdata();
			setTextArea(Off_To_On_Condition,ElementType.getNotEmpty());
			WorkSpace.getLog().debug("I do save_cond for ON ");
			condition.getFrame().setVisible(false);
			break;
		case ("_save_cond_Empty"):
			On_To_Off_Condition=condition.getdata();
			setTextArea(On_To_Off_Condition,ElementType.getEmpty());
			WorkSpace.getLog().debug("I do save_cond for OFF ");
			condition.getFrame().setVisible(false);

			break;
		case ("_add_action_ON_To_Off"):
			 actions = new ActionManagment(getParameterName(),On_To_Off_Action,ElementType.getNotEmpty());
			 actions.getFrame().setVisible(true);
			 actions.setListener(thisref);
		break;
		case ("_add_action_Off_To_On"):
		    actions = new ActionManagment(getParameterName(),Off_To_On_Action,ElementType.getNotEmpty());
			actions.getFrame().setVisible(true);
			actions.setListener(thisref);

		break;
		case ("_save_actions_NotEmpty"):
			Off_To_On_Action=actions.getdata();
			setActionArea(Off_To_On_Action,ElementType.getEmpty());
			WorkSpace.getLog().debug("I do save_actions for ON ");
//			WorkSpace.getLog().debug(actions.getdata().get(0)+actions.getdata().get(1)+actions.getdata().get(2));
//			WorkSpace.getLog().debug(Off_To_On_Action.get(0)+Off_To_On_Action.get(1)+Off_To_On_Action.get(2));
			actions.getFrame().dispose();		
			break;
	case ("_save_actions_Empty"):
			On_To_Off_Action=actions.getdata();
			setActionArea(On_To_Off_Action,ElementType.getNotEmpty());
			WorkSpace.getLog().debug("I do save_actions for OFF ");
			actions.getFrame().dispose();
			break;

	}
		
		
	}
}
