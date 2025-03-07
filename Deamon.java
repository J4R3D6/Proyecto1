import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Deamon {
    private ArrayList<Integer> yPosition;
    private boolean isVisible;
    private ArrayList<Figure> figures;

    public Deamon(int x, int y, int escala) {
        formas(escala, x, y);
        this.yPosition = new ArrayList();
        this.yPosition.add(y);
        this.yPosition.add(y+36);
    }
    public void formas(int widht, int x, int y) {
        Rectangle r1 = new Rectangle(x,y,widht,widht,"#7405af");
        Rectangle r2 = new Rectangle((x+(widht / 5)),(y+((widht /5))),(widht / 5),(widht / 5),"black");
        Rectangle r3 = new Rectangle((x+(widht / 5)*3),(y+((widht / 5))),(widht / 5),(widht / 5),"black");
        Rectangle r4 = new Rectangle((x+(widht / 5)),(y+((widht / 5)*3)),(widht / 5),((widht / 5)*3),"black");
        //Triangle t1 = new Triangle((x+((widht / 6))),(y-(widht / 3)),(widht / 3),(widht / 3),"red");
        //Triangle t2 = new Triangle((x+((widht / 6)*5)),(y-(widht / 3)),(widht / 3),(widht / 3),"red");
        this.figures = new ArrayList();
        figures.add(r1);
        figures.add(r2);
        figures.add(r3);
        figures.add(r4);
        //figures.add(t1);
        //figures.add(t2);
    }
    
    public void makeVisible() {
        for (Figure f: this.figures){
            f.makeVisible();
        }
        this.isVisible = true;
    }

    public void makeInvisible() {
        for (Figure f: this.figures){
            f.makeInvisible();
        }
        this.isVisible = false;
    }

    private void draw() {
        for (Figure f: this.figures){
            f.draw();
        }
    }
    public void erase(){
        for (Figure f: this.figures){
            f.erase();
        }
        }
    public void move(int x, int y) {
        erase();
        for (Figure f: this.figures){
            f.moveHorizontal(x);
            f.moveVertical(y);
        }
        draw();
    }
    public ArrayList<Integer> getYPosition(){
        return this.yPosition;
    }
}

