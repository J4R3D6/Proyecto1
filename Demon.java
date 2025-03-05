import java.awt.*;
import javax.swing.*;

public class Demon {
    private int escala;
    private int xPosition;
    private int yPosition;
    private boolean isVisible;
    private Image textura;

    public Demon(int escala, int x, int y) {
        this.escala = escala;
        this.xPosition = x;
        this.yPosition = y;
        this.isVisible = false;
        cargarImagen();        
    }
    
    private void cargarImagen() {
        ImageIcon icon = new ImageIcon("imagenes/demon.png");
        textura = icon.getImage().getScaledInstance(escala, escala, Image.SCALE_SMOOTH);
    }   
    
    public void makeVisible() {
        isVisible = true;
        draw();
    }

    public void makeInvisible() {
        erase();
        isVisible = false;
    }

    public void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.drawImage(this, textura, xPosition, yPosition);
        }
    }
    
    public void erase(){
            if(isVisible) {
                Canvas canvas = Canvas.getCanvas();
                canvas.erase(this);
            }
        }
    
    public void changeEscala(int a) {
        erase();
        this.escala = a;
        cargarImagen();
        draw();
    }
    
    public void moveTo(int x, int y) {
        erase();
        this.xPosition = x;
        this.yPosition = y;
        draw();
    }
}

