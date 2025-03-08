import java.util.*;
/**
 * Write a description of class Hole here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hole
{
    private int x1,x2,y1,y2;
    private int centroX,centroY;
    private int capacidad;
    private int ocupados = 0;
    private Circle hole;
    private boolean full = false;

    public Hole(int x, int y, int particles){
        this.hole = new Circle(x-10, y-10, 20, "#01ffdc");
        makeVisible();
        centroX = x;
        centroY = y;
        x1 = x-10;  
        x2 = x+10; 
        y1 = y-10; 
        y2 = y+10;
        capacidad = particles;
    }
    public void makeVisible(){
        this.hole.makeVisible();
    }
    public void makeInvisible(){
        this.hole.makeInvisible();
    } 
    public int[] dataHole(){
        int[] data =  {
            centroX,
            centroY,
            ocupados
        };
        return data;
    }
    
    public boolean itsFull(){
        return this.full;
    }
    
    public void atrapado(){
        ocupados += 1;
        capacidadllena();
    }
    
    public ArrayList<Integer> getCoords(){
        return new ArrayList<>(Arrays.asList(centroX,centroY));
    }
    
    public void capacidadllena(){
        if (capacidad == ocupados){
            full = true;
            makeInvisible();
        }
    }
} 