public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            {50,50, 7,10},
            {70,70,-12,-5},
            {270, 118, 3, -17},
            {60, 15, -8, 4},
            {276, 140, 25, 30},
            {130,95, 13,0}
        };

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 3, 3, particlesData);
        container.start(1000);
        //container.particles();
    }
    
}