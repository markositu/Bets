package domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class ApuestaCombinada {
	@Id
	private int id;
	@OneToMany
	private List <Apuesta> listaApuestas;
	@OneToOne
	private User usuario;
	private int cuantasGanadas;
	
	public ApuestaCombinada (int id, List <Apuesta> listaApuestas, User usuario) {
		this.id=id;
		this.listaApuestas=listaApuestas;
		this.usuario=usuario;
		this.cuantasGanadas=0;
	}
	
	public void anadirApuesta (Apuesta ap) {
		listaApuestas.add(ap);
	}
	public void anadirApuestaGanada () {
		cuantasGanadas++;
	}
	public boolean ganada() {
		boolean res=false;
		if(cuantasGanadas==listaApuestas.size()) {
			res=true;
		}
		return res;
	}
	public float gananciaPotencial(){
		float gp= 1;
		
		for (Apuesta a: listaApuestas) {
			gp*= a.getCantidadapostada() * a.getQuestion().getPronosticos().get(a.getPronostico()).getMultiplicador();
		}
		return gp;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Apuesta> getListaApuesta() {
		return listaApuestas;
	}
	public void setListaApuesta(List<Apuesta> listaApuesta) {
		this.listaApuestas = listaApuesta;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	
	
}
