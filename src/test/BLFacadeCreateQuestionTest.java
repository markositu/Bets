package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;

class BLFacadeCreateQuestionTest {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	DataAccess da = Mockito.mock(DataAccess.class);
	Event ev = Mockito.mock(Event.class);
	
	private final String date = "18/10/2030";
	private final String question = "question";
	private final float betMinimum = 1.2f;

	BLFacade sut = new BLFacadeImplementation(da);
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("Already exists")
	void test1() {
		try {
			Date d = sdf.parse(date);

			Mockito.doReturn(d)
				.when(ev).getEventDate();
			
			Mockito.when(da.createQuestion(ev, question, betMinimum))
				.thenThrow(QuestionAlreadyExist.class);

			assertThrows(QuestionAlreadyExist.class, () -> sut.createQuestion(ev, question, betMinimum));

		} catch (Exception e) {
			fail("Impossible, " + e.getClass().getName());
		}
	}
	
	@Test
	@DisplayName("Create OK")
	void test2() {
		try {
			Date d = sdf.parse(date);
			Question q = new Question(question, betMinimum, ev);

			Mockito.doReturn(d)
				.when(ev).getEventDate();
			
			Mockito.doReturn(q)
				.when(da)
				.createQuestion(ev, question, betMinimum);

			Question qCreated = sut.createQuestion(ev, question, betMinimum);

			assertEquals(q, qCreated);

		} catch (Exception e) {
			fail("Impossible, " + e.getClass().getName());
		}
	}

}
