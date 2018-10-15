package com.bati.devicesdatabase.domain;

public class User implements Comparable<User>{

	private int userId;
	private String username;
	private String password;
	private int enabled;
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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



	public int getEnabled() {
		return enabled;
	}



	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public int compareTo(User o) {
		return username.compareTo(o.getUsername());
	}

}
