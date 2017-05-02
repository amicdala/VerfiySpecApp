package ToolGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Router;
import Model.Screen;
import Model.WorkSpace;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;

import java.awt.Font;

public class MainScreenGui extends JFrame  {
	private JLabel specNameLabel;
	private JButton btnAddscreen;
	private JButton btnOpen;
	private JButton btnNew;
	private JButton btnExport;
	private JButton btnRunVerification;
	private JButton btnShowresults;
	
	public  MainScreenGui() {
		
	
		setSize(750,600);
		getContentPane().setLayout(null);
		
		 btnAddscreen = new JButton("AddScreen");
		btnAddscreen.setBounds(25, 55, 99, 23);
		getContentPane().add(btnAddscreen);
		
		specNameLabel = new JLabel();
		specNameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		specNameLabel.setBounds(34, 11, 163, 35);
		getContentPane().add(specNameLabel);
		
		 btnOpen = new JButton("Open..");
		btnOpen.setBounds(511, 17, 89, 23);
		getContentPane().add(btnOpen);
		
		 btnNew = new JButton("New");
		btnNew.setBounds(412, 17, 89, 23);
		btnNew.setActionCommand("_create_NewSpec");
		getContentPane().add(btnNew);

		 btnExport = new JButton("Save SPEC");
		btnExport.setBounds(610, 17, 99, 23);
		getContentPane().add(btnExport);
		
		btnRunVerification= new JButton("Verifiy SPEC");

		btnRunVerification.setBounds(442, 513, 136, 23);
		getContentPane().add(btnRunVerification);
		
		 btnShowresults = new JButton("ShowResults");
		btnShowresults.setBounds(588, 513, 136, 23);
		getContentPane().add(btnShowresults);
	 }
		public JLabel getSpecNameLabel() {
			return specNameLabel;
		}
		public void setSpecNameLabel(String specNameLabel) {
			this.specNameLabel.setText(specNameLabel); 
		}
		public void addMainScreenListener(ActionListener listenForOperation){       
			btnAddscreen.addActionListener(listenForOperation);
			btnExport.addActionListener(listenForOperation);
			btnNew.addActionListener(listenForOperation);
			btnOpen.addActionListener(listenForOperation);
			btnRunVerification.addActionListener(listenForOperation);
			btnShowresults.addActionListener(listenForOperation);
			btnRunVerification.addActionListener(listenForOperation);
	}
		public void addMainScreenMouseListener(MouseListener WorkSpaceController){       
		
			this.addMouseListener(WorkSpaceController);
		}
		public void addMainScreenMouseListener(MouseMotionListener WorkSpaceController)
		{
			this.addMouseMotionListener(WorkSpaceController);
		}
		
}