public class MainTest{
    public static void main(String[] args) {
        // Ejemplo 1
        int factor=10;
        int w = 7;
        int h = 4;
        int d = 1;
        int r = 1;
        int b = 1;
        int[][] particles1 = {
            {2, 1, 4, 1},
            {-3, 1, 2, 0}
        };
        System.out.println(MaxwellContest.solve(w, h, d, r, b, particles1)); // Salida: 24.000000
        
        w = 4;
        h = 4;
        d = 1;
        r = 2;
        b = 2;
        int[][] particles2 = {
            {3, 1, 2, 2},  
            {-2, 3, -2, -1},
            {3, 2, 1, -2},
            {-2, 2, 2, 2}
        };
        System.out.println(MaxwellContest.solve(w, h, d, r, b, particles2)); // Salida: impossible
    }

    
}