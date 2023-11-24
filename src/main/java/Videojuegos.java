import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


import java.util.List;

public class Videojuegos {
    @JacksonXmlProperty(localName = "id")
    private int id;
    @JacksonXmlProperty(localName = "titulo")
    private String titulo;
@JacksonXmlProperty(localName = "genero")
    private String genero;
@JacksonXmlProperty(localName = "imagen")
    private String imagen;
@JacksonXmlProperty(localName = "precio")
    private int precio;

    public Videojuegos() {
    }
    @JacksonXmlElementWrapper
    @JacksonXmlProperty(localName = "desarrolladores")
    private List<Desarrollador> desarrolladores;

    public Videojuegos(int id, String titulo, String genero, int precio,String imagen, List<Desarrollador> desarrolladores) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.precio = precio;
        this.imagen= imagen;
        this.desarrolladores = desarrolladores;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public List<Desarrollador> getDesarrollador() {
        return desarrolladores;
    }

    public void setDesarrolladores(List<Desarrollador> desarrolladores) {
        this.desarrolladores = desarrolladores;
    }

    @Override
    public String toString() {
        return "Videojuegos{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precio=" + precio +
                ", desarrolladores=" + desarrolladores +
                '}';
    }
}
