package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.print.attribute.standard.PDLOverrideSupported;

@Entity
public class Admin {
	@Id
	private String id;
	private String contrasena;
	public Admin(String pId,String pContrasena) {
		this.id=pId;
		this.contrasena=pContrasena;
	}
	public boolean validarContrasena(String pContrasena) {
		return pContrasena.equals(this.contrasena);
	}
	public String getContrasena() {
		return this.contrasena;
	}

}
