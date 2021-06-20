package utils;

import java.util.Comparator;

public class ChronoComparator implements Comparator<double[]> {

    @Override
    public int compare(double[] o1, double[] o2) {
        return (int) ((int) o1[8]-o2[8]);
    }
}
