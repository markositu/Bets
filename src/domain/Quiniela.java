package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Quiniela {
	@Id
	int id;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH) 
	List<Event> quiniela;
	public Quiniela(int id) {
		this.id=id;
	}
	public Quiniela(int id,List<Event> quiniela) {
		this.id=id;
		this.quiniela=quiniela;
	}
	public void anadirEvento(Event ev) {
		if(numeroDeEventos()<=15) {
			quiniela.add(ev);
		}else {
			System.out.println("El n�mero de eventos no puede ser superior a 15.");
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Event> getQuiniela() {
		return quiniela;
	}
	public void setQuiniela(List<Event> quiniela) {
		this.quiniela = quiniela;
	}
	public int numeroDeEventos() {
		return quiniela.size();
	}
}
