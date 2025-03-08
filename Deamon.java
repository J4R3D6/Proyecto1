import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Deamon {
    private boolean isVisible;
    private Image textura;
    private int xPosition, yPosition, escala;
    private ArrayList<Figure> figures;

    public Deamon(int x, int y, int escala) {
        //cargarImagen(escala);
        formas(escala, x, y);
        this.escala = escala;
        this.yPosition =y;
        this.xPosition =x;
    }
    //private void cargarImagen(int escala) {
    //    ImageIcon icon = new ImageIcon("imagenes/demon.png");
    //    this.textura = icon.getImage().getScaledInstance(escala, escala+3, Image.SCALE_SMOOTH);
    //} 
    public int getEscala() {
        return this.escala;
    }
    public void formas(int widht, int x, int y) {
        Rectangle r1 = new Rectangle(x,y,widht,widht,"#7405af");
        Rectangle r2 = new Rectangle((x+(widht / 5)),(y+((widht /5))),(widht / 5),(widht / 5),"black");
        Rectangle r3 = new Rectangle((x+(widht / 5)*3),(y+((widht / 5))),(widht / 5),(widht / 5),"black");
        Rectangle r4 = new Rectangle((x+(widht / 5)),(y+((widht / 5)*3)),(widht / 5),((widht / 5)*3),"black");
        this.figures = new ArrayList();
        figures.add(r1);
        figures.add(r2);
        figures.add(r3);
        figures.add(r4);
    }
    
    //public void makeVisible() {
    //    isVisible = true;
    //    draw();
    //}

    //public void makeInvisible() {
    //    erase();
    //    isVisible = false;
    //}
    
    //public void erase(){
    //        if(isVisible) {
    //            Canvas canvas = Canvas.getCanvas();
    //            canvas.erase(this);
    //        }
    //}
    //public void draw() {
    //    if (isVisible) {
    //        Canvas canvas = Canvas.getCanvas(this.Width , this.Height);
    //        canvas.drawImage(this, textura, xPosition, yPosition);
    //    }
    //}
    
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
    
    public ArrayList<Integer> getPositions(){
        ArrayList<Integer> posiciones = new ArrayList<>();
        posiciones.add(this.xPosition);
        posiciones.add(this.xPosition+this.escala);
        posiciones.add(this.yPosition);
        posiciones.add(this.yPosition+this.escala);
        return posiciones;
    }
    
    public boolean DemonAccess(){
        return Math.random() < 0.5;
    }
}

