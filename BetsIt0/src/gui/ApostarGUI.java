package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Question;
import domain.User;
import domain.Event;
import domain.Pronostico;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class ApostarGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel("EventDate");
	private final JLabel jLabelQueries = new JLabel("Queries"); 
	private final JLabel jLabelEvents = new JLabel("Events"); 
	private final JLabel lblError = new JLabel();
	private final JButton btnApostar = new JButton("Apostar"); 
	private JButton jButtonClose = new JButton("Close");
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private ArrayList<Pronostico> l = null;
	private Vector<domain.Event> events = null;
	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	
	private String[] columnNamesEvents = new String[] {
			"EventN", 
			"Event"

	};
	private String[] columnNamesQueries = new String[] {
			"QueryN", 
			"Query"

	};
	
	private Vector<Question> preguntasdelevento=null;
	Question selectedquestion=null;
	private final JComboBox<String> comboBoxPronostico = new JComboBox<String>();
	private final JTextField textFieldCantidadApostada = new JTextField();
	private final JLabel lblCantidad = new JLabel("Cantidad: "); //$NON-NLS-1$ //$NON-NLS-2$
	private User user;
	public ApostarGUI(User u)
	{
		this.user=u;
		textFieldCantidadApostada.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textFieldCantidadApostada.setBounds(552, 264, 86, 20);
		textFieldCantidadApostada.setColumns(10);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle("Apostar");

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 211, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(274, 419, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
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
				dispose();
			}
		});
		
		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));



					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=PanelAdminGUI.getBusinessLogic();

						events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText("NoEvents"+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText("Events"+ ": "+dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 228, 406, 116));
		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();
				preguntasdelevento = ev.getQuestions();
				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText("NoQueries"+": "+ev.getDescription());
				else 
					jLabelQueries.setText("SelectedEvent"+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				int qn =(int)tableModelQueries.getValueAt(i,0); 
				for (Question p:preguntasdelevento){
					if(p.getQuestionNumber()==qn){
						selectedquestion=p;
						comboBoxPronostico.removeAllItems();
						l = selectedquestion.getPronosticos();
						System.out.println("AAAAAAAAAAAAAAAAAAAAAAsize "+l.size());
						for(Pronostico pr: l) {
							comboBoxPronostico.addItem(pr.toString());
						}
					}else {
						System.out.println("Pregunta que no es: "+p.getQuestionNumber()+" / "+qn);
					}
				}
				System.out.println("Pregunta seleccionada:");
				System.out.println(selectedquestion);
				
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
			
		});
		
		
		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		
		lblError.setForeground(Color.RED);
		lblError.setBounds(243, 367, 366, 14);
		getContentPane().add(lblError);
		comboBoxPronostico.setBounds(456, 231, 182, 22);
		getContentPane().add(comboBoxPronostico);
		
		getContentPane().add(textFieldCantidadApostada);
		lblCantidad.setBounds(481, 267, 61, 14);
		
		getContentPane().add(lblCantidad);
		
		
		btnApostar.setBounds(516, 302, 89, 23);
		getContentPane().add(btnApostar);
		btnApostar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton3_actionPerformed(e);
			}
		});

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		if(user==null) {
			PanelAnonimoGUI pa=new PanelAnonimoGUI();
			pa.setVisible(true);
			dispose();
		}
		else {
		PanelUsuarioGUI pu = new PanelUsuarioGUI(user);
		pu.setVisible(true);
		dispose();
		}
	}
	private void jButton3_actionPerformed(ActionEvent e) {
		if (user==null) {
			lblError.setText("Tienes que loguearte para Apostar");
		}
		else {
		boolean check = true;
		if(selectedquestion!=null) {
			if(comboBoxPronostico.getSelectedItem()!=null) {
				if(textFieldCantidadApostada.getText()!=null) {
					for(int i=0;i<textFieldCantidadApostada.getText().length();i++) {
						int ascii =(int) textFieldCantidadApostada.getText().charAt(i);
						if(ascii>57 && ascii<48 && ascii!=46) {
							check=false;
						}
					}
						if(check) {
							float inputPrice = Float.parseFloat(textFieldCantidadApostada.getText());
							Pronostico pr = null;
							int np= 0;
							System.out.println("dinero user: " + user.getDinero());
							System.out.println(inputPrice);
							if(inputPrice<=user.getDinero()) {
								if(inputPrice>selectedquestion.getBetMinimum()) {
									for(Pronostico p: l) {
										if(p.toString()==comboBoxPronostico.getSelectedItem()) {
											pr=p;
											
											System.out.println(pr.toString());
											break;
										}
										np++;
									}
									System.out.println("ok1");
									BLFacade facade=PanelAdminGUI.getBusinessLogic();	
									
									facade.crearApuesta(user,selectedquestion,comboBoxPronostico.getSelectedIndex(),inputPrice,selectedquestion.getEvent());
									System.out.println("dineroApostado"+inputPrice);
									user = facade.addDinero(user.getId(),-inputPrice);
									//user = facade.addDinero(user.getId(),-inputPrice);
									//user = facade.addDinero(user.getId(),-inputPrice);
									
									
									lblError.setText("Apuesta realizada con exito");
								}else {
									lblError.setText("Introduce un numero superior a "+selectedquestion.getBetMinimum());
								}
							}else{
								lblError.setText("No dispones de suficiente dinero");
							}
						}else {
							lblError.setText("Introduce un numero");
						}
					
				}else {
					lblError.setText("Introduce una cantidad");
				}
			}else {
				lblError.setText("Selecciona un pronostico");
			}
		}else {
			lblError.setText("Selecciona una pregunta");
		}
	}
}
}
