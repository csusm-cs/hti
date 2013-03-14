/**
 * Created with IntelliJ IDEA.
 * User: UARSC
 * Date: 3/6/13
 * Time: 10:11 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Arrays;

public class MedianPolish {
    public static int max_iterations = 10;
    public static Double threshold = 0.01;

    private Double[][] matrix;
    private Double[] row_effects, col_effects;
    private Double total_effect;

    ///
    public MedianPolish(Double[][] mtx) {
        this.matrix = deepClone(mtx);
        row_effects = new Double[this.matrix.length];
        for (int i = 0; i < row_effects.length; i++) {
            row_effects[i] = 0.0;
        }
        col_effects = new Double[this.matrix[0].length];
        for (int i = 0; i < col_effects.length; i++) {
            col_effects[i] = 0.0;
        }
        total_effect = 0.0;
        calcMedianPolish();
    }

    public Double [][] getMatrix() {
        return matrix;
    }

    public Double [] getRowEffects() {
        return row_effects;
    }

    public Double [] getColEffects() {
        return col_effects;
    }

    public Double getOverall() {
        return total_effect;
    }

    ///
    private void calcMedianPolish() {
        int it = 0;
        Double old_sum = 0.0;
        while (it < max_iterations) {
            it++;
            // row sweep
            Double [] row_delta = getRowMedians();
            for (int i = 0; i < row_effects.length; i++) {
                row_effects[i] += row_delta[i];
                for (int j = 0; j < col_effects.length; j++) {
                    matrix[i][j] -= row_delta[i];
                }
            }
            Double row_bias = median(row_delta);
            total_effect += row_bias;
            for (int i = 0; i < row_effects.length; i++) {
                row_effects[i] -= row_bias;
            }

            // column sweep
            Double [] col_delta = getColMedians();

            for (int j = 0; j < col_effects.length; j++) {
                col_effects[j] += col_delta[j];
                for (int i = 0; i < row_effects.length; i++) {
                    matrix[i][j] -= col_delta[j];
                }
            }
            Double col_bias = median(col_delta);
            total_effect += col_bias;
            for (int j = 0; j < col_effects.length; j++) {
                col_effects[j] -= col_bias;
            }

            // check for termination condition
            Double new_sum = 0.0;
            for (int i = 0; i < row_effects.length; i++) {
                for (int j = 0; j < col_effects.length; j++) {
                    new_sum += Math.abs(matrix[i][j]);
                }
            }
            if(new_sum == 0 || Math.abs(new_sum - old_sum) < threshold*new_sum) {
                System.out.println("Converged! Row bias " + Double.toString(row_bias) + ", Col bias " + Double.toString(col_bias));
                return;
            }
            old_sum = new_sum;
        }

    }

    ///
    private Double[][] deepClone(final Double [][] mtx) {
        Double [][] result = new Double[mtx.length][];
        for(int i = 0; i < mtx.length; i++) {
            result[i] = mtx[i].clone();
        }
        return result;
    }

    private Double[] getRowMedians() {
        Double [] rdelta = new Double[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            rdelta[i] = median(matrix[i]);
        }
        return rdelta;
    }

    private Double[] getColMedians() {
        Double [] cdelta = new Double[matrix[0].length];
        Double [] col = new Double[matrix.length];
        for(int i = 0; i < col_effects.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                col[j] = matrix[j][i];
            }
            cdelta[i] = median(col);
        }
        return cdelta;
    }

    // compute the median of an array
    private Double median(Double [] data) {
        Double [] tmpData = data.clone();
        Arrays.sort(tmpData);
        int size = tmpData.length;
        return size % 2 == 1 ? tmpData[size / 2] : (tmpData[size / 2] + tmpData[size / 2 - 1]) / 2;
    }

}
