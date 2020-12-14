package model;



public class StaffSession {
    private static StaffSession instance;

    private static int staff_id;
    private static int position_id;


    public StaffSession(Integer staff_id, Integer position_id) {
    	this.position_id = position_id;
        this.staff_id = staff_id;
    }

    public static int getInstanceId() {

        return staff_id;
    }
    public static int getInstancePositionId() {

        return position_id;
    }

    public static StaffSession getInstance(Integer staff_id, Integer position_id) {
        if(instance == null) instance = new StaffSession(staff_id, position_id);
        return instance;
    }


    public static void cleanStaffSession() {
        instance = null;
        staff_id = 0;// or null
        position_id = 0;
    }
}
