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
    private ArrayList<Hole> holes;
    private ArrayList<Particle> particles;
    private ArrayList<Rectangle> tablero;
    private int Height;
    private int Width;
    private int blueParticles;
    private int redParticles;
    private boolean isVisible;
    private boolean isOk;
    
    /**
     * Constructor for objects of class MaxwellContainer
     */
    private MaxwellContainer(int h, int w){
        if  ((2<=h && h<=2000) && (2<=w && w<=2000)){
            this.isOk = true;
            this.Height = h;
            this.Width = w; 
            CrearCanvas(w, h);
            CrearTablero(w, h);
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
            this.holes = new ArrayList<>();
            this.particles = new ArrayList<>();
            this.blueParticles = b;
            this.redParticles = r;
            addParticlesForMatriz(particlesData);
            mandarMedidas();
            addDeamon(d);
            this.makeVisible();
        }
    }
    
    private boolean  ok(){
        return isOk;
    }
    private void mandarMedidas(){
        if(this.particles.size() !=0 ){
            this.particles.get(0).medidas(this.Width ,this.Height);
        }
    }
    private void CrearCanvas(int width, int heigth){
        canvas = new Canvas(width, heigth);
        canvas.getCanvas(width, heigth);
    }
    
    private void CrearTablero(int w, int h){
        Rectangle recta1 = new Rectangle( 0, 0, h, w, "orange");
        Rectangle recta2 = new Rectangle( 5, 5, (h-10) , (w - 10), "white");
        Rectangle  recta3 = new Rectangle( ((w/2)-(2)), 0 ,h, (4), "gray");
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
        for(Hole h : this.holes){
            h.makeVisible();
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
        for(Hole h : this.holes){
            h.makeInvisible();
        }
    }
    
    private boolean searchDeamon(int d){
        return this.demons.containsKey(d);
    }
    
    private boolean crashDeamon(int d){
        for (int i=(d-10); i<= (d+10) ;i++){
            if (searchDeamon(i)){
                return true;
            }
        }
        return false;
    }
    
    public void addDeamon(int d) {
        if (!searchDeamon(d)) {
            Deamon deamon = new Deamon(((this.Width / 2)-(10)), (d - (10)),20);
            deamon.makeVisible();
            demons.put(d,deamon);
            if(this.particles.size() !=0 ){
                this.particles.get(0).addDemon((this.Width / 2) , d);
            }
        }else{
            System.out.println("Hay un demonio en este lugar");
        }
    }
    
    public void delDeamon(int d) {
        if (searchDeamon(d)){
            this.demons.get(d).erase();
            this.demons.remove(d);
            if(this.particles.size() !=0 ){
                this.particles.get(0).delDemon((this.Width / 2) , d);
            }
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
        Particle partucila = new Particle(color, isRed, x, y, vx, vy);
        this.particles.add(partucila);       
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
        }
        //particles.parallelStream().forEach(p -> p.move(1));
    }
    public ArrayList<Integer> demonEscala(){
        ArrayList<Integer> Escalas = new ArrayList<>();
        for(Deamon d : this.demons.values()){
                 Escalas.add(d.getEscala());
            }
        return Escalas;
    }
    public void finish(){
        if (isGoal()){
             JOptionPane.showMessageDialog(null, "Juego terminado");
         }
    }
    
    public boolean isGoal(){
         for (Particle p: particles){
             if (!p.posicionCorrecta()){
                 return false;
             }
         }
         return true;
    }
    public int[][] particles(){
        int [][] dataParticles = new int[4][this.particles.size()];
        for(int i=0 ;i<this.particles.size();i++){
            int[] data = particles.get(i).getParticleData();
            dataParticles[i][0] =data[0];
            dataParticles[i][1] =data[1];
            dataParticles[i][2] =data[2];
            dataParticles[i][3] =data[3];
        }
        return dataParticles;
    }
    public int[] demons(){
        int[] dataDemons = demons.keySet().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(dataDemons);
        return dataDemons;
    }
    public void addHole(int x, int y, int particles){
        if (particles > 0){
            Hole a = new Hole(x, y, particles);
            this.holes.add(a);
            if(this.particles.size() !=0 ){
                this.particles.get(0).addhole(a);
            }
            isOk =true;
            ok();
        }else{
            isOk =false;
            ok();
        }
    }
        public int[][] holes(){
        int [][] dataHoles = new int[3][holes.size()];
        for(int i=0 ;i<holes.size();i++){
            int[] data = holes.get(i).dataHole();
            dataHoles[i][0] =data[0];
            dataHoles[i][1] =data[1];
            dataHoles[i][2] =data[2];
        }
        return dataHoles;
    }
}
