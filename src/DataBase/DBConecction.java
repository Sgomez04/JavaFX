package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConecction {
    Connection con;
    String usuario;
    String password;

    public DBConecction() throws Exception{
        this("root","");

    }

    public DBConecction(String usuario, String pass) throws Exception{

        this.usuario = usuario;
        this.password = pass;
        String sURL = "jdbc:mysql://localhost/bdmultijuegos?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        con = DriverManager.getConnection(sURL,this.usuario,this.password);
    }

    public Connection getCon() {
        return con;
    }
    public void cerrarConexion() throws  Exception{
        con.close();
    }
    

}