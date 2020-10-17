package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Quiniela;
import domain.User;
import domain.Admin;
import domain.Apuesta;
import domain.ApuestaCombinada;
import domain.Event;
import domain.Pronostico;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	
	private DataAccess dbManager;
	
	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}
	
	public BLFacadeImplementation(DataAccess da) {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();
		}
		dbManager=da;
	}
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	public User addDinero(String id,float dinero) {
		dbManager.open (false);
		User u= dbManager.meterDinero(id, dinero);
		dbManager.close();
		return u;
	}
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	    dbManager.open (false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
   @WebMethod
   public Question createQuestionQ(Question q){
	   
	    //The minimum bed must be greater than 0
	    dbManager.open (false);	
	    Question qt = dbManager.createQuestionQ(q);
		dbManager.close();
		return qt;
   };
  @WebMethod
   public void updateQuestion(Question q) {
           dbManager.open (false);
          dbManager.updateQuestion(q);
          dbManager.close();
       }
   @WebMethod
   public boolean existeUsuario(String id) {
	   boolean res;
	   DataAccess dbManager=new DataAccess(true);
	   res=dbManager.existeUsuario(id);
	  dbManager.close();
	   return res;
   }
   @WebMethod
   public boolean existeAdmin(String id) {
	   boolean res;
	   DataAccess dbManager=new DataAccess(true);
	   res=dbManager.existeAdmin(id);
	  dbManager.close();
	   return res;
   }
   @WebMethod
   public void createEvent(Date date, String descrition){
	   
	    //The minimum bed must be greater than 0
	    DataAccess dbManager=new DataAccess(true);

	    dbManager.createEvent(date,descrition);		

		dbManager.close();
		
  };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open (false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}
    
    @WebMethod	
	public Vector<Event> getAllEvents()  {
		dbManager.open (false);
		Vector<Event>  events=dbManager.getAllEvents();
		dbManager.close();
		return events;
	}
    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open (false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		dbManager.open (false);
		dbManager.initializeDB();
		dbManager.close();
	}
	@WebMethod public User getUserById(String id) {
		dbManager.open (false);
		User u=dbManager.getUserById(id);
		dbManager.close();
		return u;
	}
	@WebMethod public Admin getAdminById(String id) {
		dbManager.open (false);
		Admin u=dbManager.getAdminById(id);
		dbManager.close();
		return u;
	}
	
	
	

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod
	public void registrarUsuario(String id,String contrasena) {
    	dbManager.open (false);
    	dbManager.registrarUsuario(id,contrasena);
    	System.out.println("Registrado"+id);
    	dbManager.close();
	}
	@WebMethod
	public void registrarAdmin(String id,String contrasena) {
    	dbManager.open (false);
    	dbManager.registrarAdmin(id,contrasena);
    	System.out.println("Registrado"+id);
    	dbManager.close();
	}

	@WebMethod
	public boolean compararContrasenasA(String id,String contrasena) {
		dbManager.open (false);
		boolean res=dbManager.correctPasswordA(id, contrasena);
		dbManager.close();
		return res;
	}
	@WebMethod
	public boolean compararContrasenasU(String id,String contrasena) {
		dbManager.open (false);
		boolean res=dbManager.correctPasswordU(id, contrasena);
		dbManager.close();
		return res;
	}
	@WebMethod 
	public User conseguirUser(User u) {
		dbManager.open (false);
		User us=dbManager.getUser(u);
		dbManager.close();
		return us;
	}
	public Apuesta crearApuesta(User u, Question q, int p, float c, Event e){
		dbManager.open (false);
		Apuesta ap =dbManager.crearApuesta(u, q, p, c, e);
		dbManager.close();
		return ap;
	}
	public void subirResultado (Event e, Question q, int np) {
		
	}
	@WebMethod
	public ApuestaCombinada meterApuestaCombinada (User u, ArrayList <Apuesta> listaApTemp) {
		dbManager.open (false);
		ApuestaCombinada ac=dbManager.meterApuestaCombinada (u, listaApTemp);		
		dbManager.close();
		return ac;
	}
	@WebMethod
	public Quiniela meterQuiniela (ArrayList <Event> listaeventos) {
		dbManager.open (false);
		Quiniela ac=dbManager.meterQuiniela (listaeventos);		
		dbManager.close();
		return ac;
	}
	@WebMethod
	public List<Quiniela> sacarQuinielas () {
		dbManager.open (false);
		List<Quiniela> q=dbManager.sacarQuinielas();		
		dbManager.close();
		return q;
	}
	@WebMethod
	public void guardarQuiniela(User u, Quiniela q, int[] res) {
		dbManager.open (false);
		dbManager.guardarQuiniela(u, q, res);
		
		dbManager.close();
		
	}
	@WebMethod
	 public void subirResultadoQuiniela(Quiniela quinielaActual,int []resultadoQuiniela) {
		dbManager.open (false);
		dbManager.subirResultadoQuiniela(quinielaActual,resultadoQuiniela);
		dbManager.close();
	}
}

