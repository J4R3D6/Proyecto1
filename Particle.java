import java.lang.Math;
import java.util.*;
/**
 * Write a description of class Particle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Particle
{
    private static final int size = 10;
    private static int width,height,middle;
    private int vx,vy;
    private int x,y;
    private static ArrayList<ArrayList<Integer>> demonios = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> holes = new ArrayList<>();
    //private boolean colision = true;
    private final boolean isRed;
    private Circle circle;
    private String color;
    
    public Particle(String color, boolean Red, int x, int y, int vx, int vy) {
        this.isRed = Red;
        this.vx=vx;
        this.vy=vy;
        this.x=x;
        this.y=y;
        this.circle= new Circle(x, y, this.size, color);
        this.color = color;
    }
    public void medidas(int w, int h){
        this.width=w;
        this.height=h;
        this.middle= (w/2);
    }
    public void erase(){
        this.circle.erase();
    }
    public void delDemon(int x , int y){
        this.demonios.remove(new ArrayList<>(Arrays.asList(x,y)));
    }
    
    
    public void addDemon(int x , int y){
        this.demonios.add(new ArrayList<>(Arrays.asList(x,y)));
    }
    public void addhole(int x , int y){
        this.holes.add(new ArrayList<>(Arrays.asList(x,y)));
    }
    
    public boolean posicionCorrecta(){
        if(middle < x-this.size && this.isRed){
            return true;
        }else if(x < middle && !this.isRed){
            return true;
        }
        return false;
    }
    
    public void draw(){
        this.circle.draw();
    } 
    
    public boolean getIsRed(){
        return this.isRed;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public void makeVisible(){
        this.circle.makeVisible();
    }
    
    public void makeInvisible(){
        this.circle.makeInvisible();
    }
    
    public boolean DemonAccess(int Y){
        for (ArrayList<Integer> demon : demonios) {
            if (demon.get(0) == middle && demon.get(1) == Y) {
                return Math.random() < 0.5;
            }
        }
        return false;
    }
    
    public void move(int dt) {
        int newX = x + vx * dt;
        int newY = y + vy * dt;
        
        // Colisión con los bordes laterales
        if (newX < 5) {
            newX = 5;
            vx *= -1;
        }
        if (newX > width - 5 - size) {
            newX = width - 5 - size;
            vx *= -1;
        }
        
        // Colisión con los bordes superior e inferior
        if (newY < 5) {
            newY = 5;
            vy *= -1;
        }
        if (newY > height - 5 - size) {
            newY = height - 5 - size;
            vy *= -1;
        }
        
        // Intentar cruzar el muro (middle) solo si hay un demonio en la misma coordenada y
        if ((x < middle && newX >= middle) || (x > middle && newX <= middle)) {
            if (!DemonAccess(y)) {
                newX = (vx > 0) ? middle - 1 : middle + 1;
                vx *= -1;
            }
        }

        // Verificar si cae en un agujero
        for (ArrayList<Integer> hole : holes) {
            if (hole.get(0) == newX && hole.get(1) == newY) {
                if (Math.random() < 0.5) { // Probabilidad de desaparecer
                    makeInvisible();
                    return;
                }
            }
        }

        // Actualizar posición
        if (newX != x || newY != y) {
            softMove(newX - x, newY - y);
            x = newX;
            y = newY;
        }
    }
    public int[] getParticleData() {
        return new int[]{x,y,vx,vy};
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
