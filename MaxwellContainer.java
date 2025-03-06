import java.util.*;

/**
 * Write a description of class MaxwellContainer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MaxwellContainer
{
    private static Canvas canvas;
    private TreeMap<Integer, Deamon> demons;
    private ArrayList<Rectangle> tablero;
    private final int Height;
    private final int Width;
    private boolean isVisible;
    /**
     * Constructor for objects of class MaxwellContainer
     */
    public MaxwellContainer(int width, int height){
        this.Height = height;
        this.Width = width*2; 
        CrearCanvas(width*2, height);
        CrearTablero( width*2,height);
        this.demons = new TreeMap<>();
    }
    
    private void CrearCanvas(int width, int heigth){
        canvas = new Canvas(width, heigth);
        canvas.getCanvas(width, heigth);
    }
    private void CrearTablero(int width, int heigth){
        Rectangle recta1 = new Rectangle( 0, 0, heigth, width, "magenta");
        Rectangle recta2 = new Rectangle( 10, 10, (heigth-20) , (width - 20), "white");
        Rectangle  recta3 = new Rectangle( (width / 2), 10 ,(heigth-20), 5, "blue");
        this.tablero = new ArrayList<>();
        tablero.add(recta1);
        tablero.add(recta2);
        tablero.add(recta3);
        
    }
    public void makeVisible(){
        for(Rectangle o : this.tablero){
            o.makeVisible();
        }
        for(Deamon d : this.demons.values()){
            d.makeVisible();
        }
        }
    public void makeInvisible(){
        for(Rectangle o : this.tablero){
            o.makeInvisible();
        }
        for(Deamon d : this.demons.values()){
            d.makeInvisible();
        }
        
    }
    public boolean searchDeamon(int d){
        return this.demons.containsKey(d);
    }
    public void addDeamon(int d) {
        if (!searchDeamon(d)) {
            Deamon deamon = new Deamon(((this.Width / 2)), (this.Height - d));
            deamon.move(-18,0);
            deamon.makeVisible();
            demons.put(d,deamon);
        }else{
            System.out.println("Lugar ya esta ocupado");
        }
    }
    public void delDeamon(int d) {
        if (searchDeamon(d)){
            this.demons.get(d).erase();
            this.demons.remove(d);
        }else{
            System.out.println("Demoniio no Existe");
        }
    }
}
