package app;

import utils.ChronoComparator;
import utils.Portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stocks extends Portfolio {

    private String name = "AKCIE";
    private double startDeposit;
    private int month;
    private double startValue;
    private double startQuantity;
    private double timelineProfit;
    private double timelineProfitPercent;
    private double profit;
    private double profitPercent;

    private double actSum;
    private double prevSum = 0;
    private double actValue;
    private double prevQuantity = 0;
    private List<double[]> infoTable;
    private double actQuantity;

    public Stocks(double deposit, int months) {
        startDeposit = deposit;
        actSum = deposit;
        month = months;
        startValue = generateStartValue();
        startQuantity = generateStartQuantity();
        actQuantity = startQuantity;
        actValue = startValue;

        infoTable = loadInfoTable();
        timelineProfitPercent = generateTimelineProfitPercent();
    }

    public Stocks(double actValue, double actQuantity, double actSum, double startValue, double startQuantity, double startDeposit, int month, double profit, double profitPercent){
        this.actValue = actValue;
        this.actQuantity = actQuantity;
        this.actSum = actSum;
        this.startValue = startValue;
        this.startQuantity = startQuantity;
        this.startDeposit = startDeposit;
        this.month = month;
        this.timelineProfit = profit;
        this.timelineProfitPercent = profitPercent;
    }

    public double getActValue () {return actValue;}
    public double getActQuantity () {return actQuantity;}
    public double getActSum () {return actSum;}
    public double getStartValue () {return startValue;}
    public double getStartQuantity () {return startQuantity;}
    public double getStartDeposit () {return startDeposit;}
    public double getTimelineProfit() {return timelineProfit;}
    public double getTimelineProfitPercent() {return timelineProfitPercent;}
    public double getProfit() {return profit;}
    public double getProfitPercent() {return profitPercent;}

    @Override
    public void sell(double withdraw){
        actSum -= withdraw;
        actQuantity -= withdraw/actValue;
    }

    @Override
    public String timeLineChrono() {
        Collections.sort(infoTable, new ChronoComparator());
        StringBuilder sb = new StringBuilder();
        for (double[] item: infoTable
        ) {
            sb.append(String.format("%5f %5f %15f", item[0], item[5], item[2]));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    @Override
    public String timeLineProfit() {
        Collections.sort(infoTable, new utils.ProfitComparator());
        StringBuilder sb = new StringBuilder();
        for (double[] item: infoTable
        ) {
            sb.append(String.format("%5f %5f %15f", item[0], item[5], item[2]));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    @Override
    public String getReport() {
        String report = null;
        for (double[] arr : infoTable
             ) {
            report = String.format("%s %f %f %f %f %f %f", getName(),arr[0], arr[1], arr[2], arr[3], arr[6], arr[7]);
        }
        return report;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<double[]> getInfoTable(){
        return infoTable;
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
        double startQuantity = getStartDeposit()/ getStartValue();
        return startQuantity;
    }

    @Override
    protected double generateActQuantity() {
        double actQuantity = generateStartQuantity();
        return actQuantity;
    }

    @Override
    protected double generateTimelineProfitPercent() {
        this.timelineProfitPercent = ((actSum*100)/prevSum)-100;
        return timelineProfitPercent;
    }

    protected double generateProfitPercent() {
        this.profitPercent = ((actSum*100)/startDeposit)-100;
        return profitPercent;
    }

    protected List<double[]> loadInfoTable(){
        List infoTable = new ArrayList<double[]>();
        for (int i = 0; i < month; i++) {
            prevSum = actSum;
            actValue = actValue * getRandomPercent();
            actSum = actValue * generateActQuantity();
            timelineProfit = actSum - prevSum;
            profit = actSum - startDeposit;
            double[] arr = new double[8];
            arr[0] = month;
            arr[1] = actValue;
            arr[2] = actSum;
            arr[3] = generateActQuantity();
            arr[4] = timelineProfit;
            arr[5] = generateTimelineProfitPercent();
            arr[6] = profit;
            arr[7] = generateProfitPercent();
            infoTable.add(arr);
        }
        return infoTable;
    }

    @Override
    protected List<double[]> loadInfoTableSold() {
        List infoTableSold = new ArrayList<double[]>();
        prevSum = actSum;
        prevQuantity = actQuantity;
        actQuantity = actSum/actValue;
        double[] arr = new double[4];
        arr[0] = prevSum;
        arr[1] = actSum;
        arr[2] = prevQuantity;
        arr[3] = actQuantity;
        infoTableSold.add(arr);
        return infoTableSold;
    }
}
