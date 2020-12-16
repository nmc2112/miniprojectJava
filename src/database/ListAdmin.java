package database;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListAdmin extends DBConnection{
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    Admin Admin;
    public ArrayList<Admin> listAdmin = new ArrayList<Admin>();
    public ListAdmin(){

    }
    public ArrayList<Admin> list(String columns, String condition){
        String sql = " SELECT " + columns + " FROM tbladmin a " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn+1];
            while(resultSet.next()){
            	Admin = new Admin();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "ad_id":
                        	Admin.setAd_id(resultSet.getInt(i));
                            break;
                        case "ad_username":
                        	Admin.setAd_username(resultSet.getString(i));
                            break;
                        case "ad_password":
                        	Admin.setAd_password(resultSet.getString(i));
                            break;
                        case "role_id":
                        	Admin.setRole_id(resultSet.getInt(i));
                            break;
                        case "role_name":
                        	Admin.setRole_name(resultSet.getString(i));
                            break;
                        case "ad_email":
                        	Admin.setAd_email(resultSet.getString(i));
                            break;
                        
                        default:
                            break;
                    }
                }
                listAdmin.add(Admin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listAdmin;
    }

}
