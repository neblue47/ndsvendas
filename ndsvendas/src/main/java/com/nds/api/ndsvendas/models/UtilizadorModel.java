package com.nds.api.ndsvendas.models;
 
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.nds.api.ndsvendas.enums.EUserGroup;

@Entity
@Table(name="TB_UTILIZADOR")
public class UtilizadorModel implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // , columnDefinition = "VARCHAR(36)"
	@Column(nullable = false, length = 150,columnDefinition = "VARCHAR(100)" )
	@Type(type = "uuid-char")
	private UUID id;

	@Column(nullable = false, length = 150) 
	private String username;
	
	@Column(nullable = false, length = 250) 
	private String password; 	
	 
	@Column(nullable = false, length = 250) 
	private String fullName;
	
	@Column(nullable = false) 
	private EUserGroup userGroup;
	
	@Column(nullable = false) 
	private boolean Status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public EUserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(EUserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	 
	
	
}
