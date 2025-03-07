import java.lang.Math;
/**
 * Write a description of class Particle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Particle
{
    private int m1, m2, m3, m4, t1,t2;
    private int x1, y1,x2, y2; 
    private int vx, vy;
    private boolean isRed; 
    private Circle circle;
    private String color;
    private int diametro;
    
    public Particle(String color, boolean isRed, int x, int y, int vx, int vy, int d,int h, int w) {
        int X = (x-(d/2));
        int Y = (y-(d/2));
        this.x1 = X;
        this.y1 = Y;
        this.x2 = X+d;
        this.y2 = Y+d;
        this.diametro = Math.abs(x1-x2);
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
    
    public String getColor(){
        return this.color;
    }
    
    public void makeVisible(){
        this.circle.makeVisible();
    }
    
    public void makeInvisible(){
        this.circle.makeInvisible();
    }
    
    public void crash(){
        if((y1 == t1 || y1+this.vy < t1) || (y2 == t2 ||  y2+this.vy > t2)){
             if(y1+this.vy < t1|| y2+this.vy > t2){
                if(y1+this.vy < t1){
                    this.y1 = this.t1;
                    this.y2 = this.y1+this.diametro;
                    this.vy = -vy;
                }else if(y2+this.vy > t2){
                    this.y2 = this.t2;
                    this.y1 = this.y2-this.diametro;
                    this.vy = -vy;
                }
            }else{
                this.vy = -vy;
            }
        }
        if((x1 == m1 || x1+this.vx < m1) || (x2 == m3 || x2+this.vx > m3)){
            if(x1+this.vx < m1|| x2+this.vx > m3){
                if(x1+this.vx < t1){
                    this.x1 = this.m1;
                    this.x2 = this.x1+this.diametro;
                    this.vx = -vx;
                }else if(x2+this.vx > m3){
                    this.x2 = this.m3;
                    this.x1 = this.x2-this.diametro;
                    this.vx = -vx;
                }
            }else{
                this.vx = -vx;
            }
        }
    }
    
    public void move(int dt) {
        for(int i = 0;i < dt;i++){
            this.crash();
            x1 += vx;
            y1 += vy;
            x2 += vx;
            y2 += vy;
            this.softMove();
        }
    }
    public void softMove(){
            this.circle.makeInvisible();
            this.circle.moveHorizontal(vx);

            this.circle.moveVertical(vy);
            this.circle.makeVisible();
    }
}
