public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            {150,100, 17,26},
            {250,100,-5,7}/**,
            {270, 118, 31, 23},
            {60, 15, -8, 4},
            {276, 140, 10, 10},
            {276, 140, 45, 37}**/
        };

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 1, 1, particlesData);
        container.start(500);
        //container.particles();
    }
    
}