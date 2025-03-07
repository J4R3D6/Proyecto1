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
    private int m1, m2, m3, t1,t2;
    private int x1, y1,x2, y2; 
    private int vx, vy;
    private boolean isRed; 
    private Circle circle;
    private String color;
    private int diametro;
    private int radioDemonio;
    private int posicionDemonio,DcoorX1,DcoorX2,DcoorY1,DcoorY2,DcoorM;
    
    public Particle(String color, boolean isRed, int x, int y, int vx, int vy, int d,int h, int w, int posDemon , int demon) {
        int X = (x-(d/2));
        int Y = (y-(d/2));
        this.x1 = X;
        this.y1 = Y;
        this.x2 = X+d;
        this.radioDemonio =demon;
        this.posicionDemonio =posDemon;
        this.y2 = Y+d;
        this.diametro = Math.abs(x1-x2);
        this.DcoorX1 = ((w/2))-demon;
        this.DcoorX2 = ((w/2))+demon;
        this.DcoorY1 = posDemon-demon;
        this.DcoorY2 = posDemon+demon;
        this.DcoorM = ((w/2));
        this.vx = vx;
        this.vy = vy;
        this.m1 = 5;
        this.m2 = ((w/2));
        this.m3 = w-5;
        this.t1 = 5;
        this.t2 = h-5;
        this.isRed = isRed;
        this.circle = new Circle(X, Y, d, color);
        this.color = color;
        circle.makeVisible();
    }
    
    public void erase(){
        this.circle.erase();
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
    
    public void crashX(){
        if(x1 == m1 || x2 == m3 ){
                this.vx = -vx;
        }
        else if(x1+this.vx < m1|| x2+this.vx > m3){
            if(x1+this.vx < m1){
                this.softMove(m1-x1, 0);
                this.x1 = this.m1;
                this.x2 = this.x1+this.diametro;
                this.vx = -vx;
            }else if(x2+this.vx > m3){
                this.softMove(m3-x2, 0);
                this.x2 = this.m3;
                this.x1 = this.x2-this.diametro;
                this.vx = -vx;
            }
        }
    }
    
    public void crashY(){
        if(y1 == t1 || y2 == t2 ){
            this.vy = -vy;
        }
        else if (y1+this.vy < t1 ||  y2+this.vy > t2){
            if(y1+this.vy < t1){
                this.softMove(0, t1-y1);
                this.y1 = this.t1;
                this.y2 = this.y1+this.diametro;
                this.vy = -vy;
            }else if(y2+this.vy > t2){
                this.softMove(0, t2-y2);
                this.y2 = this.t2;
                this.y1 = this.y2-this.diametro;
                this.vy = -vy;
            }
        }
    }
    public void crashMDer(){
        if(x1 == m2){
            this.vx = -vx;
        }
        else if(x1>m2 && x1+this.vx < m2){
            this.softMove(m2-x1, 0);
            this.x1 = this.m2;
            this.x2 = this.x1+this.diametro;
            this.vx = -vx;
        }
    }
    
    public void crashMIzq(){
        if(x2 == m2){
            this.vx = -vx;
        }
        else if(x2<m2 && x2+this.vx > m2){
            this.softMove(m2-x2, 0);
            this.x2 = this.m2;
            this.x1 = this.x2-this.diametro;
            this.vx = -vx;
        }
    }
    
    public void crashDemonIzq(){
        if( ((DcoorX1<= x2 || DcoorX1<= x2+vx) &&  (x2<= DcoorM || vx+x2<= DcoorM)) && ( (DcoorY2 <= y1 && y1<= DcoorY1) || (DcoorY1<= y2 && y2 <= DcoorY2) )){
            boolean permiso = this.DemonAccess();
            System.out.println(permiso);
            if (permiso){
                x1 += vx*2;
                y1 += vy*2;
                x2 += vx*2;
                y2 += vy*2;
                this.softMove(vx*2, vy*2);
            }else{
                this.vx = -vx;
            }
        }
    }
    
    public void crashDemonDer(){
        if( ((DcoorM <= x1 || DcoorM <= x1+vx ) &&  (x1<= DcoorX2 || x1+vx<= DcoorX2)) && ( (DcoorY2 <= y1 && y1<= DcoorY1) || (DcoorY1<= y2 && y2 <= DcoorY2) )){
            boolean permiso = this.DemonAccess();
            System.out.println(permiso);
            if (permiso){
                x1 += vx*2;
                y1 += vy*2;
                x2 += vx*2;
                y2 += vy*2;
                this.softMove(vx*2, vy*2);
            }else{
                this.vx = -vx;
            }
        }
    }
    
    public boolean DemonAccess(){
        return Math.random() < 0.5;
    }
    
    public void move(int dt) {
        for(int i = 0;i < dt;i++){
            this.crashDemonIzq();
            this.crashDemonDer();
            this.crashY();
            this.crashMIzq();
            this.crashMDer();
            this.crashX();
            x1 += vx;
            y1 += vy;
            x2 += vx;
            y2 += vy;
            this.softMove(vx, vy);
        }
    }
    public void softMove(int x, int y){
            this.circle.makeInvisible();
            this.circle.moveHorizontal(x);
            this.circle.moveVertical(y);
            this.circle.makeVisible();
    }
    
    public ArrayList<Integer> getPositionsY(){
        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(y1);
        positions.add(y2);
        return positions;
    }
    public ArrayList<Integer> getPositionsX(){
        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(x1);
        positions.add(x2);
        return positions;
    }
    public ArrayList<Integer> getPositions(){
        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(x1);
        positions.add(x2);
        positions.add(y1);
        positions.add(y2);
        return positions;
    }
}
