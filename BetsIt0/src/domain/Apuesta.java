package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apuesta {
	@Id
	private int id;
	private User user;
	private Question question;
	private int nQuestion;
	private int nPronostico;
	private float cantidadapostada;
	private Pronostico pron;
	private Event event;
	private boolean ganada;
	public Apuesta(int id, User user, Question question, int nPronostico, float cantidadapostada, Event event) {
		super();
		this.id = id;
		this.user = user;
		this.question = question;
		this.nPronostico = nPronostico;
		this.cantidadapostada = cantidadapostada;
		this.event = event;
		this.nQuestion=question.getQuestionNumber();
		this.ganada=false;
		
	}
	public void setGanada() {
		this.ganada=true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getPronostico() {
		return nPronostico;
	}
	public void setPronostico(int nPronostico) {
		this.nPronostico = nPronostico;
	}
	public float getCantidadapostada() {
		return cantidadapostada;
	}
	public void setCantidadapostada(float cantidadapostada) {
		this.cantidadapostada = cantidadapostada;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Pronostico utilpron (Pronostico pron) {
		return pron;
	}
	
}
