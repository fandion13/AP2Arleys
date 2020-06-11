package frb.edu.br.filipe.infra.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DaoUtil {
    private Connection cx = null;
    
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        if (this.cx == null){
            String url = "jdbc:mysql://localhost:3306/arleyAP2?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
            String psw = "12200412";
            String usr = "root";
            String drive = "com.mysql.cj.jdbc.Driver";
            
            Class.forName(drive);
            cx = DriverManager.getConnection(url, usr, psw);
        }
        return cx;
    }
    
    public void getFechaConnection() throws SQLException{
        if(this.cx != null){
            this.cx.close();
            this.cx = null;
        }
    }
    
    public Statement getStatement() throws ClassNotFoundException, SQLException {
        return this.getConnection().createStatement();
    }
    
    public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException{
        return this.getConnection().prepareStatement(sql);
    }
}