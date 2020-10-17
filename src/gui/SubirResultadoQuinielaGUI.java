package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.Quiniela;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import domain.User;


public class SubirResultadoQuinielaGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
    private static BLFacade appFacadeInterface;
    private User user;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnLogout;
	
	private List<Quiniela> listaQuinielas;
	private Quiniela quinielaActual;
	private List<JRadioButton>rb1;
	private List<JRadioButton>rbx;
	private List<JRadioButton>rb2;
	private JLabel lblError;
	/**
	 * This is the default constructor
	 */
	public SubirResultadoQuinielaGUI() {
		super();
		
		BLFacade facade=PanelAdminGUI.getBusinessLogic();
		listaQuinielas = facade.sacarQuinielas();
		quinielaActual = listaQuinielas.get(listaQuinielas.size()-1);
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
		this.setSize(480, 560);
		this.setContentPane(getJContentPane());
		this.setTitle("Subir Resultado Quiniela");
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
			jContentPane.add(getbtnLogout());
			
			JLabel lbl2 = new JLabel(""); 
			lbl2.setBounds(66, 72, 176, 14);
			jContentPane.add(lbl2);
			////
			
			
			
			
			
			
			
			///
			lblError = new JLabel("");
			lblError.setBounds(92, 432, 70, 15);
			jContentPane.add(lblError);
			JRadioButton rdbtn2_2 = new JRadioButton("2"); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtn2_2.setBounds(392, 63, 57, 23);
			jContentPane.add(rdbtn2_2);
			
			JRadioButton rdbtn1_2 = new JRadioButton("1"); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtn1_2.setBounds(258, 63, 65, 23);
			jContentPane.add(rdbtn1_2);
			
			JRadioButton rdbtnx_2 = new JRadioButton("x"); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnx_2.setBounds(325, 63, 65, 23);
			jContentPane.add(rdbtnx_2);
			
			JLabel lbl3 = new JLabel("New label");
			lbl3.setBounds(66, 97, 176, 14);
			jContentPane.add(lbl3);
			
			JRadioButton rdbtn1_3 = new JRadioButton("1");
			rdbtn1_3.setBounds(258, 88, 65, 23);
			jContentPane.add(rdbtn1_3);
			
			JRadioButton rdbtnx_3 = new JRadioButton("X");
			rdbtnx_3.setBounds(325, 88, 65, 23);
			jContentPane.add(rdbtnx_3);
			
			JRadioButton rdbtn2_3 = new JRadioButton("2");
			rdbtn2_3.setBounds(392, 88, 57, 23);
			jContentPane.add(rdbtn2_3);
			
			JLabel lbl4 = new JLabel("New label");
			lbl4.setBounds(66, 122, 176, 14);
			jContentPane.add(lbl4);
			
			JRadioButton rdbtn1_4 = new JRadioButton("1");
			rdbtn1_4.setBounds(258, 113, 65, 23);
			jContentPane.add(rdbtn1_4);
			
			JRadioButton rdbtnx_4 = new JRadioButton("X");
			rdbtnx_4.setBounds(325, 113, 65, 23);
			jContentPane.add(rdbtnx_4);
			
			JRadioButton rdbtn2_4 = new JRadioButton("2");
			rdbtn2_4.setBounds(392, 113, 57, 23);
			jContentPane.add(rdbtn2_4);
			
			JLabel lbl5 = new JLabel("New label");
			lbl5.setBounds(66, 147, 176, 14);
			jContentPane.add(lbl5);
			
			JRadioButton rdbtn1_5 = new JRadioButton("1");
			rdbtn1_5.setBounds(258, 138, 65, 23);
			jContentPane.add(rdbtn1_5);
			
			JRadioButton rdbtnx_5 = new JRadioButton("X");
			rdbtnx_5.setBounds(325, 138, 65, 23);
			jContentPane.add(rdbtnx_5);
			
			JRadioButton rdbtn2_5 = new JRadioButton("2");
			rdbtn2_5.setBounds(392, 138, 57, 23);
			jContentPane.add(rdbtn2_5);
			
			JLabel lbl6 = new JLabel("New label");
			lbl6.setBounds(66, 172, 176, 14);
			jContentPane.add(lbl6);
			
			JRadioButton rdbtn1_6 = new JRadioButton("1");
			rdbtn1_6.setBounds(258, 163, 65, 23);
			jContentPane.add(rdbtn1_6);
			
			JRadioButton rdbtnx_6 = new JRadioButton("X");
			rdbtnx_6.setBounds(325, 163, 65, 23);
			jContentPane.add(rdbtnx_6);
			
			JRadioButton rdbtn2_6 = new JRadioButton("2");
			rdbtn2_6.setBounds(392, 163, 57, 23);
			jContentPane.add(rdbtn2_6);
			
			JLabel lbl7 = new JLabel("New label");
			lbl7.setBounds(66, 197, 176, 14);
			jContentPane.add(lbl7);
			
			JRadioButton rdbtn1_7 = new JRadioButton("1");
			rdbtn1_7.setBounds(258, 188, 65, 23);
			jContentPane.add(rdbtn1_7);
			
			JRadioButton rdbtnx_7 = new JRadioButton("X");
			rdbtnx_7.setBounds(325, 188, 65, 23);
			jContentPane.add(rdbtnx_7);
			
			JRadioButton rdbtn2_7 = new JRadioButton("2");
			rdbtn2_7.setBounds(392, 188, 57, 23);
			jContentPane.add(rdbtn2_7);
			
			JLabel lbl8 = new JLabel("New label");
			lbl8.setBounds(66, 217, 176, 14);
			jContentPane.add(lbl8);
			
			JRadioButton rdbtn1_8 = new JRadioButton("1");
			rdbtn1_8.setBounds(258, 213, 65, 23);
			jContentPane.add(rdbtn1_8);
			
			JRadioButton rdbtnx_8 = new JRadioButton("X");
			rdbtnx_8.setBounds(325, 213, 65, 23);
			jContentPane.add(rdbtnx_8);
			
			JRadioButton rdbtn2_8 = new JRadioButton("2");
			rdbtn2_8.setBounds(392, 213, 57, 23);
			jContentPane.add(rdbtn2_8);
			
			JLabel lbl9 = new JLabel("New label");
			lbl9.setBounds(66, 242, 176, 14);
			jContentPane.add(lbl9);
			
			JRadioButton rdbtn1_9 = new JRadioButton("1");
			rdbtn1_9.setBounds(258, 238, 65, 23);
			jContentPane.add(rdbtn1_9);
			
			JRadioButton rdbtnx_9 = new JRadioButton("X");
			rdbtnx_9.setBounds(325, 238, 65, 23);
			jContentPane.add(rdbtnx_9);
			
			JRadioButton rdbtn2_9 = new JRadioButton("2");
			rdbtn2_9.setBounds(392, 238, 57, 23);
			jContentPane.add(rdbtn2_9);
			
			JLabel lbl10 = new JLabel("New label");
			lbl10.setBounds(66, 267, 176, 14);
			jContentPane.add(lbl10);
			
			JRadioButton rdbtn1_10 = new JRadioButton("1");
			rdbtn1_10.setBounds(258, 263, 65, 23);
			jContentPane.add(rdbtn1_10);
			
			JRadioButton rdbtnx_10 = new JRadioButton("X");
			rdbtnx_10.setBounds(325, 263, 65, 23);
			jContentPane.add(rdbtnx_10);
			
			JRadioButton rdbtn2_10 = new JRadioButton("2");
			rdbtn2_10.setBounds(392, 263, 57, 23);
			jContentPane.add(rdbtn2_10);
			
			JLabel lbl11 = new JLabel("New label");
			lbl11.setBounds(66, 292, 176, 14);
			jContentPane.add(lbl11);
			
			JRadioButton rdbtn1_11 = new JRadioButton("1");
			rdbtn1_11.setBounds(258, 288, 65, 23);
			jContentPane.add(rdbtn1_11);
			
			JRadioButton rdbtnx_11 = new JRadioButton("X");
			rdbtnx_11.setBounds(325, 288, 65, 23);
			jContentPane.add(rdbtnx_11);
			
			JRadioButton rdbtn2_11 = new JRadioButton("2");
			rdbtn2_11.setBounds(392, 288, 57, 23);
			jContentPane.add(rdbtn2_11);
			
			JLabel lbl12 = new JLabel("New label");
			lbl12.setBounds(66, 317, 176, 14);
			jContentPane.add(lbl12);
			
			JRadioButton rdbtn1_12 = new JRadioButton("1");
			rdbtn1_12.setBounds(258, 313, 65, 23);
			jContentPane.add(rdbtn1_12);
			
			JRadioButton rdbtnx_12 = new JRadioButton("X");
			rdbtnx_12.setBounds(325, 313, 65, 23);
			jContentPane.add(rdbtnx_12);
			
			JRadioButton rdbtn2_12 = new JRadioButton("2");
			rdbtn2_12.setBounds(392, 313, 57, 23);
			jContentPane.add(rdbtn2_12);
			
			JLabel lbl13 = new JLabel("New label");
			lbl13.setBounds(66, 342, 176, 14);
			jContentPane.add(lbl13);
			
			JRadioButton rdbtn1_13 = new JRadioButton("1");
			rdbtn1_13.setBounds(258, 338, 65, 23);
			jContentPane.add(rdbtn1_13);
			
			JRadioButton rdbtnx_13 = new JRadioButton("X");
			rdbtnx_13.setBounds(325, 338, 65, 23);
			jContentPane.add(rdbtnx_13);
			
			JRadioButton rdbtn2_13 = new JRadioButton("2");
			rdbtn2_13.setBounds(392, 338, 57, 23);
			jContentPane.add(rdbtn2_13);
			
			JLabel lbl14 = new JLabel("New label");
			lbl14.setBounds(66, 367, 176, 14);
			jContentPane.add(lbl14);
			
			JRadioButton rdbtn1_14 = new JRadioButton("1");
			rdbtn1_14.setBounds(258, 363, 65, 23);
			jContentPane.add(rdbtn1_14);
			
			JRadioButton rdbtnx_14 = new JRadioButton("X");
			rdbtnx_14.setBounds(325, 363, 65, 23);
			jContentPane.add(rdbtnx_14);
			
			JRadioButton rdbtn2_14 = new JRadioButton("2");
			rdbtn2_14.setBounds(392, 363, 57, 23);
			jContentPane.add(rdbtn2_14);
			
			JLabel lbl15 = new JLabel("New label");
			lbl15.setBounds(66, 392, 176, 14);
			jContentPane.add(lbl15);
			
			JRadioButton rdbtn1_15 = new JRadioButton("1");
			rdbtn1_15.setBounds(258, 388, 65, 23);
			jContentPane.add(rdbtn1_15);
			
			JRadioButton rdbtnx_15 = new JRadioButton("X");
			rdbtnx_15.setBounds(325, 388, 65, 23);
			jContentPane.add(rdbtnx_15);
			
			JRadioButton rdbtn2_15 = new JRadioButton("2");
			rdbtn2_15.setBounds(392, 388, 57, 23);
			jContentPane.add(rdbtn2_15);
			
			JLabel lbl1 = new JLabel("New label");
			lbl1.setBounds(66, 51, 176, 14);
			jContentPane.add(lbl1);
			List<Event> p=quinielaActual.getQuiniela();
			Event q=p.get(0);lbl1.setText(q.getDescription());
			q=p.get(1);lbl2.setText(q.getDescription());
			q=p.get(2);lbl3.setText(q.getDescription());
			q=p.get(3);lbl4.setText(q.getDescription());
			q=p.get(4);lbl5.setText(q.getDescription());
			q=p.get(5);lbl6.setText(q.getDescription());
			q=p.get(6);lbl7.setText(q.getDescription());
			q=p.get(7);lbl8.setText(q.getDescription());
			q=p.get(8);lbl9.setText(q.getDescription());
			q=p.get(9);lbl10.setText(q.getDescription());
			q=p.get(10);lbl11.setText(q.getDescription());
			q=p.get(11);lbl12.setText(q.getDescription());
			q=p.get(12);lbl13.setText(q.getDescription());
			q=p.get(13);lbl14.setText(q.getDescription());
			q=p.get(14);lbl15.setText(q.getDescription());
			
			
			
			
			JRadioButton rdbtn1_1 = new JRadioButton("1");
			rdbtn1_1.setBounds(258, 42, 65, 23);
			jContentPane.add(rdbtn1_1);
			
			JRadioButton rdbtnx_1 = new JRadioButton("X");
			rdbtnx_1.setBounds(325, 42, 65, 23);
			jContentPane.add(rdbtnx_1);
			
			JRadioButton rdbtn2_1 = new JRadioButton("2");
			rdbtn2_1.setBounds(392, 42, 57, 23);
			jContentPane.add(rdbtn2_1);
			rb1=new ArrayList<JRadioButton>();
			rbx=new ArrayList<JRadioButton>();
			rb2=new ArrayList<JRadioButton>();
			rb1.add(rdbtn1_1);
			rb1.add(rdbtn1_2);
			rb1.add(rdbtn1_3);
			rb1.add(rdbtn1_4);
			rb1.add(rdbtn1_5);
			rb1.add(rdbtn1_6);
			rb1.add(rdbtn1_7);
			rb1.add(rdbtn1_8);
			rb1.add(rdbtn1_9);
			rb1.add(rdbtn1_10);
			rb1.add(rdbtn1_11);
			rb1.add(rdbtn1_12);
			rb1.add(rdbtn1_13);
			rb1.add(rdbtn1_14);
			rb1.add(rdbtn1_15);
			rb2.add(rdbtn2_1);
			rb2.add(rdbtn2_2);
			rb2.add(rdbtn2_3);
			rb2.add(rdbtn2_4);
			rb2.add(rdbtn2_5);
			rb2.add(rdbtn2_6);
			rb2.add(rdbtn2_7);
			rb2.add(rdbtn2_8);
			rb2.add(rdbtn2_9);
			rb2.add(rdbtn2_10);
			rb2.add(rdbtn2_11);
			rb2.add(rdbtn2_12);
			rb2.add(rdbtn2_13);
			rb2.add(rdbtn2_14);
			rb2.add(rdbtn2_15);
			rbx.add(rdbtnx_1);
			rbx.add(rdbtnx_2);
			rbx.add(rdbtnx_3);
			rbx.add(rdbtnx_4);
			rbx.add(rdbtnx_5);
			rbx.add(rdbtnx_6);
			rbx.add(rdbtnx_7);
			rbx.add(rdbtnx_8);
			rbx.add(rdbtnx_9);
			rbx.add(rdbtnx_10);
			rbx.add(rdbtnx_11);
			rbx.add(rdbtnx_12);
			rbx.add(rdbtnx_13);
			rbx.add(rdbtnx_14);
			rbx.add(rdbtnx_15);
			
			JButton btnCompletarQuiniela = new JButton("Subir Resultado"); //$NON-NLS-1$ //$NON-NLS-2$
			btnCompletarQuiniela.setBounds(193, 453, 109, 23);
			jContentPane.add(btnCompletarQuiniela);
			btnCompletarQuiniela.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					//Completar Quiniela Rellena
					int resultadoQuiniela[]=new int[15];
					boolean error=false;
					
					for(int i=0;i<15;i++) {
						int numSel=0;
						if(rb1.get(i).isSelected()) {
							resultadoQuiniela[i]=1;
							numSel++;
						}
						 if(rb2.get(i).isSelected()) {
							resultadoQuiniela[i]=2;
							numSel++;
						}
						if(rbx.get(i).isSelected()) {
							resultadoQuiniela[i]=3;
							numSel++;
						}
						if (numSel>1) {
							lblError.setText("Solo puedes seleccionar una opción por evento");
							 error=true;
							break;
						}
					
					}
					
					if(!error) {
						
						BLFacade facade=PanelAdminGUI.getBusinessLogic();
						facade.subirResultadoQuiniela(quinielaActual,resultadoQuiniela);
					}
					
			}});
			JButton btnClose = new JButton("Close"); 
			btnClose.setBounds(193, 487, 109, 23);
			jContentPane.add(btnClose);
			
			
			btnClose.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					PanelAdminGUI pu = new PanelAdminGUI();
					pu.setVisible(true);
					dispose();
					
				}
			});
		}
		
		return jContentPane;
	}
	
	private JButton getbtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout"); 
			btnLogout.setBounds(193, 0, 109, 23);
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
} 


