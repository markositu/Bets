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
	
}
