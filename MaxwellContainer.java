import java.util.*;
import javax.swing.JOptionPane;

/**
 * Clase MaxwellContainer
 * 
 * Esta clase representa un contenedor que simula un sistema de partículas (rojas y azules)
 * en un tablero con demonios y agujeros. Las partículas se mueven en el tablero, y el juego
 * termina cuando todas las partículas alcanzan su posición correcta.
 * 
 * @author (tu nombre)
 * @version (versión o fecha)
 */
public class MaxwellContainer {
    private static Canvas canvas;
    private ArrayList<Demon> demons = new ArrayList<>(); 
    private ArrayList<Hole> holes = new ArrayList<>(); 
    private ArrayList<Particle> particles = new ArrayList<>();
    private Rectangle middle;
    private int w; 
    private int h;
    private boolean isVisible = false;
    private boolean isOk = true;
    private int factor=10;

    
    private MaxwellContainer(int h, int w) {
        if ((2 <= h && h <= 200) && (2 <= w && w <= 200)) {
            this.w = w*factor;
            this.h = h*factor;
            canvas = new Canvas(w*2*factor, h*factor);
            canvas.getCanvas(w*2*factor, h*factor);
            middle = new Rectangle((w*factor)-1, 0, h*factor,(2) , "gray");
            this.isOk = true;
        }else{
            errorSignal("Medidas para el tablero invalidas");
            this.isOk = false;
        }
    }


    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particlesData) {
        this(h, w);
        if (isOk) {
            for (int i = 0; i < (b + r); i++) {
                addParticle(""+i, i < r, particlesData[i][0], particlesData[i][1], particlesData[i][2], particlesData[i][3]);
            }
            addDeamon(d*factor); 
        }
    }

    public boolean ok() {
        return isOk;
    }

    /**
     * Hace visible el tablero y todos sus elementos.
     */
    public void makeVisible() {
        this.middle.makeVisible();
        for (Demon d : this.demons) {
                d.makeVisible();
            }
        for (Particle o : particles) {
                o.makeVisible();
            }
        for (Hole h : this.holes) {
            h.makeVisible();
        }
        this.isVisible = true;
    }

    /**
     * Hace invisible el tablero y todos sus elementos.
     */
    public void makeInvisible() {
        this.middle.makeInvisible();
        for (Demon d : this.demons) {
                d.makeInvisible();
        }
        for (Particle o : particles) {
                o.makeInvisible();
        }
        for (Hole h : this.holes) {
            h.makeInvisible();
        }
        this.isVisible = false;
    }

    /**
     * Añade un demonio al tablero.
     * 
     * @param d Posición del demonio.
     */
    public void addDeamon(int d) {
        boolean alreadyExist = false;
        for (Demon de: demons){
            if(de.getDistance() == d){
                alreadyExist = true;
            }
        }
        if(!alreadyExist){
            Demon newDemon = new Demon(this.w, d);
            if(isVisible){
                newDemon.makeVisible();
            }
            this.demons.add(newDemon);
        }else{
            errorSignal("Ya existe un demonio en este lugar");
        }
    }

    /**
     * Elimina un demonio del tablero.
     * 
     * @param d Posición del demonio.
     */
    public void delDeamon(int d) {
        Demon demon = null;
        for (Demon de : demons) {
            if (de.getDistance() == (d)) {
                de.makeInvisible();
                demon  = de;
            }
        }
        if(demon != null){
            this.demons.remove(demon);
        }else{
            errorSignal("No existe algun demonio en este lugar");
        }
    }

    /**
     * Añade una partícula al tablero.
     * 
     * @param color Color de la partícula.
     * @param isRed Indica si la partícula es roja.
     * @param x Posición x de la partícula.
     * @param y Posición y de la partícula.
     * @param vx Velocidad en x de la partícula.
     * @param vy Velocidad en y de la partícula.
     */
    public void addParticle(String color, boolean isRed, int x, int y, int vx, int vy) {
        boolean alreadyExist = false;
        for (Particle p : particles) {
                if(p.getColor().equals(color)){
                    alreadyExist = true;
                }
            }
        if (!alreadyExist){
            Particle newParticle = new Particle( color,  isRed, (x*factor)+(w*factor), y*factor,  vx,vy);
            if(isVisible){
                newParticle.makeVisible();
            }
            this.particles.add(newParticle);
        }else{
            errorSignal("Particula con ese identificador ya existe");
        }
    }

    /**
     * Elimina partículas del tablero por su identificador.
     * 
     * @param color Color de las partículas a eliminar.
     */
    public void delParticle(String color) {
        Particle particle = null;
        for (Particle p : particles) {
            if (p.getColor().equals(color)) {
                p.makeInvisible();
                particle  = p;
            }
        }
        if(particle != null){
            this.particles.remove(particle);
        }else{
            errorSignal("Particula no se encuentra");
        }
    }

    /**
     * Inicia la simulación del juego.
     * 
     * @param ticks Número de pasos de la simulación.
     */
    public void start(int ticks) {
        if(!isGoal()){
            for (int i=0; i<ticks; i++){
                for (Particle p : this.particles){
                    p.move(w,h,demons,holes,this);
                }
                particles.removeIf(p -> p.getInHole()==true);
                if(isGoal()){
                    errorSignal("JUEGO TERMINADO");
                    break;
                }
            }
        }else{
            errorSignal("Particulas ya estan bien posicionadas");
        }
    }

    /**
     * Verifica si el juego ha terminado.
     */
    public void finish() {
        System.exit(0);
    }

    /**
     * Verifica si todas las partículas están en su posición correcta.
     * 
     * @return true si todas las partículas están en su posición correcta, false en caso contrario.
     */
    public boolean isGoal() {
        for (Particle p : particles) {
            if (!p.rigthPosition(w)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Obtiene los datos de las partículas.
     * 
     * @return Matriz con los datos de las partículas.
     */
    public int[][] particles() {
        if(particles.size() != 0){
            int[][] dataParticles = new int[this.particles.size()][4];
            for (int i = 0; i < this.particles.size(); i++) {
                int[] data = particles.get(i).getParticleData();
                dataParticles[i] = data;
            }
            return dataParticles;
        }else{
            errorSignal("No hay Particulas");
            return null;
        }
    }

    /**
     * Obtiene las posiciones de los demonios.
     * 
     * @return Arreglo con las posiciones de los demonios.
     */
    public int[] demons() {
        if(demons.size() != 0){
            int[] dataDemons = new int[this.demons.size()];
            for (int i = 0; i < this.demons.size(); i++) {
                dataDemons[i] = this.demons.get(i).getDistance();
            }
            Arrays.sort(dataDemons);
            return dataDemons;
        }else{
            errorSignal("No hay Demonios");
            return null;
        }
    }

    /**
     * Añade un agujero al tablero.
     * 
     * @param x Posición x del agujero.
     * @param y Posición y del agujero.
     * @param particles Número de partículas que puede contener el agujero.
     */
    public void addHole(int x, int y, int particles) {
        if(particles > 0){
            Hole hole = new Hole((x*factor),y*factor,particles);
            if(isVisible){
                hole.makeVisible();
            }
            this.holes.add(hole);
        }else{
            errorSignal("Capacidad 0 o negativa");
        }
    }

    /**
     * Obtiene los datos de los agujeros.
     * 
     * @return Matriz con los datos de los agujeros.
     */
    public int[][] holes() {
        if(holes.size() != 0){
            int[][] dataHoles = new int[holes.size()][3];
            for (int i = 0; i < holes.size(); i++) {
                int[] data = holes.get(i).dataHole();
                dataHoles[i] = data;
            }
            return dataHoles;
        }else{
            errorSignal("No hay Huecos");
            return null;
        }
    }

    /**
     * Muestra un mensaje de error.
     * 
     * @param error Mensaje de error.
     */
    private void errorSignal(String error) {
        if (isVisible) {
            JOptionPane.showMessageDialog(null, error);
        }
    }
}