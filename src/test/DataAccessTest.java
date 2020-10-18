package test;
/**
 * DataAccessTest: Some JUnit example for DataAccess
 */


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Apuesta;
import domain.ApuestaCombinada;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;
import test.TestFacadeImplementation;

class DataAccessTest {
	// sut- System Under Test
	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private TestFacadeImplementation testBL = new TestFacadeImplementation();

	private String queryText = "A question";
	private Float betMinimum = 2.0f;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private final String date = "18/10/2030";
	private Event ev;
	private Admin a;

	@Test
	@DisplayName("Existe Admin")
	void existeAdmin() {

		try {
			
			// configure the state of the system (create object in the dabatase)
			a = testBL.addAdmin("Admin", "admin");
			

			// invoke System Under Test (sut)
			assertTrue(sut.existeAdmin("Admin"));
		} catch (Exception e) {
			fail("Algo ha ido mal");
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeAdmin(a);
			assertTrue(b);
		}

	}
	@Test
	@DisplayName(" NoExiste Admin")
	void noExisteAdmin() {

		try {
			
			// configure the state of the system (create object in the dabatase)
			
			

			// invoke System Under Test (sut)
			assertFalse(sut.existeAdmin("Admin"));
		} catch (Exception e) {
			fail("Algo ha ido mal");
		} finally {
			// Remove the created objects in the database (cascade removing)
			
		}

	}
	@Test
	@DisplayName("The event has one question with a queryText")
	void createQuestionDATest1() {

		try {
			Date oneDate = sdf.parse("05/10/2022");

			// configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate);
			sut.createQuestion(ev, queryText, betMinimum);

			// invoke System Under Test (sut)
			assertThrows(QuestionAlreadyExist.class, () -> sut.createQuestion(ev, queryText, betMinimum));

		} catch (ParseException | QuestionAlreadyExist e) {
			fail("No problems should arise: ParseException/QuestionaAlreadyExist");
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			assertTrue(b);
		}

	}

	@Test
	@DisplayName("The event has NOT one question with a queryText")
	void createQuestionDATest2() {

		try {
			Date oneDate = sdf.parse("05/10/2022");

			// configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate);

			// invoke System Under Test (sut)
			Question q = sut.createQuestion(ev, queryText, betMinimum);

			// verify the results
			assertNotNull(q);
			assertEquals(queryText, q.getQuestion());
			assertEquals(betMinimum, q.getBetMinimum(), 0);

		} catch (QuestionAlreadyExist | ParseException e) {
			fail("No problems should arise: ParseException/QuestionaAlreadyExist");
		
		} finally {
			// Remove the created objects in the database (cascade removing)
			boolean b = testBL.removeEvent(ev);
			assertTrue(b);
		}
	}
	
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
