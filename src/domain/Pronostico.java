package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Pronostico {
	
	private String pronostico;
	private float multiplicador;
	@XmlIDREF
	Question qref;
	public Pronostico( String p,float mul, Question q) {
				
		this.multiplicador=mul;
		this.pronostico=p;
		this.qref=q;
		System.out.println("PR: "+mul+" , "+p);
	}
	public String getPronostico() {
		return pronostico;
	}
	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}
	public float getMultiplicador() {
		return multiplicador;
	}
	public void setMultiplicador(float mul) {
		this.multiplicador=mul;
	}
	public String toString(){
		return "Pr: "+pronostico+", mul: "+multiplicador;
	}

}
