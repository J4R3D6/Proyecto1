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
    private TreeMap<Integer, Deamon> demons = new TreeMap<>(); 
    private ArrayList<Hole> holes = new ArrayList<>(); 
    private ArrayList<Particle> particles = new ArrayList<>(); 
    private ArrayList<Rectangle> tablero = new ArrayList<>(); 
    private int Height; 
    private int Width; 
    private int blueParticles; 
    private int redParticles; 
    private boolean isVisible = true; 
    private boolean gameOver = false; 
    private boolean isOk = true; 

    /**
     * Constructor privado de MaxwellContainer.
     * Inicializa el contenedor con las dimensiones dadas.
     * 
     * @param h Altura del tablero.
     * @param w Ancho del tablero.
     */
    private MaxwellContainer(int h, int w) {
        if ((2 <= h && h <= 200) && (2 <= w && w <= 200)) {
            this.isOk = true;
            this.Height = h;
            this.Width = w * 2;
            CrearCanvas(w * 2, h);
            CrearTablero(w * 2, h); 
        } else if (this.isOk) {
            this.isOk = false;
            errorSignal("Medidas para el tablero invalidas");
            ok();
        }
    }

    /**
     * Constructor público de MaxwellContainer.
     * Inicializa el contenedor con partículas, demonios y agujeros.
     * 
     * @param h Altura del tablero.
     * @param w Ancho del tablero.
     * @param d Posición del demonio.
     * @param b Número de partículas azules.
     * @param r Número de partículas rojas.
     * @param particlesData Matriz con los datos de las partículas (posición y velocidad).
     */
    public MaxwellContainer(int h, int w, int d, int b, int r, int[][] particlesData) {
        this(h, w);
        if (isOk) {
            this.blueParticles = b;
            this.redParticles = r;
            addParticlesForMatriz(particlesData); 
            mandarMedidas();
            addDeamon(d); 
            this.makeVisible(); 
        }
    }

    /**
     * Verifica si el estado del contenedor es válido.
     * 
     * @return true si el estado es válido, false en caso contrario.
     */
    public boolean ok() {
        return isOk;
    }

    /**
     * Envía las medidas del tablero a las partículas.
     */
    private void mandarMedidas() {
        if (this.particles.size() != 0) {
            this.particles.get(0).medidas(this.Width, this.Height);
        }
    }

    /**
     * Crea el lienzo del tablero.
     * 
     * @param width Ancho del lienzo.
     * @param height Altura del lienzo.
     */
    private void CrearCanvas(int width, int height) {
        canvas = new Canvas(width, height);
        canvas.getCanvas(width, height);
    }

    /**
     * Crea el tablero con rectángulos.
     * 
     * @param w Ancho del tablero.
     * @param h Altura del tablero.
     */
    private void CrearTablero(int w, int h) {
        Rectangle recta1 = new Rectangle(0, 0, h, w, "orange"); 
        Rectangle recta2 = new Rectangle(5, 5, (h - 10), (w - 10), "white");
        Rectangle recta3 = new Rectangle(((w / 2) - (2)), 0, h, (4), "gray");
        tablero.add(recta1);
        tablero.add(recta2);
        tablero.add(recta3);
    }

    /**
     * Hace visible el tablero y todos sus elementos.
     */
    public void makeVisible() {
        for (Rectangle o : this.tablero) {
            o.makeVisible();
        }
        if (this.demons.size() != 0) {
            for (Deamon d : this.demons.values()) {
                d.makeVisible();
            }
        }
        for (Particle o : this.particles) {
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
        for (Rectangle o : this.tablero) {
            o.makeInvisible();
        }
        if (this.demons.size() != 0) {
            for (Deamon d : this.demons.values()) {
                d.makeInvisible();
            }
        }
        for (Particle o : this.particles) {
            o.makeInvisible();
        }
        for (Hole h : this.holes) {
            h.makeInvisible();
        }
        this.isVisible = false;
    }

    /**
     * Busca un demonio en la posición dada.
     * 
     * @param d Posición del demonio.
     * @return true si el demonio existe, false en caso contrario.
     */
    private boolean searchDeamon(int d) {
        return this.demons.containsKey(d);
    }

    /**
     * Verifica si hay un demonio cerca de la posición dada.
     * 
     * @param d Posición del demonio.
     * @return true si hay un demonio cerca, false en caso contrario.
     */
    private boolean crashDeamon(int d) {
        for (int i = (d - 10); i <= (d + 10); i++) {
            if (searchDeamon(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Añade un demonio al tablero.
     * 
     * @param d Posición del demonio.
     */
    public void addDeamon(int d) {
        if (5 <= d && d <= (this.Height - 5) && !searchDeamon(d) && this.isOk) {
            if (!crashDeamon(d)) {
                this.isOk = true;
                Deamon deamon = new Deamon(((this.Width / 2) - (10)), (d - (10)), 20);
                deamon.makeVisible();
                demons.put(d, deamon);
                if (this.particles.size() != 0) {
                    this.particles.get(0).addDemon((this.Width / 2), d);
                }
            } else {
                errorSignal("Existe un Demonio muy cerca");
                ok();
            }
        } else if (this.isOk) {
            errorSignal("Demonio fuera del rango");
            ok();
        }
    }

    /**
     * Elimina un demonio del tablero.
     * 
     * @param d Posición del demonio.
     */
    public void delDeamon(int d) {
        if (searchDeamon(d) && this.isOk) {
            this.isOk = true;
            this.demons.get(d).erase();
            this.demons.remove(d);
            if (this.particles.size() != 0) {
                this.particles.get(0).delDemon((this.Width / 2), d);
            }
        } else if (this.isOk) {
            errorSignal("No existe ese demonio");
            ok();
        }
    }

    /**
     * Añade partículas al tablero desde una matriz de datos.
     * 
     * @param particlesData Matriz con los datos de las partículas.
     */
    public void addParticlesForMatriz(int[][] particlesData) {
        if (((blueParticles + redParticles) == particlesData.length) && this.isOk) {
            this.isOk = true;
            for (int i = 0; i < (blueParticles + redParticles); i++) {
                if (i < redParticles) {
                    addParticle("red", true, particlesData[i][0], particlesData[i][1], particlesData[i][2], particlesData[i][3]);
                } else {
                    addParticle("blue", false, particlesData[i][0], particlesData[i][1], particlesData[i][2], particlesData[i][3]);
                }
            }
        } else if (this.isOk) {
            this.isOk = false;
            errorSignal("La cantidad de particulas no coincide");
            ok();
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
        if ((0 < Math.abs(x) && Math.abs(x) < (this.Width / 2) && 0 < y && y < (this.Height) && Math.abs(vx) < (this.Width / 2) && Math.abs(vy) < (this.Height) && vx != 0) && this.isOk) {
            this.isOk = true;
            Particle partucila = new Particle(color, isRed, x + (this.Width / 2), y, vx, vy);
            this.particles.add(partucila);
        } else if (this.isOk) {
            this.isOk = false;
            errorSignal("Algun dato de la particula es invalido");
            ok();
        }
    }

    /**
     * Elimina partículas del tablero por color.
     * 
     * @param color Color de las partículas a eliminar.
     */
    public void delParticle(String color) {
        if ((color.equals("red") || color.equals("blue")) && this.isOk) {
            this.isOk = true;
            for (Particle p : this.particles) {
                p.erase();
            }
            this.particles.removeIf(p -> p.getColor().equals(color));
            for (Particle p : this.particles) {
                p.draw();
            }
        } else if (this.isOk) {
            errorSignal("Color no es identificado");
            ok();
        }
    }

    /**
     * Inicia la simulación del juego.
     * 
     * @param ticks Número de pasos de la simulación.
     */
    public void start(int ticks) {
        if (((ticks > 0) && this.isOk)) {
            this.isOk = true;
            for (int i = 0; i < ticks; i++) {
                for (Particle p : this.particles) {
                    p.move(1);
                }
                finish();
                if (gameOver) {
                    break;
                }
            }
        } else if (this.isOk) {
            errorSignal("Los tick deben ser mayores a 0");
            ok();
        }
    }

    /**
     * Verifica si el juego ha terminado.
     */
    public void finish() {
        if (isGoal()) {
            gameOver = true;
            JOptionPane.showMessageDialog(null, "Juego terminado");
        }
    }

    /**
     * Verifica si todas las partículas están en su posición correcta.
     * 
     * @return true si todas las partículas están en su posición correcta, false en caso contrario.
     */
    public boolean isGoal() {
        for (Particle p : particles) {
            if (!p.posicionCorrecta()) {
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
        int[][] dataParticles = new int[this.particles.size()][4];
        for (int i = 0; i < this.particles.size(); i++) {
            int[] data = particles.get(i).getParticleData();
            dataParticles[i][0] = data[0];
            dataParticles[i][1] = data[1];
            dataParticles[i][2] = data[2];
            dataParticles[i][3] = data[3];
        }
        ordenarMatriz(dataParticles);
        return dataParticles;
    }

    /**
     * Obtiene las posiciones de los demonios.
     * 
     * @return Arreglo con las posiciones de los demonios.
     */
    public int[] demons() {
        int[] dataDemons = demons.keySet().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(dataDemons);
        return dataDemons;
    }

    /**
     * Añade un agujero al tablero.
     * 
     * @param x Posición x del agujero.
     * @param y Posición y del agujero.
     * @param particles Número de partículas que puede contener el agujero.
     */
    public void addHole(int x, int y, int particles) {
        if ((5 <= Math.abs(x) && Math.abs(x) <= this.Width - 5 && 5 <= y && y <= this.Height - 5) && this.isOk) {
            if (particles > 0) {
                Hole a = new Hole(x + (this.Width / 2), y, particles);
                this.holes.add(a);
                if (this.particles.size() != 0) {
                    this.particles.get(0).addhole(a);
                }
                isOk = true;
                ok();
            } else {
                isOk = false;
                ok();
            }
        } else if (this.isOk) {
            errorSignal("Coordenadas fuera de rango");
            ok();
        }
    }

    /**
     * Obtiene los datos de los agujeros.
     * 
     * @return Matriz con los datos de los agujeros.
     */
    public int[][] holes() {
        int[][] dataHoles = new int[holes.size()][3];
        for (int i = 0; i < holes.size(); i++) {
            int[] data = holes.get(i).dataHole();
            dataHoles[i][0] = data[0];
            dataHoles[i][1] = data[1];
            dataHoles[i][2] = data[2];
        }
        ordenarMatriz(dataHoles);
        return dataHoles;
    }

    /**
     * Muestra un mensaje de error.
     * 
     * @param error Mensaje de error.
     */
    private void errorSignal(String error) {
        if (!isOk && isVisible) {
            JOptionPane.showMessageDialog(null, error);
        }
    }

    /**
     * Ordena una matriz de enteros.
     * 
     * @param matriz Matriz a ordenar.
     */
    public static void ordenarMatriz(int[][] matriz) {
        Arrays.sort(matriz, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                for (int i = 0; i < Math.min(a.length, b.length); i++) {
                    if (a[i] != b[i]) {
                        return Integer.compare(a[i], b[i]);
                    }
                }
                return Integer.compare(a.length, b.length);
            }
        });
    }
}