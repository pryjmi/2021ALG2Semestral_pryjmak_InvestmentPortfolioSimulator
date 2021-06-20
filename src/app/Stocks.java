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
    private boolean loaded;

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
        loaded = false;
    }

    public Stocks(double actValue, double actQuantity, double actSum, double startValue, double startQuantity, double startDeposit, int month, double profit, double profitPercent){
        this.actValue = actValue;
        this.actQuantity = actQuantity;
        this.actSum = actSum;
        this.startValue = startValue;
        this.startQuantity = startQuantity;
        this.startDeposit = startDeposit;
        this.month = month;
        this.profit = profit;
        this.profitPercent = profitPercent;
        infoTable = loadInfoTableFile();
        loaded = true;
    }

    public double getActValue () {return actValue;}
    public double getActQuantity () {return actQuantity;}
    public double getActSum () {return actSum;}
    public double getStartValue () {return startValue;}
    public double getStartQuantity () {return startQuantity;}
    public double getStartDeposit () {return startDeposit;}
    public double getTimelineProfit() {return timelineProfit;}
    public double getTimelineProfitPercent() {return timelineProfitPercent;}

    @Override
    public String timeLineChrono() {
        if (!loaded) {
            Collections.sort(infoTable, new utils.ChronoComparator());
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%20s %20s %20s %n", "Měsíc","Profit (%)","Hodnota investice"));
            for (double[] item : infoTable
            ) {
                sb.append(String.format("%20.0f. %20f %20f", item[8], item[5], item[2]));
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        } else return "U načteného portfolia není známa historie.";
    }

    @Override
    public String timeLineProfit() {
        if (!loaded) {
            Collections.sort(infoTable, new utils.ProfitComparator());
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%20s %20s %20s %n", "Měsíc","Profit (%)","Hodnota investice"));
            for (double[] item : infoTable
            ) {
                sb.append(String.format("%20.0f. %20f %20f", item[8], item[5], item[2]));
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        } else return "U načteného portfolia není známa historie.";
    }

    @Override
    public String getReport() {
        String report = null;
        for (double[] arr : infoTable
             ) {
            report = String.format("%s %.0f %f %f %f %f %f", getName(), arr[0], arr[1], arr[2], arr[3], arr[6], arr[7]);
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
            double[] arr = new double[9];
            arr[0] = month;
            arr[1] = actValue;
            arr[2] = actSum;
            arr[3] = generateActQuantity();
            arr[4] = timelineProfit;
            arr[5] = generateTimelineProfitPercent();
            arr[6] = profit;
            arr[7] = generateProfitPercent();
            arr[8] = i+1;
            infoTable.add(arr);
        }
        return infoTable;
    }

    @Override
    protected List<double[]> loadInfoTableFile() {
        List infoTable = new ArrayList<double[]>();
        double[] arr = new double[8];
        arr[0] = month;
        arr[1] = actValue;
        arr[2] = actSum;
        arr[3] = actQuantity;
        arr[4] = timelineProfit;
        arr[5] = timelineProfitPercent;
        arr[6] = profit;
        arr[7] = profitPercent;
        infoTable.add(arr);
        return infoTable;
    }
}
