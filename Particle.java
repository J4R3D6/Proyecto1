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
    private final int vx,vy;
    private int x,y;
    private static ArrayList<ArrayList<Integer>> demonios;
    private static ArrayList<ArrayList<Integer>> holes;
    private boolean colision = true;
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
        this.demonios = new ArrayList<>();
        this.holes = new ArrayList<>();
        this.color = color;
    }
    
    public void erase(){
        this.circle.erase();
    }
    public void delDemon(int x , int y){
        ArrayList<Integer> a = new ArrayList<>();
        a.add(x);
        a.add(y);
        this.demonios.remove(a);
    }
    public void addDemon(int x , int y){
        ArrayList<Integer> a = new ArrayList<>();
        a.add(x);
        a.add(y);
        this.demonios.add(a);
    }
    public void addhole(int x , int y){
        ArrayList<Integer> a = new ArrayList<>();
        a.add(x);
        a.add(y);
        this.holes.add(a);
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
            this.softMove(vx, vy);
        }
    }
    public int[] cunsultarParticula() {
        int[] datos = {
            x,
            y,
            vx,
            vy
        };
        return datos;
    }
    public void softMove(int x, int y){
        this.circle.makeInvisible();
        this.circle.moveHorizontal(x);
        this.circle.moveVertical(y);
        this.circle.makeVisible();
    }
}
