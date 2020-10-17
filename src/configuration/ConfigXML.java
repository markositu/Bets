package configuration;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * It provides the configuration data from the "resources/config.xml" XML file
 */
public class ConfigXML {
	
	private String configFile = "resources/config.xml";
		
	private String businessLogicNode;

	private String businessLogicPort;

	private String businessLogicName;
	
	private static String dbFilename;

	//Two possible values: "open" or "initialize"
	private String dataBaseOpenMode;

	//Two possible values: true (no instance of RemoteServer needs to be launched) or false (RemoteServer needs to be run first)
	private boolean businessLogicLocal;

	//Two possible values: true (if the database is in same node as business logic ) or false (in other case)
	private boolean databaseLocal;
	
	private String databaseNode;
	
	private int databasePort;
	

	
	private String user;
	
	private String password;
	
	private String locale;

	public String getLocale() {
		return locale;
	}
	
	public int getDatabasePort() {
		return databasePort;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean isDatabaseLocal() {
		return databaseLocal;
	}

	public boolean isBusinessLogicLocal() {
		return businessLogicLocal;
	}
	private static ConfigXML theInstance = new ConfigXML();

	private ConfigXML(){
		
		  try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(new File(configFile));
			  doc.getDocumentElement().normalize();

			  NodeList list = doc.getElementsByTagName("config");
			  Element config = (Element) list.item(0); // list.item(0) is a Node that is an Element

			  
				//Two possible values: true (no instance of RemoteServer needs to be launched) or false (RemoteServer needs to be run first)
			  String value= ((Element)config.getElementsByTagName("businessLogic").item(0)).getAttribute("local");
			  businessLogicLocal=value.equals("true");

			  businessLogicNode = getTagValue("businessLogicNode", config);

			  businessLogicPort = getTagValue("businessLogicPort", config);

			  businessLogicName = getTagValue("businessLogicName", config);
			  
			  locale = getTagValue("locale", config);

			  
			  
				

			  dbFilename = getTagValue("dbFilename", config);

				//Two possible values: true (no instance of RemoteServer needs to be launched) or false (RemoteServer needs to be run first)
			  value= ((Element)config.getElementsByTagName("database").item(0)).getAttribute("local");
			  databaseLocal=value.equals("true");
			  
			  
			  //Two possible values: "open" or "initialize"
			  //dataBaseOpenMode= getTagValue("dataBaseOpenMode", config);
			  dataBaseOpenMode="open";
			  
			  databaseNode = getTagValue("databaseNode", config);
			  
			  databasePort=Integer.parseInt(getTagValue("databasePort", config));
				
			  user=getTagValue("user", config);
			  password=getTagValue("password", config);

			  System.out.print("Read from config.xml: ");
			  System.out.print("\t businessLogicLocal="+businessLogicLocal);
			  System.out.print("\t databaseLocal="+databaseLocal);
			  System.out.println("\t dataBaseOpenMode="+dataBaseOpenMode); 
					  
		  } catch (Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		  }		
		
	}

	private static String getTagValue(String sTag, Element eElement)
	 {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File("resources/config.xml"));
			Node node= doc.getElementsByTagName(sTag).item(0);
			System.out.println(node.getTextContent());
			return node.getTextContent();
		}catch (Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ "resources/config.xml");
		    e.printStackTrace();
		}	return null;
	 }
	private static void setTagValue(String sTag, Element eElement ,String set)
	 {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File("resources/config.xml"));
			Node node= doc.getElementsByTagName(sTag).item(0);
			System.out.println("Antes: "+node.getTextContent());
			node.setTextContent(set);
			System.out.println("Despues: "+node.getTextContent());
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("resources/config.xml"));
			transformer.transform(source, result);
	 	} catch (Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ "resources/config.xml");
		    e.printStackTrace();
		}	
	 }
	
	public static ConfigXML getInstance() {
		return theInstance;
	}

	public String getBusinessLogicNode() {
		return businessLogicNode;
	}

	public String getBusinessLogicPort() {
		return businessLogicPort;
	}

	public String getBusinessLogicName() {
		return businessLogicName;
	}
	
	public String getDbFilename(){
		return dbFilename;
	}

	public String getDataBaseOpenMode(){
		return dataBaseOpenMode;
	}

	public String getDatabaseNode() {
		return databaseNode;
	}

	public String getQuestionId() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			return getTagValue("questionn", config);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		    return null;
		}		
	}

	public void setQuestionId(String id_q) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			setTagValue("questionn", config, id_q);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		}	
	}

	public String getEventId() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			return getTagValue("eventn", config);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		    return null;
		}	
	}

	public void setEventId(String id_e) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			setTagValue("eventn", config, id_e);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		}	
	}

	public String getApostarId() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			return getTagValue("apostarn", config);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		    return null;
		}	
	}

	public void setApostarId(String id_a) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			setTagValue("apostarn", config, id_a);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		}	
	}
	
	public String getApuestaCId() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			return getTagValue("apuestaCn", config);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		    return null;
		}	
	}

	public void setApuestaCId(String id_a) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			setTagValue("apuestaCn", config, id_a);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		}	
	}
	public String getQuinielaId() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			return getTagValue("quinielan", config);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		    return null;
		}	
	}

	public void setQuinielaId(String id_a) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			setTagValue("quinielan", config, id_a);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		}	
	}
	public String getQuiRellId() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			return getTagValue("quirelln", config);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		    return null;
		}		
	}
	

	public void setQuiRellId(String id_q) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(configFile));
			NodeList list = doc.getElementsByTagName("config");
			Element config = (Element) list.item(0);
			setTagValue("quirelln", config, id_q);
		}catch(Exception e) {
			System.out.println("Error in ConfigXML.java: problems with "+ configFile);
		    e.printStackTrace();
		}	
	}

}
