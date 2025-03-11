public class Main {
    public static void main(String[] args) {
        int[][] particlesData = {
            {-50,100, 17,26},
            {50,100,-5,7},
            {70, 118, 31, 23},
            {-140, 15, -8, 4},
            {76, 140, 10, 12},
            {-76, 148, 15, 10}
            /**{-100,100,10,0},{100, 157,10,0}
            {-11,96,10,3},{134,79,1,1},{-34,79,6,19}**/
        };

        MaxwellContainer container = new MaxwellContainer(200, 200, 100, 3, 3, particlesData);
        container.addHole(135, 80, 1);
        container.addHole(357, 135, 2);
        container.addDeamon(157);
        container.addDeamon(37);
        container.start(1000);
    }
    
}