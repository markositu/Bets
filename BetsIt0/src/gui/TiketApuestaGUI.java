package gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import businessLogic.BLFacade;
import domain.Apuesta;
import domain.User;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class TiketApuestaGUI extends JFrame {
	public JList list;
	public DefaultListModel<String> mov;
	public JScrollPane scrollBar;
	public ArrayList<Apuesta> apuestas;
	public TiketApuestaGUI(ArrayList<Apuesta> pApuestas) {
		
		apuestas=pApuestas;
		mov=new DefaultListModel<String>();
		setBounds(700, 101, 220, 323);
		getContentPane().setLayout(null);
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(59, 262, 109, 25);
		getContentPane().add(btnQuitar);
		list=new JList(mov);
		list.setVisibleRowCount(10);
		list.setBounds(136, 161, 225, 75);
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(0, 0, 220, 250);
		getContentPane().add(scrollBar);
		
	
	
	
		
		
		
		btnQuitar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				mov.remove(list.getSelectedIndex());
				apuestas.remove(list.getSelectedIndex()+1);
				
				
				
			}
		});
		
		
		
		
		
	}
}
