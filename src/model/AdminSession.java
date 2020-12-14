package model;

public class AdminSession {
	
    private static AdminSession instance;
    private static int ad_id;
    private static int role_id;


    public AdminSession(Integer ad_id, Integer role_id) {
    	this.role_id = role_id;
        this.ad_id = ad_id;
    }

    public static int getInstanceId() {

        return ad_id;
    }
    public static int getInstanceroleId() {

        return role_id;
    }

    public static AdminSession getInstance(Integer ad_id, Integer role_id) {
        if(instance == null) instance = new AdminSession(ad_id, role_id);
        return instance;
    }


    public static void cleanadSession() {
        instance = null;
        ad_id = 0;// or null
        role_id = 0;
    }
}
