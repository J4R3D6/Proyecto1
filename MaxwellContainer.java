import java.util.*;
import javax.swing.JOptionPane;

/**
 * Write a description of class MaxwellContainer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MaxwellContainer
{
    private static Canvas canvas;
    private TreeMap<Integer, Deamon> demons;
    private ArrayList<Particle> particles;
    private ArrayList<Rectangle> tablero;
    private int Height;
    private int Width;
    private int blueParticles;
    private int redParticles;
    private boolean isVisible;
    private int DistanciaDomonio;
    private boolean isOk;
    /**
     * Constructor for objects of class MaxwellContainer
     */
    private MaxwellContainer(int h, int w){
        if  ((2<=h && w<=2000) && (2<=w && w<=2000)){
            this.isOk = true;
            this.Height = h;
            this.Width = w; 
            CrearCanvas(w, h);
            CrearTablero(w, h);
            this.demons = new TreeMap<>();
        }else{
            this.isOk = false;
            ok();
        }
    }
    //, int[][] particles
    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particlesData){
        this(h, (w*2));
        if(isOk){
            this.demons = new TreeMap<>();
            this.particles = new ArrayList<>();
            addDeamon(d);
            this.isOk = true;
            this.DistanciaDomonio=d;
            this.blueParticles = b;
            this.redParticles = r;
            addParticlesForMatriz(particlesData);
            this.makeVisible();
        }
    }
    
    private boolean  ok(){
        return isOk;
    }
    
    private void CrearCanvas(int width, int heigth){
        canvas = new Canvas(width, heigth);
        canvas.getCanvas(width, heigth);
    }
    
    private void CrearTablero(int w, int h){
        Rectangle recta1 = new Rectangle( 0, 0, h, w, "orange");
        Rectangle recta2 = new Rectangle( 5, 5, (h-10) , (w - 10), "white");
        Rectangle  recta3 = new Rectangle( ((w/2)-(w/128)), 0 ,h, (w/64), "gray");
        this.tablero = new ArrayList<>();
        tablero.add(recta1);
        tablero.add(recta2);
        tablero.add(recta3);
    }
    
    public boolean oK(){
        return true;
    }
    
    public void makeVisible(){
        for(Rectangle o : this.tablero){
            o.makeVisible();
        }
        if (this.demons.size() != 0){
            for(Deamon d : this.demons.values()){
                d.makeVisible();
            }
        }
        for(Particle o : this.particles){
            o.makeVisible();
        }
    }
    
    public void makeInvisible(){
        for(Rectangle o : this.tablero){
            o.makeInvisible();
        }
        if (this.demons.size() != 0){
            for(Deamon d : this.demons.values()){
                d.makeInvisible();
            }
        }
        for(Particle o : this.particles){
            o.makeInvisible();
        }
    }
    
    private boolean searchDeamon(int d){
        return this.demons.containsKey(d);
    }
    
    private boolean crashDeamon(int d){
        for (int i=(d-(this.Height/5)); i<= (d+(this.Height/5)) ;i++){
            if (searchDeamon(i)){
                return true;
            }
        }
        return false;
    }
    
    public void addDeamon(int d) {
        if (!crashDeamon(d)) {
            Deamon deamon = new Deamon(((this.Width / 2)-(this.Height/20)), (d - (this.Height/20)),(this.Height/10));
            deamon.makeVisible();
            demons.put(d,deamon);
            System.out.println("Demonio en "+d+" fue creado exitosamente");
        }else{
            System.out.println("Hay un demonio en este lugar o muy cerca");
        }
    }
    
    public void delDeamon(int d) {
        if (searchDeamon(d)){
            this.demons.get(d).erase();
            this.demons.remove(d);
        }else{
            System.out.println("Demoniio no Existe");
        }
    }
    
    public void addParticlesForMatriz(int[][] particlesData){
        for (int i=0; i<(blueParticles+redParticles);i++) {
            if(i<redParticles){
                addParticle("red", true, particlesData[i][0], particlesData[i][1], particlesData[i][2], particlesData[i][3]);
            }else{
                addParticle("blue", false, particlesData[i][0], particlesData[i][1], particlesData[i][2], particlesData[i][3]);
            }
        }        
    }
    
    public void addParticle(String color, boolean isRed, int x, int y, int vx, int vy){
        this.particles.add(new Particle(color, isRed, x, y, vx, vy,(this.Height/20), this.Height, this.Width,(this.DistanciaDomonio - (this.Height/20)),(this.Height/20)));        
    }
    
    public void delParticle(String color){
        for(Particle p: this.particles){
            p.erase();
        }
        this.particles.removeIf(p -> p.getColor().equals(color));
        for(Particle p: this.particles){
            p.draw();
        }
    }
    
    public void start(int ticks) {
        for (int i = 0; i < ticks; i++) {
            for(Particle p: this.particles){
                p.move(1);
                finish();
            }
            finish();
        }
        //particles.parallelStream().forEach(p -> p.move(1));
    }
    
    public void finish(){
         if (isGoal()){
             JOptionPane.showMessageDialog(null, "Juego terminado");
         }
    }
    
    public boolean isGoal(){
         for (Particle p: particles){
             ArrayList<Integer> posiciones = p.getPositionsX();
             if ( !((posiciones.get(0) >= (Width/2) && p.getIsRed()) && (posiciones.get(1) <= (Width/2) && !p.getIsRed())) ){
                 return true;
             }
         }
         return true;
    }
}
