package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Admin;
import domain.Event;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;


public class LoguinGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JPasswordField passwordField;
	private JRadioButton rdbtnAdmin;
	private JLabel errorText;
	/**
	 * This is the default constructor
	 */
	public LoguinGUI() {
		super();
		getContentPane().setForeground(Color.RED);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblUser.setBounds(60, 75, 70, 15);
		getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblPassword.setBounds(60, 116, 70, 15);
		getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(234, 73, 114, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		passwordField.setBounds(234, 114, 119, 19);
		getContentPane().add(passwordField);
		
		JLabel lblUser_1 = new JLabel("Usuario"); //$NON-NLS-1$ //$NON-NLS-2$
		lblUser_1.setBounds(72, 75, 70, 15);
		getContentPane().add(lblUser_1);
		
		JLabel lblPassword_1 = new JLabel("Password"); //$NON-NLS-1$ //$NON-NLS-2$
		lblPassword_1.setBounds(60, 116, 70, 15);
		getContentPane().add(lblPassword_1);
		
		rdbtnAdmin = new JRadioButton("Admin"); //$NON-NLS-1$ //$NON-NLS-2$
		rdbtnAdmin.setBounds(228, 141, 149, 23);
		getContentPane().add(rdbtnAdmin);
		
		JButton btnLogin = new JButton("Login"); //$NON-NLS-1$ //$NON-NLS-2$
		btnLogin.setBounds(186, 172, 117, 25);
		getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BLFacade bl=LoguinGUI.getBusinessLogic();
				if(!rdbtnAdmin.isSelected()) {
					try {
					System.out.println("entra");
					if(bl.compararContrasenasU(textField.getText(),String.copyValueOf(passwordField.getPassword()))){
						User u= bl.getUserById(textField.getText());
						
						System.out.println("TEST:"+u.toString());
						System.out.println("login ok!");
						PanelUsuarioGUI pu = new PanelUsuarioGUI(u);
						pu.setVisible(true);
						dispose();
					}
					else {
						errorText.setText("Contraseña incorrecta");
					}}
					catch(Exception e){
						errorText.setText("Usuario no existe");
					}
				}
				else {
					
					try {
					if(bl.compararContrasenasA(textField.getText(),String.copyValueOf(passwordField.getPassword()))){
						Admin a= bl.getAdminById(textField.getText());
						PanelAdminGUI pa = new PanelAdminGUI();
						pa.setVisible(true);
						dispose();
					}
					else {
						errorText.setText("Contraseña incorrecta");
						
					}}
					catch(Exception e) {
						errorText.setText("Admin no existe");
					}
				}
				
			}
		});
		
		JButton btnRegister = new JButton("Register"); //$NON-NLS-1$ //$NON-NLS-2$
		btnRegister.setBounds(186, 209, 117, 25);
		getContentPane().add(btnRegister);
		
		 errorText = new JLabel();
		 errorText.setForeground(Color.RED);
		errorText.setBounds(186, 47, 220, 15);
		getContentPane().add(errorText);
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				RegisterGUI r=new RegisterGUI();
				r.setVisible(true);
				dispose();
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setTitle("Login");
	}
} // @jve:decl-index=0:visual-constraint="0,0"

	
