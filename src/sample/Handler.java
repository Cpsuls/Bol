package sample;

import sample.Const;
import sample.dataconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Handler extends dataconnect {
    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void createNew(String name_table) {
        String create = "CREATE TABLE " + "?" + "(" +
                Const.OPERATIONS_ID + " INT AUTO_INCREMENT NOT NULL,\n" +
                Const.OPERATIONS_NUMBERS + " VARCHAR(100) NOT NULL,\n" +
                Const.OPERATIONS_CHARACTER + " VARCHAR(5) NOT NULL,\n" +
                Const.OPERATIONS_RESULT + " VARCHAR(100) NOT NULL\n" +
                ");";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(create);
            prSt.setString(1, name_table);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void enterRes(String Numbers_tab, String Character_tab, String Result_tab){
        String insert = "INSERT INTO " + Const.OPERATIONS_TABLE + "(" + Const.OPERATIONS_NUMBERS + ", "
                + Const.OPERATIONS_CHARACTER + ", " + Const.OPERATIONS_RESULT + ")" + " VALUES (?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, Numbers_tab);
            prSt.setString(2, Character_tab);
            prSt.setString(3, Result_tab);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}