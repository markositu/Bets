package test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Vector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;


import java.text.SimpleDateFormat;
import test.TestFacadeImplementation;



public class BLFacadeUpdateQuestionTest {
	
	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	private TestFacadeImplementation testBL = new TestFacadeImplementation();
	
	
	private String queryText = "A question";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private final String date = "18/10/2030";
	private Event ev;
	
	
	@Test
	@DisplayName("Test1: evento vacio")
	void updateQuestionTest1() {
		try {
			Date d = new Date(date);
			ev = testBL.addEvent(queryText, d);
			Question qu = new Question((Integer) 1, "Primera pregunta", (float) 20, ev);
			Vector<Question> veQ = new Vector();
			veQ.add(qu);
	
			// Actualizamos la pregunta
			sut.updateQuestion(qu);
	
			// Comprobacion
			Vector<Event> events = sut.getEvents(d);
			for (Event event : events) {
				if (event.getEventNumber() == ev.getEventNumber()) {
					assertEquals(veQ, event.getQuestions());
				}
			}
		
		} catch (Exception e) {
			fail("No problems should arise: ParseException/QuestionaAlreadyExist");
		
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			assertTrue(b);
		}
	}
	
	@Test
	@DisplayName("Test2: Evento con preguntas actualizadas")
	void updateQuestionDATest2() {

		try{
			Date d = new Date(date);
			ev = testBL.addEvent(queryText, d);
			Question q = new Question((Integer) 1, "Primera pregunta", (float) 20, ev);
			Question q2 = new Question((Integer) 1, "Segunda pregunta", (float) 20, ev);
			Question q3 = new Question((Integer) 1, "Tercera pregunta", (float) 20, ev);
			Vector<Question> veQ = new Vector();
			veQ.add(q);
			veQ.add(q2);
			veQ.add(q3);
			ev.setQuestions(veQ);
			//Despues de asignar las preguntas crearemos el array esperado
			//Donde modificaremos la tercera pregunta
			veQ.removeElement(q3);
			q3.setQuestion("Pregunta n.3");
			veQ.add(q3);
			
			sut.updateQuestion(q3);

			
			Vector<Event> events = sut.getEvents(d);
			for (Event event : events) {
				if (event.getEventNumber() == ev.getEventNumber()) {
					assertEquals(veQ, event.getQuestions());
				}
			}
		} catch (Exception e) {
		fail("No problems should arise: ParseException/QuestionaAlreadyExist");
	
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			assertTrue(b);
		}
		
	}
	
	@Test
	@DisplayName("Test3:Evento con preguntas sin parametro correcto")
	void updateQuestionTest3() {
		
		try {
			Date d = new Date(date);
			ev = testBL.addEvent(queryText, d);
			Question qu = new Question((Integer) 1, "1a pregunta", (float) 20, ev);
			Question qu2 = new Question((Integer) 1, "2a pregunta", (float) 20, ev);
			Question qu3 = new Question((Integer) 1, "3a pregunta", (float) 20, ev);
			Vector<Question> veQ = new Vector();
			veQ.add(qu);
			veQ.add(qu2);
			ev.setQuestions(veQ);
	
			
			sut.updateQuestion(qu3);
	
			
			Vector<Event> events = sut.getEvents(d);
			for (Event event : events) {
				if (event.getEventNumber() == ev.getEventNumber()) {
					assertEquals(veQ, event.getQuestions());
				}
		}
		} catch (Exception e) {
			fail("No problems should arise: ParseException/QuestionaAlreadyExist");
		
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			assertTrue(b);
		}
	}

}
