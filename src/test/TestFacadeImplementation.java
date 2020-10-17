package test;
/**
 * Auxiliary FacadeImplementation class for testing DataAccess
 */

import java.util.Date;

import configuration.ConfigXML;
import domain.Admin;
import domain.Event;
import test.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		public Event addEvent(String desc, Date d) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEvent(desc,d);
			dbManagerTest.close();
			return o;

		}
		public boolean removeAdmin(Admin a) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeAdmin(a);
			dbManagerTest.close();
			return b;

		}
		
		public Admin addAdmin(String name, String pass) {
			dbManagerTest.open();
			Admin a=dbManagerTest.addAdmin(name, pass);
			dbManagerTest.close();
			return a;

		}

}
