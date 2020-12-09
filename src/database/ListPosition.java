package database;
import model.Position;
import model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListPosition extends DBConnection{
    Connection connection = (Connection) DBConnection.getConnection();
    ResultSet resultSet;
    Position position;
    public ArrayList<Position> listPosition = new ArrayList<Position>();
    public ListPosition(){

    }
    public ArrayList<Position> list(String columns, String condition){
        String sql = " SELECT " + columns + " FROM tblpositions p " + condition;
        int countColumn = 1;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            countColumn = resultSet.getMetaData().getColumnCount();
            String[] columnName = new String[countColumn+1];
            while(resultSet.next()){
            	position = new Position();
                for (int i = 1; i <= countColumn; i++) {
                    columnName[i] = resultSet.getMetaData().getColumnName(i);
                    switch (columnName[i]) {
                        case "position_id":
                        	position.setPosition_id(resultSet.getInt(i));
                            break;
                        case "position_name":
                        	position.setPosition_name(resultSet.getString(i));
                            break;
                       
                        default:
                            break;
                    }
                }
                listPosition.add(position);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listPosition;
    }

}
