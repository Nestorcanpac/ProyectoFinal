import java.util.List;

public class Root {
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
