package utils;

import java.util.List;

public abstract class Portfolio {

    public abstract String getName();

    public abstract List<double[]> getHistory();

    protected abstract double getRandomPercent();

    protected abstract double generateStartValue();

    protected abstract double generateStartQuantity();

    protected abstract double generateActQuantity();

    protected abstract double generateProfit();

    protected abstract double generateProfitPercent();

    public abstract double getActValue ();
    public abstract double getActQuantity ();
    public abstract double getActSum ();
    public abstract double getStartValue ();
    public abstract double getStartQuantity ();
    public abstract double getStartDeposit ();
    public abstract double getProfit ();
    public abstract double getProfitPercent ();
    public abstract int getMonth ();
    public abstract void sell (double withdraw);
    public abstract String timeLineChrono ();
    public abstract String timeLineProfit ();

}
