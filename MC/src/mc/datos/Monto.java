package mc.datos;

/**
 *
 * @author luna
 */
public class Monto {

    private int idMonto;
    private int idDato;
    private double subtotal;
    private double total;

    public Monto() {
        this.idMonto = 0;
        this.idDato = 0;
        this.subtotal = 0;
        this.total = 0;
    }

    public Monto(int idMonto, int idDato, double subtotal, double total) {
        this.idMonto = idMonto;
        this.idDato = idDato;
        this.subtotal = subtotal;
        this.total = total;
    }

    public void setIdMonto(int idMonto) {
        this.idMonto = idMonto;
    }

    public void setIdDato(int idDatos) {
        this.idDato = idDatos;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdMonto() {
        return idMonto;
    }

    public int getIdDato() {
        return idDato;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Monto{" + "idMonto=" + idMonto
                + ", idDatos=" + idDato
                + ", subtotal=" + subtotal
                + ", total=" + total + '}';
    }
}
