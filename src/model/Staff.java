package model;

import com.mysql.jdbc.Blob;

public class Staff {

	private int staff_id;
	private String staff_name;
	private String staff_DOB; 
	private String staff_gender;
	private String staff_address;
	private int staff_salary;
	private int position_id;
	private int staff_startYearofwork;
	private String position_name;
	private byte[] staff_img;
	private int division_id;
	private String division_name;
	
	public int getDivision_id() {
		return division_id;
	}


	public void setDivision_id(int division_id) {
		this.division_id = division_id;
	}


	public String getDivision_name() {
		return division_name;
	}


	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}


	public byte[] getStaff_img() {
		return staff_img;
	}


	public void setStaff_img(byte[] bs) {
		this.staff_img = bs;
	}


	public String getPosition_name() {
		return position_name;
	}


	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}


	public Staff() {
		
	}
	
	public String getStaff_DOB() {
		return staff_DOB;
	}


	public void setStaff_DOB(String staff_DOB) {
		this.staff_DOB = staff_DOB;
	}

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_gender() {
		return staff_gender;
	}

	public void setStaff_gender(String staff_gender) {
		this.staff_gender = staff_gender;
	}

	public String getStaff_address() {
		return staff_address;
	}

	public void setStaff_address(String staff_address) {
		this.staff_address = staff_address;
	}

	public int getStaff_salary() {
		return staff_salary;
	}

	public void setStaff_salary(int staff_salary) {
		this.staff_salary = staff_salary;
	}

	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public int getStaff_startYearofwork() {
		return staff_startYearofwork;
	}

	public void setStaff_startYearofwork(int staff_startYearofwork) {
		this.staff_startYearofwork = staff_startYearofwork;
	}
	
	
}
