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
    
    public boolean DemonAccess(){
        return Math.random() < 0.5;
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
    
    
    public void move(int dt) {
        for(int i = 0;i < dt;i++){
            int newX = x + vx * dt;
        int newY = y + vy * dt;
        
        // Colisión con los bordes
        if (newX < 5 || newX > width - 5 - size) vx *= -1;
        if (newY < 5 || newY > height - 5 - size) vy *= -1;
        
        // Intentar cruzar el muro (middle)
        if ((x < middle && newX >= middle) || (x > middle && newX <= middle)) {
            if (!DemonAccess()) {
                vx *= -1; // Rebote si no puede cruzar
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
        x += vx * dt;
        y += vy * dt;
        softMove(vx * dt, vy * dt);
        }
    }
    public int[] getParticleData() {
        return new int[]{x,y,vx,vy};
    }
    public void softMove(int x, int y){
        this.circle.makeInvisible();
        this.circle.moveHorizontal(x);
        this.circle.moveVertical(y);
        this.circle.makeVisible();
    }
}
