public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            {50, 50, 5, 5},
            {360, 20, 5, 5},
            {60, 140, 5, 5},
            {270, 118, 5, 5},
            {60, 15, 5, 5},
            {176, 140, 5, 5},
            {270, 78, 5, 5}};

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 3, 4, particlesData);
        container.start(150);
        //container.particles();
    }
    
}