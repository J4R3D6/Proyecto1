import java.util.ArrayList;

/**
 * Write a description of class MaxwellContainer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MaxwellContainer
{
    private static Canvas canvas;
    private ArrayList<Deamon> demons;
    private ArrayList<Rectangle> tablero;
    private final int Height;
    private final int Width;
    /**
     * Constructor for objects of class MaxwellContainer
     */
    public MaxwellContainer(int width, int height){
        this.Height = height;
        this.Width = width; 
        CrearCanvas(width, height);
        CrearTablero( width,height);
        this.demons = new ArrayList<>();
    }
    private void CrearCanvas(int width, int height){
        canvas = new Canvas(width, height);
        canvas.getCanvas(width, height);
    }
    private void CrearTablero(int width, int height){
        Rectangle recta1 = new Rectangle( 20, 20, (height - 40), (width - 40), "black");
        Rectangle recta2 = new Rectangle( 30, 30, (height - 60), (width - 60), "white");
        Rectangle  recta3 = new Rectangle( (width / 2), 30 ,(height-60), 10, "black");
        this.tablero = new ArrayList<>();
        tablero.add(recta1);
        tablero.add(recta2);
        tablero.add(recta3);
        
    }
    public void makeVisible(){
        for(Rectangle o : this.tablero){
            o.makeVisible();
        }
        for(Deamon d : this.demons){
            d.makeVisible();
        }
        }
    public void makeInvisible(){
        for(Rectangle o : this.tablero){
            o.makeInvisible();
        }
        for(Deamon d : this.demons){
            d.makeInvisible();
        }
    }
    public void addDeamon() {
        Deamon deamon = new Deamon(100, (this.Width / 2), (this.Height / 2));
        demons.add(deamon);
    }
}
