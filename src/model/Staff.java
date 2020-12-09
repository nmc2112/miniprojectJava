package model;

public class Staff {

	private int staff_id;
	private String staff_name;
	private int staff_age;
	private String staff_gender;
	private String staff_address;
	private int staff_salary;
	private int position_id;
	private int staff_startYearofwork;
	
	public Staff() {
		
	}
	
	
	public Staff(int staff_id, String staff_name, int staff_age, String staff_gender, String staff_address,
			int staff_salary, int position_id, int staff_startYearofwork) {
		super();
		this.staff_id = staff_id;
		this.staff_name = staff_name;
		this.staff_age = staff_age;
		this.staff_gender = staff_gender;
		this.staff_address = staff_address;
		this.staff_salary = staff_salary;
		this.position_id = position_id;
		this.staff_startYearofwork = staff_startYearofwork;
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

	public int getStaff_age() {
		return staff_age;
	}

	public void setStaff_age(int staff_age) {
		this.staff_age = staff_age;
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
