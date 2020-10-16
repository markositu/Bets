package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
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
import domain.User;


public class PanelUsuarioGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;
    private static BLFacade appFacadeInterface;
    private User user;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnLogout;
	private JButton btnCartera;
	private JButton jButtonApostarGUI;
	/**
	 * This is the default constructor
	 */
	public PanelUsuarioGUI(User usuario) {
		super();
		user=usuario;
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
		this.setSize(496, 355);
		this.setContentPane(getJContentPane());
		this.setTitle("Panel de Usuario");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getPanel());
			jContentPane.add(getBtnNewButton());
			jContentPane.add(getJButtonApostarGUI());
			
			JButton rellenarQuiniela = new JButton("Rellenar Quiniela"); //$NON-NLS-1$ //$NON-NLS-2$
			rellenarQuiniela.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					RellenarQuinielaGUI r=new RellenarQuinielaGUI(user);
					r.setVisible(true);
					dispose();
				}
			});
			rellenarQuiniela.setBounds(252, 73, 220, 62);
			jContentPane.add(rellenarQuiniela);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 73, 239, 62);
			jButtonQueryQueries.setText("QueryQueries");
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
		
	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel("SelectOption");
			jLabelSelectOption.setBounds(121, 0, 239, 62);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(121, 221, 239, 62);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
			panel.add(getbtnLogout());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText("SelectOption");
		jButtonQueryQueries.setText("QueryQueries");
		this.setTitle("Panel de Usuario");
	}
	private JButton getbtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout"); 
			btnLogout.setBounds(390, 0, 89, 23);
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LoguinGUI l=new LoguinGUI();
					l.setVisible(true);
					dispose();
				}
			});
		}
		return btnLogout;
	}
	private JButton getBtnNewButton() {
		
			btnCartera = new JButton("Cartera"); //$NON-NLS-1$ //$NON-NLS-2$
			btnCartera.setBounds(368, 0, 117, 25);
			btnCartera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CarteraGUI c=new CarteraGUI(user);
					c.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
		
		return btnCartera;
	}
	private JButton getJButtonApostarGUI() {
		if (jButtonApostarGUI == null) {
			jButtonApostarGUI = new JButton();
			jButtonApostarGUI.setBounds(121, 144, 239, 62);
			jButtonApostarGUI.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ApuestaCombinadaGUI d=new ApuestaCombinadaGUI(user);
					d.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
			jButtonApostarGUI.setText("Apostar");
		}
		return jButtonApostarGUI;
	}
} // @jve:decl-index=0:visual-constraint="0,0"


