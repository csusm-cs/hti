import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        CSVDataParser parser = new CSVDataParser();
        // NuclearData nucData = parser.readFile("data/ntr1.csv");

	    // write your code here
        double [][] test_data = {{14, 15, 14}, {7,4, 7}, {8, 2, 10}, {15, 9, 10}, {0, 2, 0}};
        Double [][] Double_data = {{14.0, 15.0, 14.0}, {7.0,4.0, 7.0}, {8.0, 2.0, 10.0},
                {15.0, 9.0, 10.0}, {0.0, 2., 0.}};

        printMatrix(Double_data);
        MedianPolish med = new MedianPolish(Double_data);

        printMatrix(med.getMatrix());
        System.out.println("Row effects " + Arrays.toString(med.getRowEffects()));
        System.out.println("Col effects " + Arrays.toString(med.getColEffects()));
        System.out.println("Overall " + Double.toString(med.getOverall()));
    }

    public static void printMatrix(Double[][] matrix) {
        for (Double[] row : matrix) {
            for (Double cell : row) {
                System.out.print(String.format("%" + 5 + ".1f", cell));
            }
            System.out.println();
        }
        System.out.println();
    }
}
