import java.util.*;
/**
 * Write a description of class Hole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hole{
    private int capacidad,x,y;
    private ArrayList<Particle> particles = new ArrayList<>();
    private boolean full = false;
    private  Circle circle;

    public Hole(int x, int y, int particles){
        this.x = x;
        this.y = y;
        this.capacidad = particles;
        this.circle = new Circle(x,y,15,"black");
    }
    public int[] dataHole(){
        int[] data =  {
            x,
            y,
            (capacidad-this.particles.size())
        };
        return data;
    }
    
    public void cath(Particle particle){
        if(!this.full){
            particle.makeInvisible();
            particle.setInHole(true);
            particles.add(particle);
            this.itsFull();
        }
    }
    public void makeVisible(){
        this.circle.makeVisible();
    }
    public void makeInvisible(){
        this.circle.makeInvisible();
    }
    private void itsFull(){
        if(capacidad == this.particles.size()){
            circle.changeColor("gray");
            this.full=true;
        }
    }
    public ArrayList<Integer> getCoords(){
        return new ArrayList<>(Arrays.asList(x,y));
    }
    public boolean getFull(){
        return full;
    }
} 