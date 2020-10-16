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


public class PanelAdminGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonSubirResultadoQuiniela= null;
	private JButton jbuttoncreatequestion = null;
    private static BLFacade appFacadeInterface;
	
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
	private JButton btnCreatePronostico;
	
	/**
	 * This is the default constructor
	 */
	public PanelAdminGUI() {
		super();
		
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
		this.setContentPane(getJContentPane());
		this.setTitle("Panel de Admin");
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
			jContentPane.add(getBoton4());
			jContentPane.add(getBoton2());
			jContentPane.add(getPanel());
			jContentPane.add(getBtnCreatePronostico());
			jContentPane.add(getbtnLogout());
			jContentPane.add(getBtnCreatePronostico());
			
			JButton btnSubirResultado = new JButton("Subir Resultado"); //$NON-NLS-1$ //$NON-NLS-2$
			btnSubirResultado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SubirResultadoGUI sr = new SubirResultadoGUI(null);
					sr.setVisible(true);
					dispose();
				}
			});
			btnSubirResultado.setBounds(326, 115, 143, 62);
			jContentPane.add(btnSubirResultado);
			
			JButton btnCrearQuiniela = new JButton("Crear Quiniela"); //$NON-NLS-1$ //$NON-NLS-2$
			btnCrearQuiniela.setBounds(170, 115, 150, 62);
			jContentPane.add(btnCrearQuiniela);
			btnCrearQuiniela.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CrearQuinielaGUI sr = new CrearQuinielaGUI(null);
					sr.setVisible(true);
					dispose();
				}
			});
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBounds(326, 50, 143, 62);
			jButtonCreateQuery.setText("Create Event");
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade=PanelAdminGUI.getBusinessLogic();
					Vector<Event> events=facade.getAllEvents();
					JFrame a = new CreateEventGUI(events);
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonSubirResultadoQuiniela == null) {
			jButtonSubirResultadoQuiniela = new JButton();
			jButtonSubirResultadoQuiniela.setBounds(10, 115, 150, 62);
			jButtonSubirResultadoQuiniela.setText("Subir Resultado Quiniela");
			jButtonSubirResultadoQuiniela.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SubirResultadoQuinielaGUI a = new SubirResultadoQuinielaGUI();

					a.setVisible(true);
					dispose();
				}
			});
		}
		return jButtonSubirResultadoQuiniela;
	}
	/*
	private JButton getBoton4() {
		if (jbuttoncreatequestion == null) {
			jbuttoncreatequestion = new JButton();
			jbuttoncreatequestion.setBounds(0, 50, 239, 62);
			jbuttoncreatequestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			jbuttoncreatequestion.setText("PanelAdminGUI.jbuttoncreatequestion.text")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return jbuttoncreatequestion;
	}
	*/
	private JButton getBoton4() {
		if (jbuttoncreatequestion == null) {
			jbuttoncreatequestion = new JButton();
			jbuttoncreatequestion.setBounds(10, 50, 150, 62);
			jbuttoncreatequestion.setText("Create Question");
			jbuttoncreatequestion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade=PanelAdminGUI.getBusinessLogic();
					Vector<Event> events=facade.getAllEvents();
					JFrame a = new CreateQuestionGUI(events);
					a.setVisible(true);
				}
			});
		}
		return jbuttoncreatequestion;
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
			panel.setBounds(121, 178, 239, 62);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText("SelectOption");
		jButtonSubirResultadoQuiniela.setText("QueryQueries");
		jButtonCreateQuery.setText("CreateQuery");
		this.setTitle("Panel de Admin");
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

	private JButton getBtnCreatePronostico() {
		if (btnCreatePronostico == null) {
			btnCreatePronostico = new JButton("Create Pronostico");
			btnCreatePronostico.setBounds(170, 50, 150, 62);
			btnCreatePronostico.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade=PanelAdminGUI.getBusinessLogic();
					//Vector<Event> events=facade.getAllEvents();
					JFrame d = new CreatePronosticoGUI();
					d.setVisible(true);
				}
			});
		}
		return btnCreatePronostico;
	}
}
