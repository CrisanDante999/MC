package mc.datos;


/**
 *
 * @author luna
 */
public class Pedido {
  private int idPedido;
  private int idDatos;
  private int cantidad;
  private String producto;
  private double montoUnit;
  private double subtotal;
  private int idMonto;
  private String fecha;

  public Pedido() {
        this.idPedido = 0;
        this.idDatos = 0;
        this.cantidad = 0;
        this.producto = "";
        this.montoUnit = 0;
        this.subtotal = 0;
        this.idMonto = 0;
        this.fecha = "";
        
    }
  
    public Pedido(int idPedido, int idDatos, int cantidad,
            String producto, double montoUnit, double subtotal,
            int idMonto, String fecha) {
        this.idPedido = idPedido;
        this.idDatos = idDatos;
        this.cantidad = cantidad;
        this.producto = producto;
        this.montoUnit = montoUnit;
        this.subtotal = subtotal;
        this.idMonto  = idMonto;
        this.fecha = fecha;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdDatos(int idDatos) {
        this.idDatos = idDatos;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setMontoUnit(double montoUnit) {
        this.montoUnit = montoUnit;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setIdMonto(int idMonto) {
        this.idMonto = idMonto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public int getIdPedido() {
        return idPedido;
    }

    public int getIdDatos() {
        return idDatos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public double getMontoUnit() {
        return montoUnit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getIdMonto() {
        return idMonto;
    }

    public String getFecha() {
        return fecha;
    }
    
    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido 
                + ", idDatos=" + idDatos 
                + ", cantidad=" + cantidad 
                + ", producto=" + producto 
                + ", montoUnit=" + montoUnit 
                + ", subtotal=" + subtotal
                + ", idMonto=" + idMonto
                + ", fecha=" + fecha+ '}';
    }
  
}
