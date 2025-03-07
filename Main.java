public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            {150,100, 5,0},
            {250,100,-5,0}/**,
            {270, 118, 3, -17},
            {60, 15, -8, 4},
            {276, 140, 25, 30},
            {130,95, 29,0}**/
        };

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 1, 1, particlesData);
        container.start(100);
        //container.particles();
    }
    
}