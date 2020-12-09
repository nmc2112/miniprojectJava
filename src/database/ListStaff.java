package database;
import model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListStaff extends DBConnection{
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    Staff staff;
    public ArrayList<Staff> listStaff = new ArrayList<Staff>();
    public ListStaff(){

    }
    public ArrayList<Staff> list(String columns, String condition){
        String sql = " SELECT " + columns + " FROM tblstaffs s " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn+1];
            while(resultSet.next()){
            	staff = new Staff();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "staff_id":
                            staff.setStaff_id(resultSet.getInt(i));
                            break;
                        case "staff_name":
                            staff.setStaff_name(resultSet.getString(i));
                            break;
                        case "staff_age":
                            staff.setStaff_age(resultSet.getInt(i));
                            break;
                        case "staff_gender":
                            staff.setStaff_gender(resultSet.getString(i));
                            break;
                        case "staff_address":
                            staff.setStaff_address(resultSet.getString(i));
                            break;
                        case "staff_startYearofwork":
                            staff.setStaff_startYearofwork(resultSet.getInt(i));
                            break;
                        case "staff_salary":
                            staff.setStaff_salary(resultSet.getInt(i));
                        case "position_id":
                            staff.setPosition_id(resultSet.getInt(i));
                        case "position_name":
                            staff.setPosition_name(resultSet.getString(i));
                        default:
                            break;
                    }
                }
                listStaff.add(staff);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listStaff;
    }

}
