package fr.simplon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity User
 * 
 * @author JGL
 *
 */

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "email")
	@NotBlank(message = "Email obligatoire")
	@Length(min=2,message="L'adresse email est deja utilisée")
	@Email(regexp="^[a-zA-Z0-9._-]+@[a-z0-9.-_]{2,}.[a-z]{2,4}$")
	private String email;
	
	@Column(name = "password")
	@NotBlank(message = "Mot de passe obligatoire")
	@Length(min = 4, message = "La chaîne doit avoir au moins 4 caractères")
	private String passwd;
	
	@OneToOne
	@JoinColumn(name="id_employe")
	private Employe employe;
	
	@ManyToOne
	@JoinColumn(name="id_role")
	private Role role;
	
	public User(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}



	

	
}
