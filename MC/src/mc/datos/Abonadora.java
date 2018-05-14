package mc.datos;

import java.sql.Date;

/**
 *
 * @author luna
 */
public class Abonadora {

    private int idAbono;
    private int idDatos;
    private double abono;
    private String fecha;

    public Abonadora() {
        this.idAbono = 0;
        this.idDatos = 0;
        this.abono = 0;
        this.fecha = "";
    }

    public Abonadora(int idAbono, int idDatos, double abono, String fecha) {
        this.idAbono = idAbono;
        this.idDatos = idDatos;
        this.abono = abono;
        this.fecha = fecha;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public void setIdDatos(int idDatos) {
        this.idDatos = idDatos;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdAbono() {
        return idAbono;
    }

    public int getIdDatos() {
        return idDatos;
    }

    public double getAbono() {
        return abono;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Abonadora{" + "idAbono=" + idAbono
                + ", idDatos=" + idDatos
                + ", abono=" + abono
                + ", fecha=" + fecha + '}';
    }

}
