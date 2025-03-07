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
    
    public Particle(String color, boolean isRed, int x, int y, int vx, int vy, int d,int h, int w) {
        int X = (x-(d/2));
        int Y = (y-(d/2));
        this.x1 = X;
        this.y1 = Y;
        this.x2 = X+d;
        this.y2 = Y+d;
        this.vx = vx;
        this.vy = vy;
        this.m1 = 5;
        this.m2 = ((w/2)-(w/128));
        this.m3 = ((w/2)-(w/128))+(w/64);
        this.m4 = w-5;
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
        if(y1== t1 || y2 == t2){
            this.vy = -vy;
        }
        if(x1 == m1 || y2 == m2 || x1 == m3 || x2 == m4){
            this.vx = -vx;
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
