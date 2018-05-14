package mc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crisan
 */
public class Conexion {

    private static Connection con = null;
    private static final String[] dbd = {"obd", "root", "19019122", "localhost", "3306"};

    public static Connection conectar() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + dbd[3] + ":" + dbd[4] + "/" + dbd[0]+"?autoReconnect=true&useSSL=false", dbd[1], dbd[2]);
                return con;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        return con;
    }

    public static void cerrar() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Conexion.conectar();
        Conexion.cerrar();
    }
}
