import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "rss")
public class Root {
    @JacksonXmlElementWrapper(localName = "videojuegos")
    @JacksonXmlProperty(localName = "videojuego")
    public List<Videojuegos> videojuegos;

    public Root(List<Videojuegos> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public Root() {
    }

    public List<Videojuegos> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(List<Videojuegos> videojuegos) {
        this.videojuegos = videojuegos;
    }
}
