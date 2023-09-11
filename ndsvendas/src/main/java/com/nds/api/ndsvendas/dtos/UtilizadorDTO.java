package com.nds.api.ndsvendas.dtos;
 
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.nds.api.ndsvendas.enums.EUserGroup;

public class UtilizadorDTO {

 
	private UUID id;
	@NotBlank
	private String userName;	
	@NotBlank 
	private String password;	 
	@NotBlank
	private String fullName;	
	@NotBlank
	private String repeatPassword;
 
	private int userGroup;
	private String userGroupDescription;     
	private boolean firstLogin;
	private boolean Status;
	
	public UtilizadorDTO() {
		
	}
	public UtilizadorDTO(UtilizadorDTO user) {
		this.userName = user.userName; 
		this.fullName = user.fullName; 
		this.userGroup = user.userGroup; 
	}
	public UtilizadorDTO(String fullName, String username, String password, int userGroup, boolean status) {
		this.userName = username;
		this.password = password;
		this.fullName = fullName; 
		this.userGroup = userGroup;
		Status = status;
	}

	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
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
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public int getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}
	public String getUserGroupDescription() { 
		return EUserGroup.fromId(userGroup).name();
	}
	public void setUserGroupDescription(String userGroupDescription) {
		this.userGroupDescription = userGroupDescription;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	} 
	
	
	
	
}
