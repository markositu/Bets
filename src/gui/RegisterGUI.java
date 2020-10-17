package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

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


public class RegisterGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JPasswordField confirmPass;
	private JRadioButton rdbtnAdmin;
	private JLabel errorText;
	
	/**
	 * This is the default constructor
	 */
	public RegisterGUI() {
		super();
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblPassword = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblPassword.setBounds(60, 101, 70, 15);
		getContentPane().add(lblPassword);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(234, 73, 156, 19);
		getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		
		passwordField.setBounds(234, 101, 156, 19);
		getContentPane().add(passwordField);
		
		JLabel lblUser_1 = new JLabel("User"); //$NON-NLS-1$ //$NON-NLS-2$
		lblUser_1.setBounds(60, 75, 70, 15);
		getContentPane().add(lblUser_1);
		
		JLabel lblPassword_1 = new JLabel("Password"); //$NON-NLS-1$ //$NON-NLS-2$
		lblPassword_1.setBounds(60, 101, 70, 15);
		getContentPane().add(lblPassword_1);
		
		rdbtnAdmin = new JRadioButton("Admin"); //$NON-NLS-1$ //$NON-NLS-2$
		rdbtnAdmin.setBounds(205, 175, 149, 23);
		getContentPane().add(rdbtnAdmin);
		

		
		JLabel lblConfirmPassword = new JLabel("Confirm password"); //$NON-NLS-1$ //$NON-NLS-2$
		lblConfirmPassword.setBounds(60, 127, 117, 15);
		getContentPane().add(lblConfirmPassword);
		
		confirmPass = new JPasswordField();
		
		confirmPass.setBounds(234, 124, 156, 19);
		getContentPane().add(confirmPass);
		
		JButton btnRegister = new JButton("Register"); //$NON-NLS-1$ //$NON-NLS-2$
		btnRegister.setBounds(181, 205, 117, 25);
		getContentPane().add(btnRegister);
		
		 errorText = new JLabel();
		errorText.setForeground(Color.RED);
		errorText.setBounds(142, 47, 248, 15);
		getContentPane().add(errorText);
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				System.out.println("botón pulsado");
				if(String.valueOf(confirmPass.getPassword()).equals(String.valueOf(passwordField.getPassword()))){
					System.out.println("coinciden");
					if(!rdbtnAdmin.isSelected()) {
						
						BLFacade bl= RegisterGUI.getBusinessLogic();
						String pass=String.copyValueOf(passwordField.getPassword());
						System.out.println(pass);
						System.out.println(textUsuario.getText());
						System.out.println(bl);
						if(bl.existeUsuario(textUsuario.getText())) {
							errorText.setText("Ya existe ese usuario");
						}
						else {
							bl.registrarUsuario(textUsuario.getText(),pass );
							LoguinGUI l=new LoguinGUI();
							l.setVisible(true);
							dispose();
						}
						
							
							
						
					}
					else {
						BLFacade bl= RegisterGUI.getBusinessLogic();
						String pass=String.copyValueOf(passwordField.getPassword());
						System.out.println(pass);
						System.out.println(textUsuario.getText());
						System.out.println(bl);
						if(bl.existeAdmi(textUsuario.getText())) {
							errorText.setText("Ya existe ese admin");
						}
						else {
							bl.registrarAdmin(textUsuario.getText(),pass );
							LoguinGUI l=new LoguinGUI();
							l.setVisible(true);
							dispose();
						}
						
					}
					
				}
				else {
					System.out.println("no coinciden");
					errorText.setText("Contraseñas no coinciden");
				}
				
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
				dispose();	
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
		this.setTitle("Register");
	}
	
	
} // @jve:decl-index=0:visual-constraint="0,0"

	