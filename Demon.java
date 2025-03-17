import java.util.ArrayList;
import java.lang.Math;
/**
 * Write a description of class Demon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Demon
{
    private int x,y;
    private int size=10;
    private ArrayList<Figure> figures = new ArrayList<>();

    /**
     * Constructor for objects of class Demon
     */
    public Demon(int x, int d){
        this.x=x;
        this.y=d;
        //Rectangle r2 = new Rectangle((x+(widht / 5)),(y+((widht /5))),(widht / 5),(widht / 5),"black");
        //Rectangle r3 = new Rectangle((x+(widht / 5)*3),(y+((widht / 5))),(widht / 5),(widht / 5),"black");
        //Rectangle r4 = new Rectangle((x+(widht / 5)),(y+((widht / 5)*3)),(widht / 5),((widht / 5)*3),"black");
        figures.add(new Rectangle(x-(size/2),y-(size/2),size,size,"#7405af"));
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean demonAccess(Particle particle){
        return  Math.random() < 0.5;
    }
    public void makeVisible() {
        for (Figure f: this.figures){
            f.makeVisible();
        }
    }

    public void makeInvisible() {
        for (Figure f: this.figures){
            f.makeInvisible();
        }
    }
    public int getDistance() {
        return this.y;
    }
}
