package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel("ListEvents");
	private JLabel jLabelQuery = new JLabel("Pregunta");
	private JLabel jLabelMinBet = new JLabel("Apuesta minima");
	private JLabel jLabelEventDate = new JLabel("EventDate");

	private JTextField jTextFieldQuery = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton("CreateQuery");
	private JButton jButtonClose = new JButton("Close");
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private final JLabel lblPronosticoUno = new JLabel("Pronostivo 1 (opcional)");
	private final JLabel lblPronosticoDos = new JLabel("Pronostivo 2 (opcional)");
	private final JTextField textFieldPronosticoDos = new JTextField();
	private final JTextField textFieldPronosticoUno = new JTextField();
	private JTextField textFieldMulUno;
	private JTextField textFieldMulDos;

	public CreateQuestionGUI(Vector<domain.Event> v) {
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle("Create Question");

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));
		jLabelQuery.setBounds(new Rectangle(25, 211, 75, 20));
		jTextFieldQuery.setBounds(new Rectangle(100, 211, 429, 20));
		jLabelMinBet.setBounds(new Rectangle(25, 243, 75, 20));
		jTextFieldPrice.setBounds(new Rectangle(100, 243, 60, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(100, 275, 130, 30));
		jButtonCreate.setEnabled(false);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(275, 275, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(175, 240, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);
		this.getContentPane().add(jTextFieldPrice, null);

		this.getContentPane().add(jLabelMinBet, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		lblPronosticoUno.setBounds(289, 123, 110, 14);
		
		getContentPane().add(lblPronosticoUno);
		lblPronosticoDos.setBounds(289, 154, 110, 14);
		
		getContentPane().add(lblPronosticoDos);
		textFieldPronosticoDos.setText((String) null);
		textFieldPronosticoDos.setColumns(10);
		textFieldPronosticoDos.setBounds(409, 151, 120, 20);
		
		getContentPane().add(textFieldPronosticoDos);
		textFieldPronosticoUno.setText((String) null);
		textFieldPronosticoUno.setColumns(10);
		textFieldPronosticoUno.setBounds(409, 120, 120, 20);
		
		getContentPane().add(textFieldPronosticoUno);
		
		textFieldMulUno = new JTextField();
		textFieldMulUno.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textFieldMulUno.setBounds(539, 120, 28, 20);
		getContentPane().add(textFieldMulUno);
		textFieldMulUno.setColumns(10);
		
		textFieldMulDos = new JTextField();
		textFieldMulDos.setText("");
		textFieldMulDos.setColumns(10);
		textFieldMulDos.setBounds(539, 151, 28, 20);
		getContentPane().add(textFieldMulDos);

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					jCalendar.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					try {
						BLFacade facade = PanelAdminGUI.getBusinessLogic();

						Vector<domain.Event> events = facade.getEvents(firstDay);
						if (events.isEmpty())
							jLabelListOfEvents.setText("NoEvents"
									+ ": " + dateformat1.format(calendarMio.getTime()));
						else
							jLabelListOfEvents.setText("Events" + ": "
									+ dateformat1.format(calendarMio.getTime()));
						jComboBoxEvents.removeAllItems();
						System.out.println("Events " + events);

						for (domain.Event ev : events)
							modelEvents.addElement(ev);
						jComboBoxEvents.repaint();

						if (events.size() == 0)
							jButtonCreate.setEnabled(false);
						else
							jButtonCreate.setEnabled(true);

					} catch (Exception e1) {

						jLabelError.setText(e1.getMessage());
					}

				}
				paintDaysWithEvents(jCalendar);
			}
		});
	}



