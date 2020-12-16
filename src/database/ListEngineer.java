package database;
import model.Engineer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListEngineer extends DBConnection{
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    Engineer engineer;
    public ArrayList<Engineer> listengineer = new ArrayList<Engineer>();
    public ListEngineer(){

    }
    public ArrayList<Engineer> list(String columns, String condition){
        String sql = " SELECT " + columns + " FROM tblstaffs s " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn+1];
            while(resultSet.next()){
            	engineer = new Engineer();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "staff_id":
                        	engineer.setStaff_id(resultSet.getInt(i));
                            break;
                        case "staff_name":
                        	engineer.setStaff_name(resultSet.getString(i));
                            break;
                        case "staff_DOB":
                        	engineer.setStaff_DOB(resultSet.getString(i));
                            break;
                        case "staff_gender":
                        	engineer.setStaff_gender(resultSet.getString(i));
                            break;
                        case "staff_address":
                        	engineer.setStaff_address(resultSet.getString(i));
                            break;
                        case "staff_startYearofwork":
                        	engineer.setStaff_startYearofwork(resultSet.getInt(i));
                            break;
                        case "staff_level":
                        	engineer.setStaff_level(resultSet.getString(i));
                            break;
                        case "staff_major":
                        	engineer.setStaff_major(resultSet.getString(i));
                            break;
                        case "staff_salary":
                        	engineer.setStaff_salary(resultSet.getInt(i));
                            break;
                        case "staff_academiclevel":
                        	engineer.setStaff_academiclevel(resultSet.getString(i));
                            break;
                        case "position_id":
                        	engineer.setPosition_id(resultSet.getInt(i));
                            break;
                        case "position_name":
                        	engineer.setPosition_name(resultSet.getString(i));
                            break;
                        case "staff_img":
                        	engineer.setStaff_img(resultSet.getBytes(i));
                            break;
                        case "division_id":
                        	engineer.setDivision_id(resultSet.getInt(i));
                            break;
                        case "division_name":
                        	engineer.setDivision_name(resultSet.getString(i));
                            break;
                        default:
                            break;
                    }
                }
                listengineer.add(engineer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listengineer;
    }

}
