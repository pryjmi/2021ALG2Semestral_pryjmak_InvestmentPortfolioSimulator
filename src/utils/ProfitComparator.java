package utils;

import java.util.Comparator;

public class ProfitComparator implements Comparator<double[]> {


    @Override
    public int compare(double[] o1, double[] o2) {
        return (int) ((int) o1[4]-o2[4]);
    }
}
