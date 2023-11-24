import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Desarrollador {
@JacksonXmlProperty(localName = "id")
    private int id;
@JacksonXmlProperty(localName = "nombre")
    private String nombre;
@JacksonXmlProperty(localName = "pais")
    private String pais;

    public Desarrollador(int id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Desarrollador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Desarrollador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
