package utils;

import java.util.Comparator;

public class ChronoComparator implements Comparator<double[]> {

    @Override
    public int compare(double[] o1, double[] o2) {
        return (int) ((int) o1[0]-o2[0]);
    }
}
