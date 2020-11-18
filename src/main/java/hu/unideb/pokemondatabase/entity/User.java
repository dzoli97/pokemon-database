package hu.unideb.pokemondatabase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 5, message = "A felhasználó névnek legalább 5 karakter hosszúnak kell lennie!")
	@Column(unique = true, nullable = false)
	private String username;

	@Size(min = 5, message = "A jelszónak legalább 5 karakter hosszúnak kell lennie!")
	@Column(nullable = false)
	private String password;

	private String matchingPassword;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

}