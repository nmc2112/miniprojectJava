package model;

public class Admin {
	private int ad_id;
	private String ad_username;
	private String ad_password;
	private String ad_email;
	private int role_id;
	private String role_name;
	
	public String getAd_email() {
		return ad_email;
	}

	public void setAd_email(String ad_email) {
		this.ad_email = ad_email;
	}

	public Admin() {

	}

	public int getAd_id() {
		return ad_id;
	}

	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_username() {
		return ad_username;
	}

	public void setAd_username(String ad_username) {
		this.ad_username = ad_username;
	}

	public String getAd_password() {
		return ad_password;
	}

	public void setAd_password(String ad_password) {
		this.ad_password = ad_password;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	
	

}
