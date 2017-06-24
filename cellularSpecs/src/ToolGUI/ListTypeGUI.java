package ToolGUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class ListTypeGUI extends JFrame {
	private JTextArea textArea;
	private JTextField elementName;
	private String values[]={""} ; 
	JButton butListSave;
	String ScreenName; 
	public ListTypeGUI(String screenName)
	{
		this.ScreenName=screenName; 
		setTitle("List Element");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblOnoff = new JLabel(screenName+"-List Element");
		lblOnoff.setFont(new Font("Arial", Font.BOLD, 22));
		lblOnoff.setBounds(20, 11, 361, 36);
		getContentPane().add(lblOnoff);
		
		JLabel lblName = new JLabel("Element Name:");
		lblName.setBounds(20, 44, 74, 14);
		getContentPane().add(lblName);
		
		JLabel lblDefaulval = new JLabel("DefaultVal:");
		lblDefaulval.setBounds(21, 218, 64, 14);
		getContentPane().add(lblDefaulval);
		
		elementName = new JTextField();
		elementName.setBounds(104, 41, 152, 20);
		getContentPane().add(elementName);
		elementName.setColumns(10);
		
		JComboBox parameterNAme = new JComboBox();
		parameterNAme.setModel(new DefaultComboBoxModel(new String[] {"new parameter", "1", "2", "3", "4", "5"}));
		parameterNAme.setBounds(104, 63, 152, 22);
		getContentPane().add(parameterNAme);
		setSize(501, 367);
		
		JComboBox comboBox = new JComboBox();


		comboBox.setModel(new DefaultComboBoxModel(values));
		comboBox.setBounds(104, 215, 152, 20);
		getContentPane().add(comboBox);
		
		
		JLabel lblValues = new JLabel("Values:");
		lblValues.setBounds(21, 93, 64, 14);
		getContentPane().add(lblValues);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 96, 149, 108);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		setSize(501, 405);
		
		values=textArea.getText().split("\n");
		comboBox.setModel(new DefaultComboBoxModel(values));
		
		JButton btnSave = new JButton("save");
		btnSave.setBounds(73, 365, 89, 23);
		getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.setBounds(201, 365, 89, 23);
		getContentPane().add(btnCancel);
		System.out.println(values.toString());
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 243, 445, 2);
		getContentPane().add(separator);
		ImageIcon imageForOne = new ImageIcon(getClass().getResource("../add.png"));
		JButton button = new JButton("Add action",imageForOne);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setBackground(UIManager.getColor("Button.highlight"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(20, 243, 236, 28);
		getContentPane().add(button);
		
		 butListSave = new JButton("Save");
		 butListSave.setToolTipText("");
		butListSave.setBounds(120, 295, 112, 23);
		getContentPane().add(butListSave);
		
		JButton button_2 = new JButton("Cancel");
		button_2.setBounds(242, 295, 116, 23);
		getContentPane().add(button_2);
		
		JLabel lblParameterName = new JLabel("Parameter name");
		lblParameterName.setBounds(20, 67, 89, 14);
		getContentPane().add(lblParameterName);
		setSize(501, 378);
		
		comboBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				values=textArea.getText().split("\n");
				comboBox.setModel(new DefaultComboBoxModel(values));
				System.out.println(values.toString());
			}
		});
		
	}
	public String[] getValues() {
		return textArea.getText().split("\n");
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	public void setListTypeListener(ActionListener listTypeListener ){       
		
		 butListSave.addActionListener(listTypeListener);
		 butListSave.setActionCommand("_save_List");
	}
	public JTextField getElementName() {
		return elementName;
	}
	public String getScreenName() {
		return ScreenName;
	}
	

}
