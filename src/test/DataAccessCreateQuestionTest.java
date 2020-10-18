package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;

class DataAccessCreateQuestionTest {

	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	private TestFacadeImplementation testBL = new TestFacadeImplementation();
	
	private final String question = "question";
	private final float betMinimum = 1.2f;
	
	@Test
	@DisplayName("null Event")
	void test1() {
		assertThrows(Exception.class, () -> sut.createQuestion(null, "p", 2.0f));
	}
	
	@Test
	@DisplayName("Already exists")
	void test2() {
		Event ev = testBL.addEvent("desc", new Date());
		try {
			sut.createQuestion(ev, question, betMinimum);			
		} catch (QuestionAlreadyExist e) {
			fail("Impossible");
		}
		
		assertThrows(QuestionAlreadyExist.class, () -> sut.createQuestion(ev, question, betMinimum));
		
		testBL.removeEvent(ev);
	}
	
	@Test
	@DisplayName("Create OK")
	void test3() {
		Event ev = testBL.addEvent("desc", new Date());
		try {
			Question q = sut.createQuestion(ev, question, betMinimum);
			
			assertEquals(question, q.getQuestion());
			assertEquals(betMinimum, q.getBetMinimum());
			
			testBL.removeEvent(ev);
			
		} catch (QuestionAlreadyExist q) {
			fail("Impossible");
		}
	}

}
