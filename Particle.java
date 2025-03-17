import java.util.*;
/**
 * Write a description of class Particle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Particle
{
    private int x,y,vx,vy;
    private int size=5;
    private boolean isRed;
    private Circle circle;
    private String color;
    private boolean inHole = false;
    

    /**
     * Constructor for objects of class Particle
     */
    public Particle(String color, boolean Red, int x, int y, int vx, int vy) {
        this.color = color;
        this.isRed = Red;
        this.vx=vx;
        this.vy=vy;
        this.x=x;
        this.y=y;
        if(Red){
            this.circle= new Circle(x, y, size, "red");
        }else{
            this.circle= new Circle(x, y, size, "blue");
        }
    }
    
    public void makeVisible(){
        this.circle.makeVisible();
    }
    
    public void makeInvisible(){
        this.circle.makeInvisible();
    }
    public boolean getIsRed(){
        return this.isRed;
    }
    public String getColor() {
        return this.color;
    }
    public int[] getPosition() {
        return new int[]{x,y};
    }
    public int[] getParticleData() {
        return new int[]{x,y,vx,vy};
    }
    
    public void move(int w, int h, ArrayList<Demon> demons, ArrayList<Hole> holes, MaxwellContainer tablero){
        int newX = x + vx;
        int newY = y + vy;
        if (newX < 0) {
            newX = 0;
            vx *= -1;
        }
        if (newX > (w*2) - size) {
            newX = (w*2) - size;
            vx *= -1;
        }
        if (newY < 0) {
            newY = 0;
            vy *= -1;
        }
        if (newY > h - size) {
            newY = h - size;
            vy *= -1;
        }
        
        // Intentar cruzar el muro (middle) solo si hay un demonio en la misma coordenada y
        if ((x < w && newX >= w) || (x > w  && newX <= w)) {
            Demon demon = null;
            for(Demon d:demons){
                if (Math.abs(d.getDistance() - newY) <= 8) {
                    demon = d;
                }
            }
            if (demon != null) {
                if (demon.demonAccess(this)){
                    newX = (vx > 0) ? w - 1 : w + 1;
                    vx *= -1;
                }
            }else{
                newX = (vx > 0) ? w - 1 : w + 1;
                vx *= -1;
            }
        }
        crashHole(holes, newX,newY);

        // Actualizar posición
        if (newX != x || newY != y) {
            softMove(newX - x, newY - y);
            x = newX;
            y = newY;
        }
    }
    
    private void crashHole(ArrayList<Hole> holes, int newX, int newY){
        for (Hole hole : holes) {
            if (Math.abs(hole.getCoords().get(0) - newX) <=13 && Math.abs(hole.getCoords().get(1) - newY) <=13 ) {
                
                hole.cath(this);
                
            }
        }
    }
    public boolean rigthPosition(int w){
         if(w < x && !this.isRed){
            return true;
        }else if(x+this.size < w && this.isRed){
            return true;
        }
        return false;
    }
    public void setInHole(boolean inHole){
        this.inHole = inHole;
    }
    public boolean getInHole(){
        return this.inHole;
    }
    public void softMove(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            this.circle.makeInvisible();
            this.circle.moveHorizontal(dx);
            this.circle.moveVertical(dy);
            this.circle.makeVisible();
        }
    }
}