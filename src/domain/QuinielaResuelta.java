package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class QuinielaResuelta {
	@Id
	int id;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	Quiniela quiniela;
	int [] resultados;
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	User user;
	public QuinielaResuelta(int id) {
		this.id=id;
	}
	public QuinielaResuelta(int id,Quiniela quiniela,int[] result,User u) {
		this.id=id;
		this.quiniela=quiniela;
		this.resultados=result;
		this.user=u;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return this.user;
	}
	public Quiniela getQuiniela() {
		return quiniela;
	}
	public void setQuiniela(Quiniela quiniela) {
		this.quiniela = quiniela;
	}
	public int compararResultados(int[]r) {
		int respuesta=0;
		for(int i:resultados) {
			if(resultados[i]==r[i]) {
				respuesta++;
			}
		}
		return respuesta;
	}
}
