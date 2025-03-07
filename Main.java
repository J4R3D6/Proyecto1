public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            {2, 2, 19, 30},
            {360, 20, 5, 5},
            {60, 140,-400, 0},
            {270, 118, 55, -35},
            /**{60, 15, 15, 5},
            {176, 140, 25, 5},
            {270, 78, 55, 5}**/};

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 0, 4, particlesData);
        container.start(250);
        //container.particles();
    }
    
}