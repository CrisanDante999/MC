package mc.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mc.datos.*;

/**
 *
 * @author luna
 */
public class Transaccion {

    public static Object[] login(User u) {
        String sql = "SELECT COUNT(u.id_user) AS lol, u.id_user, u.nickname"
                + " FROM user u "
                + "WHERE u.nickname =? AND u.password=? ORDER BY u.id_user;";
        Object[] rm = new Object[3];
        try {
            int values = 0;

            boolean noqueno = false;
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            pstm.setString(1, u.getNickname());
            pstm.setString(2, u.getPassword());
            //System.err.println(pstm);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                values = rs.getInt(1);//esta corresponde a la existencia del usuario, retorna un uno o cero
                rm[1] = rs.getInt(2);//en esta manejamos el id del usuario
                rm[2] = rs.getString(3);//sirve para el nombre del usuario
            }
            if (values == 1) {
                rm[0] = noqueno = true;
            } else {
                rm[0] = noqueno = false;
            }
        } catch (SQLException sqle) {
            java.util.logging.Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return rm;
    }

    public static boolean insertDatos(Datos d) {
        boolean temp = true;
        try {
            String sql = "INSERT INTO datos VALUES (?,?,?,?,?);";
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            pstm.setInt(1, Types.NULL);
            pstm.setString(2, d.getMatricula());
            pstm.setString(3, d.getNombre());
            pstm.setString(4, d.getApPat());
            pstm.setString(5, d.getApMat());
            //System.err.println(pstm);
            temp = pstm.execute();
        } catch (SQLException e) {

        }
        return temp;
    }

    public static int selectSearchData() {
        int temp = 0;
        try {
            String sql = "SELECT id_datos FROM datos ORDER BY id_datos DESC LIMIT 1;";
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                temp = rs.getInt(1);
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, e);
        }
        return temp;
    }

    public static boolean insertMonto(Monto m) {
        boolean temp = true;
        try {
            String sql = "INSERT INTO monto VALUES (?,?,?,?);";
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            pstm.setInt(1, Types.NULL);
            pstm.setInt(2, m.getIdDato());
            pstm.setDouble(3, m.getSubtotal());
            pstm.setDouble(4, m.getTotal());
            temp = pstm.execute();
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, e);
        }
        return temp;
    }

    public static int selectSearchMonto() {
        int temp = 0;
        try {
            String sql = "SELECT id_monto FROM monto ORDER BY id_monto DESC LIMIT 1;";
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                temp = rs.getInt(1);
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, e);
        }
        return temp;
    }

    public static boolean insertPedido(Pedido p) {
        boolean temp = true;
        try {
            String sql = "INSERT INTO pedido VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            pstm.setInt(1, Types.NULL);
            pstm.setInt(2, p.getIdDatos());
            pstm.setInt(3, p.getCantidad());
            pstm.setString(4, p.getProducto());
            pstm.setDouble(5, p.getMontoUnit());
            pstm.setDouble(6, p.getSubtotal());
            pstm.setInt(7, p.getIdMonto());
            pstm.setString(8, p.getFecha());
            pstm.executeLargeUpdate();
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

    public static boolean insertAbonadora(Abonadora a) {
        boolean temp = true;
        try {
            String sql = "INSERT INTO abonadora VALUES(?,?,?,?)";
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            pstm.setInt(1, Types.NULL);
            pstm.setInt(2, a.getIdDatos());
            pstm.setDouble(3, a.getAbono());
            pstm.setString(4, a.getFecha());
            temp = pstm.execute();
        } catch (SQLException e) {
        }
        return temp;
    }

    public static Datos searchDatos(String d, int valor) {
        Datos tmp = new Datos();
        try {
            String sql;
            PreparedStatement pstm;
            ResultSet rs = null;
            switch (valor) {
                case 1:
                    sql = "SELECT id_datos, matricula, nombre, ap_pat, ap_mat FROM datos WHERE matricula=?";
                    pstm = Conexion.conectar().prepareStatement(sql);
                    pstm.setString(1, d);
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        tmp = new Datos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    }
                    break;
                case 2:
                    sql = "SELECT id_datos, matricula, nombre, ap_pat, ap_mat FROM datos WHERE id_datos=?";
                    pstm = Conexion.conectar().prepareStatement(sql);
                    pstm.setInt(1, Integer.parseInt(d));
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        tmp = new Datos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    }
                    break;
                case 3:
                    sql = "SELECT id_datos, matricula, nombre, ap_pat, ap_mat "
                            + "FROM datos "
                            + "WHERE CONCAT(nombre, ' ', ap_pat, ' ', ap_mat) "
                            + "LIKE '%" + d + "%';";
                    pstm = Conexion.conectar().prepareStatement(sql);
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        tmp = new Datos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    }
                    break;
            }
        } catch (SQLException e) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, e);
        }
        return tmp;
    }

    public static ArrayList<Pedido> searchPedido(int valor) {
        ArrayList<Pedido> p = new ArrayList<>();
        try {
            String sql = "SELECT id_pedido, id_datos, cantidad, producto,"
                    + " monto_unit, subtotal, id_monto, fecha FROM pedido "
                    + "WHERE id_datos=?;";
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            pstm.setInt(1, valor);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                p.add(new Pedido(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getDouble(5), rs.getDouble(6),
                        rs.getInt(7), rs.getString(8)));
            }
        } catch (SQLException e) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, e);
        }
        return p;
    }

    public static ArrayList<Abonadora> searchAbonadora(int valor) {
        ArrayList<Abonadora> a = new ArrayList<>();
        try {
            String sql = "SELECT id_abono, id_datos, abono, fecha"
                    + " FROM abonadora "
                    + "WHERE id_datos=?;";
            PreparedStatement pstm = Conexion.conectar().prepareStatement(sql);
            pstm.setInt(1, valor);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                a.add(new Abonadora(rs.getInt(1), rs.getInt(2), rs.getDouble(3),
                        rs.getString(4)));
            }
        } catch (SQLException e) {
            Logger.getLogger(Transaccion.class.getName()).log(Level.SEVERE, null, e);
        }
        return a;
    }

}
