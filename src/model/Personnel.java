package model;

public class Personnel extends Staff {
	private String staff_major; 
	private String staff_level;
	private String staff_academiclevel;
	
	public Personnel() {
		
	}

	public String getStaff_major() {
		return staff_major;
	}

	public void setStaff_major(String staff_major) {
		this.staff_major = staff_major;
	}

	public String getStaff_level() {
		return staff_level;
	}

	public void setStaff_level(String staff_level) {
		this.staff_level = staff_level;
	}

	public String getStaff_academiclevel() {
		return staff_academiclevel;
	}

	public void setStaff_academiclevel(String staff_academiclevel) {
		this.staff_academiclevel = staff_academiclevel;
	}
	
}
