package br.com.caelum.casadocodigo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority {
	@Id
	private String name;
	
	public String getAuthority(){
		return this.name;
	}

}
