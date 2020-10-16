package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private String id;
	private String contrasena;
	private float dinero;
	private List<String> movimientos;
	private int numMov;

	public User(String id, String contrasena) {
		this.id = id;
		this.contrasena = contrasena;
		this.dinero = 0;
		this.movimientos=new ArrayList<String>();
		movimientos.add("Saldo actual: 0");
		this.numMov=0;
	}

	public boolean validarContrasena(String pContrasena) {
		return pContrasena.equals(this.contrasena);
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void addDinero(float pDinero) {
		this.dinero += pDinero;
		if(pDinero>0) {
			movimientos.add("<<+"+pDinero+">>   Saldo actual: "+this.dinero);
		}else {
			movimientos.add("<<"+pDinero+">>   Saldo actual: "+this.dinero);
		}
		numMov++;
		
		//movimientos.add("hh");
	}
	public int getNumMov() {
		return numMov;
	}
	public List<String> getMovimientos(){
		return this.movimientos;
	}

	public float getDinero() {
		return this.dinero;
		
	}
	public String getId() {
		return this.id;
		
	}
	public String toString() {
		return "El usuario "+id+" tiene "+dinero+" dinero.";
		
	}

}
