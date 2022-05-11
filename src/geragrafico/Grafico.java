package geragrafico;

/** @author gabri */
public class Grafico {
    private String x;
    private int y;
    private String grupo;

    public Grafico(String x, int y, String grupo) {
        this.x = x;
        this.y = y;
        this.grupo = grupo;
    }

    public String getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getGrupo() {
        return grupo;
    }

}
