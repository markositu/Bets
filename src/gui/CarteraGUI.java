package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.User;
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


public class CarteraGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	private JTextField labelCantidad;
	private JButton btnLogout;
	private User u;
	private JLabel labelInfoDinero;
	private JList list;
	private DefaultListModel<String> mov;
	/**
	 * This is the default constructor
	 */
	public CarteraGUI(User pU) {
		super();
		this.u =pU;
		getbtnLogout();
		getContentPane().setLayout(null);
		
		
		JLabel lblUser_1 = new JLabel("DInero del Usuario: "); 
		lblUser_1.setBounds(147, 22, 70, 15);
		getContentPane().add(lblUser_1);
		
		  labelInfoDinero = new JLabel();
		labelInfoDinero.setText(""+this.u.getDinero());
		labelInfoDinero.setBounds(229, 22, 70, 15);
		getContentPane().add(labelInfoDinero);
		
		
		
		
		
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numMov=u.getNumMov();
				
				u.addDinero(Float.parseFloat(labelCantidad.getText()));				
				mov.add(0,u.getMovimientos().get(numMov+1));
				appFacadeInterface.addDinero(u.getId(), Float.parseFloat(labelCantidad.getText()));
				labelInfoDinero.setText(""+u.getDinero());
				
			}
		});
		
		btnIngresar.setBounds(116, 100, 117, 25);
		getContentPane().add(btnIngresar);
		
		JButton btnRetirar = new JButton("Retirar"); //$NON-NLS-1$ //$NON-NLS-2$
		
		btnRetirar.setBounds(237, 100, 117, 25);
		btnRetirar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {
			int numMov=u.getNumMov();
			
			u.addDinero(-1*Float.parseFloat(labelCantidad.getText()));
			labelInfoDinero.setText(""+u.getDinero());
			mov.add(0,u.getMovimientos().get(numMov+1));
			appFacadeInterface.addDinero(u.getId(),-1* Float.parseFloat(labelCantidad.getText()));
			
			
			
			
		}}
	);
		getContentPane().add(btnRetirar);
		
		labelCantidad = new JTextField();
		//labelCantidad.setText("Cantidad"); //$NON-NLS-1$ //$NON-NLS-2$
		labelCantidad.setBounds(222, 61, 114, 19);
		getContentPane().add(labelCantidad);
		labelCantidad.setColumns(10);
		
		labelCantidad.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad: "); //$NON-NLS-1$ //$NON-NLS-2$
		lblCantidad.setBounds(147, 63, 70, 15);
		getContentPane().add(lblCantidad);
		
		
		JLabel lblMovimientos = new JLabel("Movimientos"); //$NON-NLS-1$ //$NON-NLS-2$
		lblMovimientos.setBounds(172, 137, 134, 12);
		getContentPane().add(lblMovimientos);
		
		int indice=0;
		System.out.println(indice);
		mov=new DefaultListModel<String>();

		for(String i:u.getMovimientos()) {
			
			mov.add(0, i);
			
		}
		list=new JList(mov);
		
		// list = new JList(movimie);
		list.setVisibleRowCount(3);
		list.setBounds(136, 161, 220, 73);
		
		
		JScrollPane scrollBar = new JScrollPane(list);
		scrollBar.setBounds(120, 161, 280, 73);//332, 163, 17, 61);
		getContentPane().add(scrollBar);
		
		JButton btnAtrs = new JButton("Atr�s");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelUsuarioGUI p=new PanelUsuarioGUI(u);
				
				p.setVisible(true);
				dispose();
			}
		});
		btnAtrs.setBounds(12, -1, 117, 25);
		getContentPane().add(btnAtrs);
		
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
	private JButton getbtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout"); 
			btnLogout.setBounds(390, 0, 89, 23);
			btnLogout.setVisible(true);
			getContentPane().add(btnLogout);
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
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setTitle("Cartera");
	}
} // @jve:decl-index=0:visual-constraint="0,0"