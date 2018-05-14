package mc.datos;

/**
 *
 * @author luna
 */
public class Datos {
    private int idDatos;
    private String matricula;
    private String nombre;
    private String apPat;
    private String apMat;

    public Datos() {
        this.idDatos = 0;
        this.matricula = "";
        this.nombre = "";
        this.apPat = "";
        this.apMat = "";
    }
    
    public Datos(int idDatos, String matricula, String nombre,
            String apPat, String apMat) {
        this.idDatos = idDatos;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apPat = apPat;
        this.apMat = apMat;
    }

    public void setIdDatos(int idDatos) {
        this.idDatos = idDatos;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApPat(String apPat) {
        this.apPat = apPat;
    }

    public void setApMat(String apMat) {
        this.apMat = apMat;
    }

    public int getIdDatos() {
        return idDatos;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApPat() {
        return apPat;
    }

    public String getApMat() {
        return apMat;
    }

    @Override
    public String toString() {
        return "Datos{" + "idDatos=" + idDatos 
                + ", matricula=" + matricula 
                + ", nombre=" + nombre 
                + ", apPat=" + apPat 
                + ", apMat=" + apMat + '}';
    }
    
}
