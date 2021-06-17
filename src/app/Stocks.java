package app;

import utils.ChronoComparator;
import utils.Portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stocks extends Portfolio {

    private static String name;
    private static double startDeposit;
    private static int month;
    private static double startValue;
    private static double startQuantity;
    private static double profit;
    private static double profitPercent;

    private static double actSum = 0;
    private static double actValue = 0;
    private static List<double[]> history;
    private static double actQuantity;

    public Stocks(double deposit, int months) {
        name = "AKCIE";
        startDeposit = deposit;
        month = months;
        this.startValue = generateStartValue();
        startQuantity = generateStartQuantity();
        actQuantity = startQuantity;

        history = loadHistory();
        profit = generateProfit();
        profitPercent = generateProfitPercent();
    }

    public Stocks(double actValue, double actQuantity, double actSum, double startValue, double startQuantity, double startDeposit, int month, double profit, double profitPercent){
        name = "AKCIE";
        this.actValue = actValue;
        this.actQuantity = actQuantity;
        this.actSum = actSum;
        this.startValue = startValue;
        this.startQuantity = startQuantity;
        this.startDeposit = startDeposit;
        this.profit = profit;
        this.profitPercent = profitPercent;
    }

    public double getActValue () {return actValue;}
    public double getActQuantity () {return actQuantity;}
    public double getActSum () {return actSum;}
    public double getStartValue () {return startValue;}
    public double getStartQuantity () {return startQuantity;}
    public double getStartDeposit () {return startDeposit;}
    public double getProfit () {return profit;}
    public double getProfitPercent () {return profitPercent;}

    @Override
    public void sell(double withdraw){
        actSum -= withdraw;
        actQuantity -= withdraw/actValue;
    }

    @Override
    public String timeLineChrono() {
        Collections.sort(history, new ChronoComparator());
        StringBuilder sb = new StringBuilder();
        for (double[] item:history
        ) {
            sb.append(String.format("%5f %5f %15f", item[0], item[5], item[2]));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    @Override
    public String timeLineProfit() {
        Collections.sort(history, new utils.ProfitComparator());
        StringBuilder sb = new StringBuilder();
        for (double[] item:history
        ) {
            sb.append(String.format("%5f %5f %15f", item[0], item[5], item[2]));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<double[]> getHistory(){
        return history;
    }

    @Override
    protected double getRandomPercent() {
        double randomPercent = ((Math.random() * (0.2 - (-0.1))) + (-0.1)) + 1;
        return randomPercent;
    }

    @Override
    protected double generateStartValue() {
        double startValue = ((Math.random() * 5 - (-0.5))) + (-0.5);
        return startValue;
    }

    @Override
    protected double generateStartQuantity() {
        double startQuantity = generateStartValue()/ generateStartValue();
        return startQuantity;
    }

    @Override
    protected double generateActQuantity() {
        double actQuantity = generateStartQuantity();
        return actQuantity;
    }


    @Override
    protected double generateProfit() {
        double profit = getActSum() - startDeposit;
        return profit;
    }

    @Override
    protected double generateProfitPercent() {
        double profitPercent = ((getActSum() * 100) / startDeposit);
        return profitPercent;
    }

    private List<double[]> loadHistory(){
        List history = new ArrayList<double[]>();
        for (int i = 0; i < month; i++) {
            actValue = actValue * getRandomPercent();
            actSum = actValue * generateActQuantity();
            double[] arr = new double[6];
            arr[0] = month;
            arr[1] = actValue;
            arr[2] = actSum;
            arr[3] = generateActQuantity();
            arr[4] = generateProfit();
            arr[5] = generateProfitPercent();
            history.add(arr);
        }
        return history;
    }


}
