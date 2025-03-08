public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            {150,100, 5,0},
            {250,100,-5,0},
            {270, 118, 3, -17},
            {60, 15, -8, 4},
            {276, 140, 10, 10}
        };

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 2, 3, particlesData);
        container.start(500);
        //container.particles();
    }
    
}