/* Less eficient version: too many calls to business logic 
	  
	 
	public static void paintDaysWithEvents(JCalendar jCalendar) {
		// For each day in current month, it is checked if there are events, and in that
		// case, the background color for that day is changed.

		BLFacade facade = MainGUI.getBusinessLogic();

		Calendar calendar = jCalendar.getCalendar();

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);

		int offset = calendar.get(Calendar.DAY_OF_WEEK);
		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		int month = calendar.get(Calendar.MONTH);
		while (month == calendar.get(Calendar.MONTH)) {
			Vector<domain.Event> events = facade.getEvents(calendar.getTime());
			if (events.size() > 0) {
				// Obtain the component of the day in the panel of the DayChooser of the
				// JCalendar.
				// The component is located after the decorator buttons of "Sun", "Mon",... or
				// "Lun", "Mar"...,
				// the empty days before day 1 of month, and all the days previous to each day.
				// That number of components is calculated with "offset" and is different in
				// English and Spanish
//				    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
				Component o = (Component) jCalendar.getDayChooser().getDayPanel()
						.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
				o.setBackground(Color.CYAN);
			}
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
		}
		calendar.set(Calendar.MONTH, month);
	}

*/
	
	public static void paintDaysWithEvents(JCalendar jCalendar) {
		// For each day in current month, it is checked if there are events, and in that
		// case, the background color for that day is changed.

		BLFacade facade = PanelAdminGUI.getBusinessLogic();

		Vector<Date> dates=facade.getEventsMonth(jCalendar.getDate());
			
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:dates){

	 		calendar.setTime(d);
	 		System.out.println(d);
	 		

			
			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
	 		calendar.set(Calendar.DAY_OF_MONTH, 1);
	 		calendar.set(Calendar.MONTH, month);
	 	
	}
	
	
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldQuery.getText();

			if (inputQuery.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				float inputPrice = Float.parseFloat(jTextFieldPrice.getText());
				boolean check = true, checkuno = false, checkdos = false;
				float n = 0, m = 0;
				if (inputPrice <= 0) {
					jLabelError.setText("ErrorNumber");
				} else {

					// Obtain the business logic from a StartWindow class (local or remote)
					BLFacade facade = PanelAdminGUI.getBusinessLogic();
					Question q = new Question(jTextFieldQuery.getText(), inputPrice, event);
					if(textFieldPronosticoUno.getText()!=null) {
						for(int i=0;i<textFieldMulUno.getText().length();i++) {
							int ascii =(int) textFieldMulUno.getText().charAt(i);
							if(ascii>57 && ascii<48 && ascii!=46) {
								System.out.println("ascii"+ascii);
								check=false;
							}
						}
						if(check) {
							n = Float.parseFloat(textFieldMulUno.getText());
							if(n>1) {
								//Pronostico p1 = new Pronostico(textFieldPronosticoUno.getText(),n);
								//q.anadirPronostico(p1);
								checkuno=true;
							}else {
								check=false;
								jLabelError.setText("El multiplicador dese ser > 1");
							}
						}else {
							check=false;
							jLabelError.setText("El multiplicador debe ser un número");
					}
					if(textFieldPronosticoDos.getText()!=null) {
						for(int i=0;i<textFieldMulDos.getText().length();i++) {
							int ascii =(int) textFieldMulDos.getText().charAt(i);
							if(ascii>57 && ascii<48 && ascii!=46) {
								System.out.println("ascii"+ascii);
								check=false;
							}
						}
						if(check) {
							m = Float.parseFloat(textFieldMulDos.getText());
							System.out.println("AAAAAAAAAAAAA:"+m);
							if(m>1) {
								//Pronostico p2 = new Pronostico(textFieldPronosticoDos.getText(),m);
								//q.anadirPronostico(p2);
								checkdos=true;
							}else {
								check=false;
								jLabelError.setText("El multiplicador dese ser > 1");
							}
						}else {
							check=false;
							jLabelError.setText("El multiplicador debe ser un número");
						}
					}
					if(check) {
						q.setEvent(event);
						Question qt = facade.createQuestionQ(q);
						if(checkuno) {
							Pronostico p1 = new Pronostico(textFieldPronosticoUno.getText(),n,qt);
							qt.anadirPronostico(p1);
						}
						if(checkdos) {
							Pronostico p2 = new Pronostico(textFieldPronosticoDos.getText(),m,qt);
							qt.anadirPronostico(p2);
						}
						facade.updateQuestion(qt);
						jLabelMsg.setText("QueryCreated");
					}
					
				}
			} 
			}else {
				jLabelMsg.setText("ErrorQuery");
			}
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText("ErrorNumber");
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}