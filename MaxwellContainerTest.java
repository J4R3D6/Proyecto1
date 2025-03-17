import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaxwellContainerTest {

    private MaxwellContainer container;

    @Before
    public void setUp() {
        // Inicializa un contenedor con dimensiones 100x100, 1 demonio, 2 partículas rojas y 2 azules
        int[][] particlesData = {
            {10, 10, 1, 1}, // Partícula roja
            {20, 20, -1, -1}, // Partícula roja
            {30, 30, 1, -1}, // Partícula azul
            {40, 40, -1, 1}  // Partícula azul
        };
        container = new MaxwellContainer(100, 100, 50, 2, 2, particlesData);
    }

    @Test
    public void testConstructor() {
        assertNotNull(container);
        assertTrue(container.ok());
    }

    @Test
    public void testNotAddDeamon() {
        container.addDeamon(50);
        int[] demons = container.demons();
        assertEquals(1, demons.length); // Ya había uno en el setup
        assertEquals(50, demons[0]);
    }
    @Test
    public void testAddDeamon() {
        container.addDeamon(40);
        assertTrue(container.ok());
        int[] demons = container.demons();
        assertEquals(2, demons.length); // Ya había uno en el setup
        assertEquals(40, demons[0]);
        assertEquals(50, demons[1]);
    }

    @Test
    public void testDelDeamon() {
        container.delDeamon(50);
        assertTrue(container.ok());
        int[] demons = container.demons();
        assertEquals(null, demons);
    }
    
    @Test
    public void testNotDelDeamon() {
        container.delDeamon(60);
        assertTrue(container.ok());
        int[] demons = container.demons();
        assertEquals(1, demons.length);
        assertEquals(50, demons[0]);
    }

    @Test
    public void testAddParticle() {
        container.addParticle("red", true, 15, 15, 1, 1);
        assertTrue(container.ok());
        int[][] particles = container.particles();
        assertEquals(5, particles.length);
    }
    
    @Test
    public void testNotAddParticle() {
        container.addParticle("1", true, 15, 15, 1, 1);
        assertTrue(container.ok());
        int[][] particles = container.particles();
        assertEquals(4, particles.length);
    }

    @Test
    public void testDelParticle() {
        container.delParticle("0");
        assertTrue(container.ok());
        int[][] particles = container.particles();
        assertEquals(3, particles.length);
    }
    
    @Test
    public void testNotDelParticle() {
        container.delParticle("-1");
        assertTrue(container.ok());
        int[][] particles = container.particles();
        assertEquals(4, particles.length);
    }

    @Test
    public void testAddHole() {
        container.addHole(10, 10, 5);
        assertTrue(container.ok());
        int[][] holes = container.holes();
        assertEquals(1, holes.length);
        assertEquals(10, holes[0][0]);
        assertEquals(10, holes[0][1]);
        assertEquals(5, holes[0][2]);
    }
    
    @Test
    public void testNotAddHole() {
        container.addHole(10, 10, -5);
        assertTrue(container.ok());
        int[][] holes = container.holes();
        assertEquals(null, holes);
    }

    @Test
    public void testStart() {
        container.start(10);
        assertTrue(container.ok());
        // Verifica que las partículas se hayan movido
        int[][] particles = container.particles();
        for (int[] particle : particles) {
            assertNotEquals(10, particle[0]); // Las partículas deberían haberse movido de su posición inicial
        }
    }
    
    @Test
    public void testNotStart() {
        container.start(-10);
        assertTrue(container.ok());
        // Verifica que las partículas se hayan movido
        int[][] particles = container.particles();
        for (int i=0 , p=10 ; i < particles.length ; i++, p+=10) {
            assertNotEquals(p, particles[i][0]); // Las partículas deberían haberse movido de su posición inicial
        }
    }

    @Test
    public void testIsGoal() {
        // Asegúrate de que las partículas estén en la posición correcta para que el juego termine
        int[][] particlesData = {
            {-10, 10, 0, 0}, // Partícula roja
            {-20, 20, 0, 0}, // Partícula roja
            {30, 30, 0, 0}, // Partícula azul
            {40, 40, 0, 0}  // Partícula azul
        };
        MaxwellContainer goalContainer = new MaxwellContainer(100, 100, 50, 2, 2, particlesData);
        assertTrue(goalContainer.isGoal());
    }

    @Test
    public void testNotIsGoal() {
        // Asegúrate de que las partículas estén en la posición correcta para que el juego termine
        int[][] particlesData = {
            {10, 10, 0, 0}, // Partícula roja
            {20, 20, 0, 0}, // Partícula roja
            {-30, 30, 0, 0}, // Partícula azul
            {-40, 40, 0, 0}  // Partícula azul
        };
        MaxwellContainer goalContainer = new MaxwellContainer(100, 100, 50, 2, 2, particlesData);
        assertFalse(goalContainer.isGoal());
    }
}