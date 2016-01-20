package com.hptsec.vulnlab.Model;

public class M2LoginUserModel {

	// variables
	private int Id;
	private String Username;
	private String Password;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public M2LoginUserModel() {
		// TODO Auto-generated constructor stub
		this.Id = 0;
		this.Username = "";
		this.Password = "";
	}

}
