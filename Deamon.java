import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Deamon {
    private int xPosition;
    private int yPosition;
    private boolean isVisible;
    private ArrayList<Figure> figures;

    public Deamon(int width, int x, int y) {
        formas(width, x, y);
    }
    public void formas(int widht, int x, int y) {
        Rectangle r1 = new Rectangle(x,y,widht,widht,"red");
        Rectangle r2 = new Rectangle((x+(widht / 3)),(y+((widht / 18)*14)),(widht / 9),(widht / 3),"black");
        Rectangle r3 = new Rectangle((x+(widht / 9)),(y+((widht / 9))),(widht / 9),(widht / 3),"black");
        Rectangle r4 = new Rectangle((x+(widht / 9)*5),(y+((widht / 9))),(widht / 9),(widht / 3),"black");

        Rectangle r5 = new Rectangle((x+(widht / 18)*4),(y+((widht / 3))),(widht /9),(widht / 9),"black");

        //Rectangle r5 = new Rectangle(x,y,width,width,"red");
        //Rectangle r6 = new Rectangle(x,y,width,width,"red");
        //Triangle t1 = new Triangle(x,y,width,width,"red");
        //Triangle t2 = new Triangle(x,y,width,width,"red");
        //Triangle t3 = new Triangle(x,y,width,width,"red");
        
        this.figures = new ArrayList();
        figures.add(r1);
        figures.add(r2);
        figures.add(r3);
        figures.add(r4);
        figures.add(r5);
        //figures.add(r6);
        //figures.add(t1);
        //figures.add(t1);
        //figures.add(t1);
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

    private void draw() {
        for (Figure f: this.figures){
            f.draw();
        }
    }
    
    private void erase(){
            for (Figure f: this.figures){
            f.erase();
        }
        }
    
    public void changeSize(int a) {
        erase();
        draw();
        this.makeVisible();
    }
    
    public void moveTo(int x, int y) {
        erase();
        this.xPosition = x;
        this.yPosition = y;
        draw();
    }
}

