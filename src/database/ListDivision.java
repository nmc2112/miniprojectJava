package database;
import model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListDivision extends DBConnection{
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    Division division;
    public ArrayList<Division> listDivision = new ArrayList<Division>();
    public ListDivision(){

    }
    public ArrayList<Division> list(String columns, String condition){
        String sql = " SELECT " + columns + " FROM tbldivision d " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn+1];
            while(resultSet.next()){
            	division = new Division();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "division_id":
                        	division.setDivision_id(resultSet.getInt(i));
                            break;
                        case "division_name":
                        	division.setDivision_name(resultSet.getString(i));
                            break;
                        default:
                            break;
                    }
                }
                listDivision.add(division);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listDivision;
    }

}
